

	function generateCategoryList(data) {
		
		var elm = document.getElementById('categoryList');
		
		data.forEach(function(item){			
			var category = generateCategory(item.id,item.categoryName);	
			
			elm.append(category);			
		});	
	}

	function generateCategory(id, name) {
		
		var cat = document.createElement('div');
		
		$(cat)
			.attr("category-id", id)
			.attr("class", "list-group-item categoryInactive listItemContainer")
			.attr("contenteditable", "false")
			.attr("category-name", name)
			.attr('type', 'category')
			.attr("parentID", "categoryList");

		
		setCategoryListener(cat);
		$('#categoryList').append(cat);	
		
		var txtEl = document.createElement("p");
			$(txtEl)
				.attr("contenteditable", "false")
				.attr("class", "unselectable ")
				.html(name);
			cat.append(txtEl);
		return cat;
	}

	function addIssuesToCategory(data) {
												
		data.forEach(function(item){
			generateIssue(item.id, item.issueName, item.issueDescription);															
		});										
	}
	
	function generateIssue(id, name, desc) {
		
		var issue = document.createElement('div');
				
		$(issue)
			.attr("issue-id", id)
			.attr("class", "list-group-item issueInactive listItemContainer")
			.attr("issue-name", name)
			.attr("issue-description", desc)
			.attr('type', 'issue')
			.attr("parentID", "issueList");
		setIssueListener(issue);
				
			var nameEl = document.createElement("p");
			$(nameEl)
				.attr("contenteditable", "false")
				.attr("class", "unselectable ")
				.html(name);
			issue.append(nameEl);
			
			var descEl = document.createElement("p");
			$(descEl)
				.attr("contenteditable", "false")
				.attr("class", "unselectable ")
				.html(desc);
			issue.append(descEl);
		
		$('#issueList').append(issue);
		return issue;
	}
	
	function addQuestionsToIssue(data) {
					
			$("#issueDescription").html(data.issueDescription);
			numberOfQuestions = data.length;
					
			var questionList = document.createElement('ul');
			questionList.setAttribute("class", "list-group");
			
			var iterator = 1;
			
			data.forEach(function(item) {
				var questionID = "question_" +iterator.toString(8);			
				var question = generateQuestion(questionID, item.questionText);				
				addOptionsToQuestion(question, questionID, "YES", "NO", "DON'T KNOW");	
				addWarningToQuestion(question, questionID);
				$("#questionList").append(question);
				iterator++;
			});
			
			
		}
		
	function generateQuestion(questionID, questionDescription) {
		
		var question = document.createElement('li');
		question.setAttribute("class", "list-group-item");
			
		var questionDescriptionElement = document.createElement("p");
		questionDescriptionElement.setAttribute("id", questionID);
		questionDescriptionElement.setAttribute("class", "noHover" +" unselectable");
		questionDescriptionElement.innerHTML = questionDescription;			
						
		question.appendChild(questionDescriptionElement);	
		return question;
	}
	
	function addOptionsToQuestion(question, questionID, ...restArgs) {
			
		var options = document.createElement("div");		
		var iterator = 1;
		
		restArgs.forEach(function(arg) {
			
			var option_ = document.createElement("div");
			option_.setAttribute("class", "form-check form-check-inline");	
			var optionID = questionID +"_option_" +iterator.toString(8);			
			var label = document.createElement('label');
			label.setAttribute("class", "form-check-label");
			label.setAttribute("for", optionID);
			label.innerHTML = arg;			
			
			var input = document.createElement('input');
			input.setAttribute("id", optionID);
			input.setAttribute("name", questionID);
			input.setAttribute("type", "radio");
			input.setAttribute("class", "form-check-input");
			input.setAttribute("data-choice", arg);
			
			option_.appendChild(input);
			option_.appendChild(label);
			
			iterator++;
			options.appendChild(option_);
		}); 	
		question.appendChild(options);
	}
	
	function addWarningToQuestion(question, questionID) {
			
			var warning = document.createElement("p");
			warning.setAttribute("id", questionID +"_warning");
			warning.setAttribute("class", "hide warning");
			warning.innerHTML = "Question not answered";
			question.appendChild(warning);
		}
	
	
	
	
	
	
	