package com.nhatminh.training;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.nhatminh.adapter.AdapterContents2;
import com.nhatminh.vocabulary.MainActivity;
import com.nhatminh.vocabulary.MyApplication;
import com.nhatminh.vocabulary.R;
import com.nhatminh.vocabulary.TrainingActivity;

import java.util.ArrayList;

public class Contents extends AppCompatActivity {

    ListView lv;
    AdapterContents2 ad;
    ArrayList<Words> lw ;

    MediaPlayer m = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents);

        LoadContents();

        lv = (ListView) findViewById(R.id.lv_contents);
        ad = new AdapterContents2(this,R.layout.list_words,lw);
        lv.setAdapter(ad);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = "Audio/"+(lw.get(position).getTenTV()).trim()+".mp3";
                playSound(s);
            }
        });


        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Words w =  lw.get(position);
                showDialog(w);
                return true;
            }
        });
    }

    public void showDialog(final Words w)
    {
        AlertDialog.Builder b = new AlertDialog.Builder(this);		// getActivity() thay bằng tên Activity.this nếu đang dùng trên Activity
        b.setTitle("Favorite");
        b.setMessage("Add <" + w.getTenTV() + "> To Favorite?");
        b.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something
                        MyApplication.dtb.addToFavorite(w);
                    }
                });

        b.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.cancel();
                    }
                });
        b.show();
    }

    public void LoadContents()
    {
        try
        {
            lw = MyApplication.dtb.getListWords(getMaMH());
        }
        catch (Exception ex){
            Log.i("Loi",""+ex);
        }
    }

    public String getMaMH(){
        if(MyApplication.trainingFragment) {
            if (Training.selectedContents < 10) {
                return "L0" + Training.selectedContents;

            } else
                return "L" + Training.selectedContents;
        }
        else{
            if (TrainingActivity.selectedContents < 10) {
                return "L0" + TrainingActivity.selectedContents;

            } else
                return "L" + TrainingActivity.selectedContents;
        }
    }
    public void playSound(String s)
    {
        try {
            AssetFileDescriptor descriptor = getAssets().openFd(s);
            m.reset();
            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            m.prepare();
            m.start();

        }
        catch (Exception e){
            Log.i("xxxMedia",e+"");
        }
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        m.release();
    }

}
