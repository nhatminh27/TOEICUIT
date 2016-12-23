package com.nhatminh.vocabulary;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.nhatminh.note.Favorite;
import com.nhatminh.playgame.Games;
import com.nhatminh.playgame.Highscore;
import com.nhatminh.playgame.ListLevel;
import com.nhatminh.training.Training;

public class Home extends Fragment {

    ImageButton btnscore, btnplay,btntrainning,btnfavorite;



    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, viewGroup, false);

        btnscore = (ImageButton) view.findViewById(R.id.rank_home);
        btnplay = (ImageButton) view.findViewById(R.id.play_home);
        btntrainning = (ImageButton) view.findViewById(R.id.train_home);
        btnfavorite = (ImageButton) view.findViewById(R.id.favor_home);
        MyApplication.playMusicBackGround();

        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.stopMusicBackground();
                startFragmentPlay();
            }
        });

        btnfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.stopMusicBackground();
                startFragmentFavor();
            }
        });

        btntrainning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.stopMusicBackground();
                startFragmentTrainning();
            }
        });

        btnscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.stopMusicBackground();
                Intent t = new Intent(getActivity(), Highscore.class);
                startActivity(t);
            }
        });

        return view;
    }

    public void startFragmentPlay()
    {
        Intent t = new Intent(getActivity(),ListLevelActivity.class);
        startActivity(t);
    }
    public void startFragmentFavor()
    {
        Intent t = new Intent(getActivity(),FavoriteActivity.class);
        startActivity(t);
    }
    public void startFragmentTrainning()
    {
        Intent t = new Intent(getActivity(),TrainingActivity.class);
        startActivity(t);
    }
}

