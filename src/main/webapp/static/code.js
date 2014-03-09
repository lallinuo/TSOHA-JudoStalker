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

    .state('judokat', {
        url: "/judokat",
        templateUrl: "TSOHA-JudoStalker/css/partials/judokat.html"
    })
    .state('tekniikat', {
        url: "/tekniikat",
        templateUrl: "TSOHA-JudoStalker/css/partials/tekniikat.html",
     
    });
});
var judoStalkerControllers = angular.module('judoStalkerControllers', ['judoStalkerService'])
var judoStalkerServices = angular.module('judoStalkerService', ['ngResource']);

judoStalkerServices.factory('Kayttaja', ['$resource', function($resource) {
    return $resource('TSOHA-JudoStalker/kayttaja',{},{
        haeKaikki: {
            method: 'GET',
            isArray:true
        }
    });
}]);

judoStalkerServices.factory('Judoka', ['$resource', function($resource) {
    return $resource('TSOHA-JudoStalker/judoka',{},{
        haeKaikki: {
            method: 'GET',
            isArray:true
        }
    });
}]);

judoStalkerServices.factory('Tekniikka', ['$resource', function($resource) {
    return $resource('TSOHA-JudoStalker/tekniikka',{},{
        haeKaikki: {
            method: 'GET',
            isArray:true
        }
    });
}]);


judoStalkerControllers.controller('loginCtrl', ["$scope", "$http", "Kayttaja", function($scope, $http, Kayttaja) {
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
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function(data) {
            if (data.id >= 0) {
                console.log(data);
                $scope.kayttaja = data;
                $scope.kirjautuminen = true;
            } else {
                $scope.error = "V‰‰r‰ k‰ytt‰j‰tunnus tai salasana";
            }
        });
    };
}]);
    
judoStalkerControllers.controller('judokaCtrl',["$scope","Judoka", function($scope,Judoka){
    $scope.judokat = Judoka.haeKaikki();
    console.log(Judoka.haeKaikki())
}]);

judoStalkerControllers.controller('kayttajaCtrl',["$scope","Kayttaja", function($scope,Kayttaja){
    $scope.kayttajat = Kayttaja.haeKaikki();
    console.log(Kayttaja.haeKaikki())
}]);

judoStalkerControllers.controller('tekniikkaCtrl',["$scope","Tekniikka", function($scope,Tekniikka){
    $scope.tekniikat = Tekniikka.haeKaikki();
    console.log(Tekniikka.haeKaikki())
}]);
