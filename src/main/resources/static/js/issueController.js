
$(document).ready(function(){
	getAllCategories();
	
	//## SET VARS
	currentPage = 1;

}); 

	// ## VARS
	
	var previousPage;
	var currentPage;
	var currentIssue;
	var activeIssue;
	var currentQuestionsAndAnswers;
	var numberOfQuestions;


	// ## ONCLICK
	
	function categoryClicked(currentElement) {
		if(currentElement.classList.contains("categoryActive")){
			currentElement.classList.replace("categoryActive", "categoryInactive")
			removeIssuesFromCategory(currentElement.getAttribute("data-category"));			
		}
		else {
			setActiveCategoriesInactive();
			currentElement.classList.replace("categoryInactive", "categoryActive")
			addIssuesToCategory(currentElement);
		}	
		disableFwdBtn();
	}
	
	function issueClicked(currentElement) {
		
		if(currentElement.classList.contains("issueInactive")){
			setActiveIssuesInactive();
			currentElement.classList.replace("issueInactive", "issueActive")
			currentIssue = currentElement.getAttribute("data-issue");
			enableFwdBtn();
		}
		else{
			currentElement.classList.replace("issueActive", "issueInactive")
			disableFwdBtn();
			
		}		
	}
		
	function fwdBtnClicked() {
					
		changePage("fwd");		
	}	
	
	function bckBtnClicked() {
		
		changePage();									
	}
	
	
	// ## GENERAL
			
	function increaseCurrentPage() {
		currentPage++;
	}
	
	function decreaseCurrentPage() {
		currentPage--;
	}
	
	function setActiveIssue() {
		
		activeIssue = currentIssue;
	}
	
	function changePage(change) {
		
		console.log("Current page: " +currentPage);
		previousPage = currentPage;
		
		if(change == "fwd")
			increaseCurrentPage();	
		
		else
			decreaseCurrentPage();
						
		switch(currentPage){
			
			case 1:	hideQuestions();
					showCategories();
					disableBackBtn();
					break;
					
			case 2:	if(previousPage == 1) {						
						enableBckBtn();
						hideCategories();
						determineIssueQuestions();
					}
					
					if(previousPage == 3) {
						hideUserInfo();
						disableSubmitBtn();
						enableFwdBtn();
						
					}
					showQuestions();					
					break;
				
			case 3: if(checkQuestionsAnswered()){								
						showUserInfo();
						hideQuestions();
						disableFwdBtn();
						enableSubmitBtn();
						getAllQuestionsAndAnswers();
					}
					else{
						decreaseCurrentPage();
						previousPage = currentPage;
					}
					
					break;
		}
		setCurrentDescription();
	}
	
	function determineIssueQuestions() {
		
		if(activeIssue != currentIssue) {
			setActiveIssue();
			removeQuestionsFromIssue();
			getQuestionsFromIssue();
		}	
	}
	
	
		function setActiveCategoriesInactive() {
		
		var categories = document.getElementsByClassName("categoryActive");		
		for (i = 0; i < categories.length; i++) {
			removeIssuesFromCategory(categories[i].getAttribute("data-category"));
			categories[i].classList.replace("categoryActive", "categoryInactive");		
		} 
	}
	
	function setActiveIssuesInactive() {
		
		var issues = document.getElementsByClassName("issueActive");		
		for(i = 0; i < issues.length; i++){
			issues[i].classList.replace("issueActive", "issueInactive");
		}		
	}
	
	function checkQuestionsAnswered() {
		
		var answered = true;
		
		for(var k = 1; k <= numberOfQuestions; k++){
			var questionID = "question_" +k.toString(8);
			
			if ($('input[name=' +questionID +']:checked').length == 0) {
				$('#' +questionID +"_warning").removeClass("hide");
				answered = false;	
			}	
			
			else {
				$('#' +questionID +"_warning").addClass("hide");
			}
		}
		return answered;
	}
		
	function setCurrentDescription() {
		switch(currentPage){			
			case 1: 	$("#desc1").removeClass("hide");
						$("#desc2").addClass("hide");
						$("#desc3").addClass("hide");
						break;
			case 2:		$("#desc1").addClass("hide");
						$("#desc2").removeClass("hide");
						$("#desc3").addClass("hide");
						break;
			
			case 3:		$("#desc1").addClass("hide");
						$("#desc2").addClass("hide");
						$("#desc3").removeClass("hide");
						break;			
		}
	}
	
	
	//## DISPLAY / HIDE ELEMENTS
	
	function hideCategories() {
		
		$("#categoryList").addClass("hide");
	}
	
	function showCategories() {
		
		$("#categoryList").removeClass("hide");
	}
	
	function hideQuestions() {
		
		$("#questionList").addClass("hide");
	}
	
	function showQuestions() {
		
		$("#questionList").removeClass("hide");
	}
	
	function showUserInfo() {
		
		$("#userInfo").removeClass("hide");
	}
	
	function hideUserInfo() {
		
		$("#userInfo").addClass("hide");
	}
	
	function enableFwdBtn() {
		
		$('#fwdBtn').removeClass("hide");
	}
	
	function disableFwdBtn() {
		
		$('#fwdBtn').addClass("hide");
	}
		
	function enableBckBtn() {
		if(currentPage != 1)
			$('#bckbtn').removeClass("hide");
	}
	
	function disableBackBtn() {
		if(currentPage == 1)
			$('#bckbtn').addClass("hide");
	}
	
	function enableSubmitBtn() {
		
		$('#submitFormBtn').removeClass("hide");
	
	}
	
	function disableSubmitBtn() {
		
		$('#submitFormBtn').addClass("hide");
	
	}
	
	//## GENERATE DYNAMIC ELEMENTS
	
	function generateCategoryList(data) {
		
		var categoryList = document.createElement('ul');
		categoryList.setAttribute("class", "list-group");
		
		data.forEach(function(item){			
			var category = generateCategory(item.categoryName, item.categoryDescription);											
			var issueList = generateIssueList(item.categoryName);	

			categoryList.appendChild(category);			
			categoryList.appendChild(issueList);
		});	
		$("#categoryList").append(categoryList);
	}
	
	function generateCategory(categoryName, categoryDescription) {
		
		var category = document.createElement('li');
		category.setAttribute("id", categoryName +"_desc");
		category.setAttribute("class", "list-group-item" +" categoryInactive");
		category.setAttribute("onclick", "categoryClicked(this);");
		category.setAttribute("data-category", categoryName);
		
		generateCategoryName(category, categoryName);			
		generateCategoryDescription(category, categoryDescription);
		return category;
	}
	
	function generateCategoryName(category, categoryName) {
		
		var categoryNameElement = document.createElement("p");
		categoryNameElement.setAttribute("class", "noHover" +" unselectable");
		categoryNameElement.innerHTML = categoryName;
		category.appendChild(categoryNameElement);		
	}

	function generateCategoryDescription(category, categoryDescription) {
		
		var categoryDescriptionElement = document.createElement("p");						
		categoryDescriptionElement.innerHTML = categoryDescription;
		categoryDescriptionElement.setAttribute("class", "noHover" +" unselectable");
		category.appendChild(categoryDescriptionElement);
	}
	
	function generateIssueList(categoryName) {
		
		var issueList = document.createElement('li');
		issueList.setAttribute("id", categoryName +"_issuelist");
		issueList.setAttribute("class", "list-group-item" +" hide");	
		return issueList;
	}
	
	function addQuestionsToIssue(data) {
				
		$("#issueDescription").html(data.issueDescription);
		numberOfQuestions = data.questions.length;
		console.log(numberOfQuestions);
				
		var questionList = document.createElement('ul');
		questionList.setAttribute("class", "list-group");
		
		var iterator = 1;
		
		data.questions.forEach(function(item) {
			var questionID = "question_" +iterator.toString(8);			
			var question = generateQuestion(questionID, item);				
			addOptionsToQuestion(question, questionID, "YES", "NO", "DON'T KNOW");	
			addWarningToQuestion(question, questionID);
			questionList.appendChild(question);	
			iterator++;
		});
		
		$("#questions").append(questionList);
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
		warning.setAttribute("class", "hide");
		warning.innerHTML = "Question not answered";
		question.appendChild(warning);
	}
	
	
	// ## DATA COLLECTION
	
	function getAllQuestionsAndAnswers() {
		
		currentQuestionsAndAnswers = [];
		
		var questionsAndAnswers = [];
		
		for(var k = 1; k <= numberOfQuestions; k++){
			var questionID = "question_" +k.toString(8);
			var question = $("#" +questionID).html() +": ";
			var questionReply;
			
			if ($('input[name=' +questionID +']:checked').length > 0) {
				questionReply = $('input[name=' +questionID +']:checked').attr("data-choice");
	
			question = question +questionReply;
			console.log(question);
			questionsAndAnswers.push(question);
			}			
		}
		currentQuestionsAndAnswers = questionsAndAnswers;	
	}
	
	// ## AJAX
	
	function getAllCategories() {
			
			$.get("/api/issue/getallcategories", function(data, status){
			data = $.parseJSON(data);
			generateCategoryList(data);
		});	
	}
				
	function addIssuesToCategory(currentElement) {
		
		var category = currentElement.getAttribute("data-category");
		
		var element = $(this).attr(category +"_issuelist");
		element.setAttribute("class", "list-group-item");

		$.post('/api/issue/getissuesfromcategory', { "category": category}, function(returnedData){
			returnedData = $.parseJSON(returnedData);
			
			var issueList = document.createElement('ul');
			issueList.setAttribute("class", "list-group");
			
			returnedData.forEach(function(item){
				
				var issueName = document.createElement("p");
				issueName.setAttribute("class", "noHover" +" unselectable");
				issueName.innerHTML = item.issueName;
			
				var issueDescription = document.createElement("p");						
				issueDescription.innerHTML= item.issueDescription;
				issueDescription.setAttribute("class", "noHover" +" unselectable");
				
				var issue = document.createElement('li');
				issue.setAttribute("id", item.issueName +"_desc");
				issue.setAttribute("class", "list-group-item" +" issueInactive" +" alignRight");
				issue.setAttribute("onclick", "issueClicked(this);");
				issue.setAttribute("data-active", "false");
				issue.setAttribute("data-issue", item.issueName);
				issue.appendChild(issueName);
				issue.appendChild(issueDescription);
				issueList.appendChild(issue);				
			});
			element.appendChild(issueList);
			
		}).fail(function(){
			console.log("Failed to add issues to category");
		});
	}
	
	function removeIssuesFromCategory(category) {
			
		var element = $(this).attr(category +"_issuelist");
		element.setAttribute("class", "list-group-item" +" hide");
		
		while (element.firstChild) {
			element.removeChild(element.firstChild);
		}
	}
	
	function removeQuestionsFromIssue() {
		
		console.log("REMOVING QUESTIONS");
		$("#questions").empty(); 
		
	}
	
	function getQuestionsFromIssue() {
		
		console.log("ADDING QUESTIONS");
		
		$.post('/api/issue/getquestionsfromissue', { "issue": currentIssue}, function(returnedData) {
			returnedData = $.parseJSON(returnedData);
			addQuestionsToIssue(returnedData);
			
		}).fail(function(){
			console.log("Failed to add questions to issue");
		});
	}
	
	function submitHelperForm() {
		
			var userInfo = $('#formInput_phone').val();
			console.log("SUBMITTING FORM")
			var answers = JSON.stringify(currentQuestionsAndAnswers);
			
			console.log(currentQuestionsAndAnswers);
			
			$.post('/api/helper/submithelperform', {"userInfo": userInfo, "issue": currentIssue, "questionsAndAnswers": answers}, function(returnedData) {
				
		
		
		}).fail(function(){
			console.log("Failed to submit form");
		});
		
	}




