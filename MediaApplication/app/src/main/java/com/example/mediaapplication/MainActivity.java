package com.example.mediaapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static TextView tv_total;
    private static TextView tv_progress;
    ObjectAnimator animator;
    MyServiceConn conn;
    MusicService musicService;
    static SeekBar sb;
    private boolean isUnbind = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initSeekBar();
        tv_progress = (TextView) findViewById(R.id.tv_progress);
        tv_total = (TextView) findViewById(R.id.tv_total);
        findViewById(R.id.btn_play).setOnClickListener(this);
        findViewById(R.id.btn_pause).setOnClickListener(this);
        findViewById(R.id.btn_continue_play).setOnClickListener(this);
        findViewById(R.id.btn_exit).setOnClickListener(this);

        ImageView iv_music = (ImageView) findViewById(R.id.iv_music);
        animator = ObjectAnimator.ofFloat(iv_music, "rotation", 0f, 360.0f);
        animator.setDuration(10000);//动画旋转一周的时间需要10s
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(-1);//-1表示设置动画无限循环

        Intent intent = new Intent(this, MusicService.class);
        conn = new MyServiceConn();  //创建服务连接对象
        bindService(intent, conn, BIND_AUTO_CREATE); //绑定服务


    }

    private void initSeekBar() {
        sb = (SeekBar) findViewById(R.id.sb);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {//滑动进度条改变是，会调用此方法
                if (progress == seekBar.getMax()) {//当滑动进度条到末端时，结束动画
                    animator.pause();//停止播放动画
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {//当滑动条开始滑动时调用

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {//当滑动条停止滑动时调用
                //根据拖到的进度改变音乐播放进度
                int progress = seekBar.getProgress();//获取seekBar的进度
                musicService.seekTo(progress);//改变播放进度

            }
        });
    }

    public static Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData(); //获取从子线程发送过来的音乐播放进度
            int duration = bundle.getInt("duration");                  //歌曲的总时长
            int currentPostition = bundle.getInt("currentPosition");//歌曲当前进度
            sb.setMax(duration);                //设置SeekBar的最大值为歌曲总时长
            sb.setProgress(currentPostition);//设置SeekBar当前的进度位置
            //歌曲的总时长
            int minute = duration / 1000 / 60;
            int second = duration / 1000 % 60;
            String strMinute = null;
            String strSecond = null;
            if (minute < 10) {              //如果歌曲的时间中的分钟小于10
                strMinute = "0" + minute; //在分钟的前面加一个0
            } else {
                strMinute = minute + "";
            }
            if (second < 10) {             //如果歌曲的时间中的秒钟小于10
                strSecond = "0" + second;//在秒钟前面加一个0
            } else {
                strSecond = second + "";
            }
            tv_total.setText(strMinute + ":" + strSecond);
            //歌曲当前播放时长
            minute = currentPostition / 1000 / 60;
            second = currentPostition / 1000 % 60;
            if (minute < 10) {             //如果歌曲的时间中的分钟小于10
                strMinute = "0" + minute;//在分钟的前面加一个0
            } else {
                strMinute = minute + "";
            }
            if (second < 10) {               //如果歌曲的时间中的秒钟小于10
                strSecond = "0" + second;  //在秒钟前面加一个0
            } else {
                strSecond = second + "";
            }
            tv_progress.setText(strMinute + ":" + strSecond);
        }

    };


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_play://播放按钮点击事件
                musicService.play();//播放音乐
                animator.start();//播放动画
                break;
            case R.id.btn_pause:  //暂停按钮点击事件
                musicService.pauserPlay();//暂停播放音乐
                animator.pause();//暂停播放动画
                break;
            case R.id.btn_continue_play:  //继续播放按钮点击事件
                musicService.continuePlay();//继续播放音乐
                animator.start();//播放动画
                break;
            case R.id.btn_exit:
                unbind(isUnbind);//退出按钮点击事件
                isUnbind = true;//完成解绑服务
                finish(); //关闭音乐播放界面
                break;
        }

    }
    private void unbind(boolean isUnbind){
        if (!isUnbind){    //判断服务是否被解绑
            musicService.pauserPlay();//暂停播放音乐
            unbindService(conn);//解绑服务
        }

    }
    class MyServiceConn implements ServiceConnection { //用于实现连接服务
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicService = ((MusicService.MusicControl) service).getService();
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    }



}
