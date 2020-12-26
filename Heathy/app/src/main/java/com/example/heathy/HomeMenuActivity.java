package com.example.heathy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class HomeMenuActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.home_btn:
                intent.setClass(com.example.heathy.HomeMenuActivity.this, MainActivity.class);
                break;
            case R.id.login:
                intent.setClass(com.example.heathy.HomeMenuActivity.this, LoginActivity.class);
                break;
            case R.id.regist:
                intent.setClass(com.example.heathy.HomeMenuActivity.this, RegisterActivity.class);
                break;
        }
        startActivity(intent);
    }
}
