
(function () {
    'use strict';

    angular.module('bioskopPoy').service('notifyService', notifyService);

    notifyService.$inject = ['$http', 'notify', '$rootScope'];


    function notifyService( $http, notify, $rootScope) {
        

        return {
            showInfo: showInfo,
            showError: showError,
            showSuccess: showSuccess
        };


        function showInfo(msg) {
           let template = '';

            let positions = ['center', 'left', 'right'];
            let position = positions[2];
            let classes = "alert-info"; 
            let duration = 10000;

            
                notify({
                    message: msg,
                    classes: classes,
                    scope: $rootScope,
                    templateUrl: template,
                    position: position,
                    duration: duration
                });
        
        }

        function showError(msg) {
            let template = '';

            let positions = ['center', 'left', 'right'];
            let position = positions[2];
            let classes = "alert-danger"; 
            let duration = 10000;

           
                notify({
                    message: msg,
                    classes: classes,
                    scope: $rootScope,
                    templateUrl: template,
                    position: position,
                    duration: duration
                });
            }
        

        
        function showSuccess(msg) {
           
            let positions = ['center', 'left', 'right'];
            let position = positions[2];
            let classes = "alert-success"; 
            let duration = 10000;
                notify({
                    message: msg,
                    classes: classes,
                    position: position,
                    duration: duration
                });
            }
            
    }


})();
