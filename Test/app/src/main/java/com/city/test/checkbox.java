package com.city.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class checkbox extends AppCompatActivity {

    private TextView hobby;
    private List<CheckBox> checkBoxList = new ArrayList<CheckBox>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check);

        hobby = findViewById(R.id.hobby);
        CheckBox shuttlecock = findViewById(R.id.like_shuttlecock);
        CheckBox basketball = findViewById(R.id.like_basketball);
        CheckBox pingpong = findViewById(R.id.like_pingpong);

        checkBoxList.add(shuttlecock);
        checkBoxList.add(basketball);
        checkBoxList.add(pingpong);

        CheckBox.OnClickListener checkboxListener = new CheckBox.OnClickListener(){
            @Override
            public void onClick(View v) {
                StringBuffer sb = new StringBuffer();
                for (CheckBox checkBox:checkBoxList){
                    if(checkBox.isChecked()){
                        sb.append(checkBox.getText().toString()+"-");
                    }
                    hobby.setText(sb);
                }
            }
        };
        shuttlecock.setOnClickListener(checkboxListener);
        basketball.setOnClickListener(checkboxListener);
        pingpong.setOnClickListener(checkboxListener);
    }
}
