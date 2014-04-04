/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pro.judostalker.controller;

import com.pro.judostalker.dao.TekniikkaDAO;
import com.pro.judostalker.model.Tekniikka;
import com.pro.judostalker.service.AuthService;
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
public class TekniikkaController {

    @Autowired
    private TekniikkaDAO tekniikkaDAO;
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/tekniikka", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Tekniikka> haeKaikkiTekniikat(HttpSession session) throws SQLException {
        if (authService.isLogged(session)) {
            return tekniikkaDAO.haeKaikkiTekniikat();
        }
        return null;

    }

    @RequestMapping(value = "/tekniikka/{judokaId}/{tekniikkaId}", method = RequestMethod.POST)
    public @ResponseBody
    void lisaaTekniikkaJudokalle(@PathVariable int judokaId, @PathVariable int tekniikkaId, HttpSession session) throws SQLException {
        if (authService.isLogged(session)) {
            tekniikkaDAO.lisaaTekniikkaJudokalle(judokaId, tekniikkaId);
        }
    }

    @RequestMapping(value = "/tekniikka", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public @ResponseBody
    Tekniikka lisaaTekniikka(@RequestBody Tekniikka tekniikka, HttpSession session) throws SQLException {
        if (authService.isLogged(session)) {
            return tekniikkaDAO.lisaaTekniikka(tekniikka);
        }
        return null;
    }

    @RequestMapping(value = "/tekniikka/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Tekniikka haeTekniikka(@PathVariable int id, HttpSession session) throws SQLException {
        if (authService.isLogged(session)) {
            return tekniikkaDAO.haeTekniikka(id);
        }
        return null;
    }

    @RequestMapping(value = "/tekniikka/judoka/{judokaId}", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Tekniikka> haeJudokanTekniikat(@PathVariable int judokaId, HttpSession session) throws SQLException {
        if (authService.isLogged(session)) {
            return tekniikkaDAO.haeJudokanTekniikat(judokaId);
        }
        return null;

    }

    @RequestMapping(value = "/tekniikka/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    void poistaTekniikka(@RequestBody Tekniikka tekniikka, HttpSession session) throws SQLException {
        if (authService.isLogged(session)) {
            tekniikkaDAO.poistaTekniikka(tekniikka.getID());
        }

    }

    @RequestMapping(value = "/tekniikka/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    void paivitaTekniikka(@RequestBody Tekniikka tekniikka, HttpSession session) throws SQLException {
        if (authService.isLogged(session)) {
            tekniikkaDAO.paivitaTekniikka(tekniikka);
        }
    }

    @RequestMapping(value = "/tekniikka/{judokaId}/{tekniikkaId}", method = RequestMethod.DELETE)
    public @ResponseBody
    void poistaTekniikkaJudokalta(@PathVariable int judokaId,
            @PathVariable int tekniikkaId, HttpSession session) throws SQLException {
        if (authService.isLogged(session)) {
            tekniikkaDAO.poistaTekniikkaJudokalta(judokaId, tekniikkaId);
        }
    }
}
