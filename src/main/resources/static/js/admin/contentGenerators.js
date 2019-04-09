
//## GENERATE DYNAMIC ELEMENTS 

	//## Editables
		
	//	Types: category name, issue name, issue desciption, question description	
	function generateEditable(currentElement, txt, type) {
				
		var txtEl = document.createElement("p");
		$(txtEl)
			.attr("contenteditable", "false")
			.attr("class", "editable editableInactive unselectable " +type)
			.html(txt);
		setEditableListener(txtEl);
		currentElement.append(txtEl);
							
		return currentElement;
	}
	
	function generateButtons(el) {
		
		var btnContainer = document.createElement("div");
		$(btnContainer).attr("class", "btnContainer acceptCancelBtn-group hide noFade");	
		generateAcceptBtn(btnContainer);
		generateCancelBtn(btnContainer);
		el.append(btnContainer);
	}
		
	function generateAcceptBtn(currentElement) {
		
		var btn = document.createElement("button");
		$(btn)
			.attr("type", "button")
			.attr("class", "btn btn-success")
			.attr("onclick", "editableAcceptClicked(this)")
			.html("Accept");
		currentElement.appendChild(btn);
	}
	
	function generateCancelBtn(currentElement) {
		
		var btn = document.createElement("button");
		$(btn)
			.attr("type", "button")
			.attr("class", "btn btn-warning inline alignRight")
			.attr("onclick", "editableCancelClicked(this)")
			.html("Cancel");
		currentElement.appendChild(btn);
	}

	//## Category
						
	function generateCategoryList(data) {
												
		data.forEach(function(item){			
			generateEditableCategory(item.categoryName, item.id);								
		});				
	}
	
					
	function generateEditableCategory(categoryName, id) {
		
		var cat = document.createElement('div');
		
		$(cat)
			.attr("category-id", id)
			.attr("class", "list-group-item categoryInactive listItemContainer")
			.attr("category-name", categoryName)
			.attr('type', 'category')
			.attr("parentID", "categoryList");
		setCategoryListener(cat);
		cat = generateEditable(cat, categoryName, "category name");
		generateButtons(cat);
		$('#categoryList').append(cat);	
		return cat;
	}
		
		
	//## Issue
		
	function addIssuesToCategory(data) {
												
			data.forEach(function(item){
				generateEditableIssue(item.id, item.issueName, item.issueDescription);															
			});										
		}
		
	function generateEditableIssue(id, issueName, issueDescription) {
		
		var issue = document.createElement('div');
				
		$(issue)
			.attr("issue-id", id)
			.attr("class", "list-group-item issueInactive listItemContainer")
			.attr("issue-name", issueName)
			.attr("issue-description", issueDescription)
			.attr('type', 'issue')
			.attr("parentID", "issueList");
		setIssueListener(issue);
				
		issue = generateEditable(issue, issueName, "name");
		issue = generateEditable(issue, issueDescription, "description");
		generateButtons(issue);
						
		$('#issueList').append(issue);
		return issue;
	}
	
	//## Questions
	
	function addQuestionsToIssue(data) {
		
		data.forEach(function(item){
				generateEditableQuestion(item.id, item.questionText);															
			});				
	}
	
	function generateEditableQuestion(questionID, questionText) {
		
		var question = document.createElement('div');
		$(question)
			.attr("question-id", questionID)
			.attr("class", "list-group-item questionInactive listItemContainer")
			.attr('type', 'question')
			.attr('question-text', questionText)
			.attr("parentID", "questionList");
		setQuestionListener(question);
			
		generateEditable(question, questionText, "question text");
		generateButtons(question);
		$('#questionList').append(question);
		
		return question;
	}