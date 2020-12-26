package com.example.heathy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class FooddesActivity extends AppCompatActivity implements View.OnTouchListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fooddes);
        ImageView back = findViewById(R.id.fooddesc_iv_back);
        back.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Intent intent = new Intent(FooddesActivity.this,MainActivity.class);
        startActivityForResult(intent,1);
        return false;
    }
}
