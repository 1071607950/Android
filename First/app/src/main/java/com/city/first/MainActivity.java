package com.city.first;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar1;
    private ProgressBar mProgressBar2;
    private ProgressBar mProgressBar3;
    private TextView mLifeTV;
    private TextView mAttackTV;
    private TextView mSpeedTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLifeTV=findViewById(R.id.tv_life_progress);
        mAttackTV =findViewById(R.id.tv_attack_progress);
        mSpeedTV=findViewById(R.id.tv_speed_progress);
        initProgress();
    }
    private void initProgress(){
        mProgressBar1=findViewById(R.id.progressBar1);
        mProgressBar2=findViewById(R.id.progressBar2);
        mProgressBar3=findViewById(R.id.progressBar3);
        mProgressBar1.setMax(1000);
        mProgressBar2.setMax(1000);
        mProgressBar3.setMax(1000);
    }

    public void click(View view){
        Intent intent=new Intent(this,ShopActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (data!=null){
            if (resultCode==1){
                ItemInfo info=(ItemInfo) data.getSerializableExtra("equipment");
                updateProgress(info);
            }
        }
    }

    public void updateProgress(ItemInfo info){
        int progeress1=mProgressBar1.getProgress();
        int progeress2=mProgressBar2.getProgress();
        int progeress3=mProgressBar3.getProgress();
        mProgressBar1.setProgress(progeress1+info.getLife());
        mProgressBar2.setProgress(progeress2+info.getAttack());
        mProgressBar3.setProgress(progeress3+ info.getSpeed());
        mLifeTV.setText(mProgressBar1.getProgress()+"");
        mAttackTV.setText(mProgressBar2.getProgress()+"");
        mSpeedTV.setText(mProgressBar3.getProgress()+"");
    }
}
