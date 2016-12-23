package com.nhatminh.training;

/**
 * Created by caonh on 01/10/2016.
 */
public class Lessons {
    private int maBH;
    private String tenTA;
    private String tenTV;

    public Lessons()
    {

    }
    public Lessons(int maBH, String tenTA, String tenTV)
    {
        this.maBH = maBH;
        this.tenTA = tenTA;
        this.tenTV = tenTV;
    }
    public int getMaBH() {
        return maBH;
    }

    public void setMaBH(int maBH) {
        this.maBH = maBH;
    }

    public String getTenTA() {
        return tenTA;
    }

    public void setTenTA(String tenTA) {
        this.tenTA = tenTA;
    }

    public String getTenTV() {
        return tenTV;
    }

    public void setTenTV(String tenTV) {
        this.tenTV = tenTV;
    }
}
