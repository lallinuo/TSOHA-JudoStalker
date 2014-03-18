/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pro.judostalker.controller;

import com.pro.judostalker.dao.KayttajaDAO;
import com.pro.judostalker.model.Kayttaja;
import com.pro.judostalker.model.Kirjautuminen;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
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
public class KayttajaController {

    @Autowired
    private KayttajaDAO kayttajaDAO;

    @RequestMapping(value = "/kayttaja", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Kayttaja> haeKaikkiKayttajat() throws SQLException {
        return kayttajaDAO.haeKaikkiKayttajat();
    }

    @RequestMapping(value = "/kayttaja", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public @ResponseBody
    String lisaaKayttaja(@RequestBody Kayttaja kayttaja) throws SQLException, NoSuchAlgorithmException {
        String error = kayttajaDAO.lisaaKayttaja(kayttaja);
        return error;
    }

    @RequestMapping(value = "/kirjaudu", method = RequestMethod.POST)
    public @ResponseBody
    Kayttaja kirjaudu(@RequestBody Kirjautuminen kirjautuminen, HttpSession session) throws SQLException, NoSuchAlgorithmException {

        Kayttaja kayttaja = kayttajaDAO.kirjaudu(kirjautuminen.getKayttajanimi(), kirjautuminen.getSalasana());
        if (kayttaja == null) {
            return null;
        } else {
            session.setAttribute("kirjautunut", kayttaja.getId() + "");
            return kayttaja;
        }
    }

    @RequestMapping(value = "/onKirjautunut", method = RequestMethod.GET)
    @ResponseBody
    public String onKirjautunut(HttpSession session) {
        System.out.println("this is getting spammed");
        if (session.getAttribute("kirjautunut") != null) {
            return (String) session.getAttribute("kirjautunut");
        } else {
            return "false";
        }

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public @ResponseBody
    void kirjauduUlos(HttpSession session) {
        session.invalidate();
    }

    @RequestMapping(value = "/kayttaja/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Kayttaja haeKayttaja(@PathVariable int id) throws SQLException {

        return kayttajaDAO.haeKayttaja(id);
    }

    @RequestMapping(value = "/kayttaja/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    void poistaKayttaja(@PathVariable int id, HttpSession session) throws SQLException {
        if (((String)session.getAttribute("kirjautunut")).equals(id+"")) {
            kayttajaDAO.poistaKayttaja(id);
        }

    }

    @RequestMapping(value = "/kayttaja/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    void paivitaKayttaja(@RequestBody Kayttaja kayttaja) throws SQLException {
        kayttajaDAO.paivitaKayttaja(kayttaja);

    }
}
