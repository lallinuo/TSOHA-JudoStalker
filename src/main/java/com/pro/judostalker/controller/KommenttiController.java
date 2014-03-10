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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/judoka/{judokaId}/kommentit", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Kommentti> haeKaikkiJudokanKommentit(@PathVariable int judokaId) throws SQLException {
        return kommenttiDAO.haeKaikkiJudokanKommentit(judokaId);
    }

    @RequestMapping(value = "/kommentti", method = RequestMethod.POST)
    public void lisaaKommentti(@RequestBody Kommentti kommentti) throws SQLException {
        kommenttiDAO.lisaaKommentti(kommentti);
    }

    @RequestMapping(value = "/kommentti", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Kommentti> haeKaikkiKommentit() throws SQLException {
        return kommenttiDAO.haeKaikkiKommentit();
    }

    @RequestMapping(value = "kommentti/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Kommentti haeKommentti(@PathVariable int kommenttiId) throws SQLException {
        return kommenttiDAO.haeKommentti(kommenttiId);
    }

    @RequestMapping(value = "/kommentti/{id}", method = RequestMethod.DELETE)
    public void poistaKommentti(@PathVariable int kommenttiId) throws SQLException {
        kommenttiDAO.poistaKommentti(kommenttiId);
    }

    @RequestMapping(value = "/kommentti/{id}", method = RequestMethod.PUT)
    public void paivitaKommentti(@RequestBody Kommentti kommentti) throws SQLException {
        kommenttiDAO.paivitaKommenti(kommentti);
    }
}
