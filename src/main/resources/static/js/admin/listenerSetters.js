
// **SET LISTENERS

//		**Editables

function setEditableListener(editable) {

		editable.addEventListener("mouseover", function() { 
			if(edited == null){
				editableHoverActive(editable);
			}					
			}, false);
			
		editable.addEventListener("mouseout", function() {   
			if(edited == null){				
				editableHoverInactive(editable);				
			}
		}, false);

		editable.addEventListener("click", function() { 
			
			if(edited == null){								
				setEditableSelected(editable);
			}				
		}, false);
	}
	
	
//		**Categories

	function setCategoryListener(category) {
		
		category.addEventListener("mouseover", function(event) {
			if(category != currentSelectedCategory && edited == null) {
				category.classList.replace("categoryInactive", "categoryHover");
				
			}
			
			
		}, false);
		
		category.addEventListener("mouseout", function(event) {
			if(category != currentSelectedCategory && edited == null) {
				if(category.classList.contains("categoryHover")){
					category.classList.replace("categoryHover", "categoryInactive");
				}
				
				if(category.classList.contains("categoryActive")) {
					category.classList.replace("categoryActive", "categoryInactive");
				}				
			}
			
			
		}, false);
		
		category.addEventListener("mousedown", function(event) {
			if(edited == null && editableHover == null){
				category.classList.replace("categoryHover", "categoryActive");
			}
			
			
		}, false);
		
		category.addEventListener("click", function(event) {
			if(edited == null && editableHover == null){
				determineCategory(category);
			}
			
			if(acceptCancelClicked){
				edited = null;	
				acceptCancelClicked = false;				
			}
			
			
		}, false);
	}
	
	
// 		**Issues

	function setIssueListener(issue) {
		
		issue.addEventListener("mouseover", function() {
			if(issue != currentSelectedIssue && edited == null) {
				$(issue)
					.removeClass("issueInactive")
					.addClass("issueHover");
			}
			
		}, false);
		
		issue.addEventListener("mouseout", function() {
			if(issue != currentSelectedIssue && edited == null) {
				if($(issue).hasClass("issueHover")) {
					$(issue)
						.removeClass("issueHover")
						.addClass("issueInactive");
				}	
				if($(issue).hasClass("issueActive")) {
					$(issue)
						.removeClass("issueActive")
						.addClass("issueInactive");
				}
			}									
		}, false);
		
		issue.addEventListener("mousedown", function() {
			if(edited == null && editableHover == null){
				$(issue)
					.removeClass("issueHover")
					.addClass("issueActive");
			}
			
		}, false);
		
		issue.addEventListener("click", function() {
			if(edited == null && editableHover == null){
				determineIssue(issue);
			}
			
			if(acceptCancelClicked){
				edited = null;	
				acceptCancelClicked = false;				
			}
			
		}, false);
	}
	
		function setQuestionListener(question) {
		
		question.addEventListener("mouseover", function() {
			if(question != currentSelectedQuestion) {
				$(question)
					.removeClass("questionInactive")
					.addClass("questionHover");
			}
			
		}, false);
		
		question.addEventListener("mouseout", function() {
			if(question != currentSelectedQuestion) {
				if($(question).hasClass("questionHover")) {
					$(question)
						.removeClass("questionHover")
						.addClass("questionInactive");
				}	
				if($(question).hasClass("questionActive")) {
					$(question)
						.removeClass("questionActive")
						.addClass("questionInactive");
				}
			}									
		}, false);
		
		question.addEventListener("mousedown", function() {
			if(edited == null && editableHover == null){
				$(question)
					.removeClass("questionHover")
					.addClass("questionActive");
			}
			
		}, false);
		
		question.addEventListener("click", function() {
			if(edited == null && editableHover == null){
				determineQuestion(question);
			}
			
			if(acceptCancelClicked){
				edited = null;	
				acceptCancelClicked = false;				
			}
			
		}, false);
	}
	
	