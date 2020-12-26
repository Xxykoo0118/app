package com.example.mediaapplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

public class MusicService extends Service {
    private MediaPlayer player;
    private Timer timer;
    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       return new MusicControl();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = new MediaPlayer();//创建音乐播放器对象

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player == null)return;
        if (player.isPlaying())player.stop();//停止播放音乐
        player.release();//释放占用资源
        player = null;//将player置为空

    }
    class MusicControl extends Binder{
        MusicService getService(){
            return MusicService.this;
        }
    }
    public void play(){
        try{
            player.reset();
            player = MediaPlayer.create(getApplicationContext(),R.raw.wawa);
            player.start();
            addTimer();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void addTimer() {
        if (timer == null){
            timer = new Timer();//创建计数器对象
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    if (player == null)return;
                    int duration =player.getDuration();//获取歌曲总时长
                    int currentPosition = player.getCurrentPosition();//获取播放进度
                    Message msg =MainActivity.handler.obtainMessage();//创建消息对象
                    //将音乐的总时长和播放进度封装到消息对象中
                    Bundle bundle = new Bundle();
                    bundle.putInt("duration",duration);
                    bundle.putInt("currentPosition",currentPosition);
                    msg.setData(bundle);
                    //将消息发送到主线程的消息队列
                    MainActivity.handler.sendMessage(msg);

                }
            };
            //开始件数任务后的5毫秒，第一次执行task任务，以后每500毫秒执行一次
            timer.schedule(task,5,500);
        }
    }

    public void pauserPlay(){
        player.pause();    //暂停播放音乐
    }
    public void continuePlay(){
        player.start();   //继续播放音乐
    }
    public void seekTo(int progress){
        player.seekTo(progress);  //设置音乐的播放位置
    }
}
