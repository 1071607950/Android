package com.city.first;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class Activity03 extends AppCompatActivity {
    private Button btn_send;
    private EditText et_name;
    private RadioButton manRadio;
    private RadioButton womanRadio;
    private EditText et_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity03);
        et_name=findViewById(R.id.et_name);
        et_password=findViewById(R.id.et_password);
        btn_send=findViewById(R.id.btn_send);
        manRadio=findViewById(R.id.radioMale);
        womanRadio=findViewById(R.id.radioFemale);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passDate();
            }
        });
    }
    public void passDate(){
        Intent intent=new Intent(this,Activity04.class);
        intent.putExtra("name",et_name.getText().toString().trim());
        intent.putExtra("password",et_password.getText().toString().trim());
        String str="";
        if(manRadio.isChecked()){
            str="男";
        }else if (womanRadio.isChecked()){
            str="女";
        }
        intent.putExtra("sex",str);
        startActivity(intent);
    }
}
