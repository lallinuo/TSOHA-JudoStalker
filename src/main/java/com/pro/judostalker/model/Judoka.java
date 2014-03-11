/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pro.judostalker.model;

import org.springframework.util.StringUtils;


/**
 *
 * @author Lalli
 */
public class Judoka {

    private int id;
    private String etunimi;
    private String sukunimi;
    private String painoluokka;
    private String sukupuoli;
    private String maa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtunimi() {
        return etunimi;
    }

    public void setEtunimi(String etunimi) {

        etunimi = StringUtils.capitalize(etunimi);
        this.etunimi = etunimi;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public void setSukunimi(String sukunimi) {
        sukunimi = StringUtils.capitalize(sukunimi);
        this.sukunimi = sukunimi;
    }

    public String getPainoluokka() {
        return painoluokka;
    }

    public void setPainoluokka(String painoluokka) {
        this.painoluokka = painoluokka;
    }

    public String getSukupuoli() {
        return sukupuoli;
    }

    public void setSukupuoli(String sukupuoli) {
        this.sukupuoli = sukupuoli;
    }

    public String getMaa() {
        return maa;
    }

    public void setMaa(String maa) {
        maa = StringUtils.capitalize(maa);
        this.maa = maa;
    }
}
