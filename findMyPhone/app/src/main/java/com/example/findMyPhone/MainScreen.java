 package com.example.findMyPhone;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Operation;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.TimeUnit;

import static android.app.NotificationChannel.DEFAULT_CHANNEL_ID;
import static android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_LOCATION;

 public class MainScreen extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_mainscreen);

        PermissionsAndLocation check = new PermissionsAndLocation();
        Activity activity = MainScreen.this;

        Context context = MainScreen.this;
        Button startDeviceButton = (Button) findViewById(R.id.startDeviceButton);
        check.verifyPermission(context, activity);

        startDeviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check.verifyPermission(context, activity)) {
                    AlarmCallback.startSnapshot(context,FirebaseCrud.getUid());

                    Intent foregroundServiceIntent = new Intent(MainScreen.this, ForegroundService.class);
                    startService(foregroundServiceIntent);


                    startActivity(new Intent(MainScreen.this, ThanksScreen.class));
                }

            }
        });
    }

    public void Logout(View view) {
        FirebaseCrud.Logout();
        startActivity(new Intent(MainScreen.this, MainActivity.class));
    }
}