package com.nhatminh.note;

import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.nhatminh.vocabulary.MainActivity;
import com.nhatminh.vocabulary.MyApplication;
import com.nhatminh.vocabulary.R;

import java.util.Calendar;

public class Settings extends Fragment {

    RadioButton on, off, cus, alw;
    ImageButton ques, start, end;
    TextView txtstart, txtend;
    FrameLayout frame, frame1;
    boolean isStart;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, viewGroup, false);

        on = (RadioButton) view.findViewById(R.id.on_set);
        off = (RadioButton) view.findViewById(R.id.off_set);
        cus = (RadioButton) view.findViewById(R.id.cus_set);
        alw = (RadioButton) view.findViewById(R.id.alw_set);

        ques = (ImageButton) view.findViewById(R.id.ques_set);
        start = (ImageButton) view.findViewById(R.id.startclock_set);
        end = (ImageButton) view.findViewById(R.id.endclock_set);

        txtstart = (TextView) view.findViewById(R.id.start_set);
        txtend = (TextView) view.findViewById(R.id.end_set);

        frame = (FrameLayout) view.findViewById(R.id.frame_set);
        frame1 = (FrameLayout) view.findViewById(R.id.frame1_set);

        showOnOff();
        showMode();
        showTime();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStart = true;
                showTimePickerDialog();
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStart = false;
                showTimePickerDialog();
            }
        });
        alw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMode(false);
            }
        });
        cus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMode(true);
            }
        });
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MyApplication.dtb.getListFavorite().size() == 0)
                {
                    Toast.makeText(getActivity(),"Not Favorite Words?",Toast.LENGTH_SHORT).show();
                    setOnOff(false);
                    return;
                }
                setOnOff(true);
            }
        });
        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnOff(false);
            }
        });


        return view;
    }
    public void showTimePickerDialog()
    {
        TimePickerDialog.OnTimeSetListener callback=new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view,int hourOfDay, int minute) {

                if(isStart)
                {
                    int starthour = hourOfDay  ;
                    int startminute = minute;
                    setTime(isStart, starthour, startminute);
                }
                else
                {
                    int endhour = hourOfDay  ;
                    int endminute = minute ;
                    setTime(isStart,endhour,endminute);
                }

            }

        };

        // Gio phut khi moi hien dialog len
        int hour, minute;
        if(isStart)
        {
            hour = MyApplication.starth;
            minute = MyApplication.startm;
        }
        else
        {
            hour = MyApplication.endh;
            minute = MyApplication.endm;
        }
        TimePickerDialog time=new TimePickerDialog(getActivity(),callback, hour, minute, true);
        time.setTitle("Set Time");
        time.show();
    }
    public String convertTime(int time)
    {
        if(time < 10)
            return  "0" + time;
        else
            return "" + time;
    }
    public void showOnOff()
    {
        if(MyApplication.onoff == 0)
        {
            off.setChecked(true);
            on.setChecked(false);
            frame1.setVisibility(View.GONE);
        }
        else
        {
            off.setChecked(false);
            on.setChecked(true);
            frame1.setVisibility(View.VISIBLE);
        }
    }
    public void showMode()
    {
        if(MyApplication.mode ==0)
        {
            cus.setChecked(false);
            alw.setChecked(true);
            frame.setVisibility(View.GONE);
        }
        else
        {
            cus.setChecked(true);
            alw.setChecked(false);
            frame.setVisibility(View.VISIBLE);
        }

    }
    public void showTime()
    {
        txtstart.setText(convertTime(MyApplication.starth) +":"+convertTime(MyApplication.startm) );
        txtend.setText(convertTime(MyApplication.endh) +":"+convertTime(MyApplication.endm) );
    }
    public void setOnOff(boolean b)
    {
        if(b)
        {
            ContentValues cv = new ContentValues();
            cv.put("onoff",1);
            MyApplication.dtb.updateTimes(cv);
            MyApplication.createTimes();
        }
        else
        {
            ContentValues cv = new ContentValues();
            cv.put("onoff",0);
            MyApplication.dtb.updateTimes(cv);
            MyApplication.createTimes();
        }
        showOnOff();
    }
    public void setMode(boolean b)
    {
        if(b)
        {
            ContentValues cv = new ContentValues();
            cv.put("mode",1);
            MyApplication.dtb.updateTimes(cv);
            MyApplication.createTimes();
        }
        else
        {
            ContentValues cv = new ContentValues();
            cv.put("mode",0);
            MyApplication.dtb.updateTimes(cv);
            MyApplication.createTimes();
        }
        showMode();
    }
    public void setTime(boolean b,int h, int m)
    {
        if(b)
        {
            ContentValues cv = new ContentValues();
            cv.put("starth",h);
            cv.put("startm",m);
            MyApplication.dtb.updateTimes(cv);
            MyApplication.createTimes();
        }
        else
        {
            ContentValues cv = new ContentValues();
            cv.put("endh",h);
            cv.put("endm",m);
            MyApplication.dtb.updateTimes(cv);
            MyApplication.createTimes();
        }
        showTime();
    }
}

