package com.example.salat;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.Date;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageView time, qibla, surah, hadith, mosque, calendar;

    DrawerLayout drawer;
    NavigationView nav;
    Toolbar toolbar;
    TextView date;
    String currentDateTimeString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        date=findViewById(R.id.date);
        currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
        date.setText(currentDateTimeString);

        time = findViewById(R.id.time);
        qibla = findViewById(R.id.qibla);
        surah = findViewById(R.id.surah);
        hadith = findViewById(R.id.hadith);
        mosque = findViewById(R.id.mosques);
        calendar = findViewById(R.id.calendars);

        toolbar = findViewById(R.id.toolbar);
        nav = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        nav.setNavigationItemSelectedListener(this);



        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Time.class);
                startActivity(intent);
                //finish();
            }
        });
        qibla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Qibla.class);
                startActivity(intent);
                //finish();
            }
        });
        surah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Surah.class);
                startActivity(intent);
                //finish();
            }
        });
        hadith.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Hadith.class);
                startActivity(intent);
                // finish();
            }
        });
        mosque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Mosque.class);
                startActivity(intent);
                //finish();
            }
        });
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Calendar.class);
                startActivity(intent);
                // finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
       switch (item.getItemId()){
           case R.id.menu_home:
                Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(Dashboard.this, Dashboard.class);
                startActivity(intent);
                break;
           case R.id.time:
               Toast.makeText(getApplicationContext(),"Time",Toast.LENGTH_LONG).show();
               drawer.closeDrawer(GravityCompat.START);
               Intent intent1 = new Intent(Dashboard.this, Time.class);
               startActivity(intent1);
               break;
           case R.id.qibla:
               Toast.makeText(getApplicationContext(),"Qibla",Toast.LENGTH_LONG).show();
               drawer.closeDrawer(GravityCompat.START);
               Intent intent2 = new Intent(Dashboard.this, Qibla.class);
               startActivity(intent2);
               break;
           case R.id.surah:
               Toast.makeText(getApplicationContext(),"Surah",Toast.LENGTH_LONG).show();
               drawer.closeDrawer(GravityCompat.START);
               Intent intent3 = new Intent(Dashboard.this, Surah.class);
               startActivity(intent3);
               break;
           case R.id.hadith:
               Toast.makeText(getApplicationContext(),"Hadith",Toast.LENGTH_LONG).show();
               drawer.closeDrawer(GravityCompat.START);
               Intent intent4 = new Intent(Dashboard.this, Hadith.class);
               startActivity(intent4);
               break;
           case R.id.mosques:
               Toast.makeText(getApplicationContext(),"Mosque",Toast.LENGTH_LONG).show();
               drawer.closeDrawer(GravityCompat.START);
               Intent intent5 = new Intent(Dashboard.this, Mosque.class);
               startActivity(intent5);
               break;
           case R.id.calendars:
               Toast.makeText(getApplicationContext(),"Calendars",Toast.LENGTH_LONG).show();
               drawer.closeDrawer(GravityCompat.START);
               Intent intent6 = new Intent(Dashboard.this, Calendar.class);
               startActivity(intent6);
               break;
           case R.id.Share:
               Toast.makeText(getApplicationContext(),"Share it With Your Friends",Toast.LENGTH_LONG).show();
               drawer.closeDrawer(GravityCompat.START);
               Intent intent7 = new Intent(Dashboard.this, share.class);
               startActivity(intent7);
               break;
           case R.id.rate:
               Toast.makeText(getApplicationContext(),"Rate Us",Toast.LENGTH_LONG).show();
               drawer.closeDrawer(GravityCompat.START);
               Intent intent8 = new Intent(Dashboard.this, rate.class);
               startActivity(intent8);
               break;


       }
        return true;
    }
}