/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pro.judostalker.controller;

import com.pro.judostalker.dao.JudokaDAO;
import com.pro.judostalker.model.Judoka;
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
public class JudokaController {

    @Autowired
    private JudokaDAO judokaDAO;

    @RequestMapping(value = "/judoka", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Judoka> haeKaikkiJudokat() throws SQLException {
        return judokaDAO.haeKaikkiJudokat();
    }

    @RequestMapping(value = "/judoka/{id}", method = RequestMethod.DELETE)
    public void poistaJudoka(@PathVariable int id) throws SQLException {
        judokaDAO.poistaJudoka(id);
    }

    @RequestMapping(value = "/judoka/{id}", method = RequestMethod.GET)
    public @ResponseBody Judoka haeJudoka(@PathVariable int id, HttpSession session) throws SQLException {
        if (session.getAttribute("kirjautunut") != null) {
            System.out.println("attribute found");
            return judokaDAO.haeJudoka(id);
        } else {
            return null;
        }

    }

    @RequestMapping(value = "/judoka", method = RequestMethod.POST)
    public @ResponseBody
    void lisaaJudoka(@RequestBody Judoka judoka) throws SQLException {
        judokaDAO.lisaaJudoka(judoka);
    }

    @RequestMapping(value = "/judoka", method = RequestMethod.PUT)
    public @ResponseBody
    void paivitaJudoka(@RequestBody Judoka judoka) throws SQLException {
        judokaDAO.paivitaJudoka(judoka);
    }
}
