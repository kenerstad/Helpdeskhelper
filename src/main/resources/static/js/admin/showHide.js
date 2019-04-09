
// ## SHOW/HIDE

	function hideElementByID(elID) {
		
		$('#' +elID).fadeOut(0);
	}
	
	function showElementByID(elID) {
		
		$('#' +elID).removeClass("hide");
	}
	
	function hideElement(el) {
		
		$(el).fadeOut(0);
	}
	
	function showElement(el) {
		
		$(el).removeClass("hide");
	}
			
	function clearElementByID(elID) {
				
		$('#' +elID).empty();
	}	
	
	function showRemoveCategoryBtn() {
		
		$('#removeCategoryBtn').fadeIn(100);
	}
	
	function hideRemoveCategoryBtn() {
		
		$('#removeCategoryBtn').fadeOut(0);
	}
	
	function showRemoveIssueBtn() {
		
		$('#removeIssueBtn').fadeIn(100);
	}
	
	function hideRemoveIssueBtn() {
		
		$('#removeIssueBtn').fadeOut(0);
	}
	
	function showRemoveQuestionBtn() {
		
		$('#removeQuestionBtn').fadeIn(100);
	}
	
	function hideRemoveQuestionBtn() {
		
		$('#removeQuestionBtn').fadeOut(0);
	}
	
	function hideIssues() {
		
		$('#issueContainer').fadeOut(0);
	}
	
	function hideQuestions() {
		
		$('#questionContainer').fadeOut(0);
	}
	
			
	function setExplanationMsg(explanation) {

		$('#adminExplanationText').html(explanation);			
		
	}	
	
	function clearExplanationMsg() {
		
		$('#adminExplanationText').html("");
	}

	function setAlertMsg(msg, colour) {
		
		$('#adminAlertText').html(msg);
	}
		
	function setIssueListTitle(category) {
		
		$('#issueListTitle').html("Issues for category " +category)
	}
	
	function setQuestionListTitle(category) {
		
		$('#questionListTitle').html("Questions for issue " +category)
	}
	
	function showEditableButtons(el) {
		
		var el2 = $(el).closest('.listItemContainer').find('.btnContainer');
		el2.fadeIn(100);
	}
	
	function hideEditableButtons(el) {
		
		console.log("hiding buttons");
		var el2 = $(el).closest('.listItemContainer').find('.btnContainer');
		el2.addClass("hide");
	}
	
	function showNewRemoveButtons(el) {
		
		$(el)
			.closest('.listContainer')
			.find('.newRemoveBtn-group')
			.fadeTo(300, 1);
	}
	
		function hideNewRemoveButtons(el) {
		
		$(el)
			.closest('.listContainer')
			.find('.newRemoveBtn-group')
			.fadeTo(300, 0.5);
	}
	
	function fadeAllExcept(el) {
		
		$(el).addClass('noFade');
		$('div:not(".noFade")').fadeTo(150, 0.3);
	}
	
	function fadeAllIn() {
				
		$('div:not(".noFade")').fadeTo(150, 1);
		//$(el).closest('.listItemContainer').find('.btnContainer').addClass('noFade');
	}
	
	
	
	
	