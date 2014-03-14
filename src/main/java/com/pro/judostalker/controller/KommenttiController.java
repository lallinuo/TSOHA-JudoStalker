/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pro.judostalker.controller;

import com.pro.judostalker.dao.KommenttiDAO;
import com.pro.judostalker.model.Kommentti;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Lalli
 */
@Controller
public class KommenttiController {

    @Autowired
    private KommenttiDAO kommenttiDAO;

    @RequestMapping(value = "/kayttaja/{id}/kommentit", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Kommentti> haeKaikkiKayttajanKommentit(@PathVariable int kayttajaId) throws SQLException {
        return kommenttiDAO.haeKayttajanKommentit(kayttajaId);

    }

    @RequestMapping(value = "/kommentti/judoka/{judokaId}", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Kommentti> haeKaikkiJudokanKommentit(@PathVariable int judokaId) throws SQLException {
        return kommenttiDAO.haeKaikkiJudokanKommentit(judokaId);
    }

    @RequestMapping(value = "/kommentti", method = RequestMethod.POST)
    public @ResponseBody
    Kommentti lisaaKommentti(@RequestBody Kommentti kommentti) throws SQLException {

        return kommenttiDAO.lisaaKommentti(kommentti);
    }

    @RequestMapping(value = "/kommentti", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Kommentti> haeKaikkiKommentit() throws SQLException {

        return kommenttiDAO.haeKaikkiKommentit();
    }

    @RequestMapping(value = "kommentti/{kommenttiId}", method = RequestMethod.GET)
    public @ResponseBody
    Kommentti haeKommentti(@PathVariable int kommenttiId) throws SQLException {

        return kommenttiDAO.haeKommentti(kommenttiId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/kommentti/{kommenttiId}", method = RequestMethod.DELETE)
    public void poistaKommentti(@PathVariable int kommenttiId) throws SQLException {
        kommenttiDAO.poistaKommentti(kommenttiId);
    }

    @RequestMapping(value = "/kommentti/{id}", method = RequestMethod.PUT)
    public @ResponseBody void paivitaKommentti(@RequestBody Kommentti kommentti) throws SQLException {
        kommenttiDAO.paivitaKommenti(kommentti);
    }


}
