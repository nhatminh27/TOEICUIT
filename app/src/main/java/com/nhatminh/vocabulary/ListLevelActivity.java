package com.nhatminh.vocabulary;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nhatminh.adapter.AdapterListLevel;
import com.nhatminh.adapter.AdapterScore;
import com.nhatminh.playgame.Games;
import com.nhatminh.playgame.Level;
import com.nhatminh.playgame.Player;
import com.nhatminh.playgame.RandomArray;

import java.util.ArrayList;

/**
 * Created by caonh on 26-Nov-16.
 */
public class ListLevelActivity extends AppCompatActivity {

    ListView lv;
    ImageButton play;
    AdapterListLevel ad;
    ArrayList<Level> listLv;
    ArrayList<Integer> listIdChecked = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Play games");
        setContentView(R.layout.fragment_list_level);
        lv = (ListView) findViewById(R.id.lv_listlv);
        play = (ImageButton) findViewById(R.id.btnplay_listlv);

        listLv = MyApplication.dtb.getListLevel();
        ad = new AdapterListLevel(ListLevelActivity.this,R.layout.list_level,listLv);
        lv.setAdapter(ad);
        //Cap nhat thay doi cho list view (check - uncheck)
        ad.setNotifyOnChange(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listLv.get(position).checked();
                ad.notifyDataSetChanged();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getListIdChecked();
                if(listIdChecked.size() == 0)
                {
                    Toast.makeText(ListLevelActivity.this, "Bạn Chưa Chọn Bài Kiểm Tra", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    MyApplication.getListWordsF(listIdChecked);
                    MyApplication.test();

                    RandomArray r = new RandomArray(MyApplication.listWordF.size());
                    MyApplication.listWordGame.clear();
                    MyApplication.listWordGame.addAll(r.getValue()) ;

                    Intent t = new Intent(ListLevelActivity.this,Games.class);
                    startActivity(t);

                }
            }
        });

    }

    //Lấy ra id của những bài học đc check (1,3,5)
    public void getListIdChecked()
    {
        listIdChecked.clear();
        for(int i =0; i<listLv.size(); i++)
        {
            if(listLv.get(i).getIsCheck())
            {
                int id = listLv.get(i).getLessons().getMaBH();
                listIdChecked.add(id);
            }
        }
    }

}
