package com.fortong;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import pl.droidsonroids.gif.GifImageView;

public class EggActivity extends AppCompatActivity {
    private static String TAG = "Egg";

    private View eggLaytou;
    private GifImageView gifImageView;
    private TextView tv_egg;
    private Button bt_back,bt_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egg);
        Log.e(TAG,MainActivity.eggIndex[0]+","+MainActivity.eggIndex[1]+","+MainActivity.eggIndex[2]+","+MainActivity.eggIndex[3]);
        initView();
        setEggText();
    }

    private void initView(){
//        eggLaytou = findViewById(R.id.eggLayout);
//        eggLaytou.setBackgroundResource(R.mipmap.egg_background);

        gifImageView = findViewById(R.id.egg_gif);

        tv_egg = findViewById(R.id.egg_text);
        bt_back = findViewById(R.id.egg_back);
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bt_next = findViewById(R.id.egg_next);
        if (MainActivity.eggIndex[0] == 1 && MainActivity.eggIndex[1] == 1 && MainActivity.eggIndex[2] == 1){
            bt_next.setVisibility(View.VISIBLE);
            bt_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bt_next.setVisibility(View.INVISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            MainActivity.eggIndex[3] = 1;
                            tv_egg.setText(R.string.egg_4);

                        }
                    }, 1000);
                }
            });
        }
    }

    private void setEggText(){
        if (MainActivity.eggIndex[2] == 1){
            tv_egg.setText(R.string.egg_3);
            return;
        }
        if (MainActivity.eggIndex[1] == 1){
            tv_egg.setText(R.string.egg_2);
            return;
        }
        if (MainActivity.eggIndex[0] == 1){
            tv_egg.setText(R.string.egg_1);
            return;
        }

    }
}
