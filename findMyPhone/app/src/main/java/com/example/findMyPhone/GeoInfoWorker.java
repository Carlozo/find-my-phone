package com.example.findMyPhone;

import android.Manifest;
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

public class GeoInfoWorker extends Worker {
    private Context context;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationManager service;

    public GeoInfoWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
        this.context = context;
        service = (LocationManager) this.context.getSystemService(LOCATION_SERVICE);
    }

    @Override
    public Result doWork() {

        while (!service.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Log.i("=====>", "LOCALIZACAO NAO ATIVADA!!!");
            //Toast.makeText(this.context, "A localização está desativada, por favor ative para" +
             //               "  que possamos achar seu celular!!",
               //     Toast.LENGTH_LONG).show();
            AlertDialog.Builder dialog = new AlertDialog.Builder(this.context);
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            this.context.startActivity(intent);
        }
        while (ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(this.context, MainActivity.class);
            context.startActivity(intent);
            Toast.makeText(this.context, "Permita o acesso a localização para o " +
                                "app funcionar!!",
                    Toast.LENGTH_SHORT).show();
        }
        LocationServices.getFusedLocationProviderClient(this.context).getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                Log.i("==$$=$$==>", "public void onSuccess(Location location) {...");
                if (location != null) {

                    Log.w("%*%*%*%*%*%", "public Result doWork() {...");
                    FirebaseDataBase.SaveDocument("localization", location.getLatitude(), location.getLongitude());

                } else {
                    if (location != null) {
                            Log.i("===>", "Location foi null :((");
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("==$$==$$=>", "public void onFailure(@NonNull Exception e) {...");
                e.printStackTrace();
            }
        });
        return Result.success();
    }
}
