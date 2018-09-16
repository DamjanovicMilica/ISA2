/**
 * Displays guest profile information, provides methods for editing guest account information
 * @author Jelena Jankovic RA139-2013
 *
 */
(function () {
  
    'use strict';
  
    angular.module('bioskopPoy').controller('LoginController', LoginController);
    LoginController.$inject = ['$scope', '$http', 'authenticationService', 'userRoles', '$location', '$state', 'sessionService', 'notifyService'];
  
    function LoginController($scope, $http, authenticationService, userRoles, $location, $state, sessionService, notifyService) {
      console.log("LoginController ready!");
      
     
      $scope.user = {
        username: null,
        password: null
      };
  
      var loginOk = function(data) {
          
          if (data.role === userRoles["GUEST"]) {
            $state.go('guest-home')
          } else {
            notifyService.showError('LoginController::Invalid user role:::' + data.role);
            $location.path("/");
          }
      };
  
      var loginError = function (error) {
          notifyService.showError('Login failed!');
      };
      
      
      $scope.login = function () {
        authenticationService.login($scope.user.username, $scope.user.password).then(loginOk, loginError);
        console.log("ULOGOVAN USER SA ID: " + sessionService.getUserInfo()); 
      };
  
  
      $scope.register = function () {
        $location.path("/guest/register");
      }
    }
  })();