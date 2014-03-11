/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pro.judostalker.model;

import java.sql.Date;

/**
 *
 * @author Lalli
 */
public class Kommentti {

    private String kommentti;
    private int id;
    private int judokaId;
    private int kayttajaId;
    private String lahettaja;
    private Date pvm;

    public Date getPvm() {
        return pvm;
    }

    public void setPvm(Date pvm) {
        this.pvm = pvm;
    }

    public String getLahettaja() {
        return lahettaja;
    }

    public void setLahettaja(String lahettaja) {
        this.lahettaja = lahettaja;
    }

    public int getKayttajaId() {
        return kayttajaId;
    }

    public void setKayttajaId(int kayttaja) {
        this.kayttajaId = kayttaja;
    }

    public int getJudokaId() {
        return judokaId;
    }

    public void setJudokaId(int judoka) {
        this.judokaId = judoka;
    }

    public String getKommentti() {
        return kommentti;
    }

    public void setKommentti(String kommentti) {
        this.kommentti = kommentti;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
