package com.example.android.ratingin10so1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EndSurvey extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_survey);
    }
    public void sendMessage(View view)
    {
        Intent intent = new Intent(EndSurvey.this, StartSurvey.class);
        startActivity(intent);
    }
}
