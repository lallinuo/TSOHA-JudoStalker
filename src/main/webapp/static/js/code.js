var judoStalker = angular.module('judoStalker', ['ui.router',
    'judoStalkerControllers']);
var judoStalkerControllers = angular.module('judoStalkerControllers', ['judoStalkerService'])
var judoStalkerServices = angular.module('judoStalkerService', ['ngResource']);

judoStalkerServices.service("kayttajaService",["$http",function($http){
    this.kayttaja = null;
    this.haeKayttaja = function(){
        if(this.kayttaja != null){
            return this.kayttaja;
        }else{
            return $http({
                method:'GET',
                url:'/onKirjautunut'
            }).then(function(data){
                if(data.data =="false"){
                    return false;
                }else{
                    this.kayttaja = 1;
                    return data.data;
                }
            })
             
        }
    }
    
}])

judoStalkerControllers.controller('judokatCtrl', ["$scope", "Judoka","kirjautuminen", function($scope, Judoka) {
 
    $scope.judokat = Judoka.query();
}]);

judoStalkerControllers.controller('judokaCtrl', ["$scope", "Judoka", "$stateParams", "$state", "$location", "Kommentti", "Tekniikka","kirjautuminen",
    function($scope, Judoka, $stateParams, $state, $location, Kommentti, Tekniikka,kirjautuminen) {
        $scope.judoka = Judoka.get({}, {
            id: $stateParams.id
        });

        $scope.tekniikat = Tekniikka.query();

        $scope.judokanTekniikat = Tekniikka.haeJudokanTekniikat({
            id: $scope.judoka.id
        });
        $scope.kommentit = Kommentti.judokanKommentit({
            id: $scope.judoka.id
        }, {
            id: $stateParams.id
        });

        $scope.edit = function() {
            $scope.judoka.$put(function() {
                $location.path("judokat/" + $scope.judoka.id)
            });

        }

        $scope.poistaJudoka = function() {
            if (confirm("Haluatko varmasti poistaa judokan?")) {
                $scope.judoka.$delete();
                $state.go('judokat');
            }
        }
        $scope.kommentoi = function() {

            $scope.kommentti.kayttajaId = kirjautuminen;
            $scope.kommentti.judokaId = $scope.judoka.id;
            Kommentti.save(JSON.stringify($scope.kommentti), function(data) {
                $scope.message = "Viesti lähetetty";
                $scope.kommentti.kommentti = ""
                $scope.kommentit.push(data);
            })
        }
        $scope.poistaKommentti = function(kommentti) {
            kommentti.$delete();
            $scope.kommentit.splice($scope.kommentit.indexOf(kommentti), 1);
        }
        $scope.avaaEditointi = function(kommentti) {
            kommentti.editointi = "true";
        }
        $scope.editoi = function(kommentti) {
            delete kommentti["editointi"];
            kommentti.$put();
        }

        $scope.lisaaTekniikka = function(judoka) {
            console.log($scope.valittuTekniikka);
            if ($scope.judokanTekniikat.indexOf($scope.valittuTekniikka) == -1) {
                Tekniikka.lisaaTekniikkaJudokalle({
                    id: $scope.valittuTekniikka.id,
                    judoka: judoka.id
                }, function() {
                    $scope.judokanTekniikat.push($scope.valittuTekniikka)
                })
            }else{
                alert("Judokalla on jo tämä tekniikka");
            }
        }

        $scope.poistaTekniikka = function(tekniikka, judoka) {
            tekniikka.$poistaTekniikkaJudokalta({
                judoka: judoka.id, 
                id: tekniikka.id
            }, function() {
                $scope.judokanTekniikat.splice($scope.judokanTekniikat.indexOf(tekniikka), 1);
            });

        }

    }]);

judoStalkerControllers.controller('rekisterointiCtrl', ["$scope", "Kayttaja", "$location", function($scope, Kayttaja, $location) {
    $scope.rekisteroidy = function() {
        Kayttaja.save(JSON.stringify($scope.form),function(data){
            if(data.error){
                $scope.error = "Käyttäjänimi on jo käytössä";
            }else{
                $location.path("/kirjaudu")
            }
        });
    }
}]);

judoStalkerControllers.controller('kayttajaCtrl',["$scope","Kayttaja","$stateParams","kirjautuminen","$http","$location",function($scope,Kayttaja,$stateParams,kirjautuminen,$http,$location){
    $scope.kayttaja = Kayttaja.get({
        id:$stateParams.id
        })
    console.log(kirjautuminen);
    if($stateParams.id == kirjautuminen){
        $scope.poisto = true;
    }
    
    $scope.poistaKayttaja = function(){
        $scope.kayttaja.$delete();
        $http({
            method: 'GET',
            url: '/'
        }).success(function() {
            $location.path("kirjaudu/")
        })
    }
}]);

judoStalkerControllers.controller('uusiJudokaCtrl', ["$scope", "Judoka", "$location", function($scope, Judoka, $location) {
    $scope.lisaaJudoka = function() {
        Judoka.save(JSON.stringify($scope.form), function(data) {
            $location.path("judokat/" + data.id);
        });
    }
}]);

judoStalkerControllers.controller('kayttajatCtrl', ["$scope", "Kayttaja", function($scope, Kayttaja) {
    $scope.kayttajat = Kayttaja.query();
}]);

judoStalkerControllers.controller('tekniikatCtrl', ["$scope", "Tekniikka", function($scope, Tekniikka) {
    $scope.tekniikat = Tekniikka.query();
}]);

judoStalkerControllers.controller('tekniikkaCtrl', ["$scope", "Tekniikka", "$stateParams","Judoka", function($scope, Tekniikka, $stateParams,Judoka) {
    $scope.tekniikka = Tekniikka.get({
        id: $stateParams.id
    });
        
    $scope.judokat = Judoka.haeTekniikkaaKayttavatJudokat({
        id: $stateParams.id
    })
}]);

judoStalkerControllers.controller('uusiTekniikkaCtrl', ["$scope", "Tekniikka", "$location", function($scope, Tekniikka, $location) {
    $scope.lisaaTekniikka = function() {
        Tekniikka.save(JSON.stringify($scope.form), function(data) {
            $location.path("tekniikat/" + data.id);
        })
    }
}]);




judoStalkerControllers.controller('loginCtrl', ["$scope", "$http", "Kayttaja", "$resource","$location","kirjautuminen", function($scope, $http, Kayttaja, $resource,$location,kirjautuminen) {
    $scope.kirjautuminen = kirjautuminen;
    $scope.logout = function() {
        
        $http({
            method: 'GET',
            url: '/logout'
        }).success(function() {
            $location.path("kirjaudu/")
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
                $location.path("judokat/")
            } else {
                $scope.error = "Väärä käyttäjätunnus tai salasana";
            }
        });
    };
}]);
    