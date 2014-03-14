/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pro.judostalker.dao;

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

    public Tekniikka lisaaTekniikka(Tekniikka tekniikka) throws SQLException {
        Connection yhteys = yhdista();
        try {
            PreparedStatement prepareStatement = yhteys.prepareStatement("INSERT INTO Tekniikka(nimi) VALUES (?) RETURNING id");
            prepareStatement.setString(1, tekniikka.getNimi());
            ResultSet tulos = prepareStatement.executeQuery();
            tulos.next();
            tekniikka.setID(tulos.getInt("id"));
            yhteys.close();
        } finally {
            return tekniikka;
        }
    }

    public Tekniikka haeTekniikka(int id) throws SQLException {
        Connection yhteys = yhdista();
        try {
            PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM Tekniikka WHERE id = ?");
            prepareStatement.setInt(1, id);
            ResultSet tulos = prepareStatement.executeQuery();
            tulos.next();
            yhteys.close();
            return luoTekniikkaOlio(tulos);
        } finally {
            yhteys.close();
        }
    }

    public void paivitaTekniikka(Tekniikka tekniikka) throws SQLException {
        Connection yhteys = yhdista();
        try {
            PreparedStatement prepareStatement = yhteys.prepareStatement("UPDATE Tekniikka SET nimi = ? ");
            prepareStatement.setString(1, tekniikka.getNimi());
            prepareStatement.execute();
        } finally {
            yhteys.close();
        }
    }

    public void poistaTekniikka(int id) throws SQLException {
        Connection yhteys = yhdista();
        try {
            PreparedStatement prepareStatement = yhteys.prepareStatement("DELETE FROM Tekniikka WHERE id = ?");
            prepareStatement.setInt(1, id);
            ResultSet tulos = prepareStatement.executeQuery();
        } finally {
            yhteys.close();
        }
    }

    public ArrayList<Tekniikka> haeKaikkiTekniikat() throws SQLException {
        Connection yhteys = yhdista();
        try {
            ArrayList<Tekniikka> tekniikat = new ArrayList<Tekniikka>();
            PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM Tekniikka");
            if (prepareStatement.execute()) {
                ResultSet tulokset = prepareStatement.getResultSet();

                while (tulokset.next()) {
                    tekniikat.add(luoTekniikkaOlio(tulokset));
                }
            }
            return tekniikat;

        } finally {
            yhteys.close();
        }
    }

    private Tekniikka luoTekniikkaOlio(ResultSet tulos) throws SQLException {
        Tekniikka tekniikka = new Tekniikka();
        tekniikka.setNimi(tulos.getString("nimi"));
        tekniikka.setID(tulos.getInt("id"));

        return tekniikka;

    }

    public ArrayList<Tekniikka> haeJudokanTekniikat(int judokaId) throws SQLException {
        Connection yhteys = yhdista();
        PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT tekniikkaid FROM tekniikka_judoka WHERE judokaid = ?");
        prepareStatement.setInt(1, judokaId);
        ResultSet resultSet = prepareStatement.executeQuery();
        ArrayList<Tekniikka> tekniikat = new ArrayList<Tekniikka>();
        int haettava;
        while (resultSet.next()) {
            haettava = resultSet.getInt("tekniikkaid");
            tekniikat.add(haeTekniikka(haettava));  //Haetaan 
        }
        return tekniikat;


    }

    public void lisaaTekniikkaJudokalle(int judokaId, int tekniikkaId) throws SQLException {
        Connection yhteys = yhdista();
        try {
            PreparedStatement prepareStatement = yhteys.prepareStatement("INSERT INTO tekniikka_judoka(judokaid,tekniikkaid) VALUES(?,?)");
            prepareStatement.setInt(1, judokaId);
            prepareStatement.setInt(2, tekniikkaId);
            prepareStatement.execute();
        } finally {
            yhteys.close();
        }
    }

    public void poistaTekniikkaJudokalta(int judokaId, int tekniikkaId) throws SQLException {
        Connection yhteys = yhdista();
        try {
            PreparedStatement prepareStatement = yhteys.prepareStatement("DELETE FROM tekniikka_judoka WHERE judokaid = ? and tekniikkaid = ?");
            prepareStatement.setInt(1, judokaId);
            prepareStatement.setInt(2, tekniikkaId);
            prepareStatement.execute();
        } finally {
            yhteys.close();
        }
    }
}
