/**
 * IFFE is used to avoid dirtying global namespace and or potential conflicts.
 * EACH module, controller, service should be contained within its own scope by wrapping it within an IIFE.
 * .$inject is used to specify required DependencyInjection; for code to remain functional after minification/obfuscation.
 */
(function () {
	'use strict';

	angular.module('bioskopPoy', ['ngRoute', 'cgNotify', 'ui.router', 'ui.bootstrap', 'chart.js', 'dndLists', 'ui.bootstrap.datetimepicker']).config(function ($stateProvider, $urlRouterProvider, $httpProvider) {

		$stateProvider
			.state('login', {
				url: '/login',
				controller: 'LoginController',
				templateUrl: 'pages/user/login.html',
			})
			.state('register', {
				url: '/guest/register',
				templateUrl: 'pages/guest/register/registerGuest.html',
				controller: 'RegisterGuestController'
			})
			.state('guest-profile', {
				url: '/guest/profile-page',
				templateUrl: 'pages/guest/profilePage/profilePage.html',
				controller: 'ProfilePageController'
			})
			.state('guest-confirm/:guestId', {
				url: '/guest/confirm-registration/:guestId',
				templateUrl: 'pages/guest/confirmRegistration/confirmRegistration.html',
				controller: 'ConfirmRegistrationController'
			})
			.state('guest-home', {
				url: '/guest/home-page',
				templateUrl: 'pages/guest/homePage/homePage.html',
				controller: 'HomePageController'
			})
			.state('guest-reservations', {
				url: '/guest/myReservations-page',
				templateUrl: 'pages/guest/myReservationsPage/myReservationsPage.html',
				controller: 'MyReservationsPageController'
			})
			.state('guest-friends', {
				url: '/guest/friends-page',
				templateUrl: 'pages/guest/friendsPage/friendsPage.html',
				controller: 'FriendsPageController'
			})
			.state('guest-cinemas', {
				url: '/guest/cinemas-page',
				templateUrl: 'pages/guest/cinemasPage/cinemasPage.html',
				controller: 'CinemasPageController'
			})
			.state('guest-theatres', {
				url: '/guest/theatres-page',
				templateUrl: 'pages/guest/theatresPage/theatresPage.html',
				controller: 'TheatresPageController'
			})
			.state('reserve1Theatre/:theatreId', {
				url: '/guest/reserve1Theatre-page/:theatreId',
				templateUrl: 'pages/guest/reservePage/reserve1TheatrePage.html',
				controller: 'Reserve1TheatrePageController'
			})
			.state('reserve1Cinema/:cinemaId', {
				url: '/guest/reserve1Cinema-page/:cinemaId',
				templateUrl: 'pages/guest/reservePage/reserve1CinemaPage.html',
				controller: 'Reserve1CinemaPageController'
			})
			.state('reserve2', {
				url: '/guest/reserve2-page',
				templateUrl: 'pages/guest/reservePage/reserve2Page.html',
				controller: 'Reserve2PageController'
			})
			.state('reserve3', {
				url: '/guest/reserve3-page',
				templateUrl: 'pages/guest/reservePage/reserve3Page.html',
				controller: 'Reserve3PageController'
			})
			.state('reservationDetails/:reservationId', {
				url: '/guest/reservation-details/:reservationId',
				templateUrl: 'pages/guest/reservationDetails/reservationDetails.html',
				controller: 'ReservationDetailsController'
			});

	}).run(run);

	// required DependencyInjection
	run.$inject = ["$rootScope", "$location", '$state', 'sessionService'];

	function run($rootScope, $location, sessionService) {
		console.log("Application ready to go!");

		if (sessionService.getUserInfo === null && $location.path() !== 'pages/guest/confirm-registration') {
			$state.go('login');
		}
	$rootScope.$on("$routeChangeStart", function (event, next, current) {
            if (sessionService().getUserInfo() === null) {
                // no logged user, we should be going to #login
                if (next.templateUrl !== "pages/user/login.html" && next.templateUrl !== "pages/guest/register/registerGuest.html" && next.templateUrl !== "pages/guest/confirmRegistration/confirmRegistration.html") {
                    console.log("Not logged in! Redirecting to login...");
                    // not going to #login, we should redirect now
                    $state.go('login');
                }
            }
        });

	}

})();