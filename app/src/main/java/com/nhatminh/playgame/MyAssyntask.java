package com.nhatminh.playgame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;

import com.nhatminh.vocabulary.MyApplication;
import com.nhatminh.vocabulary.R;

/**
 * Created by caonh on 28/09/2016.
 */
public class MyAssyntask extends AsyncTask<Void, Integer, Void> {

    Activity activityGame;
    int timer = 100;
    int speed = 100;

    boolean stop = true;
    boolean loop = true;
    boolean isPause = false;



    public MyAssyntask(Activity activity) {
        activityGame = activity;
    }

    // Ham duoc thuc hien dau tien
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    // Ham nay se chay tiep theo
    // Khong duoc cap nhat giao dien trong ham nay
    @Override
    protected Void doInBackground(Void... arg2) {

        while (loop) {
            SystemClock.sleep(speed);

            if(!isPause) {
                timer--;
                publishProgress(timer);
                if (timer <= 0) {
                    loop = false;

                }
            }
        }

        return null;
    }

    // Ham cap nhat giao dien
    @Override
    protected void onProgressUpdate(Integer... values) {
        ProgressBar bar = (ProgressBar) activityGame.findViewById(R.id.progressbar);

        int color = 0xFFff0000;
        bar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        bar.getProgressDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);


        int value = values[0];
        bar.setProgress(value);

    }

    // Khi thuc hien tien trinh xong thi toi ham nay tra ve ket qua
    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        if(stop)
        {

            Intent t = new Intent(activityGame, GameOver.class);
            activityGame.startActivity(t);
            activityGame.finish();
        }

    }
    protected void Thua(){
        timer = 5;
    }
    protected void Thang(){
        timer = 110;
        MyApplication.playMusicWin();

    }
    protected  void updateSpeed(int speed)
    {
        if(speed > 10)
            this.speed = speed;
    }
    protected  void updateStop()
    {
        timer = 5;
        stop = false;

    }
    protected  void pauseAss()
    {
        isPause = true;
    }
    protected  void resumeAss()
    {
        isPause = false;
    }
}

