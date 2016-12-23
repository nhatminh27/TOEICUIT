package com.nhatminh.vocabulary;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nhatminh.adapter.AdapterContents2;
import com.nhatminh.adapter.AdapterListLessons2;
import com.nhatminh.note.Favorite;
import com.nhatminh.training.Contents;
import com.nhatminh.training.Lessons;
import com.nhatminh.training.Words;

import java.util.ArrayList;

/**
 * Created by caonh on 26-Nov-16.
 */
public class FavoriteActivity extends AppCompatActivity {

    ListView lv;
    AdapterContents2 ad;
    ArrayList<Words> lw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Favorite");
        setContentView(R.layout.fragment_favorite);

        lv = (ListView) findViewById(R.id.lv_favorite);

        lw = MyApplication.dtb.getListFavorite();
        ad = new AdapterContents2(FavoriteActivity.this, R.layout.list_words, lw);
        lv.setAdapter(ad);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Words w = lw.get(position);
                showDialog(w);
                return true;
            }
        });

    }
    public void showDialog(final Words w) {
        AlertDialog.Builder b = new AlertDialog.Builder(FavoriteActivity.this);        // getActivity() thay bằng tên Activity.this nếu đang dùng trên Activity
        b.setTitle("Delete");
        b.setMessage("Delete <" + w.getTenTV() + ">?");
        b.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something
                        MyApplication.dtb.deleteFavorite(w.getId());
                        lw.clear();
                        //Hien thi lai lan nua
                        lw = MyApplication.dtb.getListFavorite();
                        ad = new AdapterContents2(FavoriteActivity.this, R.layout.list_words, lw);
                        lv.setAdapter(ad);

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

}
