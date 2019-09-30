package com.example.versatileapp;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Accelerometer extends AppCompatActivity implements SensorEventListener {

    private Sensor mySensor ;
    private SensorManager sm;
    private TextView y_text,x_text,z_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        y_text=findViewById(R.id.y_text);
        x_text=findViewById(R.id.x_text);
        z_text=findViewById(R.id.z_text);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        mySensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sm.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];

        y_text.setText("y is: " + Float.toString(y));
        x_text.setText("x is: " + Float.toString(x));
        z_text.setText("z is: " + Float.toString(z));

        if(y>=0 && y<=0.8){
            getWindow().getDecorView().setBackgroundColor(Color.CYAN);
        }
        else{
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    protected void onResume(){
        super.onResume();
        sm.registerListener(this,mySensor,sm.SENSOR_DELAY_NORMAL);
    }

    protected void onPause(){
        super.onPause();
        sm.unregisterListener(this);

    }

}
