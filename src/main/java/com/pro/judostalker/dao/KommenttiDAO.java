/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pro.judostalker.dao;

import com.pro.judostalker.model.Kayttaja;
import com.pro.judostalker.model.Kommentti;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lalli
 */
@Component
public class KommenttiDAO extends DBYhdistaja {

    @Autowired
    private KayttajaDAO kayttajaDAO;

    public KommenttiDAO() throws ClassNotFoundException {
        super();
    }

    public Kommentti lisaaKommentti(Kommentti kommentti) throws SQLException {
        Connection yhteys = yhdista();
        PreparedStatement prepareStatement = yhteys.prepareStatement("INSERT INTO Kommentti(kayttajaid, judokaid, kommentti,pvm) VALUES (?,?,?,?) returning id");
        prepareStatement.setInt(1, kommentti.getKayttajaId());
        prepareStatement.setInt(2, kommentti.getJudokaId());
        prepareStatement.setString(3, kommentti.getKommentti());
        java.util.Date today = new java.util.Date();
        prepareStatement.setDate(4, new java.sql.Date(today.getTime()));
        ResultSet tulos = prepareStatement.executeQuery();
        tulos.next();
        yhteys.close();
        return haeKommentti(tulos.getInt("id"));
    }

    public Kommentti haeKommentti(int id) throws SQLException {
        Connection yhteys = yhdista();
        PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM Kommentti WHERE id = ?");
        prepareStatement.setInt(1, id);
        ResultSet tulos = prepareStatement.executeQuery();
        tulos.next();
        yhteys.close();
        return luoKommenttiOlio(tulos);
    }

    public void poistaKommentti(int id) throws SQLException {
        Connection yhteys = yhdista();
        PreparedStatement prepareStatement = yhteys.prepareStatement("DELETE FROM Kommentti WHERE id = ?");
        prepareStatement.setInt(1, id);
        prepareStatement.execute();
        yhteys.close();
    }

    public void paivitaKommenti(Kommentti kommentti) throws SQLException {
        Connection yhteys = yhdista();
        PreparedStatement prepareStatement = yhteys.prepareStatement("UPDATE Kommentti SET kommentti = ? WHERE id = ?");
        prepareStatement.setString(1, kommentti.getKommentti());
        prepareStatement.setInt(2, kommentti.getId());
        prepareStatement.execute();
        yhteys.close();
    }

    public ArrayList<Kommentti> haeKayttajanKommentit(int id) throws SQLException {
        Connection yhteys = yhdista();
        PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM Kommentti WHERE id = ?");
        prepareStatement.setInt(1, id);
        ArrayList<Kommentti> kommentit = new ArrayList<Kommentti>();
        ResultSet tulos = prepareStatement.executeQuery();
        while (tulos.next()) {
            kommentit.add(luoKommenttiOlio(tulos));
        }
        return kommentit;
    }

    private Kommentti luoKommenttiOlio(ResultSet tulos) throws SQLException {
        Kommentti kommentti = new Kommentti();
        kommentti.setId(tulos.getInt("id"));
        kommentti.setKayttajaId(tulos.getInt("kayttajaid"));
        kommentti.setKommentti(tulos.getString("kommentti"));
        kommentti.setPvm(tulos.getDate("pvm"));
        Kayttaja kayttaja = kayttajaDAO.haeKayttaja(tulos.getInt("kayttajaId"));
        kommentti.setLahettaja(kayttaja.getKayttajanimi());
        return kommentti;
    }

    public ArrayList<Kommentti> haeKaikkiJudokanKommentit(int judokaId) throws SQLException {
        ArrayList<Kommentti> kommentit = new ArrayList<Kommentti>();
        Connection yhteys = yhdista();
        PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM Kommentti WHERE judokaid = ?");
        prepareStatement.setInt(1, judokaId);
        ResultSet tulos = prepareStatement.executeQuery();
        while (tulos.next()) {
            kommentit.add(luoKommenttiOlio(tulos));
        }
        yhteys.close();
        return kommentit;

    }

    public ArrayList<Kommentti> haeKaikkiKommentit() throws SQLException {
        ArrayList<Kommentti> kommentit = new ArrayList<Kommentti>();
        Connection yhteys = yhdista();
        PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM Kommentti");
        ResultSet tulos = prepareStatement.executeQuery();
        while (tulos.next()) {
            kommentit.add(luoKommenttiOlio(tulos));
        }
        return kommentit;
    }
}
