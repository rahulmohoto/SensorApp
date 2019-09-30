package com.example.versatileapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.seismic.ShakeDetector;

public class Shake extends AppCompatActivity implements ShakeDetector.Listener{

    private SensorManager sm;
    boolean flag=false;
    private ShakeDetector sd;
    private EditText emergencyEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);

        emergencyEdit = findViewById(R.id.emergencyEdit);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        sd = new ShakeDetector(this);
        sd.start(sm);

    }

    @Override
    public void hearShake() {

        getWindow().getDecorView().setBackgroundColor(Color.YELLOW);

        String number=emergencyEdit.getText().toString();
        Uri u = Uri.parse("tel:" + number);
        Intent i = new Intent(Intent.ACTION_DIAL, u);
        try
        {
            startActivity(i);

        }
        catch (SecurityException e)
        {
            // show() method display the toast with
            // exception message.
            Toast.makeText(this, "Call Successful", Toast.LENGTH_SHORT)
                    .show();
        }

    }

//    protected void onResume(){
//        super.onResume();
//        sm.registerListener(sd,sm.SENSOR_DELAY_NORMAL);
//    }

//    protected void onPause(){
//        super.onPause();
//        sm.unregisterListener((SensorEventListener) this);
//
//    }


}
