package com.city.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText path;
    private Intent intent;
    private myConn conn;
    MusicService.MyBinder binder;
    private SeekBar mSeekBar;
    private Thread mThread;
    private Handler handler=new Handler(){
        public void hanleMessage(android.os.Message msg){
            switch (msg.what){
                case 100:
                    int currentPosition=(Integer) msg.obj;
                    mSeekBar.setProgress(currentPosition);
                    break;
                    default:
                        break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        path=findViewById(R.id.et_inputpath);
        findViewById(R.id.bt_play).setOnClickListener(this);
        findViewById(R.id.bt_pause).setOnClickListener(this);
        findViewById(R.id.bt_replay).setOnClickListener(this);
        findViewById(R.id.bt_stop).setOnClickListener(this);
        mSeekBar=findViewById(R.id.seekBar1);
        conn=new myConn();
        intent=new Intent(this,MusicService.class);
        bindService(intent,conn,BIND_AUTO_CREATE);
    }

    private void initSeekBar(){
        int musicWidth=binder.getMusicWidth();
        mSeekBar.setMax(musicWidth);
    }

    private void UpdateProgress(){
        mThread=new Thread(){
            public void run(){
                while (!interrupted()){
                    int currentPosition=binder.getCurrentPosition();
                    Message message=Message.obtain();
                    message.obj=currentPosition;
                    message.what=100;
                    handler.sendMessage(message);
                }
            }
        };
        mThread.start();
    }

    private class myConn implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder=(MusicService.MyBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @Override
    public void onClick(View v) {
        String pathway=path.getText().toString().trim();
        switch (v.getId()){
            case R.id.bt_play:
                if (!TextUtils.isEmpty(pathway)){
                    binder.plays(pathway);
                    initSeekBar();
                    UpdateProgress();
                }else{
                    Toast.makeText(this,"找不到音乐文件",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_pause:
                binder.pauses();
                break;
            case R.id.bt_replay:
                binder.replays(pathway);
            case R.id.bt_stop:
                mThread.interrupt();
                if (mThread.isInterrupted()){
                    binder.stops();
                }
                break;
                default:
                    break;
        }
    }

    protected void onDestroy(){
        if (mThread!=null){
            if (!mThread.isInterrupted()){
                mThread.interrupt();
            }
            unbindService(conn);
            super.onDestroy();
        }
    }
}
