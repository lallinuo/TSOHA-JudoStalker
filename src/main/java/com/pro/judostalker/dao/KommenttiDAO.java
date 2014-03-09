/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pro.judostalker.dao;

import com.pro.judostalker.model.Kommentti;
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
public class KommenttiDAO extends DBYhdistaja {

    public KommenttiDAO() throws ClassNotFoundException {
        super();
    }

    public void lisaaKommentti(Kommentti kommentti) throws SQLException {
        Connection yhteys = yhdista();
        PreparedStatement prepareStatement = yhteys.prepareStatement("INSERT INTO Kommentti(id,kayttajaid, judokaid, kommentti) VALUES (?,?,?,?)");
        prepareStatement.setInt(1, kommentti.getID());
        prepareStatement.setInt(2, kommentti.getKayttajaID());
        prepareStatement.setInt(3, kommentti.getJudokaID());
        prepareStatement.setString(4, kommentti.getKommentti());
        prepareStatement.execute();
        yhteys.close();
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
        ResultSet tulos = prepareStatement.executeQuery();
        yhteys.close();
    }

    public void paivitaKommenti(Kommentti kommentti) throws SQLException {
        Connection yhteys = yhdista();
        PreparedStatement prepareStatement = yhteys.prepareStatement("UPDATE TKommentti SET kommentti = ? WHERE id = ?");
        prepareStatement.setString(1, kommentti.getKommentti());
        prepareStatement.setInt(2, kommentti.getID());
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
        kommentti.setID(tulos.getInt("id"));
        kommentti.setKayttajaID(tulos.getInt("kayttajaid"));
        kommentti.setKommentti(tulos.getString("kommentti"));
        return kommentti;
    }
}
