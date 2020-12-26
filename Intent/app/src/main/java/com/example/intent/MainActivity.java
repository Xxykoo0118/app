package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.litepal.LitePal;
import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LitePal.initialize(this);
    }
    public class Album extends LitePalSupport {

        @Column(unique = true, defaultValue = "unknown")
        private String name;

        @Column(index = true)
        private float price;

        private List<Song> songs = new ArrayList<>();

        // generated getters and setters.

    }

    public class Song extends LitePalSupport {

        @Column(nullable = false)
        private String name;

        private int duration;

        @Column(ignore = true)
        private String uselessField;

        private Album album;

        // generated getters and setters.

    }
}
