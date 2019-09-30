package com.example.versatileapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Proximity extends AppCompatActivity implements SensorEventListener {

    private TextView proxi_data;
    private Sensor mySensor;
    private SensorManager SM;
    private Vibrator v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);

        proxi_data=findViewById(R.id.proxi_edit);

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        SM=(SensorManager)getSystemService(SENSOR_SERVICE);

        mySensor=SM.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        proxi_data.setText(Float.toString(sensorEvent.values[0]));

        if(sensorEvent.values[0]<mySensor.getMaximumRange()) {
            v.vibrate(100);
        }
        else
        {


        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    protected void onResume()
    {
        super.onResume();
        SM.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onPause()
    {
        super.onPause();
        SM.unregisterListener(this);
    }

}
