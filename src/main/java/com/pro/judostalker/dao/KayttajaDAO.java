/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pro.judostalker.dao;

import com.pro.judostalker.model.Kayttaja;
import com.pro.judostalker.model.Kayttaja;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lalli
 */
@Component
public class KayttajaDAO extends DBYhdistaja {

    public KayttajaDAO() throws ClassNotFoundException {
        super();
    }

    public void lisaaKayttaja(Kayttaja kayttaja) throws SQLException {
        Connection yhteys = yhdista();
        PreparedStatement prepareStatement = yhteys.prepareStatement("INSERT INTO Kayttaja(kayttajanimi, password, etunimi, sukunimi) VALUES (?,?,?,?)");

        prepareStatement.setString(1, kayttaja.getKayttajanimi());
        prepareStatement.setString(2, kayttaja.getSalasana());
        prepareStatement.setString(3, kayttaja.getEtunimi());
        prepareStatement.setString(3, kayttaja.getSukunimi());
        prepareStatement.execute();
        yhteys.close();
    }

    public boolean kirjaudu(String username, String password) throws SQLException {
        Connection yhteys = yhdista();
        PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM Kayttaja WHERE kayttajanimi = ? and password = ?");
        ResultSet tulos = prepareStatement.executeQuery();
        yhteys.close();
        if (tulos.first()) {
            return true; //pitää testata viel toimiiks tää näin
        }
        return false;
    }

    //nullin kaa probleemia
    public Kayttaja haeKayttaja(int id) throws SQLException {
        Connection yhteys = yhdista();
        PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM Kayttaja WHERE id = ?");
        prepareStatement.setInt(1, id);
        ResultSet tulos = prepareStatement.executeQuery();
        System.out.println("njoo");
        return luoKayttajaOlio(tulos);
    }

    public void paivitaKayttaja(Kayttaja kayttaja) throws SQLException {
        Connection yhteys = yhdista();
        PreparedStatement prepareStatement = yhteys.prepareStatement("UPDATE Kayttaja SET kayttajanimi = ?, password = ?, etunimi = ?, sukunimi = ? where id = ?");
        prepareStatement.setString(1, kayttaja.getKayttajanimi());
        prepareStatement.setString(2, kayttaja.getSalasana());
        prepareStatement.setString(3, kayttaja.getEtunimi());
        prepareStatement.setString(4, kayttaja.getSukunimi());
        prepareStatement.execute();
        yhteys.close();
    }

    public void poistaKayttaja(int id) throws SQLException {
        Connection yhteys = yhdista();
        PreparedStatement prepareStatement = yhteys.prepareStatement("DELETE FROM Kayttaja WHERE id = ?");
        prepareStatement.setInt(1, id);
        ResultSet tulos = prepareStatement.executeQuery();
    }

    public ArrayList<Kayttaja> haeKaikkiKayttajat() throws SQLException {
        Connection yhteys = yhdista();
        ArrayList<Kayttaja> kayttajat = new ArrayList<Kayttaja>();
        PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM Kayttaja");
        if (prepareStatement.execute()) {
            ResultSet tulokset = prepareStatement.getResultSet();
            while (tulokset.next()) {
                kayttajat.add(luoKayttajaOlio(tulokset));
            }
        }
        return kayttajat;
    }

    private Kayttaja luoKayttajaOlio(ResultSet tulos) throws SQLException {
        Kayttaja kayttaja = new Kayttaja();
        kayttaja.setKayttajanimi(tulos.getString("kayttajanimi"));
        kayttaja.setId(tulos.getInt("id"));
        kayttaja.setSalasana("");
        kayttaja.setEtunimi(tulos.getString("etunimi"));
        kayttaja.setSukunimi(tulos.getString("sukunimi"));
        return kayttaja;

    }
}
