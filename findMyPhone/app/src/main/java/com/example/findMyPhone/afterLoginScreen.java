package com.example.findMyPhone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Operation;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

public class afterLoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login_screen);

        Button startDeviceButton = (Button) findViewById(R.id.startDeviceButton);

        startDeviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PeriodicWorkRequest.Builder geoInfoWorkerBuilder =
                        new PeriodicWorkRequest.Builder(GeoInfoWorker.class, 15,
                                TimeUnit.MINUTES);
                PeriodicWorkRequest geoInfoPeriodicWork = geoInfoWorkerBuilder.build();
                Operation workManagerVariable = WorkManager.getInstance().enqueue(geoInfoPeriodicWork);
                Log.i("Qualquer coisa: ","Verificar a variavel WorkManager");
            }
        });
    }
}