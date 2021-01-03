package com.city.saveqq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText etNumber;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNumber=findViewById(R.id.et_number);
        etPassword=findViewById(R.id.et_password);
        btnLogin=findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=etNumber.getText().toString().trim();
                String passwor=etPassword.getText().toString().trim();
                if (TextUtils.isEmpty(number)){
                    Toast.makeText(MainActivity.this,"请输入QQ号",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passwor)){
                    Toast.makeText(MainActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(MainActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                boolean isSaveSuccess=SPSaveQQ.saveUserInfo(MainActivity.this,number,passwor);
                if (isSaveSuccess){
                    Toast.makeText(MainActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"保存失败",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Map<String,String> userInfo=SPSaveQQ.getUserInfo(this);
        if (userInfo!=null){
            etNumber.setText(userInfo.get("number"));
            etPassword.setText(userInfo.get("password"));
        }

    }
}
