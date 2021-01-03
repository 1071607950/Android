package com.city.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class button extends AppCompatActivity {

    private Button bn1,bn2,bn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button);

        bn1=findViewById(R.id.button1);
        bn2=findViewById(R.id.button2);
        bn3=findViewById(R.id.button3);

        bn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bn1.setText("你点击了按钮1");
            }
        });

    }

    public void click(View v){
        bn2.setText("你点击了按钮2");
    }
}
