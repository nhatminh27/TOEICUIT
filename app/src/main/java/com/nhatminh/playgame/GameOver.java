package com.nhatminh.playgame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nhatminh.vocabulary.MainActivity;
import com.nhatminh.vocabulary.MyApplication;
import com.nhatminh.vocabulary.R;

public class GameOver extends Activity {

    TextView newscore, highscore, txtnew, txthigh;
    Button play;
    int bestscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        newscore = (TextView)findViewById(R.id.newscore);
        highscore = (TextView)findViewById(R.id.highscore);
        txtnew = (TextView)findViewById(R.id.txtnew);
        txthigh = (TextView)findViewById(R.id.txthigh);

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/dream.ttf");
        txtnew.setTypeface(myTypeface);
        txthigh.setTypeface(myTypeface);
        newscore.setTypeface(myTypeface);
        highscore.setTypeface(myTypeface);

        bestscore= MyApplication.dtb.BestScore();

        MyApplication.playMusicLose();

        play = (Button)findViewById(R.id.btn_gameover);

        newscore.setText("" + MyApplication.score);

        highscore.setText(""+bestscore);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int minScore = MyApplication.dtb.MinScore();
//                Log.i("diem","newscore "+MyApplication.score+"   min  "+minScore);

                if(MyApplication.score > minScore)
                {
                    Dialog();

                }
                else
                    finish();
            }
        });
    }
    public void Dialog(){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Save Score");
        b.setMessage("Lọt vào Top 10\nBạn có muốn lưu điểm hơm???");
        b.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent t = new Intent(GameOver.this, SaveScore.class);
                        Bundle b = new Bundle();
                        b.putInt("Diem",MyApplication.score);
                        t.putExtra("Score",b);
                        startActivity(t);
                        finish();
                    }
                });

        b.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        // TODO Auto-generated method stub
                        finish();
                        dialog.cancel();
                    }
                });
        b.show();
    }


}
