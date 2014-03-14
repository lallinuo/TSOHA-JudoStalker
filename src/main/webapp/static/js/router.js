/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
judoStalker.config(function($stateProvider, $urlRouterProvider) {
    $stateProvider
    .state('kayttajat', {
        url: "/kayttajat",
        templateUrl: "/static/partials/kayttajat.html",
        controller: 'kayttajaCtrl'
    })

    .state('judokat', {
        url: "/judokat/",
        templateUrl: "/static/partials/judoka/judokat.html",
        controller: 'judokatCtrl'
    })
    
    .state('judoka',{
        url: "/judokat/:id",
        templateUrl:"/static/partials/judoka/judoka.html",
        controller: 'judokaCtrl'
    })
    
    .state('lisaajudoka',{
        url: "/judoka/lisaa",
        templateUrl:"/static/partials/judoka/lisaajudoka.html",
        controller: 'uusiJudokaCtrl'
    })
    .state('muokkaa_judokaa',{
        url:"/judokat/:id/editoi",
        templateUrl:"/static/partials/judoka/editjudoka.html",
        controller: 'judokaCtrl'
    })
    
    .state('tekniikat', {
        url: "/tekniikat",
        templateUrl: "/static/partials/tekniikka/tekniikat.html",
        controller: 'tekniikatCtrl'
    })
    .state('tekniikka',{
        url: "/tekniikat/:id",
        templateUrl:"/static/partials/tekniikka/tekniikka.html",
        controller: 'tekniikkaCtrl'
    })
    .state('lisaatekniikka',{
        url:"/tekniikka/lisaa",
        templateUrl:"/static/partials/tekniikka/lisaatekniikka.html",
        controller: 'uusiTekniikkaCtrl'
    })
});

