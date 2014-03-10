var myId;
var judoStalker = angular.module('judoStalker', ['ui.router',
    'judoStalkerControllers']);
var judoStalkerControllers = angular.module('judoStalkerControllers', ['judoStalkerService'])
var judoStalkerServices = angular.module('judoStalkerService', ['ngResource']);
judoStalker.config(function($stateProvider, $urlRouterProvider) {
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
        url: "judoka/lisaa",
        templateUrl:"TSOHA-JudoStalker/static/partials/lisaajudoka.html",
        controller: 'uusiJudokaCtrl'
    })
    .state('muokkaa_judokaa',{
        url:"/judokat/:id/editoi",
        templateUrl:"TSOHA-JudoStalker/static/partials/editjudoka.html",
        controller: 'judokaCtrl'
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

judoStalkerServices.factory('Kayttaja', ['$resource', function($resource) {
    return $resource('TSOHA-JudoStalker/kayttaja',{},{
        });
}]);

judoStalkerServices.factory('Judoka', ['$resource', function($resource) {
    return $resource('TSOHA-JudoStalker/judoka/:id',{
        id: "@id"
    },{
        'put' : {
            method: 'PUT'
        }
    });
}]);

judoStalkerServices.factory('Tekniikka', ['$resource', function($resource) {
    return $resource('TSOHA-JudoStalker/tekniikka',{},{});
}]);

judoStalkerServices.factory('Kommentti', ['$resource', function($resource) {
    return $resource('TSOHA-JudoStalker/kommentti/:id',{id: "@id"},{});
}]);


judoStalkerServices.factory('JudokaKommentit', ['$resource', function($resource) {
    return $resource('TSOHA-JudoStalker/judoka/:id/kommentit',{
        id: "@id"
    },{});
}]);



judoStalkerControllers.controller('judokatCtrl',["$scope","Judoka", function($scope,Judoka){
    $scope.judokat = Judoka.query();
}]);

judoStalkerControllers.controller('judokaCtrl',["$scope","Judoka","$stateParams","$state","$location","JudokaKommentit","Kommentti",
    function($scope,Judoka,$stateParams,$state,$location,JudokaKommentit,Kommentti){
        $scope.judoka = Judoka.get({},{
            id: $stateParams.id
        });
    
        $scope.kommentit = JudokaKommentit.query({},{
            id:$stateParams.id
        });
        
        $scope.edit = function(){
            $scope.judoka.$put(function(){
                $location.path("judokat/"+$scope.judoka.id)
            });
        
        }
    
        $scope.poistaJudoka = function(){
            if(confirm("Haluatko varmasti poistaa judokan?")){
                $scope.judoka.$delete();
                $state.go('judokat');
            }
        }
        $scope.kommentoi = function(){
        
            $scope.kommentti.kayttajaid = myId;
            $scope.kommentti.judokaid = $scope.judoka.id;
            Kommentti.save(JSON.stringify($scope.kommentti),function(data){
                alert(data);
            });
            console.log(JSON.stringify($scope.kommentti))
        }
    }]);


judoStalkerControllers.controller('uusiJudokaCtrl',["$scope","Judoka","$location","$state", function($scope,Judoka,$location,$state){
    $scope.lisaaJudoka = function(){ 
        Judoka.save(JSON.stringify($scope.form),function(data){
            $location.path("judokat/"+data.id);
        });
    }
}]);

judoStalkerControllers.controller('kayttajaCtrl',["$scope","Kayttaja", function($scope,Kayttaja){
    $scope.kayttajat = Kayttaja.query();
}]);

judoStalkerControllers.controller('tekniikkaCtrl',["$scope","Tekniikka","$stateParams", function($scope,Tekniikka){
    $scope.tekniikat = Tekniikka.query();
}]);



judoStalkerControllers.controller('loginCtrl', ["$scope", "$http", "Kayttaja","$resource", function($scope, $http, Kayttaja,$resource) {
    $resource("TSOHA-JudoStalker/onKirjautunut").get(function(data){
        myId = data[0];
        $scope.kirjautuminen = data.kirjautunut != data[0];
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
            if (data.id) {
                $scope.kayttaja = data;
                $scope.kirjautuminen = true;
                myId = data.id;
                console.log(myId)
            } else {
                $scope.error = "Väärä käyttäjätunnus tai salasana";
            }
        });
    };
}]);
    