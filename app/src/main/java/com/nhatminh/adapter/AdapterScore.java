package com.nhatminh.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhatminh.playgame.Player;
import com.nhatminh.vocabulary.R;

import java.util.List;

/**
 * Created by caonh on 30/09/2016.
 */
public class AdapterScore extends ArrayAdapter<Player> {
    private Activity context;
    private int layout;
    private List<Player> list;

    public AdapterScore(Context context, int textViewResourceId, List<Player> objects) {
        super(context, textViewResourceId, objects);

        this.context = (Activity) context;
        this.layout = textViewResourceId;
        this.list = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater flater = context.getLayoutInflater();
        View row = flater.inflate(layout, parent, false);

        ImageView avata = (ImageView) row.findViewById(R.id.imageView3);
        TextView ten = (TextView) row.findViewById(R.id.txtten_high);
        TextView diem = (TextView) row.findViewById(R.id.txtdiem_high);

        // Lay ra nhan vien tu toa do
        Player p = list.get(position);

        String tten = p.getName();
        int tdiem = p.getScore();

        ten.setText(tten);
        diem.setText(""+tdiem);

        return row;
    }
}
