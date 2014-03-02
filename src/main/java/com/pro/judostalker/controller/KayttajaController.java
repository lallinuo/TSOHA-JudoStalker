/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pro.judostalker.controller;

import com.pro.judostalker.dao.KayttajaDAO;
import com.pro.judostalker.model.Kayttaja;
import com.pro.judostalker.model.Kirjautuminen;
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
public class KayttajaController {
    @Autowired
    private KayttajaDAO kayttajaDAO;
    @RequestMapping(value= "/kayttaja", method = RequestMethod.GET)
    public @ResponseBody ArrayList<Kayttaja> haeKaikkiKayttajat() throws SQLException{
        return kayttajaDAO.haeKaikkiKayttajat();
        
    }
    @RequestMapping(value = "/kayttaja", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public void lisaaKayttaja(@RequestBody Kayttaja kayttaja) throws SQLException {
        kayttajaDAO.lisaaKayttaja(kayttaja);
    }
    
    @RequestMapping(value = "/kirjaudu", method = RequestMethod.POST)
    public void kirjaudu(@RequestBody Kirjautuminen kirjautuminen) throws SQLException{
        if(kayttajaDAO.kirjaudu(kirjautuminen.getKayttajanimi(), kirjautuminen.getSalasana())){
            System.out.println("Kirjauduttud");
        }else{
            System.out.println("väärä passu");
        }
    }

    @RequestMapping(value = "/kayttaja/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Kayttaja haeKayttaja(@PathVariable int id) throws SQLException {

        return kayttajaDAO.haeKayttaja(id);
    }

    @RequestMapping(value = "/kayttaja/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    void poistaKayttaja(@RequestBody Kayttaja kayttaja) throws SQLException {
        kayttajaDAO.poistaKayttaja(kayttaja.getId());

    }

    @RequestMapping(value = "/kayttaja/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    void paivitaKayttaja(@RequestBody Kayttaja kayttaja) throws SQLException {
        kayttajaDAO.paivitaKayttaja(kayttaja);

    }
}
