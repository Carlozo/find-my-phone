package com.example.findMyPhone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ThanksScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanksscreen);
    }

    public void Logout(View view){
        FirebaseCrud.Logout();
        startActivity(new Intent(ThanksScreen.this, MainActivity.class));
    }

    public void Stop(View view){
        startActivity(new Intent(this,stopForeground.class));
    }
}