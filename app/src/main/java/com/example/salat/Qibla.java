package com.example.salat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Qibla extends AppCompatActivity implements SensorEventListener, NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    NavigationView nav;
    Toolbar toolbar;
    ImageView imageView;
     SensorManager sensorManager;
    Sensor sensorAccelerometer,sensorMagneticField;

    public float [] floatGravity = new float[3];
    public float [] floatGeoMagnetic = new float[3];

    public float [] floatOrientation = new float[3];
    public float [] floatRotationMatrix = new float[9];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qibla);


        toolbar = findViewById(R.id.toolbar);
        nav = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer);
        setSupportActionBar(toolbar);

        imageView = findViewById(R.id.imageView);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorMagneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        SensorEventListener sensorEventListenerAccelerometer = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                floatGravity = event.values;

                SensorManager.getRotationMatrix(floatRotationMatrix,null,floatGravity,floatGeoMagnetic);
                SensorManager.getOrientation(floatRotationMatrix,floatOrientation);

                imageView.setRotation((float) (-floatOrientation[0]*180/3.14159));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };

        SensorEventListener sensorEventListenerMagneticField = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                floatGeoMagnetic = event.values;

                SensorManager.getRotationMatrix(floatRotationMatrix,null,floatGravity,floatGeoMagnetic);
                SensorManager.getOrientation(floatRotationMatrix,floatOrientation);

                imageView.setRotation((float) (-floatOrientation[0]*180/3.14159));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };

        sensorManager.registerListener(sensorEventListenerAccelerometer,sensorAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(sensorEventListenerMagneticField,sensorMagneticField,SensorManager.SENSOR_DELAY_NORMAL);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        nav.setNavigationItemSelectedListener(this);
    }

    public void ResetButton(View view)
    {
        imageView.setRotation(100);

    }


    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }



    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_home:
                Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(Qibla.this, Dashboard.class);
                startActivity(intent);
                break;
            case R.id.time:
                Toast.makeText(getApplicationContext(),"Time",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent1 = new Intent(Qibla.this, Time.class);
                startActivity(intent1);
                break;
            case R.id.qibla:
                Toast.makeText(getApplicationContext(),"Qibla",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent2 = new Intent(Qibla.this, Qibla.class);
                startActivity(intent2);
                break;
            case R.id.surah:
                Toast.makeText(getApplicationContext(),"Surah",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent3 = new Intent(Qibla.this, Surah.class);
                startActivity(intent3);
                break;
            case R.id.hadith:
                Toast.makeText(getApplicationContext(),"Hadith",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent4 = new Intent(Qibla.this, Hadith.class);
                startActivity(intent4);
                break;
            case R.id.mosques:
                Toast.makeText(getApplicationContext(),"Mosque",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent5 = new Intent(Qibla.this, Mosque.class);
                startActivity(intent5);
                break;
            case R.id.calendars:
                Toast.makeText(getApplicationContext(),"Calendars",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent6 = new Intent(Qibla.this, Calendar.class);
                startActivity(intent6);
                break;
            case R.id.Share:
                Toast.makeText(getApplicationContext(),"Share it With Your Friends",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent7 = new Intent(Qibla.this, share.class);
                startActivity(intent7);
                break;
            case R.id.rate:
                Toast.makeText(getApplicationContext(),"Rate Us",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent8 = new Intent(Qibla.this, rate.class);
                startActivity(intent8);
                break;
        }
        return true;
    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}