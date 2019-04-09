
$(document).ready(function(){
	
	getAllCategories();	
	currentSelectedIssue = "none";	
	issueSelected = false;
	acceptCancelClicked = false;
	fadeTime = 100;
}); 

	var currentSelectedCategory;
	var currentSelectedIssue;
	var currentSelectedQuestion;
	var acceptCancelClicked;
	var currentSelectedIssue;
	var editableHover;
	var issueSelected;
	var edited;
	var fadeTime;
	var newGenerated;
	
	
// ##ONHOVER

	function editableHoverActive(editable) {
		
		editableHover = editable;
		$(editable).removeClass("editableInactive").addClass("editableHover");
				
		setExplanationMsg("Click to edit " +$(editable).attr("editableType"));
	}
	
	function editableHoverInactive(editable) {
		
		editableHover = null;	
		$(editable)
			.removeClass("editableHover")
			.addClass("editableInactive");
		setExplanationMsg("");	
	}
	
// ##ONCLICK
		
	function addCategoryBtnClicked() {

		if(newGenerated == null && edited == null){			
			newGenerated = generateEditableCategory("New Category");		
			setEditableSelected(getEditableFromParent(newGenerated));
		}		
	}
	
	function deleteCategoryBtnClicked() {
		
		if(newGenerated == null && edited == null && currentSelectedCategory != null){			
			deleteCategory();		
		}
	}
	
	function addIssueBtnClicked() {
		
		if(newGenerated == null && edited == null){			
			newGenerated = generateEditableIssue("", "New Issue", "New issue Description");			
			setEditableSelected(getEditableFromParent(newGenerated));
		}		
	}
	
	function deleteIssueBtnClicked(el) {
		
			if(newGenerated == null && edited == null){			
			deleteIssue();		
		}
	}
	
	function addQuestionBtnClicked() {
		
		if(newGenerated == null && edited == null){			
			newGenerated = generateEditableQuestion("", "New Question");			
			setEditableSelected(getEditableFromParent(newGenerated));
		}		
	}
	
	function deleteQuestionBtnClicked(el) {
		
			if(newGenerated == null && edited == null){			
			deleteQuestion();		
		}
	}
	
	function editableAcceptClicked(el) {
				
		setEditableDeselected(el);		
		acceptCancelClicked	= true;		
		var editableParent = getEditableParent(el);
		
		if(newGenerated != null) {
			switch($(editableParent).attr('type')) {
				case 'category': 	addCategory();
									break;
				case 'issue': 		addIssueToCategory();
									break;
									
				case 'question': 	addQuestion();
									break;
			}	
			
			newGenerated = null;
		}
		
		else{
			switch($(editableParent).attr('type')) {
				case 'category': 	alterCategory();
									break;
				case 'issue': 		alterIssue();
									break;
									
				case 'question': 	alterQuestion();
									break;
			}	
		}
		
		$(editableParent).removeClass("noFade");
		fadeAllIn();
	}
	
	function editableCancelClicked(el) {
			
		if(newGenerated != null) {
			newGenerated = null;
			var editableParent = getEditableParent(el);
			editableParent.remove();
			setAlertMsg('Cancelled creating new category');
		}
		else{
			setEditableDeselected(el);	
		}
		acceptCancelClicked	= true;
		fadeAllIn();
	}
		
//## GENERAL 

	function determineExplanationMsg(el) {
		
		
	}
	
	function getEditableParent(el) {
		
		return $(el).closest('.listItemContainer');
	}
	
	function getEditableFromParent(p) {
		
		return $(p).find('.editable');
	}
	
	
//## SELECTION 

	function determineCategory(el) {
		
		clearElementByID('issueList');
		clearElementByID('questionList');
		hideIssues();
		hideQuestions();
		hideRemoveIssueBtn();
		hideRemoveQuestionBtn();
		
		if(el == currentSelectedCategory) {
			$(el)
				.removeClass("categoryActive")
				.addClass("categoryHover");
			currentSelectedCategory = null;
			hideRemoveCategoryBtn();
		}		
		else {	
			if(currentSelectedCategory != null)
				currentSelectedCategory.classList.replace("categoryActive", "categoryInactive");
			
			currentSelectedCategory = el;
			el.classList.remove("categoryHover");
			el.classList.add("categoryActive");

			setIssueListTitle($(el).attr("category-name"));
			showRemoveCategoryBtn()
			getIssuesFromCategory();
		}	
	}
	
	function determineIssue(el) {
			
		clearElementByID('questionList');
		hideQuestions();
		hideRemoveQuestionBtn();
		
		if(el == currentSelectedIssue) {
			$(el)
				.removeClass("issueActive")
				.addClass("issueInactive");
			currentSelectedIssue = null;
			hideRemoveIssueBtn();
			$('#questionContainer').fadeOut(0);			
		}		
		else {	
			if(currentSelectedIssue != null) {
				$(currentSelectedIssue)
					.removeClass("issueActive") 
					.addClass("issueInactive");
			}			
			$(el)
				.removeClass("categoryHover")
				.addClass("categoryActive");
			currentSelectedIssue = el;	
			$('#questionContainer').fadeOut(0);
			setQuestionListTitle($(el).attr("issue"));
			showRemoveIssueBtn();		
			getQuestionsFromIssue();
		}	
	}
	
		function determineQuestion(el) {
				
		if(el == currentSelectedQuestion) {
			$(el)
				.removeClass("questionActive")
				.addClass("questionInactive");
			currentSelectedQuestion = null;
			hideRemoveQuestionBtn();
		}		
		else {	
			if(currentSelectedQuestion != null) {
				$(currentSelectedQuestion)
					.removeClass("questionActive") 
					.addClass("questionInactive");
			}			
			$(el)
				.removeClass("questionHover")
				.addClass("questionActive");
			currentSelectedQuestion = el;
			showRemoveQuestionBtn();			
		}	
	}
				
	function setEditableSelected(el) {
		
		setExplanationMsg("Editing " +$(el).attr("editableType"));
		edited = $(el).closest('.listItemContainer').find('.editable');
		$(edited)
			.removeClass("editableInactive")
			.removeClass("editableHover")
			.removeClass("unselectable")
			.addClass("editableActive")		
			.attr("contenteditable", "true");
		//selectElementContents(el);
		showEditableButtons(el);
		//hideNewRemoveButtons(el);
		var item = getEditableParent(el);
		fadeAllExcept(item);
	}
	
	function setEditableDeselected(el) {
		
		var textElement = $(el).closest('.listItemContainer').find('.editable');
		var buttonElement = $(el).closest('.listItemContainer').find('.btnContainer');
		
		textElement.removeClass("editableActive").addClass("editableInactive").addClass("unselectable");
			deselectElementContents(textElement);
			editableHoverInactive(textElement);
			textElement.attr("contenteditable", "false");
		
		hideElement(buttonElement);
	}
	
	function selectElementContents(currentElement) {
		var range = document.createRange();
		range.selectNodeContents(currentElement);
		var sel = window.getSelection();
		sel.removeAllRanges();
		sel.addRange(range);
	}
	
	function deselectElementContents(currentElement) {
		
		var sel = window.getSelection();
		sel.removeAllRanges();;
	}

			
// ##AJAX
			
	function getAllCategories() {
				
				$.get("/api/issue/getallcategories", function(data, status){
				generateCategoryList($.parseJSON(data));
				$('#categoryContainer').fadeIn(100);
			});	
	}		
	
	function addCategory() {
		
		var category = newGenerated;
		var categoryName = $(getEditableFromParent(category)).html();
		
		$.post('/api/admin/addcategory', { 
			"category": categoryName}, 
			
			function(returnedData){
				var returnedData = $.parseJSON(returnedData);
				$(category).attr('category-id', returnedData.id);
				$(category).attr('category-name', categoryName);	
				setExplanationMsg(returnedData.msg);
																		
			}).fail(function(){
				console.log("Failed to add new category to DB");
			});	
	}
	
	function alterCategory() {
		
		var categoryId = $(getEditableParent(edited)).attr('category-id');
		var category = $(getEditableParent(edited)).attr('category-name');
		var categoryChange = $(edited).html();
		
		$.post('/api/admin/alterCategory', { 
			"categoryId": categoryId,
			"category": category,
			"categoryChange": categoryChange}, 
			
			function(returnedData){
				returnedData = $.parseJSON(returnedData);
				$(edited).attr('category-name', categoryChange)
				edited = null;
				setAlertMsg(returnedData.msg);
				
														
			}).fail(function(){
				console.log("Failed to get issues from category");
			});		
	}
	
	function deleteCategory() {
		
		var id = $(currentSelectedCategory).attr('category-id');	
		
		$.post('/api/admin/deletecategory', { 
			"id": id}, 
			
			function(returnedData){
				currentSelectedCategory.remove();
				hideRemoveCategoryBtn();
				clearElementByID('issueList');
				clearElementByID('questionList');
				hideIssues();
				hideQuestions();
				hideRemoveIssueBtn();
				hideRemoveQuestionBtn();
				currentSelectedCategory = null;				
														
		}).fail(function(){
		});		
	}
	
	function getIssuesFromCategory() {
		
			var id = currentSelectedCategory.getAttribute("category-id");
			
			var data = 				
			$.post('/api/issue/getissuesfromcategory', { 
			"id": id}, 
			function(returnedData){
				addIssuesToCategory($.parseJSON(returnedData));
				$('#issueContainer').fadeIn(100);
														
			}).fail(function(){
				console.log("Failed to get issues from category");
			});
	}
	
	function addIssueToCategory() {
		
		var issue = newGenerated;
		var categoryId = $(currentSelectedCategory).attr("category-id");
		var issueName = $(issue).find('.name').html();
		var issueDescription = $(issue).find('.description').html();
		
		console.log("name" +issueName +" desc: " +issueDescription);
		
			$.post('/api/admin/addissuetocategory', { 
			"categoryId": categoryId,
			"issueName": issueName,
			"issueDescription": issueDescription}, 
			
			function(returnedData){
				returnedData = $.parseJSON(returnedData);
				$(issue).attr('issue-id', returnedData.id);
				$(issue).attr('issue-name', issueName);
				$(issue).attr('issue-description', issueDescription);
				setAlertMsg(returnedData.msg);
														
			}).fail(function(){
			});	
	}
	
	function alterIssue() {
		
		var issue = $(getEditableParent(edited));
		var issueId = $(issue).attr('issue-id');
		var issueName = $(issue).attr('issue-name');
		var issueNameChange = $(issue).find('.name').html();
		var issueDescription = $(issue).attr('issue-description');
		var issueDescriptionChange = $(issue).find('.description').html();
		
		$.post('/api/admin/alterissue', { 
			"issueId": issueId,
			"issueName": issueNameChange,
			"issueDescription": issueDescriptionChange}, 
			
			function(returnedData){
				returnedData = $.parseJSON(returnedData);
				$(issue).attr('issue-name', issueNameChange);
				$(issue).attr('issue-description', issueDescriptionChange);
				edited = null;
				setAlertMsg(returnedData.msg);
				
														
			}).fail(function(){
				console.log("Failed to get issues from category");
			});		
	}
	
	function deleteIssue() {
		
				
		var id = $(currentSelectedIssue).attr('issue-id');	
		
		$.post('/api/admin/deleteissue', { 
			"id": id}, 
			
			function(returnedData){
				clearElementByID('questionList');
				hideQuestions();
				hideRemoveIssueBtn();
				hideRemoveQuestionBtn();
				
				currentSelectedIssue.remove();
				currentSelectedIssue = null;				
														
		}).fail(function(){
		});	
	}
		
	function getQuestionsFromIssue() {
		
		var issueId = currentSelectedIssue.getAttribute("issue-id");
		console.log("issue id: " +issueId);
		
			var data = 				
			$.post('/api/issue/getquestionsfromissue', { 
			"issueId": issueId}, 
			function(returnedData){
				addQuestionsToIssue($.parseJSON(returnedData));
				$('#questionContainer').fadeIn(100);
														
			}).fail(function(){
				console.log("Failed to get questions from category");
			});		
	}
	
		function addQuestion() {
		
		var question = newGenerated;
		var issueId = $(currentSelectedIssue).attr("issue-id");
		var questionText = $(question).find('.editable').html();
				
			$.post('/api/admin/addQuestionToIssue', { 
			"issueId": issueId,
			"questionText": questionText}, 
			
			function(returnedData){
				returnedData = $.parseJSON(returnedData);
				$(question).attr('question-id', returnedData.id);
				$(question).attr('question-text', questionText);
				setAlertMsg(returnedData.msg);
														
		}).fail(function(){
		});	
	}
	
	function alterQuestion() {
		
		var question = $(getEditableParent(edited));
		var questionId = $(question).attr('question-id');
		var questionChange = $(question).find('.editable').html();
		var questionText = $(question).attr('question-text');
		
		$.post('/api/admin/alterquestion', { 
			"questionId": questionId,
			"questionChange": questionChange}, 
			
			function(returnedData){
				returnedData = $.parseJSON(returnedData);
				$(question).attr('question-text', questionChange);
				edited = null;
				setAlertMsg(returnedData.msg);
				
														
			}).fail(function(){
				console.log("Failed to alter question");
			});		
	}
	
	function deleteQuestion() {
		
				
		var id = $(currentSelectedQuestion).attr('question-id');	
		
		$.post('/api/admin/deletequestion', { 
			"id": id}, 
			
			function(returnedData){
				hideRemoveQuestionBtn();
				
				currentSelectedQuestion.remove();
				currentSelectedQuestion = null;				
														
		}).fail(function(){
		});	
	}
