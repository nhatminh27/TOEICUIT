package com.nhatminh.note;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // Service tu dong chay lai neu chuong trinh bi tat
        // Giup cho service cua minh chay hoai ko bi tat - (tru khi restart may lai thoi)

        initUnlockScreenEvent();
        return START_STICKY;
    }

    public void onCreate() {
        super.onCreate();


    }
    public void initUnlockScreenEvent() {
        try {
            IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
            filter.addAction(Intent.ACTION_SCREEN_OFF);
            filter.addAction(Intent.ACTION_USER_PRESENT);
            BroadcastReceiver mReceiver = new MyReceiver();
            registerReceiver(mReceiver, filter);
        } catch (Exception e) {

        }
    }

}
