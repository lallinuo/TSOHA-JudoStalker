var myId;
var judoStalker = angular.module('judoStalker', ['ui.router',
    'judoStalkerControllers']);
var judoStalkerControllers = angular.module('judoStalkerControllers', ['judoStalkerService'])
var judoStalkerServices = angular.module('judoStalkerService', ['ngResource']);

judoStalkerControllers.controller('judokatCtrl',["$scope","Judoka", function($scope,Judoka){
    $scope.judokat = Judoka.query();
}]);

judoStalkerControllers.controller('judokaCtrl',["$scope","Judoka","$stateParams","$state","$location","Kommentti",
    function($scope,Judoka,$stateParams,$state,$location,Kommentti){
        $scope.judoka = Judoka.get({},{
            id: $stateParams.id
        });
        $scope.kommentit = Kommentti.judokanKommentit({
            id : $scope.judoka.id
        },{
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
        
            $scope.kommentti.kayttajaId = myId;
            $scope.kommentti.judokaId= $scope.judoka.id;
            Kommentti.save(JSON.stringify($scope.kommentti),function(data){
                $scope.message = "Viesti lähetetty";
                $scope.kommentti.kommentti = ""
                $scope.kommentit.push(data);
            })
        }
        $scope.poistaKommentti = function(kommentti){
            kommentti.$delete();
            $scope.kommentit.splice($scope.kommentit.indexOf(kommentti),1);
        }
        $scope.avaaEditointi= function(kommentti){
            kommentti.editointi = "true";
        }
        $scope.editoi = function(kommentti){
            delete kommentti["editointi"];
            kommentti.$put();
            
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
    $resource("/onKirjautunut").get(function(data){
        myId = data[0];
        $scope.kirjautuminen = data.kirjautunut != data[0];
    })
 
    $scope.logout = function() {
        $http({
            method: 'GET',
            url: '/logout'
        }).success(function() {
            $scope.kirjautuminen = false;
        })
    }
    $scope.submit = function() {
        $http({
            method: 'POST',
            url: '/kirjaudu',
            data: $scope.login,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function(data) {
            if (data.id) {
                $scope.kayttaja = data;
                $scope.kirjautuminen = true;
                myId = data.id;
            } else {
                $scope.error = "Väärä käyttäjätunnus tai salasana";
            }
        });
    };
}]);
    