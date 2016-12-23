package com.nhatminh.playgame;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.nhatminh.adapter.AdapterListLevel;
import com.nhatminh.vocabulary.MyApplication;
import com.nhatminh.vocabulary.R;

import java.util.ArrayList;

public class ListLevel extends Fragment {
    ListView lv;
    ImageButton play;
    AdapterListLevel ad;
    ArrayList<Level> listLv;
    ArrayList<Integer> listIdChecked = new ArrayList<Integer>();
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_level, viewGroup, false);

        lv = (ListView) view.findViewById(R.id.lv_listlv);
        play = (ImageButton) view.findViewById(R.id.btnplay_listlv);

        listLv = MyApplication.dtb.getListLevel();
        ad = new AdapterListLevel(getActivity(),R.layout.list_level,listLv);
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
                    Toast.makeText(getActivity(),"Bạn Chưa Chọn Bài Kiểm Tra", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    MyApplication.getListWordsF(listIdChecked);
                    MyApplication.test();

                    RandomArray r = new RandomArray(MyApplication.listWordF.size());
                    MyApplication.listWordGame.clear();
                    MyApplication.listWordGame.addAll(r.getValue()) ;

                    for(int i =0; i<MyApplication.listWordGame.size(); i++)
                        Log.i("random",""+MyApplication.listWordGame.get(i));

                    Intent t = new Intent(getActivity(),Games.class);
                    startActivity(t);

                }
            }
        });

        return view;
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
