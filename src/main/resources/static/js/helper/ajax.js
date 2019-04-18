

	function getAllCategories() {
				
			$.get("/api/issue/getallcategories", function(data, status){
			generateCategoryList($.parseJSON(data));
		});	
	}

	function getIssuesFromCategory() {
		
			var id = currentSelectedCategory.getAttribute("category-id");
			
			var data = 				
			$.post('/api/issue/getissuesfromcategory', { 
			"id": id}, 
			function(returnedData){
				addIssuesToCategory($.parseJSON(returnedData));
														
			}).fail(function(){
				console.log("Failed to get issues from category");
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
				
														
			}).fail(function(){
				console.log("Failed to get questions from category");
			});		
	}
	
	function ajax_SubmitHelperForm(issue, answers, phoneNumber) {
		
		$.post('/api/helper/submithelperform', {
			"phoneNumber": phoneNumber, 
			"issue": issue, 
			"questionsAndAnswers": answers}, 
			function(returnedData) {
				changePage("fwd");
	
		}).fail(function(){
			console.log("Failed to submit form");
		});	
		
	}