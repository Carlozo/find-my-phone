package com.example.findMyPhone;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.content.Context.LOCATION_SERVICE;

public class PullLocation {
    private Context context;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationManager service;
    private Double Latitude = 0.0;
    private Double Longitude = 0.0;
    private Boolean gambiarra = false;

    public void PullLocation(Context context) {
        this.context = context;
        service = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this.context, Manifest.permission.FOREGROUND_SERVICE)
            != PackageManager.PERMISSION_GRANTED) {

            Intent intent = new Intent(context, MainScreen.class);
            context.startActivity(intent);
        }
        LocationServices.getFusedLocationProviderClient(context)
                .getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                Log.d("====>",""+location);
                Log.i("====>", "public void onSuccess(Location location)");
                if (location != null) {

                    Log.w("====>", "public Result doWork() {...");
                    setLatitude(location.getLatitude());
                    setLongitude(location.getLongitude());
                    setBoolean();
                    //FirebaseDataBase.SaveDocument("localization", location.getLatitude(), location.getLongitude());

                } else {
                    if (location == null) {
                        setBoolean();
                        Log.d("====>",""+location);
                        Log.i("===>", "Location foi null :((");
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("====>", "public void onFailure(@NonNull Exception e) {...");
                e.printStackTrace();
            }
        });
    }

    public void setBoolean(){
        gambiarra = true;
    }
    public boolean getBoolean(){
        return gambiarra;
    }
    public void setLatitude(Double latitude){ this.Latitude = latitude; }
    public void setLongitude(Double longitude){ this.Longitude = longitude; }
    public double getLatitude(){
        return this.Latitude;
    }
    public double getLongitude(){ gambiarra = false; return this.Longitude; }
}
