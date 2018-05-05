package com.example.android.ratingin10so1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class StartSurvey extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_survey);
    }
    public void sendMessage(View view)
    {
        Intent intent = new Intent(StartSurvey.this, MainActivity.class);
        startActivity(intent);
    }
}
