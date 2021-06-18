 package com.example.findMyPhone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Operation;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.TimeUnit;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_mainscreen);

        Button startDeviceButton = (Button) findViewById(R.id.startDeviceButton);

        startDeviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser User = FirebaseAuth.getInstance().getCurrentUser();

                AlarmCallback.cntx = MainScreen.this;
                AlarmCallback.startSnapshot(User);

                PeriodicWorkRequest.Builder geoInfoWorkerBuilder =
                        new PeriodicWorkRequest.Builder(GeoInfoWorker.class, 15,
                                TimeUnit.MINUTES);
                PeriodicWorkRequest geoInfoPeriodicWork = geoInfoWorkerBuilder.build();
                Operation workManagerVariable = WorkManager.getInstance().enqueue(geoInfoPeriodicWork);
                Toast.makeText(MainScreen.this, "Sua localização será compartilhada conosco",
                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainScreen.this, ThanksScreen.class));
                Log.i("Qualquer coisa: ","Verificar a variavel WorkManager");
            }
        });
    }

    public void Logout(View view) {
        FirebaseCrud.Logout();
        startActivity(new Intent(MainScreen.this, MainActivity.class));
    }
}