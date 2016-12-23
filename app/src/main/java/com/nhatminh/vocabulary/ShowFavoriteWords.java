package com.nhatminh.vocabulary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.nhatminh.training.Words;

public class ShowFavoriteWords extends Activity {
    TextView title, nghiaTV, vidu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_show_favorite_words);

        title = (TextView) findViewById(R.id.title_showfavor);
        nghiaTV = (TextView) findViewById(R.id.nghiaTV_showfavor);
        vidu = (TextView) findViewById(R.id.vidu_showfavor);

        Words w = MyApplication.dtb.getRandomWord();

        String tenTV = w.getTenTV();
        String nghiadung = w.getNghiadung();
        String vidU = w.getVidu();

        title.setText(tenTV);
        nghiaTV.setText(nghiadung);
        vidu.setText(vidU);

    }

}
