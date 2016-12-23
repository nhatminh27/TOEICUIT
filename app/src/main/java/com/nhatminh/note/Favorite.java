package com.nhatminh.note;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nhatminh.adapter.AdapterContents2;
import com.nhatminh.training.Words;
import com.nhatminh.vocabulary.MyApplication;
import com.nhatminh.vocabulary.R;

import java.util.ArrayList;

public class Favorite extends Fragment {

    ListView lv;
    AdapterContents2 ad;
    ArrayList<Words> lw;
    MediaPlayer m = new MediaPlayer();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, viewGroup, false);
        lv = (ListView) view.findViewById(R.id.lv_favorite);

        lw = MyApplication.dtb.getListFavorite();
        ad = new AdapterContents2(getActivity(), R.layout.list_words, lw);
        lv.setAdapter(ad);
        ad.setNotifyOnChange(true);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = "Audio/" + (lw.get(position).getTenTV()).trim() + ".mp3";
                playSound(s);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Words w = lw.get(position);
                showDialog(w);
                return true;
            }
        });
        return view;
    }


    public void showDialog(final Words w) {
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());        // getActivity() thay bằng tên Activity.this nếu đang dùng trên Activity
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
                        ad = new AdapterContents2(getActivity(), R.layout.list_words, lw);
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
    public void playSound(String s)
    {
        try {
            AssetFileDescriptor descriptor = getActivity().getAssets().openFd(s);
            m.reset();
            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            m.prepare();
            m.start();

        }
        catch (Exception e){
            Log.i("xxxMedia", e + "");
        }
    }

}

