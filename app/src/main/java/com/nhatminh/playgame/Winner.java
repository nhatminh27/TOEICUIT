package com.nhatminh.playgame;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.nhatminh.vocabulary.MyApplication;
import com.nhatminh.vocabulary.R;

public class Winner extends Activity {

    ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winner);

        im = (ImageView) findViewById(R.id.image_win);

        MyApplication.playMusicClap();

        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(Winner.this, SaveScore.class);
                Bundle b = new Bundle();
                b.putInt("Diem", MyApplication.score);
                t.putExtra("Score",b);
                startActivity(t);
                finish();
            }
        });
    }


}
