/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pro.judostalker.controller;

import com.pro.judostalker.dao.KayttajaDAO;
import com.pro.judostalker.model.Kayttaja;
import com.pro.judostalker.model.Kirjautuminen;
import com.pro.judostalker.service.AuthService;
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
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/kayttaja", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Kayttaja> haeKaikkiKayttajat(HttpSession session) throws SQLException {
        if (authService.isLogged(session)) {
            return kayttajaDAO.haeKaikkiKayttajat();
        }
        return null;
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
            System.out.println("session set");
            System.out.println(kayttaja.getId() + " = tallennettu id");
            session.setAttribute("kirjautunut", kayttaja.getId() + "");
            return kayttaja;
        }
    }

    @RequestMapping(value = "/onKirjautunut", method = RequestMethod.GET)
    @ResponseBody
    public String onKirjautunut(HttpSession session) {
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
    Kayttaja haeKayttaja(@PathVariable int id, HttpSession session) throws SQLException {
        if (authService.isLogged(session)) {
            return kayttajaDAO.haeKayttaja(id);
        }
        return null;
    }

    @RequestMapping(value = "/kayttaja/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    void poistaKayttaja(@PathVariable int id, HttpSession session) throws SQLException {
        if (authService.isLoggedWithId(session, id)) {
            kayttajaDAO.poistaKayttaja(id);
            session.invalidate();
        }
    }

    @RequestMapping(value = "/kayttaja/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    void paivitaKayttaja(@RequestBody Kayttaja kayttaja, @PathVariable int id, HttpSession session) throws SQLException {
        if (authService.isLoggedWithId(session, id)) {
            kayttajaDAO.paivitaKayttaja(kayttaja);
        }
    }
}