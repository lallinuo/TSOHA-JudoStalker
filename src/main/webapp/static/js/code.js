var judoStalker = angular.module('judoStalker', ['ui.router',
    'judoStalkerControllers']);
judoStalker.config(function($stateProvider, $urlRouterProvider) {
    //
    // For any unmatched url, redirect to /state1
    //
    // Now set up the states
    $stateProvider
    .state('kayttajat', {
        url: "/kayttajat",
        templateUrl: "TSOHA-JudoStalker/static/partials/kayttajat.html",
        controller: 'kayttajaCtrl'
    })

    .state('judokat', {
        url: "/judokat/",
        templateUrl: "TSOHA-JudoStalker/static/partials/judokat.html",
        controller: 'judokatCtrl'
    })
    
    .state('judoka',{
        url: "/judokat/:id",
        templateUrl:"TSOHA-JudoStalker/static/partials/judoka.html",
        controller: 'judokaCtrl'
    })
    
    .state('lisaajudoka',{
        url: "lisaajudoka",
        templateUrl:"TSOHA-JudoStalker/static/partials/lisaajudoka.html",
        controller: 'uusiJudokaCtrl'
    })
    
    .state('tekniikat', {
        url: "/tekniikat",
        templateUrl: "TSOHA-JudoStalker/static/partials/tekniikat.html",
        controller: 'tekniikkaCtrl'
    })
    .state('tekniikka',{
        url: "/tekniikat/:id",
        templateUrl:"TSOHA-JudoStalker/static/partials/tekniikka.html",
        controller: 'tekniikkaCtrl'
    })
});
var judoStalkerControllers = angular.module('judoStalkerControllers', ['judoStalkerService'])
var judoStalkerServices = angular.module('judoStalkerService', ['ngResource']);

judoStalkerServices.factory('Kayttaja', ['$resource', function($resource) {
    return $resource('TSOHA-JudoStalker/kayttaja',{},{
        });
}]);

judoStalkerServices.factory('Judoka', ['$resource', function($resource) {
    return $resource('TSOHA-JudoStalker/judoka/:id',{
        id: "@id"
    });
}]);

judoStalkerServices.factory('Tekniikka', ['$resource', function($resource) {
    return $resource('TSOHA-JudoStalker/tekniikka',{},{});
}]);


judoStalkerControllers.controller('loginCtrl', ["$scope", "$http", "Kayttaja","$resource", function($scope, $http, Kayttaja,$resource) {
    
   

    $resource("TSOHA-JudoStalker/onKirjautunut").get(function(data){
        $scope.kirjautuminen = data.kirjautunut;
    })
 
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
    
judoStalkerControllers.controller('judokatCtrl',["$scope","Judoka", function($scope,Judoka){
    $scope.judokat = Judoka.query();
}]);

judoStalkerControllers.controller('judokaCtrl',["$scope","Judoka","$stateParams", function($scope,Judoka,$stateParams){
    $scope.judoka = Judoka.get({},{
        id: $stateParams.id
    });
}]);

judoStalkerControllers.controller('uusiJudokaCtrl',["$scope","Judoka","$stateParams", function($scope,Judoka,$stateParams){
        $scope.lisaaJudoka = function(){
            console.log($scope.form)
        }
}]);

judoStalkerControllers.controller('kayttajaCtrl',["$scope","Kayttaja", function($scope,Kayttaja){
    $scope.kayttajat = Kayttaja.query();
}]);

judoStalkerControllers.controller('tekniikkaCtrl',["$scope","Tekniikka","$stateParams", function($scope,Tekniikka){
    $scope.tekniikat = Tekniikka.query();
}]);
