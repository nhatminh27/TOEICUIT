package com.nhatminh.training;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nhatminh.adapter.AdapterListLessons2;
import com.nhatminh.vocabulary.MyApplication;
import com.nhatminh.vocabulary.R;

import java.util.ArrayList;

public class Training extends Fragment {

    ListView lv;
    AdapterListLessons2 ad;
    ArrayList<Lessons> list;
    public static int selectedContents;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_training, viewGroup, false);

        list = MyApplication.dtb.getListLessons();
        lv = (ListView) view.findViewById(R.id.lv_training);
        ad = new AdapterListLessons2(getActivity(),R.layout.list_lesson,list);
        lv.setAdapter(ad);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyApplication.trainingFragment = true;
                selectedContents = position + 1;
                Intent t = new Intent(getActivity(), Contents.class);
                startActivity(t);
            }
        });

        return view;
    }
}

