package com.city.test;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class layout extends Activity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linerlayout);
    }
}
