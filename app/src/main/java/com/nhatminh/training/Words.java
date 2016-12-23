package com.nhatminh.training;

/**
 * Created by caonh on 05/10/2016.
 */
public class Words {

    private int id;
    private String tenTV;
    private String nghiaTA;
    private String nghiadung;
    private String vidu;

    public Words(){}
    public Words(int id, String tenTV, String nghiaTA, String nghiadung, String vidu)
    {
        this.id = id;
        this.tenTV = tenTV;
        this.nghiaTA = nghiaTA;
        this.nghiadung = nghiadung;
        this.vidu = vidu;
    }

    public Words(int id, String tenTV, String nghiadung, String vidu)
    {
        this.id = id;
        this.tenTV = tenTV;
        this.nghiadung = nghiadung;
        this.vidu = vidu;
    }
    public String getTenTV() {
        return tenTV;
    }

    public void setTenTV(String tenTV) {
        this.tenTV = tenTV;
    }

    public String getNghiaTA() {
        return nghiaTA;
    }

    public void setNghiaTA(String nghiaTA) {
        this.nghiaTA = nghiaTA;
    }

    public String getNghiadung() {
        return nghiadung;
    }

    public void setNghiadung(String nghiadung) {
        this.nghiadung = nghiadung;
    }

    public String getVidu() {
        return vidu;
    }

    public void setVidu(String vidu) {
        this.vidu = vidu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
