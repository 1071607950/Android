package com.city.first;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Activity04 extends AppCompatActivity {

    private TextView tv_name;
    private TextView tv_password;
    private TextView tv_sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity04);

        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        String password=intent.getStringExtra("password");
        String sex=intent.getStringExtra("sex");
        tv_name=findViewById(R.id.tv_name);
        tv_password=findViewById(R.id.tv_password);
        tv_sex=findViewById(R.id.tv_sex);

        tv_name.setText("用户名:"+name);
        tv_password.setText("密码:"+password);
        tv_sex.setText("性别:"+sex);
    }
}
