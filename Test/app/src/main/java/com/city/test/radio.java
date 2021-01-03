package com.city.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class radio extends AppCompatActivity {
    private RadioGroup radioGroup;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radio);

        radioGroup = findViewById(R.id.rdg);
        textView = findViewById(R.id.tv);
        radioGroup.setOnCheckedChangeListener(new
                RadioGroup.OnCheckedChangeListener(){

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if(checkedId == R.id.rbtn){
                            textView.setText("您的性别是：男");
                            Toast.makeText(radio.this,"您的性别是：男",Toast.LENGTH_LONG).show();
                        }else{
                            textView.setText("您的性别是：女");
                            Toast.makeText(radio.this,"您的性别是：女",Toast.LENGTH_LONG).show();
                        }
                    }
                } );
    }


}
