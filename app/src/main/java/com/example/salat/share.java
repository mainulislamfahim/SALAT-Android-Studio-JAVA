package com.example.salat;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class share extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    NavigationView nav;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar = findViewById(R.id.toolbar);
        nav = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        nav.setNavigationItemSelectedListener(this);

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,"https://drive.google.com/file/d/1Po1RP9unWr7CSAWaqqydUgvbz0MipN8Y/view?usp=sharing");
        sendIntent.setType("text/plain");
        Intent shareIntent=Intent.createChooser(sendIntent,null);
        startActivity(sendIntent);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_home:
                Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(share.this, Dashboard.class);
                startActivity(intent);
                break;
            case R.id.time:
                Toast.makeText(getApplicationContext(),"Time",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent1 = new Intent(share.this, Time.class);
                startActivity(intent1);
                break;
            case R.id.qibla:
                Toast.makeText(getApplicationContext(),"Qibla",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent2 = new Intent(share.this, Qibla.class);
                startActivity(intent2);
                break;
            case R.id.surah:
                Toast.makeText(getApplicationContext(),"Surah",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent3 = new Intent(share.this, Surah.class);
                startActivity(intent3);
                break;
            case R.id.hadith:
                Toast.makeText(getApplicationContext(),"Hadith",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent4 = new Intent(share.this, Hadith.class);
                startActivity(intent4);
                break;
            case R.id.mosques:
                Toast.makeText(getApplicationContext(),"Mosque",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent5 = new Intent(share.this, Mosque.class);
                startActivity(intent5);
                break;
            case R.id.calendars:
                Toast.makeText(getApplicationContext(),"Calendars",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent6 = new Intent(share.this, Calendar.class);
                startActivity(intent6);
                break;
            case R.id.Share:
                Toast.makeText(getApplicationContext(),"Share it With Your Friends",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent7 = new Intent(share.this, share.class);
                startActivity(intent7);
                break;
            case R.id.rate:
                Toast.makeText(getApplicationContext(),"Rate Us",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent8 = new Intent(share.this, rate.class);
                startActivity(intent8);
                break;
        }
        return true;
    }
}