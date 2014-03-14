judoStalkerServices.factory('Kayttaja', ['$resource', function($resource) {
    return $resource('/kayttaja',{},{
        });
}]);


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
        return $resource('kommentti/:judoka/:id',{
            id:"@id"
        },{
            judokanKommentit :{
                method : 'GET', isArray: true, params:{judoka:"judoka"}
            },
            put: {
                method:'PUT'
            }
        })
}]);


   

