/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pro.judostalker.model;

/**
 *
 * @author Lalli
 */
public class Kommentti {
    private int ID;
    private int KayttajaID;
    private int JudokaID;
    private String kommentti;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getKayttajaID() {
        return KayttajaID;
    }

    public void setKayttajaID(int KayttajaID) {
        this.KayttajaID = KayttajaID;
    }

    public int getJudokaID() {
        return JudokaID;
    }

    public void setJudokaID(int JudokaID) {
        this.JudokaID = JudokaID;
    }

    public String getKommentti() {
        return kommentti;
    }

    public void setKommentti(String kommentti) {
        this.kommentti = kommentti;
    }
    
   
}
