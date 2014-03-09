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

    public void lisaaJudoka(Judoka judoka) throws SQLException {
        Connection yhteys = yhdista();
        PreparedStatement prepareStatement = yhteys.prepareStatement("INSERT INTO judoka(judokanimi, salasana, etunimi, sukunimi) VALUES (?,?,?,?)");

        prepareStatement.setString(1, judoka.getEtunimi());
        prepareStatement.setString(2, judoka.getSukunimi());
        prepareStatement.setString(3, judoka.getPainoluokka());
        prepareStatement.setString(4, judoka.getSukupuoli());
        prepareStatement.setString(5, judoka.getPainoluokka());
        prepareStatement.executeQuery();
        yhteys.close();
        
    }

    public Judoka haeJudoka(int id) throws SQLException {
        Connection yhteys = yhdista();
        PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM Judoka WHERE id = ?");
        prepareStatement.setInt(1, id);
        ResultSet tulos = prepareStatement.executeQuery();
        tulos.next();
        yhteys.close();

        return luoJudokaOlio(tulos);
    }

    public void paivitaJudoka(Judoka judoka) throws SQLException {
        Connection yhteys = yhdista();
        PreparedStatement prepareStatement = yhteys.prepareStatement("UPDATE Judoka SET etunimi = ?, sukunimi = ?, painoluokka = ?, sukupuoli = ?, maa = ? WHERE id = ?");
        prepareStatement.setString(1, judoka.getEtunimi());
        prepareStatement.setString(2, judoka.getSukunimi());
        prepareStatement.setString(3, judoka.getPainoluokka());
        prepareStatement.setString(4, judoka.getSukupuoli());
        prepareStatement.setString(5, judoka.getMaa());
        prepareStatement.setInt(6, judoka.getId());
        prepareStatement.executeQuery();
        yhteys.close();
    }

    public void poistaJudoka(int id) throws SQLException {
        Connection yhteys = yhdista();
        PreparedStatement prepareStatement = yhteys.prepareStatement("DELETE FROM Judoka WHERE id = ?");
        prepareStatement.setInt(1, id);
        prepareStatement.executeQuery();
        yhteys.close();

    }

    public ArrayList<Judoka> haeKaikkiJudokat() throws SQLException {
        ArrayList<Judoka> judokat = new ArrayList<Judoka>();
        Connection yhteys = yhdista();
        PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM Judoka");
        ResultSet tulos = prepareStatement.executeQuery();
        while (tulos.next()) {
            judokat.add(luoJudokaOlio(tulos));
        }

        return judokat;
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
}
