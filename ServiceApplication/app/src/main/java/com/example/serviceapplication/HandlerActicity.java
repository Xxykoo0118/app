package com.example.serviceapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HandlerActicity extends AppCompatActivity implements View.OnClickListener {
    public static final int UPDATE_TEXT = 1;
    public static final int UPDATE_BG = 2;
    private TextView myText;

    Handler tmpHandler =new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);

        }
    };
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case UPDATE_TEXT:
                    //进行UI操作
                    myText.setText("Nice to meet you");
                    break;
                case UPDATE_BG:
                    myText.setTextColor(Color.parseColor("#FF7F00"));
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        myText = findViewById(R.id.mytext);
        Button change_text = findViewById(R.id.change_text);
        change_text.setOnClickListener(this);
        Button change_bd = findViewById(R.id.change_bg);
        change_bd.setOnClickListener(this);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.change_text:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case R.id.change_bg:
                new Thread(
                        new Runnable(){
                            @Override
                            public void run() {
                                Message message = new Message();
                                message.what = UPDATE_BG;
                                handler.sendMessage(message);
                            }
                        }
                ).start();
                default:
                    break;
        }
    }
}
