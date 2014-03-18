/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pro.judostalker.dao;

import com.pro.judostalker.model.Judoka;
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
public class JudokaDAO extends DBYhdistaja {

    public JudokaDAO() throws ClassNotFoundException {
        super();
    }

    public Judoka lisaaJudoka(Judoka judoka) throws SQLException {
        Connection yhteys = yhdista();
        try {
            PreparedStatement prepareStatement = yhteys.prepareStatement("INSERT INTO Judoka(etunimi, sukunimi, painoluokka, sukupuoli,maa) VALUES (?,?,?,?,?)");
            prepareStatement.setString(1, judoka.getEtunimi());
            prepareStatement.setString(2, judoka.getSukunimi());
            prepareStatement.setString(3, judoka.getPainoluokka());
            prepareStatement.setString(4, judoka.getSukupuoli());
            prepareStatement.setString(5, judoka.getMaa());
            prepareStatement.execute();
            prepareStatement = yhteys.prepareStatement("SELECT max(id) as max from Judoka");
            ResultSet tulos = prepareStatement.executeQuery();
            tulos.next();
            judoka.setId(tulos.getInt("max"));
            yhteys.close();
            return judoka;

        } finally {
            yhteys.close();
        }

    }

    public Judoka haeJudoka(int id) throws SQLException {

        Connection yhteys = yhdista();
        try {
            PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM Judoka WHERE id = ?");
            prepareStatement.setInt(1, id);
            ResultSet tulos = prepareStatement.executeQuery();
            yhteys.close();
            if (tulos.next()) {
                return luoJudokaOlio(tulos);
            }
            return null;
        } finally {
            yhteys.close();
        }
    }

    public void paivitaJudoka(Judoka judoka) throws SQLException {

        Connection yhteys = yhdista();
        try {
            PreparedStatement prepareStatement = yhteys.prepareStatement("UPDATE Judoka SET etunimi = ?, sukunimi = ?, painoluokka = ?, sukupuoli = ?, maa = ? WHERE id = ?");
            prepareStatement.setString(1, judoka.getEtunimi());
            prepareStatement.setString(2, judoka.getSukunimi());
            prepareStatement.setString(3, judoka.getPainoluokka());
            prepareStatement.setString(4, judoka.getSukupuoli());
            prepareStatement.setString(5, judoka.getMaa());
            prepareStatement.setInt(6, judoka.getId());
            prepareStatement.execute();
        } finally {
            yhteys.close();
        }
    }

    public void poistaJudoka(int id) throws SQLException {
        Connection yhteys = yhdista();
        try {
            PreparedStatement prepareStatement = yhteys.prepareStatement("DELETE FROM Judoka WHERE id = ?");
            prepareStatement.setInt(1, id);
            prepareStatement.execute();
        } finally {
            yhteys.close();
        }
    }

    public ArrayList<Judoka> haeKaikkiJudokat() throws SQLException {
        Connection yhteys = yhdista();
        try {
            ArrayList<Judoka> judokat = new ArrayList<Judoka>();

            PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM Judoka");
            ResultSet tulos = prepareStatement.executeQuery();
            while (tulos.next()) {
                judokat.add(luoJudokaOlio(tulos));
            }

            return judokat;
        } finally {
            yhteys.close();
        }
    }

    public Judoka luoJudokaOlio(ResultSet tulos) throws SQLException {
        Judoka judoka = new Judoka();
        judoka.setEtunimi(tulos.getString("etunimi"));
        judoka.setSukunimi(tulos.getString("sukunimi"));
        judoka.setMaa(tulos.getString("maa"));
        judoka.setPainoluokka(tulos.getString("painoluokka"));
        judoka.setSukupuoli(tulos.getString("sukupuoli"));
        judoka.setId(tulos.getInt("id"));
        return judoka;
    }

    public ArrayList<Judoka> haeTekniikkaaKayttavatJudokat(int tekniikkaId) throws SQLException {
        Connection yhteys = yhdista();
        try {
            ArrayList<Judoka> judokat = new ArrayList<Judoka>();
            PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT judokaid FROM tekniikka_judoka WHERE tekniikkaid = ?");
            prepareStatement.setInt(1, tekniikkaId);
            ResultSet tulos = prepareStatement.executeQuery();
            int haettava;
            while (tulos.next()) {
                haettava = tulos.getInt("judokaid");
                judokat.add(haeJudoka(haettava));
            }
            return judokat;
        } finally {
            yhteys.close();
        }
    }
}
