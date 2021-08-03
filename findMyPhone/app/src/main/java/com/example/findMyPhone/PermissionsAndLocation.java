package com.example.findMyPhone;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

public class PermissionsAndLocation {

    private LocationManager service;

    public boolean verifyPermission(Context context, Activity activity){
        service = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Log.i("====>","Entrou na verificação");
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.FOREGROUND_SERVICE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION
                    ,Manifest.permission.ACCESS_FINE_LOCATION
                    ,Manifest.permission.FOREGROUND_SERVICE},123);

            /*Toast.makeText(context, "Permita o acesso a localização para o " +
                            "app funcionar!!",
                    Toast.LENGTH_SHORT).show();*/
            return false;
        }else{
            return true;
        }
    }
    public boolean verifylocation(Context context){
        service = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if(!service.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            Log.i("=====>", "LOCALIZACAO NAO ATIVADA!!!");

            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            context.startActivity(intent);
            return false;
        }else{
            Log.i("=====>", "LOCALIZACAO ATIVADA!!!");
            return true;
        }
    }


}
