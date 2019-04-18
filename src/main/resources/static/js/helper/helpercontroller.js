
$(document).ready(function(){
	
	getAllCategories();	
	initVars();	
		
}); 


// ## VARS
	{
		var currentSelectedCategory;
		var currentSelectedIssue;
		var previousPage;
		var currentPage;
		var currentQuestionsAndAnswers;
		var numberOfQuestions;
		var fadeTime;

		function initVars() {
			
			currentPage = 1;
			fadeTime = 100;
		}
	}
	
	
	function fwdBtnClicked() {
					
		changePage("fwd");		
	}	
	
	function bckBtnClicked() {
		
		changePage("bck");									
	}


	function anotherIssueClicked() {
		
		changePage(1);
	}

	function determineCategory(el) {
		
		clearElementByID('issueList');
		clearElementByID('questionList');
		
		if(el == currentSelectedCategory) {
			$(el)
				.removeClass("categoryActive")
				.addClass("categoryHover");
			currentSelectedCategory = null;
		}		
		else {	
			if(currentSelectedCategory != null)
				currentSelectedCategory.classList.replace("categoryActive", "categoryInactive");
			
			currentSelectedCategory = el;
			el.classList.remove("categoryHover");
			el.classList.add("categoryActive");

			getIssuesFromCategory();
		}	
	}
	
	function determineIssue(el) {
					
		if(el == currentSelectedIssue) {
			$(el)
				.removeClass("issueActive")
				.addClass("issueInactive");
			currentSelectedIssue = null;		
		}		
		
		else {	
			if(currentSelectedIssue != null) {
				$(currentSelectedIssue)
					.removeClass("issueActive") 
					.addClass("issueInactive");
			}	
			clearElementByID('questionList');
			$(el)
				.removeClass("categoryHover")
				.addClass("categoryActive");
			currentSelectedIssue = el;		
			getQuestionsFromIssue();
			showElementByID('fwdBtn');
		}	
	}
	
// ## GENERAL		

	function changePage(change) {
		
		previousPage = currentPage;
		
		if(change == "fwd")
			currentPage++;
		
		else if(change == "bck")
			currentPage--;
		
		else
			currentPage = change;
						
		switch(currentPage){
			
			case 1:	showPage1();
					if(previousPage == 2)
						hideElementByID('bckBtn');
					break;
					
			case 2:	if(previousPage == 1) {	
						showElementByID('bckbtn');
					}
					
					if(previousPage == 3) {
						showElementByID('fwdBtn');
						
					}
					showPage2();					
					break;
				
			case 3: if(checkQuestionsAnswered()){								
						showPage3();
						hideElementByID('fwdBtn');
						getAllQuestionsAndAnswers();
					}
					else{
						decreaseCurrentPage();
						previousPage = currentPage;
					}
					
					break;
			
			case 4: showPage4();
					hideElementByID('fwdBtn');	
					hideElementByID('bckBtn');	
					break;
		}
		changeProgressbar();
		setCurrentDescription();
	}
	{}
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
			case 1: 	$('#explanation').html('Please select what kind of category your problem falls into, then you may select a spesific issue.');
						break;
			case 2:		$('#explanation').html('Please fill in an answer for all the questions.');
						break;
			
			case 3:		$('#explanation').html('Here you may submit your user information so we can get back to you as soon as possible with a solution to your problem.');
						break;	

			case 4:		$('#explanation').html('');
						break;							
		}
	}
	
	
// ## DATA COLLECTION
	{
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
				questionsAndAnswers.push(question);
				}			
			}
			currentQuestionsAndAnswers = questionsAndAnswers;	
		}
		
		function submitHelperForm() {
			
			var phoneNumber = $('#formInput_phone').val();
			console.log("SUBMITTING FORM")
			
			var issue = $(currentSelectedIssue).attr('issue-name');
			var answers = JSON.stringify(currentQuestionsAndAnswers);
			
			ajax_SubmitHelperForm(issue, answers, phoneNumber);
		}
	}

			
			
			
		
		
		