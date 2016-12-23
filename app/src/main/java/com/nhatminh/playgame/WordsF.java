package com.nhatminh.playgame;

import com.nhatminh.training.Words;
import com.nhatminh.vocabulary.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by caonh on 10/26/2016.
 */
public class WordsF{
    private int id;
    private String tenTV;
    private String nghiaTA;
    private String nghiadung;
    private String nghia1;
    private String nghia2;
    private String nghia3;
    private String nghia4;
    private String nghia5;
    private String nghia6;
    private String nghia7;
    private String nghia8;
    private String nghia9;
    private String nghia10;
    private String vidu;
    private ArrayList<String> listnghiasai = new ArrayList<String>();

    public WordsF(){}
    public WordsF(int id, String tenTV, String nghiadung, String nghia1,String nghia2,String nghia3,String nghia4,String nghia5,
                  String nghia6,String nghia7,String nghia8,String nghia9,String nghia10)
    {
        this.id = id;
        this.tenTV = tenTV;
        this.nghiadung = nghiadung;
        this.nghia1 = nghia1;
        this.nghia2 = nghia2;
        this.nghia3 = nghia3;
        this.nghia4 = nghia4;
        this.nghia5 = nghia5;
        this.nghia6 = nghia6;
        this.nghia7 = nghia7;
        this.nghia8 = nghia8;
        this.nghia9 = nghia9;
        this.nghia10 = nghia10;

        listnghiasai.add(nghia1);
        listnghiasai.add(nghia2);
        listnghiasai.add(nghia3);
        listnghiasai.add(nghia4);
        listnghiasai.add(nghia5);
        listnghiasai.add(nghia6);
        listnghiasai.add(nghia7);
        listnghiasai.add(nghia8);
        listnghiasai.add(nghia9);
        listnghiasai.add(nghia10);

    }

    public String randomWrongWord()
    {
        Random r = new Random();
        int n = r.nextInt(10);

        return listnghiasai.get(n);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNghia1() {
        return nghia1;
    }

    public void setNghia1(String nghia1) {
        this.nghia1 = nghia1;
    }

    public String getNghia2() {
        return nghia2;
    }

    public void setNghia2(String nghia2) {
        this.nghia2 = nghia2;
    }

    public String getNghia3() {
        return nghia3;
    }

    public void setNghia3(String nghia3) {
        this.nghia3 = nghia3;
    }

    public String getNghia4() {
        return nghia4;
    }

    public void setNghia4(String nghia4) {
        this.nghia4 = nghia4;
    }

    public String getNghia5() {
        return nghia5;
    }

    public void setNghia5(String nghia5) {
        this.nghia5 = nghia5;
    }

    public String getNghia6() {
        return nghia6;
    }

    public void setNghia6(String nghia6) {
        this.nghia6 = nghia6;
    }

    public String getNghia7() {
        return nghia7;
    }

    public void setNghia7(String nghia7) {
        this.nghia7 = nghia7;
    }

    public String getNghia8() {
        return nghia8;
    }

    public void setNghia8(String nghia8) {
        this.nghia8 = nghia8;
    }

    public String getNghia9() {
        return nghia9;
    }

    public void setNghia9(String nghia9) {
        this.nghia9 = nghia9;
    }

    public String getNghia10() {
        return nghia10;
    }

    public void setNghia10(String nghia10) {
        this.nghia10 = nghia10;
    }

    public String getVidu() {
        return vidu;
    }

    public void setVidu(String vidu) {
        this.vidu = vidu;
    }
}
