package com.nhatminh.note;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.nhatminh.training.Words;
import com.nhatminh.vocabulary.MyApplication;

import java.util.Calendar;

public class MyReceiver extends BroadcastReceiver {

    Context context;
    @Override
    public void onReceive(Context context, Intent intent) {

        this.context = context;
        if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            Log.i("receiver", "screen on");
        }
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Log.i("receiver", "screen off");
        }
        if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
            Dialog();
        }
    }

    public void Dialog()
    {

        try {
            if (MyApplication.onoff == 1) {
                if (MyApplication.mode == 0) {


                    Intent i = new Intent();
                    i.setClassName("com.nhatminh.vocabulary", "com.nhatminh.vocabulary.ShowFavoriteWords");
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                } else {
                    if (getTime()) {
                        Intent i = new Intent();
                        i.setClassName("com.nhatminh.vocabulary", "com.nhatminh.vocabulary.ShowFavoriteWords");
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);
                    }
                }
            }
        }
        catch (Exception e){
            Log.i("receiver", "kkkk off" + e);
        }
    }

    public boolean getTime()
    {
        int starth = MyApplication.starth;
        int startm = MyApplication.startm;
        int endh = MyApplication.endh;
        int endm = MyApplication.endm;

        Calendar c = Calendar.getInstance();
        int minutes = c.get(Calendar.MINUTE);
        int hours = c.get(Calendar.HOUR_OF_DAY);

        Log.i("time","starth " + starth);
        Log.i("time","startm " + startm);
        Log.i("time","endh " + endh);
        Log.i("time","endm " + endm);
        Log.i("time","h " + hours);
        Log.i("time","m " + minutes);


        if(starth < endh)
        {
            if(starth<hours && hours< endh)
            {
                return true;
            }
            else
            {
                if(starth == hours)
                {
                    if(startm <minutes)
                    {
                        return true;
                    }
                    else
                        return false;
                }
                else
                    if(endh == hours)
                    {
                        if(endm > minutes)
                        {
                            return true;
                        }
                        else
                            return false;
                    }
                    else
                        return false;
            }

        }
        else

            if(starth > endh) {
                int _starth = endh;
                int _endh = starth;
                int _startm = endm;
                int _endm = startm;
                if (_starth < hours && hours < _endh) {
                    return false;
                } else {
                    if (_starth == hours) {
                        if (_startm < minutes) {
                            return false;
                        } else
                            return true;
                    } else if (_endh == hours) {
                        if (_endm > minutes) {
                            return false;
                        } else
                            return true;
                    } else
                        return true;
                }
            }
            else
            {
                if(hours == starth)
                {
                    if(minutes>startm && minutes<endm)
                        return true;
                    else
                        return false;
                }
                else
                    return false;
            }

    }
}

