package com.fortong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EndActivity extends AppCompatActivity {

    private ImageView iv_end;
    private Animation operatingAnim;
    private TextView tv_end;
    private Button bt_egg3;

    private int index;
    private int endArray1[];
    private int endArray2[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        Intent intent = getIntent();
        index = intent.getIntExtra("num",0);

        initView();
        initEndText();
        setEndText();
    }

    private void initView(){
        iv_end = (ImageView) findViewById(R.id.end_iv);
        operatingAnim = AnimationUtils.loadAnimation(this, R.anim.rotate_end);
        operatingAnim.setInterpolator(new LinearInterpolator());
        iv_end.startAnimation(operatingAnim);

        tv_end = (TextView) findViewById(R.id.end_text);

        bt_egg3 = findViewById(R.id.end_egg3);

        bt_egg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.eggIndex[2] = 1;
                Intent intent = new Intent(EndActivity.this, EggActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initEndText(){
        endArray1 = new int[]{
                R.string.end_4_01,R.string.end_4_02,R.string.end_4_03,
                R.string.end_4_04,R.string.end_4_05,R.string.end_4_06,
                R.string.end_4_07,R.string.end_4_08,R.string.end_4_09,
                R.string.end_4_10,R.string.end_4_11,R.string.end_4_12,
                R.string.end_4_13,R.string.end_4_14,R.string.end_4_15,
                R.string.end_4_16,R.string.end_4_17,R.string.end_4_18,
                R.string.end_4_19,R.string.end_4_20,R.string.end_4_21,
                R.string.end_4_22,R.string.end_4_23,R.string.end_4_24,
                R.string.end_4_25,R.string.end_4_26,R.string.end_4_27,
        };

        endArray2 = new int[]{
                R.string.end_2_2_a,R.string.end_2_2_b,R.string.end_2_2_c,
        };
    }

    private void setEndText(){
        if (QuestionActivity.quesBranch == 2){
            tv_end.setText(endArray2[index]);
        }
        if (QuestionActivity.quesBranch == 1){
            tv_end.setText(endArray1[index]);
        }
    }
}
