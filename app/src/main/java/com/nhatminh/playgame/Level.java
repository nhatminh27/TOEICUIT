package com.nhatminh.playgame;

import com.nhatminh.training.Lessons;
import com.nhatminh.training.Words;

/**
 * Created by caonh on 10/26/2016.
 */
public class Level {
    private Lessons lessons;
    private boolean isCheck;

    public Level(){}

    public  Level(Lessons l)
    {
        this.lessons = l;
        this.isCheck = false;
    }

    public Lessons getLessons() {
        return lessons;
    }

    public void setLessons(Lessons lessons) {
        this.lessons = lessons;
    }

    public boolean getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public void checked()
    {
        if (this.isCheck)
            this.isCheck = false;
        else
            this.isCheck = true;
    }
}
