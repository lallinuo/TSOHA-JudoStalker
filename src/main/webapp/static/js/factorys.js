judoStalkerServices.factory('Kayttaja', ['$resource', function($resource) {
        return $resource('/kayttaja/:id', {id:"@id"}, {
        });
    }]);


judoStalkerServices.factory('Judoka', ['$resource', function($resource) {
        return $resource('/judoka/:tekniikka/:id', {
            id: "@id"
        }, {
            put: {
                method: 'PUT'
            },
            haeTekniikkaaKayttavatJudokat: {
                method: 'GET', params: {tekniikka: "tekniikka"}, isArray: true
            }

        });
    }]);

judoStalkerServices.factory('Tekniikka', ['$resource', function($resource) {
        return $resource('/tekniikka/:judoka/:id', {id: "@id", judoka: "@judoka"}, {
            lisaaTekniikkaJudokalle: {
                method: 'POST'
            },
            haeJudokanTekniikat: {
                method: 'GET', isArray: true, params: {judoka: "judoka"}
            },
            poistaTekniikkaJudokalta: {
                method: 'DELETE'
            }
        });
    }]);

judoStalkerServices.factory('Kommentti', ['$resource', function($resource) {
        return $resource('kommentti/:judoka/:id', {
            id: "@id"
        }, {
            judokanKommentit: {
                method: 'GET', isArray: true, params: {judoka: "judoka"}
            },
            put: {
                method: 'PUT'
            }
        })
    }]);




