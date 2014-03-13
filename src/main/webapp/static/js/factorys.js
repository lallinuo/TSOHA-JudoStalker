
judoStalkerServices.factory('Judoka', ['$resource', function($resource) {
    return $resource('/judoka/:id',{
        id: "@id"
    },{
        'put' : {
            method: 'PUT'
        }
    });
}]);

judoStalkerServices.factory('Tekniikka', ['$resource', function($resource) {
    return $resource('/tekniikka',{},{});
}]);

judoStalkerServices.factory('Kommentti', ['$resource', function($resource) {
    return $resource('/kommentti/:id',{
        id: "@id"
    },{});
}]);


judoStalkerServices.factory('JudokaKommentit', ['$resource', function($resource) {
    return $resource('/judoka/:id/kommentit',{
        id: "@id"
    },{});
}]);
