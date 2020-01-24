package com.fortong;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static int[] eggIndex = new int[]{0,0,0,0};

    private View loginView1,loginView2;
    private ImageView iv;
    private TextView tv_login;
    private Button bt_login;
    private EditText et_login;

    private boolean QuestionFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        initPermission(this);
        initView();
    }

    private void initView(){
        QuestionActivity.quesBranch = 0;
        eggIndex[0] = 0;
        eggIndex[1] = 0;
        eggIndex[2] = 0;
        eggIndex[3] = 0;
        loginView1 = findViewById(R.id.loginLayout1);
        loginView1.setBackgroundResource(R.mipmap.login_background);
        loginView1.getBackground().setAlpha(200);

        loginView2 = findViewById(R.id.loginLayout2);

        iv = (ImageView) findViewById(R.id.touxiang);
        iv.setImageResource(R.mipmap.login_head);

        tv_login = findViewById(R.id.tv_login);
        bt_login = findViewById(R.id.bt_login);
        bt_login.setOnClickListener(this);

        et_login = findViewById(R.id.et_login);
    }

    /**
     * 动态申请权限
     * @param mainActivity
     */
    public static void initPermission(MainActivity mainActivity) {
        String permissions[] = {
                Manifest.permission.READ_EXTERNAL_STORAGE,        //need
                Manifest.permission.WRITE_EXTERNAL_STORAGE,       //need
        };
        ArrayList<String> toApplyList = new ArrayList<String>();
        for (String perm :permissions){
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(mainActivity, perm)) {
                toApplyList.add(perm);
                //进入到这里代表没有权限.

            }
        }
        String tmpList[] = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()){
            ActivityCompat.requestPermissions(mainActivity, toApplyList.toArray(tmpList), 123);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                bt_login.setEnabled(false);
                onClick_login();
                break;
        }
    }
    private void onClick_login(){
        if ("".equals(et_login.getText().toString())) {
            return;
        }
        final String name = et_login.getText().toString();
        switch (name){
            case "王彤":
            case "梦璃梅勒":
                loginView2.setVisibility(View.VISIBLE);
                tv_login.setText(R.string.str1_login);
                tv_login.setTextColor(Color.parseColor("#FFB6C1"));
                QuestionFlag = true;
                break;
            case "闫伟":
            case "yw":
                bt_login.setEnabled(true);
                MainActivity.eggIndex[0] = 1;
                Intent intent=new Intent(MainActivity.this,EggActivity.class);
                startActivity(intent);
                break;
            default:
                loginView2.setVisibility(View.VISIBLE);
                tv_login.setText(R.string.str2_login);
                bt_login.setEnabled(true);
                tv_login.setTextColor(Color.parseColor("#1E90FF"));
                QuestionFlag = false;
                break;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (QuestionFlag){
                    bt_login.setEnabled(true);
                    Intent intent=new Intent(MainActivity.this,QuestionActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 3000);
    }
}


