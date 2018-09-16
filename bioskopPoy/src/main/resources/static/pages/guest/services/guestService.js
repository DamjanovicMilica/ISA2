/**
*  @author Jelena Jankovic RA139-2013
 * Handles calls to Guest REST backend
 */
(function () {
    'use strict';

    angular.module('bioskopPoy').service('guestService', guestService);

    guestService.$inject = ['$http', 'sessionService'];

    // hail satan
    function guestService($http, sessionService) {


        return {
            getProfilePageInfo: getProfilePageInfo,
            register: register,
            verifyGuest: verifyGuest,
            getHomePageInfoCinemas: getHomePageInfoCinemas,
            getHomePageInfoTheatres: getHomePageInfoTheatres,
            getTheatresPageInfo: getTheatresPageInfo,
            getCinemasPageInfo: getCinemasPageInfo,
            getFriendsPageInfo: getFriendsPageInfo,
            updateProfileInfo: updateProfileInfo,
            getGuestFriends: getGuestFriends,
            createReservation: createReservation,
            getGuestReservations: getGuestReservations,
            getReservationDetails: getReservationDetails,
            cancelReservation: cancelReservation,
            confirmAttendance: confirmAttendance,
            cancelAttendance: cancelAttendance,
            updateReservationOrders: updateReservationOrders,
            addPeople: addPeople,
            acceptPeople: acceptPeople,
            acceptFriendshipRequest: acceptFriendshipRequest,
            sendFriendshipRequest: sendFriendshipRequest, 
            rejectFriendshipRequest: rejectFriendshipRequest, 
            deleteFriend: deleteFriend

        };


        /** getProfileDetails (secured)
        * @param {int} id - GuestID
        */
        function getProfilePageInfo(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/get-profile-page-info/' + id
                
            });
        }

        function register(newGuest) {

            return $http({
                method: 'POST',
                data: newGuest,
                url: '/rest/guest/register-guest'
            });
        }


        function verifyGuest(id) {
            // encodeURL
            return $http({
                method: 'GET',
                url: '/rest/guest/confirm-registration/' + id
            });
        }


        /** getProfileDetails (secured)
        * @param {int} id - GuestID
        */
        function getHomePageInfoCinemas(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/get-home-page-info-cinemas/' + id
               
            });
        }

        function getHomePageInfoTheatres(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/get-home-page-info-theatres/' + id
               
            });
        }


        /** getProfileDetails (secured)
        * @param {int} id - GuestID
        */
        function getFriendsPageInfo(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/get-friends-page-info/' + id,
               
            });
        }

        /** getProfileDetails (secured)
        * @param {int} id - GuestID
        */
        function updateProfileInfo(name, surname, address, id) {
            var payload = {
                name: name,
                surname: surname,
                address: address
            };

            return $http({
                method: 'POST',
                data: payload,
                url: '/rest/guest/update-profile-info/' + id,
               
            });
        }

        
        function getTheatresPageInfo(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/get-theatres-page-info/' + id,
                
            });
        }

        
        function getCinemasPageInfo(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/get-cinemas-page-info/' + id,
                
            });
        }

        
        /** getProfileDetails (secured)
        * @param {int} id - GuestID
        */
        function getGuestFriends(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/get-guest-friends-info/' + id,
                
            });
        }

        /**
         * @param  {object} reservationInfo - date required to create a new reservation
         */
        function createReservation(reservationInfo) {

            return $http({
                method: 'POST',
                data: reservationInfo,
                url: '/rest/guest/create-new-reservation',
                
            });
        }

        /** getProfileDetails (secured)
                * @param {int} id - GuestID
                */
        function getGuestReservations(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/get-guest-reservations/' + id,
                
            });
        }


        /** getProfileDetails (secured)
        * @param {int} id - GuestID
        */
        function getReserve1TheatrePageInfo(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/get-reserve1Theatre-page-info/' + id,
                headers: {
                    'Authorization': sessionService.getAuthToken()

                }
            });
        }
        
        /** getProfileDetails (secured)
         * @param {int} id - GuestID
         */
         function getReserve1CinemaPageInfo(id) {
             return $http({
                 method: 'GET',
                 url: '/rest/guest/get-reserve1Cinema-page-info/' + id,
                 headers: {
                     'Authorization': sessionService.getAuthToken()

                 }
             });
         }

        /** getProfileDetails (secured)
      * @param {int} id - GuestID
      */
        function getReserve2PageInfo(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/get-reserve2-page-info/' + id,
                headers: {
                    'Authorization': sessionService.getAuthToken()

                }
            });
        }
        
        
        function getReservationDetails(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/get-reservation-details/' + sessionService.getUserInfo().userId + '/' + id,
                
            });
        }

        function cancelReservation(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/cancel-reservation/' + sessionService.getUserInfo().userId + '/' + id,
               
            });
        }


        function confirmAttendance(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/confirm-attendance/' + sessionService.getUserInfo().userId + '/' + id,
               
            });
        }

        function cancelAttendance(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/cancel-attendance/' + sessionService.getUserInfo().userId + '/' + id,
               
            });
        }

        function updateReservationOrders(id, guestOrders) {
            return $http({
                method: 'POST',
                data: guestOrders,
                url: '/rest/guest/update-reservation-orders/' + sessionService.getUserInfo().userId + '/' + id,
               
            });
        }


        function addPeople(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/add-people/' + id,
                
            });
        }

        function acceptPeople(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/accept-people/' + id,
               
            });
        }


        function sendFriendshipRequest(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/send-friendship-request/' + id + '/' + sessionService.getUserInfo().userId,
              
            });
        }

        function acceptFriendshipRequest(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/accept-friendship-request/' + id + '/' + sessionService.getUserInfo().userId,
              
            });
        }



        function rejectFriendshipRequest(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/reject-friendship-request/' + id + '/' + sessionService.getUserInfo().userId,
               
            });
        }

        
        function deleteFriend(id) {
            return $http({
                method: 'GET',
                url: '/rest/guest/delete-friend/' + id + '/' + sessionService.getUserInfo().userId,
               
            });
        }


    }

})();
