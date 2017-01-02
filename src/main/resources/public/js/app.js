angular.module('app', ['ngRoute', 'ngMaterial'])
    .config(function ($routeProvider, $httpProvider) {

        $routeProvider.when('/', {
            templateUrl: 'ingredient-search-view.html',
            controller: 'ingredient-search-controller'
        }).when('/login', {
            templateUrl: 'login.html',
            controller: 'navigation'
        }).when('/ingredient-search-view', {
            templateUrl: 'ingredient-search-view.html',
            controller: 'ingredient-search-controller'
        }).otherwise('/');

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

    })
    .controller('ingredient-search-controller', function ($scope, $http, $log) {
        $scope.recipes =[];
        var socket = new SockJS('/websocket');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/recipereply', function (reply) {
                 $log.debug("replied " + JSON.parse(reply.body).searchId)
                 $scope.recipes =[]
                 JSON.parse(reply.body).recipes.forEach(function(recipe){
                 $scope.recipes.push(recipe);
                 })
            });
        });
        $scope.greeting2 = 'Ingredient';
        $scope.search = function (ingredient) {;
            $log.debug("searching for " + ingredient);
            stompClient.send("/app/search2", {}, JSON.stringify({'ingredient': ingredient}));
        };
            $scope.cancel = function () {
                $log.debug("search cancel");
                $scope.search ='';
            };

    })
    .controller('navigation', function ($scope, $location, $log) {
        $scope.currentNavItem = 'home';

        $scope.$watch('currentNavItem', function (current, old) {
            $location.url("/" + current)
        });
    })
    .config(function($mdThemingProvider) {
      $mdThemingProvider.theme('dark-grey').backgroundPalette('grey').dark();
      $mdThemingProvider.theme('dark-orange').backgroundPalette('orange').dark();
      $mdThemingProvider.theme('dark-purple').backgroundPalette('deep-purple').dark();
      $mdThemingProvider.theme('dark-blue').backgroundPalette('blue').dark();
    });
