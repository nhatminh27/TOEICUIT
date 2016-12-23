package com.nhatminh.playgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nhatminh.vocabulary.MyApplication;
import com.nhatminh.vocabulary.R;

public class SaveScore extends Activity {

    EditText ten;
    TextView diem;
    Button save;

    int ddiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_score);

        ten = (EditText) findViewById(R.id.edten_save);
        diem = (TextView) findViewById(R.id.txtscore_save);
        save = (Button) findViewById(R.id.btnsave_save);

        Intent t = getIntent();
        Bundle b = t.getBundleExtra("Score");

        ddiem = b.getInt("Diem");
        diem.setText(""+ddiem);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = ten.getText().toString();
                if(s.equals(""))
                {
                    return ;
                }

                Player p = new Player(s,ddiem);
                MyApplication.dtb.AddPlayer(p);
                finish();
            }
        });
    }


}
