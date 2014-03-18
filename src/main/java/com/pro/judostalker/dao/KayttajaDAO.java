/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pro.judostalker.dao;

import com.pro.judostalker.model.Kayttaja;
import com.pro.judostalker.model.Kayttaja;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 *
 * @author Lalli
 */
@Component
public class KayttajaDAO extends DBYhdistaja {

    public KayttajaDAO() throws ClassNotFoundException {
        super();
    }

    public String lisaaKayttaja(Kayttaja kayttaja) throws SQLException, NoSuchAlgorithmException {
        Connection yhteys = yhdista();
        try {
            PreparedStatement prepareStatement = yhteys.prepareStatement("INSERT INTO Kayttaja(kayttajanimi, password, etunimi, sukunimi) VALUES (?,?,?,?)");
            prepareStatement.setString(1, kayttaja.getKayttajanimi());
            prepareStatement.setString(2, MD5.crypt(kayttaja.getSalasana()));
            prepareStatement.setString(3, kayttaja.getEtunimi());
            prepareStatement.setString(4, kayttaja.getSukunimi());
            prepareStatement.execute();
            return "";
        } catch (Exception e) {
            return "{\"error\":\"Käyttäjänimi on jo käytössä\"}";
        } finally {
            yhteys.close();
        }
    }

    //salasanaan tiiviste + suola 
    public Kayttaja kirjaudu(String kayttajanimi, String salasana) throws SQLException, NoSuchAlgorithmException {
        Connection yhteys = yhdista();
        PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM Kayttaja WHERE kayttajanimi = ? and password = ?");
        prepareStatement.setString(1, kayttajanimi);
        prepareStatement.setString(2, MD5.crypt(salasana));
        ResultSet tulos = prepareStatement.executeQuery();
        yhteys.close();
        if (tulos.next()) {
            return luoKayttajaOlio(tulos);
        }
        return null;
    }

    //nullin kaa probleemia
    public Kayttaja haeKayttaja(int id) throws SQLException {
        Connection yhteys = yhdista();
        PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM Kayttaja WHERE id = ?");
        prepareStatement.setInt(1, id);
        ResultSet tulos = prepareStatement.executeQuery();
        tulos.next();
        yhteys.close();
        return luoKayttajaOlio(tulos);
    }

    public void paivitaKayttaja(Kayttaja kayttaja) throws SQLException {
        Connection yhteys = yhdista();
        PreparedStatement prepareStatement = yhteys.prepareStatement("UPDATE Kayttaja SET kayttajanimi = ?, salasana = ?, etunimi = ?, sukunimi = ? where id = ?");
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
        prepareStatement.execute();
        yhteys.close();
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
