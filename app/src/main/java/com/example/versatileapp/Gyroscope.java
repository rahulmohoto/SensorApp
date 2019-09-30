package com.example.versatileapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Gyroscope extends AppCompatActivity implements SensorEventListener {

    private TextView xGyro,yGyro,zGyro;
    private Sensor mySensor;
    private SensorManager SM;
    private int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);

        xGyro = findViewById(R.id.x_gyro);
        yGyro = findViewById(R.id.y_gyro);
        zGyro = findViewById(R.id.z_gyro);

        SM=(SensorManager)getSystemService(SENSOR_SERVICE);

        mySensor=SM.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        SM.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float x=sensorEvent.values[0];
        float y=sensorEvent.values[1];
        float z=sensorEvent.values[2];
        xGyro.setText("X: " + (int)x);
        yGyro.setText("Y: " + (int)y);
        zGyro.setText("Z: " + (int)z);

        int xx = (int)x;
        int yy = (int)y;
        int zz = (int)z;

        if(z>0 && z<=7 && flag==0){
            insertData(Integer.toString(xx),Integer.toString(yy),Integer.toString(zz));
            flag=1;
        }
        else if(z<0 && z>=-7 && flag==0){
            insertData(Integer.toString(xx),Integer.toString(yy),Integer.toString(zz));
            flag=1;
        }

        if(x==0 && y==0 && z==0){
            flag=0;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    protected void onResume()
    {
        super.onResume();
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onPause()
    {
        super.onPause();
        SM.unregisterListener(this);
    }

    private void insertData(String x,String y,String z) {
        BackgroundTask bg = new BackgroundTask(this);
        bg.execute(x,y,z);

    }
}
