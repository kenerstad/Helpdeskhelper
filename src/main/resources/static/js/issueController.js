
var issueApp = angular.module('issueApp', []).
controller('issueController' , function ($scope, $http, $location) {
    $scope.getCategories = function () {
		//var url = $location.absUrl() + "/api/getallcategories";
        console.log("I've been pressed!");  
        $http.get("/api/getallcategories", {responseType: 'text'})
            .then(function successCallback(response){
                $scope.response = response.data;
				$scope.displayCategories();
            }, function errorCallback(response){
                $scope.response = response;				
            });
    };	    
	
	$scope.displayCategories = function () {			
		$scope.response.forEach(function(item){
			console.log(item.categoryName);
			var btn = document.createElement("BUTTON");
			var t = document.createTextNode(item.categoryName);
			btn.appendChild(t);
			btn.setAttribute("name", item.categoryName);
			btn.setAttribute("ng-click", "getCategories()");
			document.body.appendChild(btn); 
		});
	};
});