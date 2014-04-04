/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pro.judostalker.controller;

import com.pro.judostalker.dao.KommenttiDAO;
import com.pro.judostalker.model.Kommentti;
import com.pro.judostalker.service.AuthService;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
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
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/kayttaja/{id}/kommentit", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Kommentti> haeKaikkiKayttajanKommentit(@PathVariable int kayttajaId, HttpSession session) throws SQLException {
        if (authService.isLogged(session)) {
            return kommenttiDAO.haeKayttajanKommentit(kayttajaId);
        }
        return null;
    }

    @RequestMapping(value = "/kommentti/judoka/{judokaId}", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Kommentti> haeKaikkiJudokanKommentit(@PathVariable int judokaId, HttpSession session) throws SQLException {
        if (authService.isLogged(session)) {
            return kommenttiDAO.haeKaikkiJudokanKommentit(judokaId);
        }
        return null;
    }

    @RequestMapping(value = "/kommentti", method = RequestMethod.POST)
    public @ResponseBody
    Kommentti lisaaKommentti(@RequestBody Kommentti kommentti, HttpSession session) throws SQLException {
        if (authService.isLogged(session)) {
            return kommenttiDAO.lisaaKommentti(kommentti);
        }
        return null;
    }

    @RequestMapping(value = "/kommentti", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Kommentti> haeKaikkiKommentit(HttpSession session) throws SQLException {
        if (authService.isLogged(session)) {
            return kommenttiDAO.haeKaikkiKommentit();
        }
        return null;
    }

    @RequestMapping(value = "kommentti/{kommenttiId}", method = RequestMethod.GET)
    public @ResponseBody
    Kommentti haeKommentti(@PathVariable int kommenttiId, HttpSession session) throws SQLException {
        if (authService.isLogged(session)) {
            return kommenttiDAO.haeKommentti(kommenttiId);
        }
        return null;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/kommentti/{kommenttiId}", method = RequestMethod.DELETE)
    public void poistaKommentti(@PathVariable int kommenttiId, HttpSession session) throws SQLException {
        if (authService.isLogged(session)) {
            kommenttiDAO.poistaKommentti(kommenttiId);
        }
    }

    @RequestMapping(value = "/kommentti/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    void paivitaKommentti(@RequestBody Kommentti kommentti, HttpSession session) throws SQLException {
        if (authService.isLogged(session)) {
            kommenttiDAO.paivitaKommenti(kommentti);
        }
    }
}
