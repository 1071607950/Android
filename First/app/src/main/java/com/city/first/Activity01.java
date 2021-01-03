package com.city.first;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Activity01 extends AppCompatActivity {

    private static String TAG1="Activity01";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity01);
        Log.i(TAG1,"onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG1,"onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG1,"onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG1,"onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG1,"onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG1,"onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG1,"onDestroy()");
    }

    public void click(View view){
        Intent intent=new Intent(this,Activity02.class);
        startActivity(intent);
    }
}
