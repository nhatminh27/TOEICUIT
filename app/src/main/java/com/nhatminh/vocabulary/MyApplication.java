package com.nhatminh.vocabulary;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.util.Log;

import com.nhatminh.database.MyDatabase;
import com.nhatminh.note.MyReceiver;
import com.nhatminh.note.MyService;
import com.nhatminh.note.Times;
import com.nhatminh.playgame.WordsF;
import com.nhatminh.training.Words;

import java.util.ArrayList;

/**
 * Created by caonh on 19/10/2016.
 */
public class MyApplication extends Application
{
    public static MyDatabase dtb;

    public static int onoff;
    public static int starth;
    public static int startm;
    public static int endh;
    public static int endm;
    public static int mode;
    public static Context context;
    public static int currentFragment;
    public static int score;

    public static boolean trainingFragment = true;

    public static MediaPlayer win, win2, clap,background;

    public static ArrayList<Integer> listWordGame = new ArrayList<Integer>();
    public static ArrayList<WordsF> listWordF = new ArrayList<WordsF>();

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = this;
        dtb = new MyDatabase(this);
        createDatabase();
        openDatabase();
        createTimes();
        initService();

        background = MediaPlayer.create(context, R.raw.background);
        background.setLooping(true);
        win = MediaPlayer.create(context, R.raw.score);
        win.setLooping(false);
        win2 = MediaPlayer.create(context, R.raw.gameover);
        win2.setLooping(false);
        clap = MediaPlayer.create(context, R.raw.clap);
        clap.setLooping(false);
    }
    public void createDatabase(){
        try
        {
            dtb.isCreatedDatabase();

        }
        catch(Exception e){}
    }
    public void openDatabase(){
        try{
            dtb.openDataBase();
        }
        catch (Exception e){}
    }
    public static void createTimes()
    {
        try {
            Times t = dtb.getTimes();
            onoff = t.getOnoff();
            starth = t.getStarth();
            endh = t.getEndh();
            startm = t.getStartm();
            endm = t.getEndm();
            mode = t.getMode();
        }
        catch (Exception e)
        {
            onoff = 0;
            starth = 12;
            endh = 0;
            startm = 13;
            endm = 0;
            mode = 0;
        }
    }
    public void initService()
    {
        Intent t = new Intent(context, MyService.class);
        startService(t);
    }
    public static void getListWordsF(ArrayList<Integer> listId)
    {
        listWordF.clear();
        for (int i=0; i<listId.size();i++)
        {
            // thêm các từ có bài học = id của các bài đc check
            listWordF.addAll(dtb.getListFalseWords(listId.get(i)));
        }
    }
    public static void test()
    {
        int soluong = listWordF.size();
        Log.i("test", " " + soluong);
    }
    public static void stopMusicBackground()
    {
        try {
            if (background.isPlaying())
                background.pause();
        }
        catch (Exception e)
        {}
    }
    public static void playMusicLose()
    {
        // Lap lai khi phat xong

        if(win2.isPlaying())
            win2.pause();

        win2.start();
    }
    public static void playMusicWin()
    {
        // Lap lai khi phat xong

        if(win.isPlaying())
            win.pause();

        win.start();
    }
    public static void playMusicClap()
    {
        // Lap lai khi phat xong

        if(clap.isPlaying())
            clap.pause();

        clap.start();
    }
    public static void playMusicBackGround()
    {
        // Lap lai khi phat xong


            if (background.isPlaying())
                background.pause();

            background.start();

    }

}
