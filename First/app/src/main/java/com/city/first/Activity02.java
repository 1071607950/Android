package com.city.first;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class Activity02 extends AppCompatActivity {

    private static String TAG2="Activity02";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity02);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG2,"onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG2,"onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG2,"onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG2,"onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG2,"onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG2,"onDestroy()");
    }
}
