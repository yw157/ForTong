package com.fortong;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener,View.OnClickListener{
    private static String TAG = "Question";

    public static int quesBranch = 0;

    private int Ques1_Num[] = {1,3,9};
    private int QuesIndex = 0;

    private ImageView iv6_1,iv6_2,iv6_3,iv6_4;
    private Animation operatingAnim;
    private View questionView;
    private TextView ques;
    private CompoundButton ans1,ans2,ans3,selectedCheckBox;
    private Button bt_ques;

    private boolean firstQuestionFlag = true;
    private int ansChoise = 1;
    private int QuesArrayIndex = 0;
    private int lastQuesArrayIndex = 0;

    private int quesArray1[];
    private int ansArray1[];
    private int quesArray2[];
    private int ansArray2[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        initView();
        initQuesAnsText();
    }

    protected void onResume() {
        super.onResume();
        setQuesLaytouText();
    }
    protected void onPause() {
        super.onPause();
    }

    private void initView(){
        iv6_1 = findViewById(R.id.iv_six_1);
        iv6_2 = findViewById(R.id.iv_six_2);
        iv6_3 = findViewById(R.id.iv_six_3);
        iv6_4 = findViewById(R.id.iv_six_4);
        operatingAnim = AnimationUtils.loadAnimation(this, R.anim.rotate);
        operatingAnim.setInterpolator(new LinearInterpolator());
        iv6_1.startAnimation(operatingAnim);
        iv6_1.setVisibility(View.VISIBLE);
        iv6_2.startAnimation(operatingAnim);
        iv6_2.setVisibility(View.VISIBLE);
        iv6_3.startAnimation(operatingAnim);
        iv6_3.setVisibility(View.VISIBLE);
        iv6_4.startAnimation(operatingAnim);
        iv6_4.setVisibility(View.VISIBLE);

        questionView = findViewById(R.id.questionLayout);
        // 根据时间设置背景图
        getBackgroundFromTime();

        ques = findViewById(R.id.ques_text);
        ans1 = (CompoundButton) findViewById(R.id.ans1);
        ans2 = (CompoundButton) findViewById(R.id.ans2);
        ans3 = (CompoundButton) findViewById(R.id.ans3);
        ans1.setOnCheckedChangeListener(this);
        ans2.setOnCheckedChangeListener(this);
        ans3.setOnCheckedChangeListener(this);
        ques.setText(R.string.ques_1_1);
        ans1.setText(R.string.ans_1_1_a);
        ans2.setText(R.string.ans_1_1_b);
        ans3.setText(R.string.ans_1_1_c);

        bt_ques = (Button) findViewById(R.id.bt_ques);
        bt_ques.setOnClickListener(this);
        bt_ques.setEnabled(false);
    }

    private void initQuesAnsText(){
        quesArray1 = new int[]{
                R.string.ques_2_1,
                R.string.ques_3_1,
                R.string.ques_3_2,
                R.string.ques_3_3,
                R.string.ques_4_1,
                R.string.ques_4_2,
                R.string.ques_4_3,
                R.string.ques_4_4,
                R.string.ques_4_5,
                R.string.ques_4_6,
                R.string.ques_4_7,
                R.string.ques_4_8,
                R.string.ques_4_9,
        };
        ansArray1 = new int[]{
                R.string.ans_2_1_a,R.string.ans_2_1_b,R.string.ans_2_1_c,
                R.string.ans_3_1_a,R.string.ans_3_1_b,R.string.ans_3_1_c,
                R.string.ans_3_2_a,R.string.ans_3_2_b,R.string.ans_3_2_c,
                R.string.ans_3_3_a,R.string.ans_3_3_b,R.string.ans_3_3_c,
                R.string.ans_4_1_a,R.string.ans_4_1_b,R.string.ans_4_1_c,
                R.string.ans_4_2_a,R.string.ans_4_2_b,R.string.ans_4_2_c,
                R.string.ans_4_3_a,R.string.ans_4_3_b,R.string.ans_4_3_c,
                R.string.ans_4_4_a,R.string.ans_4_4_b,R.string.ans_4_4_c,
                R.string.ans_4_5_a,R.string.ans_4_5_b,R.string.ans_4_5_c,
                R.string.ans_4_6_a,R.string.ans_4_6_b,R.string.ans_4_6_c,
                R.string.ans_4_7_a,R.string.ans_4_7_b,R.string.ans_4_7_c,
                R.string.ans_4_8_a,R.string.ans_4_8_b,R.string.ans_4_8_c,
                R.string.ans_4_9_a,R.string.ans_4_9_b,R.string.ans_4_9_c,
        };

        quesArray2 = new int[]{
                R.string.ques_2_2,
        };
        ansArray2 = new int[]{
                R.string.ans_2_2_a,R.string.ans_2_2_b,R.string.ans_2_2_c,
        };
    }

    @SuppressLint("SetTextI18n")
    private void setQuesLaytouText(){
        if (quesBranch == 0){
            ques.setText(R.string.ques_1_1);
            ans1.setText(R.string.ans_1_1_a);
            ans2.setText(R.string.ans_1_1_b);
            ans3.setText(R.string.ans_1_1_c);
            return;
        }
        if (quesBranch == 1){
            ques.setText(quesArray1[QuesArrayIndex]);
            ans1.setText(ansArray1[3*QuesArrayIndex+0]);
            ans2.setText(ansArray1[3*QuesArrayIndex+1]);
            ans3.setText(ansArray1[3*QuesArrayIndex+2]);
        }
        if (quesBranch == 2){
            ques.setText(quesArray2[QuesArrayIndex]);
            ans1.setText(ansArray2[3*QuesArrayIndex+0]);
            ans2.setText(ansArray2[3*QuesArrayIndex+1]);
            ans3.setText(ansArray2[3*QuesArrayIndex+2]);
        }
    }


    private void getBackgroundFromTime(){
        int index=(int)(Math.random()*20);

        int array[] = {
                R.mipmap.ques_back_00,R.mipmap.ques_back_01,R.mipmap.ques_back_02,R.mipmap.ques_back_03,R.mipmap.ques_back_04,
                R.mipmap.ques_back_05,R.mipmap.ques_back_06,R.mipmap.ques_back_07,R.mipmap.ques_back_08,R.mipmap.ques_back_09,
                R.mipmap.ques_back_10,R.mipmap.ques_back_11,R.mipmap.ques_back_12,R.mipmap.ques_back_13,R.mipmap.ques_back_14,
                R.mipmap.ques_back_15,R.mipmap.ques_back_16,R.mipmap.ques_back_17,R.mipmap.ques_back_18,R.mipmap.ques_back_19
        };
        questionView.setBackgroundResource(array[index]);
        questionView.getBackground().setAlpha(50);
    }

    @Override
    public void onCheckedChanged(CompoundButton checkBox, boolean b) {
        if (b){
            changeRadios(checkBox);
        }
    }
    private void changeRadios(CompoundButton checkBox) {
        ans1.setChecked(false);
        ans2.setChecked(false);
        ans3.setChecked(false);
        checkBox.setChecked(true);
        selectedCheckBox = checkBox;

        if (!bt_ques.isEnabled()){
            bt_ques.setEnabled(true);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_ques:
                bt_ques.setEnabled(false);
                onClick_ques();
                break;
        }
    }

    private void onClick_ques(){
        if (firstQuestionFlag){
            if (selectedCheckBox.getId() == R.id.ans1){
                firstQuestionFlag = false;
                quesBranch = 1;
            }
            if (selectedCheckBox.getId() == R.id.ans2){
                firstQuestionFlag = false;
                quesBranch = 2;
            }
            if (selectedCheckBox.getId() == R.id.ans3){
                MainActivity.eggIndex[1] = 1;
                Intent intent=new Intent(QuestionActivity.this,EggActivity.class);
                startActivity(intent);
            }
            QuesArrayIndex = 0;
            QuesIndex = 1;
        }
        else {
            lastQuesArrayIndex = QuesArrayIndex;
            if (quesBranch == 1){
                int tmp = 0;
                for (int i = 0;i < QuesIndex;i++){
                    tmp = tmp + Ques1_Num[i];
                }
                if (selectedCheckBox.getId() == R.id.ans1){
                    QuesArrayIndex = tmp + 3*(ansChoise - 1) + 0;
                    ansChoise = 1;
                }
                if (selectedCheckBox.getId() == R.id.ans2){
                    QuesArrayIndex = tmp + 3*(ansChoise - 1) + 1;
                    ansChoise = 2;
                }
                if (selectedCheckBox.getId() == R.id.ans3){
                    QuesArrayIndex = tmp + 3*(ansChoise - 1) + 2;
                    ansChoise = 3;
                }
            }
            if (quesBranch == 2){
                if (selectedCheckBox.getId() == R.id.ans1){
                    QuesArrayIndex = 1 + 3*(ansChoise - 1) + 0;
                    ansChoise = 1;
                }
                if (selectedCheckBox.getId() == R.id.ans2){
                    QuesArrayIndex = 1 + 3*(ansChoise - 1) + 1;
                    ansChoise = 2;
                }
                if (selectedCheckBox.getId() == R.id.ans3){
                    QuesArrayIndex = 1 + 3*(ansChoise - 1) + 2;
                    ansChoise = 3;
                }
            }
            QuesIndex = QuesIndex + 1;
        }

        Log.e(TAG,"[QuesArrayIndex,QuesIndex]: ["+QuesArrayIndex+","+QuesIndex+"]");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                bt_ques.setEnabled(true);
                if (quesBranch == 1 && QuesIndex == 4){
                    Intent intent=new Intent(QuestionActivity.this,EndActivity.class);
                    intent.putExtra("num",3*(lastQuesArrayIndex - 4) + (ansChoise - 1));
                    startActivity(intent);
                    finish();
                }
                else {
                    if (quesBranch == 2 && QuesIndex == 2){
                        Intent intent=new Intent(QuestionActivity.this,EndActivity.class);
                        intent.putExtra("num",ansChoise - 1);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        ans1.setChecked(false);
                        ans2.setChecked(false);
                        ans3.setChecked(false);
                        bt_ques.setEnabled(false);
                        if (quesBranch == 1){
                            questionView.getBackground().setAlpha(50*QuesIndex+50);
                        }
                        if (quesBranch == 2){
                            questionView.getBackground().setAlpha(250);
                        }
                        setQuesLaytouText();
                    }
                }
            }
        }, 1000);
    }
}
