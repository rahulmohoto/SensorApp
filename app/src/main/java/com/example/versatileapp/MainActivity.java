package com.example.versatileapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(true);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nav_view = findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();

                if(id==R.id.accelerometer){
                    openAccelerometer();
                }

                if(id==R.id.gyroscope){
                    openGyroscope();
                }

                if(id==R.id.proximity){
                    openProximity();
                }

                if(id==R.id.shake){
                    openShake();
                }

                if(id==R.id.map){
                    openMap();
                }

                if(id==R.id.gyroscope_fetch){
                    openGyroData();
                }

                return true;
            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return toggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    private void openAccelerometer() {
        Intent intent = new Intent(this, Accelerometer.class);
        startActivity(intent);
    }

    private void openProximity() {
        Intent intent = new Intent(this, Proximity.class);
        startActivity(intent);
    }

    private void openGyroscope() {
        Intent intent = new Intent(this, Gyroscope.class);
        startActivity(intent);
    }

    private void openShake() {
        Intent intent = new Intent(this, Shake.class);
        startActivity(intent);
    }

    private void openGyroData() {
        Intent intent = new Intent(this, gyroData.class);
        startActivity(intent);
    }

    private void openMap() {
        Intent intent = new Intent(this, Map.class);
        startActivity(intent);
    }
}
