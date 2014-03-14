/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pro.judostalker.controller;

import com.pro.judostalker.dao.TekniikkaDAO;
import com.pro.judostalker.model.Tekniikka;
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
public class TekniikkaController {

    @Autowired
    private TekniikkaDAO tekniikkaDAO;

    @RequestMapping(value = "/tekniikka", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Tekniikka> haeKaikkiTekniikat() throws SQLException {
        return tekniikkaDAO.haeKaikkiTekniikat();

    }

    @RequestMapping(value = "/tekniikka/{judokaId}/{tekniikkaId}", method = RequestMethod.POST)
    public @ResponseBody
    void lisaaTekniikkaJudokalle(@PathVariable int judokaId, @PathVariable int tekniikkaId) throws SQLException {
        tekniikkaDAO.lisaaTekniikkaJudokalle(judokaId, tekniikkaId);
    }

    @RequestMapping(value = "/tekniikka", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public @ResponseBody
    Tekniikka lisaaTekniikka(@RequestBody Tekniikka tekniikka) throws SQLException {
        return tekniikkaDAO.lisaaTekniikka(tekniikka);
    }

    @RequestMapping(value = "/tekniikka/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Tekniikka haeTekniikka(@PathVariable int id) throws SQLException {

        return tekniikkaDAO.haeTekniikka(id);
    }

    @RequestMapping(value = "/tekniikka/judoka/{judokaId}", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Tekniikka> haeJudokanTekniikat(int judokaId) throws SQLException {
        return tekniikkaDAO.haeJudokanTekniikat(judokaId);

    }

    @RequestMapping(value = "/tekniikka/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    void poistaTekniikka(@RequestBody Tekniikka tekniikka) throws SQLException {
        tekniikkaDAO.poistaTekniikka(tekniikka.getID());

    }

    @RequestMapping(value = "/tekniikka/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    void paivitaTekniikka(@RequestBody Tekniikka tekniikka) throws SQLException {
        tekniikkaDAO.paivitaTekniikka(tekniikka);

    }
}
