package com.nhatminh.playgame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nhatminh.vocabulary.MyApplication;
import com.nhatminh.vocabulary.R;

import java.util.Random;

public class Games extends Activity {

    MyAssyntask ass;
    Button a,b;
    TextView txttenTv, txtscore;
    String tenTv,nghiadung,nghiasai;
    int traiphai;
    Animation animation, animationbtn, animationscore;
    Typeface myTypeface, myTypeface1, myTypeface2;

    int numberQuestion = MyApplication.listWordGame.size();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        MyApplication.score = -1;
        a = (Button) findViewById(R.id.btna_game);
        b = (Button) findViewById(R.id.btnb_game);

        myTypeface = Typeface.createFromAsset(getAssets(), "fonts/home.otf");
        myTypeface1 = Typeface.createFromAsset(getAssets(), "fonts/pokemon1.ttf");
        myTypeface2 = Typeface.createFromAsset(getAssets(), "fonts/windsorb.ttf");

        txttenTv = (TextView) findViewById(R.id.tentv_game);
        txtscore = (TextView) findViewById(R.id.score_game);

        txttenTv.setTypeface(myTypeface);
        txtscore.setTypeface(myTypeface1);
        a.setTypeface(myTypeface2);
        b.setTypeface(myTypeface2);

        animationbtn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move1);
        animationscore = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);

        ass = new MyAssyntask(this);
        ass.execute();

        loadQuestion();

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String traloi = a.getText().toString();
                check(traloi);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String traloi = b.getText().toString();
                check(traloi);
            }
        });
    }

    public void check(String traloi)
    {
        if(traloi.equalsIgnoreCase(nghiadung))
        {
            ass.Thang();
            loadQuestion();
        }
        else
        {
            ass.Thua();
            finish();
        }
    }

    public void loadQuestion()
    {
        MyApplication.score++;
        if(MyApplication.score %5 == 0 && MyApplication.score < 65)
            ass.updateSpeed(100 - MyApplication.score);
        if(MyApplication.score >= numberQuestion)
        {
            Intent t = new Intent(Games.this, Winner.class);
            startActivity(t);
            ass.updateStop();
            finish();
            return;
        }
        int id = MyApplication.listWordGame.get(MyApplication.score);
        WordsF w = MyApplication.listWordF.get(id);

        Random r = new Random();
        traiphai = r.nextInt(2);

        tenTv = w.getTenTV();
        nghiadung = w.getNghiadung();
        nghiasai = w.randomWrongWord();

        txttenTv.startAnimation(animation);
        a.startAnimation(animationbtn);
        b.startAnimation(animationbtn);
        txtscore.startAnimation(animationscore);

        txttenTv.setText(tenTv);
        if(traiphai == 0)
        {
            a.setText(nghiadung);
            b.setText(nghiasai);
        }
        else
        {
            a.setText(nghiasai);
            b.setText(nghiadung);
        }
        txtscore.setText(""+MyApplication.score);


    }

    @Override
    public void onBackPressed()
    {
//        super.onBackPressed();
        ass.pauseAss();
        Dialog();
        //Toast.makeText(this,"exit",Toast.LENGTH_SHORT).show();
    }

    public void Dialog(){
        AlertDialog.Builder b = new AlertDialog.Builder(Games.this);
        b.setTitle("Exit");
        b.setMessage("Do you want to exit?");
        b.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ass.resumeAss();
                            ass.updateStop();
                            finish();
                    }
                });

        b.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        // TODO Auto-generated method stub
                        ass.resumeAss();
                        dialog.cancel();
                    }
                });
        b.show();
    }



}
