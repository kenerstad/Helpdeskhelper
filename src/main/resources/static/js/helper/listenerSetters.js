//	**Category

	function setCategoryListener(category) {
		
		category.addEventListener("mouseover", function(event) {
			if(category != currentSelectedCategory) {
				category.classList.replace("categoryInactive", "categoryHover");				
			}			
		}, false);
		
		category.addEventListener("mouseout", function(event) {
			if(category != currentSelectedCategory) {
				if(category.classList.contains("categoryHover")){
					category.classList.replace("categoryHover", "categoryInactive");
				}
				
				if(category.classList.contains("categoryActive")) {
					category.classList.replace("categoryActive", "categoryInactive");
				}				
			}			
		}, false);
		
		category.addEventListener("mousedown", function(event) {
			category.classList.replace("categoryHover", "categoryActive");			
		}, false);
		
		category.addEventListener("click", function(event) {
				determineCategory(category);		
		}, false);
	}
	
	function setIssueListener(issue) {
		
		issue.addEventListener("mouseover", function() {
			if(issue != currentSelectedIssue) {
				$(issue)
					.removeClass("issueInactive")
					.addClass("issueHover");
			}
			
		}, false);
		
		issue.addEventListener("mouseout", function() {
			if(issue != currentSelectedIssue) {
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
				$(issue)
					.removeClass("issueHover")
					.addClass("issueActive");			
		}, false);
		
		issue.addEventListener("click", function() {
				determineIssue(issue);
						
		}, false);
	}
	