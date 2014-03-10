[33mcommit bdf2c41b3fc0bc54b3ba06d3505e5a23f7fe4d9e[m
Author: Lalli Nuorteva <zuassi@gmail.com>
Date:   Mon Mar 10 18:19:57 2014 +0200

    resource fixd

[1mdiff --git a/src/main/webapp/static/js/code.js b/src/main/webapp/static/js/code.js[m
[1mindex 802c3b8..3935315 100644[m
[1m--- a/src/main/webapp/static/js/code.js[m
[1m+++ b/src/main/webapp/static/js/code.js[m
[36m@@ -45,82 +45,80 @@[m [mvar judoStalkerControllers = angular.module('judoStalkerControllers', ['judoStal[m
 var judoStalkerServices = angular.module('judoStalkerService', ['ngResource']);[m
 [m
 judoStalkerServices.factory('Kayttaja', ['$resource', function($resource) {[m
[31m-        return $resource('TSOHA-JudoStalker/kayttaja',{},{[m
[32m+[m[32m    return $resource('TSOHA-JudoStalker/kayttaja',{},{[m
         });[m
[31m-    }]);[m
[32m+[m[32m}]);[m
 [m
 judoStalkerServices.factory('Judoka', ['$resource', function($resource) {[m
[31m-        return $resource('TSOHA-JudoStalker/judoka/:id',{[m
[31m-            id: "@id"[m
[31m-        });[m
[31m-    }]);[m
[32m+[m[32m    return $resource('TSOHA-JudoStalker/judoka/:id',{[m
[32m+[m[32m        id: "@id"[m
[32m+[m[32m    });[m
[32m+[m[32m}]);[m
 [m
 judoStalkerServices.factory('Tekniikka', ['$resource', function($resource) {[m
[31m-        return $resource('TSOHA-JudoStalker/tekniikka',{},{});[m
[31m-    }]);[m
[32m+[m[32m    return $resource('TSOHA-JudoStalker/tekniikka',{},{});[m
[32m+[m[32m}]);[m
 [m
 [m
 judoStalkerControllers.controller('loginCtrl', ["$scope", "$http", "Kayttaja","$resource", function($scope, $http, Kayttaja,$resource) {[m
     [m
    [m
 [m
[31m-        $resource("TSOHA-JudoStalker/onKirjautunut").get(function(data){[m
[31m-            $scope.kirjautuminen = data.kirjautunut;[m
[31m-        })[m
[32m+[m[32m    $resource("TSOHA-JudoStalker/onKirjautunut").get(function(data){[m
[32m+[m[32m        $scope.kirjautuminen = data.kirjautunut;[m
[32m+[m[32m    })[m
  [m
[31m-        $scope.logout = function() {[m
[31m-            $http({[m
[31m-                method: 'GET',[m
[31m-                url: 'TSOHA-JudoStalker/logout'[m
[31m-            }).success(function() {[m
[31m-                $scope.kirjautuminen = false;[m
[31m-            })[m
[31m-        }[m
[32m+[m[32m    $scope.logout = function() {[m
[32m+[m[32m        $http({[m
[32m+[m[32m            method: 'GET',[m
[32m+[m[32m            url: 'TSOHA-JudoStalker/logout'[m
[32m+[m[32m        }).success(function() {[m
[32m+[m[32m            $scope.kirjautuminen = false;[m
[32m+[m[32m        })[m
[32m+[m[32m    }[m
 [m
[31m-        $scope.submit = function() {[m
[31m-            $http({[m
[31m-                method: 'POST',[m
[31m-                url: 'TSOHA-JudoStalker/kirjaudu',[m
[31m-                data: $scope.login,[m
[31m-                headers: {[m
[31m-                    'Content-Type': 'application/json'[m
[31m-                }[m
[31m-            }).success(function(data) {[m
[31m-                if (data.id >= 0) {[m
[31m-                    console.log(data);[m
[31m-                    $scope.kayttaja = data;[m
[31m-                    $scope.kirjautuminen = true;[m
[31m-                } else {[m
[31m-                    $scope.error = "V‰‰r‰ k‰ytt‰j‰tunnus tai salasana";[m
[31m-                }[m
[31m-            });[m
[31m-        };[m
[31m-    }]);[m
[32m+[m[32m    $scope.submit = function() {[m
[32m+[m[32m        $http({[m
[32m+[m[32m            method: 'POST',[m
[32m+[m[32m            url: 'TSOHA-JudoStalker/kirjaudu',[m
[32m+[m[32m            data: $scope.login,[m
[32m+[m[32m            headers: {[m
[32m+[m[32m                'Content-Type': 'application/json'[m
[32m+[m[32m            }[m
[32m+[m[32m        }).success(function(data) {[m
[32m+[m[32m            if (data.id >= 0) {[m
[32m+[m[32m                console.log(data);[m
[32m+[m[32m                $scope.kayttaja = data;[m
[32m+[m[32m                $scope.kirjautuminen = true;[m
[32m+[m[32m            } else {[m
[32m+[m[32m                $scope.error = "V‰‰r‰ k‰ytt‰j‰tunnus tai salasana";[m
[32m+[m[32m            }[m
[32m+[m[32m        });[m
[32m+[m[32m    };[m
[32m+[m[32m}]);[m
     [m
 judoStalkerControllers.controller('judokatCtrl',["$scope","Judoka", function($scope,Judoka){[m
[31m-        $scope.judokat = Judoka.query();[m
[31m-    }]);[m
[32m+[m[32m    $scope.judokat = Judoka.query();[m
[32m+[m[32m}]);[m
 [m
 judoStalkerControllers.controller('judokaCtrl',["$scope","Judoka","$stateParams", function($scope,Judoka,$stateParams){[m
[31m-        $scope.judoka = Judoka.get({},{[m
[31m-            id: $stateParams.id[m
[31m-        });[m
[31m-    }]);[m
[32m+[m[32m    $scope.judoka = Judoka.get({},{[m
[32m+[m[32m        id: $stateParams.id[m
[32m+[m[32m    });[m
[32m+[m[32m}]);[m
 [m
 judoStalkerControllers.controller('uusiJudokaCtrl',["$scope","Judoka","$location","$rootScope", function($scope,Judoka,$location,$rootScope){[m
[31m-        $scope.lisaaJudoka = function(){ [m
[31m-            Judoka.save(JSON.stringify($scope.form),function(data){[m
[31m-                $rootScope.$apply(function(){[m
[31m-                    $location.path("/judokat"); [m
[31m-                });[m
[31m-            });[m
[31m-        }[m
[31m-    }]);[m
[32m+[m[32m    $scope.lisaaJudoka = function(){[m[41m [m
[32m+[m[32m        Judoka.save(JSON.stringify($scope.form),function(data){[m
[32m+[m[32m            $location.path("/judokat/"+data.id)[m
[32m+[m[32m        });[m
[32m+[m[32m    }[m
[32m+[m[32m}]);[m
 [m
 judoStalkerControllers.controller('kayttajaCtrl',["$scope","Kayttaja", function($scope,Kayttaja){[m
[31m-        $scope.kayttajat = Kayttaja.query();[m
[31m-    }]);[m
[32m+[m[32m    $scope.kayttajat = Kayttaja.query();[m
[32m+[m[32m}]);[m
 [m
 judoStalkerControllers.controller('tekniikkaCtrl',["$scope","Tekniikka","$stateParams", function($scope,Tekniikka){[m
[31m-        $scope.tekniikat = Tekniikka.query();[m
[31m-    }]);[m
[32m+[m[32m    $scope.tekniikat = Tekniikka.query();[m
[32m+[m[32m}]);[m
