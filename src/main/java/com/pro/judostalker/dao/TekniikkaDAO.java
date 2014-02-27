/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pro.judostalker.dao;

import com.pro.judostalker.model.Judoka;
import com.pro.judostalker.model.Tekniikka;
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
public class TekniikkaDAO extends DBYhdistaja {

    public TekniikkaDAO() throws ClassNotFoundException {
        super();
    }

    public void lisaaTekniikka(Tekniikka tekniikka) throws SQLException {
        Connection yhteys = yhdista();
        PreparedStatement prepareStatement = yhteys.prepareStatement("INSERT INTO Tekniikka(nimi) VALUES (?)");
        prepareStatement.setString(1, tekniikka.getNimi());
        prepareStatement.execute();
        yhteys.close();
    }

    public Tekniikka haeTekniikka(int id) throws SQLException {
        Connection yhteys = yhdista();
        PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM Tekniikka WHERE id = ?");
        prepareStatement.setInt(1, id);
        ResultSet tulos = prepareStatement.executeQuery();
        return luoTekniikkaOlio(tulos);
    }

    public void paivitaTekniikka(Tekniikka tekniikka) throws SQLException {
    }

    public void poistaTekniikka(Tekniikka tekniikka) throws SQLException {
        Connection yhteys = yhdista();
        PreparedStatement prepareStatement = yhteys.prepareStatement("DELETE FROM Tekniikka WHERE id = ?");
        prepareStatement.setInt(1, tekniikka.getID());
        ResultSet tulos = prepareStatement.executeQuery();
    }

    public ArrayList<Judoka> haeKaikkiTekniikat() {
        return null;
    }

    private Tekniikka luoTekniikkaOlio(ResultSet tulos) throws SQLException {
        Tekniikka tekniikka = new Tekniikka();
        if (tulos.next()) {
            tekniikka.setNimi(tulos.getString("nimi"));
        }
        return tekniikka;

    }
}
