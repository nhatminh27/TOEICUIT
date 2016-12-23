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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhatminh.training.Lessons;
import com.nhatminh.vocabulary.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caonh on 01/10/2016.
 */
public class AdapterListLessons2 extends ArrayAdapter<Lessons> {
    private Activity context;
    private int layout;
    private ArrayList<Lessons> list;
    private Typeface myTypeface;
    View row;
    public AdapterListLessons2(Context context, int textViewResourceId, ArrayList<Lessons> objects)
    {
        super(context, textViewResourceId, objects);

        this.context = (Activity) context;
        this.layout = textViewResourceId;
        this.list = objects;
        this.myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/windsorb.ttf");
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        row = convertView;
        final ListLessonHolder holder;

        if(row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layout, parent, false);


            holder = new ListLessonHolder();
            holder.avata = (ImageView) row.findViewById(R.id.im_listlesson);
            holder.tenBH = (TextView) row.findViewById(R.id.tenbaihoc_listlesson);

            holder.tenBH.setTypeface(myTypeface);

            row.setTag(holder);
        }
        else
        {
            holder = (ListLessonHolder)row.getTag();
        }
        Lessons ls = list.get(position);

        String ten = ls.getTenTA().toString() + "\n"+ ls.getTenTV().toString() ;

        int maBH = ls.getMaBH();

        holder.tenBH.setText(ten);

        String tenbaihoc = ls.getTenTA();
        String bh;
        if(tenbaihoc.endsWith(" ")){
            bh = tenbaihoc.substring(0,tenbaihoc.length() - 1);
        }
        else
            bh = tenbaihoc;
        String s= "ImageLesson/" + bh.toString() + ".jpg";
        Bitmap a = getBitmapFromAsset(s);
        Log.i("xtest",s+"");

        //Bitmap a = getBitmapFromAsset("ImageLesson/"+ls.getTenTA().toString()+".jpg");

        holder.avata.setImageBitmap(a);

        Log.i("aaa", "aaa");

        return row;
    }
    public static class ListLessonHolder
    {
        ImageView avata;
        TextView tenBH;
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

}
