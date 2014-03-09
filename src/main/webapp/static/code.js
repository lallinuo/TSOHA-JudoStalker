var judoStalker = angular.module('judoStalker', ['ui.router',
    'judoStalkerControllers']);
judoStalker.config(function($stateProvider, $urlRouterProvider) {
//
// For any unmatched url, redirect to /state1
    $urlRouterProvider.otherwise("/state1");
    //
    // Now set up the states
    $stateProvider
            .state('kayttajat', {
        url: "/kayttajat",
        templateUrl: "TSOHA-JudoStalker/css/partials/kayttajat.html"
    })

            .state('state2', {
        url: "/state2",
        templateUrl: "css/partials/state2.html"
    })
            .state('state2.list', {
        url: "/list",
        templateUrl: "partials/state2.list.html",
        controller: function($scope) {
            $scope.things = ["A", "Set", "Of", "Things"];
        }
    });
});
var judoStalkerControllers = angular.module('judoStalkerControllers', [])

var judoStalkerServices = angular.module('judoStalkerService', ['ngResource']);

judoStalkerServices.factory('Kayttaja', ['$resource', function($resource) {
        return $resource('TSOHA-JudoStalker/kayttaja'), {}, {
            query: {method: 'GET'}
        }
    }]);
judoStalkerControllers.controller('loginCtrl', ["$scope", "$http", "Kayttaja", "$resource", function($scope, $http, Kayttaja, $resource) {
        $scope.kirjautuminen = false;
        $scope.logout = function() {
            $http({
                method: 'GET',
                url: 'TSOHA-JudoStalker/logout'
            }).success(function() {
                $scope.kirjautuminen = false;
            })
        }

        $scope.submit = function() {
            $http({
                method: 'POST',
                url: 'TSOHA-JudoStalker/kirjaudu',
                data: $scope.login,
                headers: {'Content-Type': 'application/json'},
            }).success(function(data) {
                console.log(data)
                if (data.id >= 0) {
                    console.log(data);
                    $scope.kayttaja = data;
                    $scope.kirjautuminen = true;
                } else {
                    $scope.error = "Väärä käyttäjänimi tai salasana";
                }
            });
        };
    }]);
