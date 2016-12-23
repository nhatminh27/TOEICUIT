package com.nhatminh.note;

/**
 * Created by caonh on 19/10/2016.
 */
public class Times {

    private int onoff;
    private int starth;
    private int startm;
    private int endh;
    private int endm;
    private int mode;

    public Times (){}
    public Times(int onoff, int starth, int endh, int startm, int endm, int mode)
    {
        this.onoff = onoff;
        this.starth = starth;
        this.endh = endh;
        this.startm = startm;
        this.endm = endh;
        this.mode = mode;
    }
    public int getOnoff() {
        return onoff;
    }

    public void setOnoff(int onoff) {
        this.onoff = onoff;
    }

    public int getStarth() {
        return starth;
    }

    public void setStarth(int starth) {
        this.starth = starth;
    }

    public int getStartm() {
        return startm;
    }

    public void setStartm(int startm) {
        this.startm = startm;
    }

    public int getEndh() {
        return endh;
    }

    public void setEndh(int endh) {
        this.endh = endh;
    }

    public int getEndm() {
        return endm;
    }

    public void setEndm(int endm) {
        this.endm = endm;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
