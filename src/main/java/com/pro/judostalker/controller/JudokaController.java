/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pro.judostalker.controller;

import com.pro.judostalker.dao.JudokaDAO;
import com.pro.judostalker.model.Judoka;
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
public class JudokaController {

    @Autowired
    private AuthService authService;
    @Autowired
    private JudokaDAO judokaDAO;

    @RequestMapping(value = "/judoka", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Judoka> haeKaikkiJudokat(HttpSession session) throws SQLException {
        if (authService.isLogged(session)) {
            return judokaDAO.haeKaikkiJudokat();
        }
        return null;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/judoka/{id}", method = RequestMethod.DELETE)
    public void poistaJudoka(@PathVariable int id, HttpSession session) throws SQLException {
        if (authService.isLogged(session)) {
            judokaDAO.poistaJudoka(id);
        }
    }

    @RequestMapping(value = "/judoka/tekniikka/{tekniikkaId}", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Judoka> haeTekniikkaaKayttavatJudokat(@PathVariable int tekniikkaId, HttpSession session) throws SQLException {
        if (authService.isLogged(session)) {
            return judokaDAO.haeTekniikkaaKayttavatJudokat(tekniikkaId);
        }
        return null;
    }

    @RequestMapping(value = "/judoka/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Judoka haeJudoka(@PathVariable int id, HttpSession session) throws SQLException {
        if (authService.isLogged(session)) {
            return judokaDAO.haeJudoka(id);
        }
        return null;
    }

    @RequestMapping(value = "/judoka", method = RequestMethod.POST)
    public @ResponseBody
    Judoka lisaaJudoka(@RequestBody Judoka judoka, HttpSession session) throws SQLException {
        if (authService.isLogged(session)) {
            return judokaDAO.lisaaJudoka(judoka);
        }
        return null;
    }

    @RequestMapping(value = "/judoka/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    void paivitaJudoka(@RequestBody Judoka judoka, HttpSession session) throws SQLException {
        if (authService.isLogged(session)) {
            judokaDAO.paivitaJudoka(judoka);
        }
    }
}
