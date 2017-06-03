package com.example.aum.smartyapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Course extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
