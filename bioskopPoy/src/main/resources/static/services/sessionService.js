/**
*  @author Jelena Jankovic RA139-2013
 * Abstract away localStorage manipulation, all controllers should use this
 * instead of handling localStorage directly 
 */
(function() {
    'use strict';

    angular
        .module('bioskopPoy')
        .service('sessionService', sessionService);
    
    function sessionService() {

        return {
            getUserInfo: getUserInfo,
            setUserInfo: setUserInfo, 
            getReservationInfo: getReservationInfo,
            setReservationInfo: setReservationInfo
        };

        
       
     

        /**
         * Stores userInfo as a JSON string within localStorage , if null is specified, deletes userId from localStorage
         * @param  {UserInfo} userInfo
         */
        function setUserInfo(userInfo) {
            if(userInfo === null) {
                localStorage.removeItem('userInfo');
                return;
            }
            localStorage.setItem('userInfo', JSON.stringify(userInfo));
        }

         /**
         * Stores userInfo , if null is specified, deletes userInfo from localStorage
         * 
         * @param  {UserInfo} userInfo as an object
         */
        function getUserInfo() {
            let value = localStorage.getItem('userInfo');
            if(value == null) {
                return null;
            }
            return JSON.parse(value);
        }


        /**
         * Stores userInfo , if null is specified, deletes userInfo from localStorage
         * 
         * @param  {UserInfo} userInfo as an object
         */
        function getReservationInfo() {
            let value = localStorage.getItem('reservationInfo');
            if(value === null) {
                return {
                    guestId: getUserInfo().userId,
                    name: "",
                    date: new Date(),
                    arrival: new Date(),
                    duration: 0,
                    tables: [],
                    invitedFriends: [], 
                    restaurantId: 0
                };
            }
            return JSON.parse(value);
        }


        /**
         *    @typedef ReservationInfo
         *    @type {object}
         *    @property {string} role - user role 
         *    @property {long} userId - user ID
         */

        /**
         * Stores userInfo , if null is specified, deletes userInfo from localStorage
         * 
         * @param  {ReservationInfo} reservationInfo as an object
         */
        function setReservationInfo(reservationInfo) {
            if(reservationInfo === null) {
                reservationInfo = {
                    guestId: getUserInfo().userId,
                    name: "",
                    date: new Date(),
                    arrival: new Date(),
                    duration: 0,
                    tables: [],
                    invitedFriends: [], 
                    restaurantId: 0
                };
            }
            localStorage.setItem('reservationInfo', JSON.stringify(reservationInfo));
        }

    }

})();