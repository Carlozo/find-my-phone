package com.example.findMyPhone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class stopForeground extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_foreground);

        Intent foregroundService = new Intent(this, ForegroundService.class);
        stopService(foregroundService);
        PeriodCall.cancelTimer();
        startActivity(new Intent(this,MainScreen.class));

    }

}