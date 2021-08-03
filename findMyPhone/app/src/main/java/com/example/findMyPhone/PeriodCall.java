 package com.example.findMyPhone;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class PeriodCall{

    static Timer timer = new Timer();
    static Boolean checkTimerRun = false;

    public static void callTimerTask (Context context){

        PullLocation pullLocationClass = new PullLocation();

        TimerTask timerTask = new TimerTask() {

            private Double Latitude;
            private Double Longitude;

                @Override
                public void run() {

                PermissionsAndLocation checkClass = new PermissionsAndLocation();

                checkClass.verifylocation(context);

                if (checkClass.verifylocation(context)){
                    pullLocationClass.PullLocation(context);

                    while (!pullLocationClass.getBoolean()){

                    }

                    Latitude = pullLocationClass.getLatitude();
                    Longitude = pullLocationClass.getLongitude();
                    FirebaseDataBase.SaveDocument("localization", Latitude, Longitude);

                }else {
                    Log.i("====>", "Reiciando chamada de localização");
                }
            }
        };

        Log.d("====>",checkTimerRun+ "");

        if(!checkTimerRun) {
            timer.schedule(timerTask, 1000, 10000);
            checkTimerRun = true;
        }else{
            //timer.purge();
            //checkTimerRun = false;
        }
    }
    public static void cancelTimer(){
        //timer.cancel();
    }
}
