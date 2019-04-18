
	function hideElementByID(elID) {
			
		$('#' +elID).fadeOut(0);
	}
		
	function showElementByID(elID) {
		
		$('#' +elID).fadeIn(100);
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

	function clearElement(el) {
				
		$(el).empty();
	}	

	function showPage1() {
		
		$("#page1").removeClass("hide");
		$("#page2").addClass("hide");
		$("#page3").addClass("hide");
		$("#page4").addClass("hide");
	}
	
	function showPage2() {
		
		$("#page2").removeClass("hide");
		$("#page1").addClass("hide");
		$("#page3").addClass("hide");
		$("#page4").addClass("hide");
	}
	
	function showPage3() {
		
		$("#page3").removeClass("hide");
		$("#page1").addClass("hide");
		$("#page2").addClass("hide");
		$("#page4").addClass("hide");
	}
	
	function showPage4() {
		
		$("#page4").removeClass("hide");
		$("#page1").addClass("hide");
		$("#page2").addClass("hide");
		$("#page3").addClass("hide");
	}
	
	function changeProgressbar() {
		
		switch(currentPage){
			
			case 1: $('#progressBar1').removeClass("hide");
					$('#progressBar2').addClass("hide");
					break;
			case 2: $('#progressBar2').removeClass("hide");
					$('#progressBar1').addClass("hide");
					$('#progressBar3').addClass("hide");					
					break;
			case 3: $('#progressBar3').removeClass("hide");
					$('#progressBar2').addClass("hide");
					break;			
		}
	}