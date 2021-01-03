package com.city.bindserver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyService.MyBinder myBinder;
    private MyConn myconn;
    private Button btn_bind,btn_call,btn_unbind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        btn_bind=findViewById(R.id.btn_bind);
        btn_call=findViewById(R.id.btn_call);
        btn_unbind=findViewById(R.id.btn_unbind);
        btn_bind.setOnClickListener(this);
        btn_call.setOnClickListener(this);
        btn_unbind.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_bind:
                if (myconn==null){
                    myconn=new MyConn();
                }
                Intent intent=new Intent(MainActivity.this,MyService.class);
                bindService(intent,myconn,BIND_AUTO_CREATE);
                break;
            case R.id.btn_call:
                myBinder.callMethodInService();
                break;
            case R.id.btn_unbind:
                if (myconn!=null){
                    unbindService(myconn);
                    myconn=null;
                }
                break;
        }
    }
    private class MyConn implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder=(MyService.MyBinder) service;
            Log.i("MainActivity","服务成功绑定,内存地址为:"+myBinder.toString());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
