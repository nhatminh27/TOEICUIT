package com.nhatminh.playgame;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.nhatminh.adapter.AdapterScore;
import com.nhatminh.vocabulary.MyApplication;
import com.nhatminh.vocabulary.R;

import java.util.ArrayList;

public class Highscore extends AppCompatActivity {

    AdapterScore ad;
    ListView lv;
    ArrayList<Player> p;
    TextView score;
    Typeface myTypeface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        lv = (ListView)findViewById(R.id.lv_highscore);
        score = (TextView)findViewById(R.id.txtscore_highscore);

        myTypeface = Typeface.createFromAsset(getAssets(), "fonts/sketch.ttf");
        score.setTypeface(myTypeface);

        p = MyApplication.dtb.TopPlayer();
        ad = new AdapterScore(Highscore.this, R.layout.adapterlistview, p);
        lv.setAdapter(ad);
    }
}
