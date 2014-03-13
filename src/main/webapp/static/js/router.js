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
        templateUrl: "/static/partials/judokat.html",
        controller: 'judokatCtrl'
    })
    
    .state('judoka',{
        url: "/judokat/:id",
        templateUrl:"/static/partials/judoka.html",
        controller: 'judokaCtrl'
    })
    
    .state('lisaajudoka',{
        url: "/judoka/lisaa",
        templateUrl:"/static/partials/lisaajudoka.html",
        controller: 'uusiJudokaCtrl'
    })
    .state('muokkaa_judokaa',{
        url:"/judokat/:id/editoi",
        templateUrl:"/static/partials/editjudoka.html",
        controller: 'judokaCtrl'
    })
    
    .state('tekniikat', {
        url: "/tekniikat",
        templateUrl: "/static/partials/tekniikat.html",
        controller: 'tekniikkaCtrl'
    })
    .state('tekniikka',{
        url: "/tekniikat/:id",
        templateUrl:"/static/partials/tekniikka.html",
        controller: 'tekniikkaCtrl'
    })
});

