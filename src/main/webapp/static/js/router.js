judoStalker.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("kirjaudu");
    
    $stateProvider
    .state('rekisteroidy',{
        resolve:{
            kirjautuminen : function(kayttajaService){
                return kayttajaService.haeKayttaja()
            }
        },
        url:"/rekisteroidy",
        views:{
            content: {
                templateUrl: "/static/partials/rekisteroidy.html",
                controller: 'rekisterointiCtrl'

            }
     
        }
    })

    .state('login',{
        resolve:{
            kirjautuminen : function(kayttajaService){
                return kayttajaService.haeKayttaja()
            }
        },
        url:"/kirjaudu",
        views:{
            content: {
                templateUrl: "/static/partials/login.html",
                controller: 'loginCtrl'

            }
     
        }
    })
    .state('kayttajat', {
        resolve:{
            kirjautuminen : function(kayttajaService){
                return kayttajaService.haeKayttaja()
            }
        },
        url: "/kayttajat",
        views:{
            content: {
                templateUrl: "/static/partials/kayttajat.html",
                controller: "kayttajatCtrl"
               
            },
            nav: {
                templateUrl: "/static/partials/nav.html",
                controller: 'loginCtrl'

            }
        }
    })

    .state('judokat', {
        resolve:{
            kirjautuminen : function(kayttajaService){
                return kayttajaService.haeKayttaja()
            }
        },
        url: "/judokat/",
        views:{
            content: {
                templateUrl: "/static/partials/judoka/judokat.html",
                controller: 'judokatCtrl'
            },
            nav:{
                templateUrl: "/static/partials/nav.html",
                controller: 'loginCtrl'
            }
       
        }
    })
    
    .state('judoka',{
        resolve:{
            kirjautuminen : function(kayttajaService){
                return kayttajaService.haeKayttaja()
            }
        },
        url: "/judokat/:id",
        views:{
            content: {
                templateUrl:"/static/partials/judoka/judoka.html",
                controller: 'judokaCtrl'
            },
            nav:{
                templateUrl: "/static/partials/nav.html",
                controller: 'loginCtrl'
            }
        }
      
    })
    
    .state('kayttaja',{
        resolve:{
            kirjautuminen : function(kayttajaService){
                return kayttajaService.haeKayttaja()
            }
        },
        url: "/kayttajat/:id",
        views:{
            content: {
                templateUrl:"/static/partials/kayttaja/kayttaja.html",
                controller: 'kayttajaCtrl'
            },
            nav:{
                templateUrl: "/static/partials/nav.html",
                controller: 'loginCtrl'
            }
        }
      
    })
    
    .state('lisaajudoka',{
        resolve:{
            kirjautuminen : function(kayttajaService){
                return kayttajaService.haeKayttaja()
            }
        },
        url: "/judoka/lisaa",
        views:{
            content: {
                templateUrl:"/static/partials/judoka/lisaajudoka.html",
                controller: 'uusiJudokaCtrl'
            },
            nav:{
                templateUrl: "/static/partials/nav.html",
                controller: 'loginCtrl'
            }
        }
    })
    .state('muokkaa_judokaa',{
        resolve:{
            kirjautuminen : function(kayttajaService){
                return kayttajaService.haeKayttaja()
            }
        },
        url:"/judokat/:id/editoi",
        views:{
            content: {
                templateUrl:"/static/partials/judoka/editjudoka.html",
                controller: 'judokaCtrl'
            },
            nav:{
                templateUrl: "/static/partials/nav.html",
                controller: 'loginCtrl'
            }
        }
    })
    
    .state('tekniikat', {
        resolve:{
            kirjautuminen : function(kayttajaService){
                return kayttajaService.haeKayttaja()
            }
        },
        url: "/tekniikat",
        views:{
            content: {
                templateUrl: "/static/partials/tekniikka/tekniikat.html",
                controller: 'tekniikatCtrl'
            },
            nav:{
                templateUrl: "/static/partials/nav.html",
                controller: 'loginCtrl'
            }
        }
    })
    .state('tekniikka',{
        resolve:{
            kirjautuminen : function(kayttajaService){
                return kayttajaService.haeKayttaja()
            }
        },
        url: "/tekniikat/:id",
        views:{
            content: {
                templateUrl:"/static/partials/tekniikka/tekniikka.html",
                controller: 'tekniikkaCtrl'
            },
            nav:{
                templateUrl: "/static/partials/nav.html",
                controller: 'loginCtrl'
            }
        }
    })
    .state('lisaatekniikka',{
        resolve:{
            kirjautuminen : function(kayttajaService){
                return kayttajaService.haeKayttaja()
            }
        },
        url:"/tekniikka/lisaa",
        views:{
            content: {
                templateUrl:"/static/partials/tekniikka/lisaatekniikka.html",
                controller: 'uusiTekniikkaCtrl'
            },
            nav:{
                templateUrl: "/static/partials/nav.html",
                controller: 'loginCtrl'
            }
        }
    })
});

