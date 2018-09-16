(function () {
    
      'use strict';
    
      angular.module('bioskopPoy').controller('HomePageController', HomePageController);
    
      HomePageController.$inject = ['$scope', '$state', '$location', 'sessionService', 'guestService', 'authenticationService', '$window'];
    
      function HomePageController($scope, $state, $location, sessionService, guestService, authenticationService, $window) {
        $scope.userInfo = sessionService.getUserInfo();
    
        $scope.homePageCinemas = []; 
        $scope.homePageTheatres = []; 
        guestService.getHomePageInfoCinemas($scope.userInfo.userId).then(function (response) { 
            $scope.homePageCinemas = response.data; 
            console.log($scope.homePageCinemas); 
        }, function (error) {
    
        });

        guestService.getHomePageInfoTheatres($scope.userInfo.userId).then(function (response) { 
            $scope.homePageTheatres = response.data; 
            console.log($scope.homePageTheatres); 
        }, function (error) {
    
        });
    
    
        $scope.logoff = function() {
          alert("loging you off");
          authenticationService.logoff(); 
          $window.location.href = '/#/login';
        
        };
       
      }
    })();
    
     
        