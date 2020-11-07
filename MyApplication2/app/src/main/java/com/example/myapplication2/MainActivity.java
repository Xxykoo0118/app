package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        Log.d(TAG,"Call onCreate()");
        Button normalBtn = findViewById(R.id.start_normal_activity);
        normalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, NormalActivity.class);
                startActivity(intent1);
            }
        });
        Button dialogBtn = findViewById(R.id.start_dialog_activity);
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(intent2);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"Call onStart()");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"Call onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"Call onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"Call onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"Call onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Call onDestroy()");
    }
}
