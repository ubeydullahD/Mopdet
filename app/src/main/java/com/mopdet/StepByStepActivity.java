package com.mopdet;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class StepByStepActivity extends AppCompatActivity {

    private final static String TAG_FRAGMENT = "TAG_FRAGMENT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_by_step);
    }
}
