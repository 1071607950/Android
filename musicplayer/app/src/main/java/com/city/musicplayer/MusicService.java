package com.city.musicplayer;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;

import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MusicService extends Service {
    private static final String TAG="MusicService";
    public MediaPlayer mediaPlayer;

    class MyBinder extends Binder{
        public void plays(String path){
            play(path);
        }
        public void pauses(){
            pause();
        }
        public void replays(String path){
            replay(path);
        }
        public void stops(){
            stop();
        }
        public int getCurrentPosition(){
            return getCurrentProgress();
        }
        public int getMusicWidth(){
            return getMusicLength();
        }
    }

    public void onCreate(){
        super.onCreate();
    }

    @SuppressLint("NewApi")
    public void play(String path){
        try {
            if(mediaPlayer==null){
                Log.i(TAG,"开始播放音乐");
                mediaPlayer=new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDataSource(path);
                mediaPlayer.prepare();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mediaPlayer.start();
                    }
                });
            }else{
                int position=getCurrentProgress();
                mediaPlayer.seekTo(position);
                try {
                    mediaPlayer.prepare();
                }catch (Exception e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void pause(){
        if (mediaPlayer!=null&&mediaPlayer.isPlaying()){
            Log.i(TAG,"播放暂停");
            mediaPlayer.pause();
        }else if (mediaPlayer!=null&&(!mediaPlayer.isPlaying())){
            mediaPlayer.start();
        }
    }

    public void replay(String path){
        if (mediaPlayer!=null){
            Log.i(TAG,"重新开始播放");
            mediaPlayer.seekTo(0);
            try {
                mediaPlayer.prepare();
            }catch (Exception e){
                e.printStackTrace();
            }
            mediaPlayer.start();
        }
    }

    public void stop(){
        if (mediaPlayer!=null){
            Log.i(TAG,"停止播放");
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer=null;
        }else {
            Toast.makeText(getApplicationContext(),"已停止",Toast.LENGTH_SHORT).show();
        }
    }

    public int getMusicLength(){
        if (mediaPlayer!=null){
            return mediaPlayer.getDuration();
        }else {
            return 0;
        }
    }

    public int getCurrentProgress(){
        try {
            if (mediaPlayer!=null){
                if (mediaPlayer.isPlaying()){
                    Log.i(TAG,"获取当前进度");
                    return mediaPlayer.getCurrentPosition();
                }else if (!mediaPlayer.isPlaying()){
                    return mediaPlayer.getCurrentPosition();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public void onDestroy(){
        if (mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer=null;
        }
        super.onDestroy();
    }
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }
}
