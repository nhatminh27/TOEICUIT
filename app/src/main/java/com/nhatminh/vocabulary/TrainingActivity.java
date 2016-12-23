package com.nhatminh.vocabulary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nhatminh.adapter.AdapterContents2;
import com.nhatminh.adapter.AdapterListLessons2;
import com.nhatminh.training.Contents;
import com.nhatminh.training.Lessons;
import com.nhatminh.training.Words;

import java.util.ArrayList;

/**
 * Created by caonh on 05-Nov-16.
 */
public class TrainingActivity extends AppCompatActivity {
    ListView lv;
    AdapterListLessons2 ad;
    ArrayList<Lessons> list;
    public static int selectedContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Training");
        setContentView(R.layout.fragment_training);

        list = MyApplication.dtb.getListLessons();
        lv = (ListView) findViewById(R.id.lv_training);
        ad = new AdapterListLessons2(TrainingActivity.this,R.layout.list_lesson,list);
        lv.setAdapter(ad);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyApplication.trainingFragment = false;
                selectedContents = position + 1;
                Intent t = new Intent(TrainingActivity.this, Contents.class);
                startActivity(t);
            }
        });
    }
}
