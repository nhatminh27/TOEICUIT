package com.nhatminh.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhatminh.playgame.Level;
import com.nhatminh.training.Lessons;
import com.nhatminh.vocabulary.R;

import java.util.List;

/**
 * Created by caonh on 10/26/2016.
 */
public class AdapterListLevel extends ArrayAdapter<Level> {
    private Activity context;
    private int layout;
    private List<Level> list;
    private Typeface myTypeface;

    public AdapterListLevel(Context context, int textViewResourceId,List<Level> objects) {
        super(context, textViewResourceId, objects);

        this.context=(Activity) context;
        this.layout=textViewResourceId;
        this.list=objects;
        this.myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/sketch.ttf");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater flater=context.getLayoutInflater();
        View row=flater.inflate(layout, parent,false);

        ImageView isCheck =(ImageView)row.findViewById(R.id.img_listlv);
        TextView tenBH =(TextView) row.findViewById(R.id.txt_listlv);
        tenBH.setTypeface(myTypeface);

        // Lay ra nhan vien tu toa do
        Level l = list.get(position);

        String ten = l.getLessons().getTenTA().toString();

        boolean ischeck = l.getIsCheck();

      //  Log.i("check",ten +" / "+ ischeck);

            if(ischeck)  {
                isCheck.setImageResource(R.drawable.check);
            }
        else
                isCheck.setImageResource(R.drawable.uncheck);



        tenBH.setText(ten);

        return row;
    }
}