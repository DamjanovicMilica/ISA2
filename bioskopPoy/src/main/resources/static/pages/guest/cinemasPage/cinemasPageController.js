(function () {
    
      'use strict';
    
      angular.module('bioskopPoy').controller('CinemasPageController', CinemasPageController);
    
      CinemasPageController.$inject = ['$scope', '$state', '$location', 'sessionService', 'notifyService', 'guestService', 'authenticationService', '$window'];
    
      function CinemasPageController($scope, $state, $location, sessionService, notifyService,  guestService, authenticationService, $window) {
    
        $scope.userInfo = sessionService.getUserInfo();
    
        $scope.restaurantsPage = []; 
        $scope.currentSort = "name";
        $scope.range = function(n) {
            var x = new Array(Math.round(n));
            for(let i = 0; i < x.length; i++) {
              x[i] = i+1;
            }
            return x;
        };
        $scope.changeSort = function(sortBy) {
          $scope.currentSort = sortBy;
        };
        notifyService.showInfo("Loading cinemas data...Please wait...");
        guestService.getCinemasPageInfo($scope.userInfo.userId).then(function (response) {
            $scope.cinemasPage = response.data; 
            console.log($scope.cinemasPage); 
        }, function (error) {
    
        });
    
        $scope.logoff = function() {
          notifyService.showInfo("Logging off...");
          authenticationService.logoff(); 
          $window.location.href = '/#/login'; 
        
        }
        
        $scope.reserve = function(id) {
            $state.go('reserve1/:cinamaId', {cinemaId: id}); 
          };
         
       
      }
    })();
    
     
        