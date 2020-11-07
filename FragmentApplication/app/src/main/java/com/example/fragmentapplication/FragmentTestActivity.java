package com.example.fragmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class FragmentTestActivity extends AppCompatActivity {
    private final static  String TAG = "FragmentTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test);
        Log.d(TAG,"Activity--onCreate()");
    }
}
