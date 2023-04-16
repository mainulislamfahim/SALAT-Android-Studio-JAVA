package com.example.salat;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class Time extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    NavigationView nav;
    Toolbar toolbar;
    WebView web;
    private static final String TAG = "tag";
    String url ;
    String tag_json_obj = "json_obj_req";
    ProgressDialog pDialog;
    TextView mFajrTv,mDuhurTv,mAsrTv,mMagribTv,mIshaTv,mlocationTv,mdateTv;
    EditText mSearchEt,timeHour,timeMinute;
    Button searchBtn,setTime,setAlarm;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour,currentMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        mFajrTv=findViewById(R.id.fajrTv);
        mDuhurTv=findViewById(R.id.duhurTv);
        mAsrTv=findViewById(R.id.asrTv);
        mMagribTv=findViewById(R.id.maghribTv);
        mIshaTv=findViewById(R.id.ishaTv);
        mlocationTv=findViewById(R.id.loacationTv);
        mdateTv=findViewById(R.id.dateTv);
        mSearchEt=findViewById(R.id.searchEt);
        searchBtn=findViewById(R.id.searchBtn);

        timeHour=findViewById(R.id.hour);
        timeMinute=findViewById(R.id.min);
        setTime = findViewById(R.id.btnTime);
        setAlarm = findViewById(R.id.btnAlarm);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = mSearchEt.getText().toString().trim();
                if(location.isEmpty()){
                    Toast.makeText(Time.this,"PLEASE ENTER LOCATION",Toast.LENGTH_SHORT).show();
                }else{
                    url= "https://muslimsalat.com/"+location+".json?key=AIzaSyD5KCoG1S_bXrmzCwmJzfHX57CqQbhGCOY";
                    searchlocation();
                }
            }
        });

        setTime.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            currentHour = calendar.get(Calendar.HOUR_OF_DAY);
            currentMinute = calendar.get(Calendar.MINUTE);

            timePickerDialog =new TimePickerDialog(Time.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                    timeHour.setText(String.format("%02d",hourOfDay));
                    timeMinute.setText(String.format("%02d",minute));

                }
            },currentHour,currentMinute,false);
            timePickerDialog.show();
        });

        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!timeHour.getText().toString().isEmpty() && !timeMinute.getText().toString().isEmpty()){
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_HOUR, Integer.parseInt(timeHour.getText().toString()));
                intent.putExtra(AlarmClock.EXTRA_MINUTES, Integer.parseInt(timeMinute.getText().toString()));
                intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Set alarm SALAT");
                startActivity(intent);
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }else{
                    Toast.makeText(Time.this, "There is no app that support this action", Toast.LENGTH_SHORT).show();
                }
                }else{
                    Toast.makeText(Time.this, "Please choose a time", Toast.LENGTH_SHORT).show();
                }
            }
        });

        toolbar = findViewById(R.id.toolbar);
        nav = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        nav.setNavigationItemSelectedListener(this);
    }

    private void searchlocation() {

        pDialog = new ProgressDialog(this);
        pDialog.setTitle("Loading");
        pDialog.setMessage("Wait while loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //get data from JSON
                        try {
                            //get location
                            String country= response.get("country").toString();
                            String state= response.get("state").toString();
                            String city= response.get("city").toString();
                            String location= country +", "+ state +", "+", "+ city;
                            //get date
                            String date = response.getJSONArray("items").getJSONObject(0).get("date_for").toString();

                            //get namaz timing
                            String mFajr= response.getJSONArray("items").getJSONObject(0).get("fajr").toString();
                            String mDuhur= response.getJSONArray("items").getJSONObject(0).get("dhuhr").toString();
                            String mAsr= response.getJSONArray("items").getJSONObject(0).get("asr").toString();
                            String mMagrib= response.getJSONArray("items").getJSONObject(0).get("maghrib").toString();
                            String mIsha= response.getJSONArray("items").getJSONObject(0).get("isha").toString();
                            //set the dtaa to textview
                            mFajrTv.setText(mFajr);
                            mDuhurTv.setText(mDuhur);
                            mAsrTv.setText(mAsr);
                            mMagribTv.setText(mMagrib);
                            mIshaTv.setText(mIsha);
                            mlocationTv.setText(location);
                            mdateTv.setText(date);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(Time.this,"ERROR",Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                pDialog.hide();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_home:
                Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(Time.this, Dashboard.class);
                startActivity(intent);
                break;
            case R.id.time:
                Toast.makeText(getApplicationContext(),"Time",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent1 = new Intent(Time.this, Time.class);
                startActivity(intent1);
                break;
            case R.id.qibla:
                Toast.makeText(getApplicationContext(),"Qibla",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent2 = new Intent(Time.this, Qibla.class);
                startActivity(intent2);
                break;
            case R.id.surah:
                Toast.makeText(getApplicationContext(),"Surah",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent3 = new Intent(Time.this, Surah.class);
                startActivity(intent3);
                break;
            case R.id.hadith:
                Toast.makeText(getApplicationContext(),"Hadith",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent4 = new Intent(Time.this, Hadith.class);
                startActivity(intent4);
                break;
            case R.id.mosques:
                Toast.makeText(getApplicationContext(),"Mosque",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent5 = new Intent(Time.this, Mosque.class);
                startActivity(intent5);
                break;
            case R.id.calendars:
                Toast.makeText(getApplicationContext(),"Calendars",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent6 = new Intent(Time.this, Calendar.class);
                startActivity(intent6);
                break;
            case R.id.Share:
                Toast.makeText(getApplicationContext(),"Share it With Your Friends",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent7 = new Intent(Time.this, share.class);
                startActivity(intent7);
                break;
            case R.id.rate:
                Toast.makeText(getApplicationContext(),"Rate Us",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent8 = new Intent(Time.this, rate.class);
                startActivity(intent8);
                break;
        }
        return true;
    }

}