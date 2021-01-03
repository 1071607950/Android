package com.city.forhelp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("MyBroadcastReceiver","自定义的广播接收者，接收到了求救广播事件");
        Log.i("MyBroadcastReceiver",intent.getAction());
    }
}
