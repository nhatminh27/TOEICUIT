package com.nhatminh.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhatminh.training.Lessons;
import com.nhatminh.training.Words;
import com.nhatminh.vocabulary.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caonh on 05/10/2016.
 */
public class AdapterContents2 extends ArrayAdapter<Words> {
    private Activity context;
    private int layout;
    private ArrayList<Words> list;
    private Typeface myTypeface1;
    View row;
    public AdapterContents2(Context context, int textViewResourceId, ArrayList<Words> objects)
    {
        super(context, textViewResourceId, objects);

        this.context = (Activity) context;
        this.layout = textViewResourceId;
        this.list = objects;
        this.myTypeface1 = Typeface.createFromAsset(context.getAssets(), "fonts/circle.ttf");
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        row = convertView;
        final ListLessonHolder holder;

        if(row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layout, parent, false);


            holder = new ListLessonHolder();
            holder.avata =(ImageView)row.findViewById(R.id.im_listword);
            holder.tentv =(TextView) row.findViewById(R.id.tu_listword);
            holder.nghiatv =(TextView) row.findViewById(R.id.nghiatv_listword);
            holder.vidu =(TextView) row.findViewById(R.id.vidu_listword);

            //holder.tentv.setTypeface(myTypeface1);
            //holder.nghiatv.setTypeface(myTypeface1);
            //holder.vidu.setTypeface(myTypeface1);

            row.setTag(holder);
        }
        else
        {
            holder = (ListLessonHolder)row.getTag();
        }
        Words w = list.get(position);

        String tenTV = w.getTenTV().toString() ;
        //  String nghiaTA = w.getNghiaTA().toString() ;
        String nghiadung = w.getNghiadung().toString() ;
        String viDu = w.getVidu().toString() ;
        int id = w.getId();

        holder.tentv.setText(tenTV);
        holder.nghiatv.setText(nghiadung);
        holder.vidu.setText(viDu);

        String tentuvung = w.getTenTV();
        String tv;
        if(tentuvung.endsWith(" ")){
            tv = tentuvung.substring(0,holder.tentv.length() - 1);
        }
        else
            tv = tentuvung;
        Bitmap a = getBitmapFromAsset("ImageWord/" + tv.toString() + ".jpg");
        holder.avata.setImageBitmap(a);

        Log.i("eee","hhhh");

        return row;
    }
    public static class ListLessonHolder
    {
        ImageView avata;
        TextView tentv;
        TextView nghiatv;
        TextView vidu;
    }
    private Bitmap getBitmapFromAsset(String strName)
    {
        AssetManager assetManager = context.getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open(strName);
        } catch (IOException e) {
            Log.i("rrr","loi "+e);
        }
        Bitmap bitmap = BitmapFactory.decodeStream(istr);
        return bitmap;
    }
//private Bitmap getBitmapFromAsset2(final String strName)
//{
//
//    final Bitmap[] aaa = new Bitmap[1];
//
//    new Thread(new Runnable() {
//        public void run() {
//            AssetManager assetManager = context.getAssets();
//            InputStream istr = null;
//            try {
//                istr = assetManager.open(strName);
//            } catch (IOException e) {
//                Log.i("rrr","loi "+e);
//            }
//             aaa[0] = BitmapFactory.decodeStream(istr);
//        }
//    }).start();
//    return aaa[0];
//}


}