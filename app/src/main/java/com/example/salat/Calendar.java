package com.example.salat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Calendar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "tag";
    DrawerLayout drawer;
    NavigationView nav;
    Toolbar toolbar;
    RequestQueue mQueue;
    String url,url2;
    WebView webView;

    ProgressDialog pDialog;
    TextView cal,date;
    String currentDateTimeString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        date=findViewById(R.id.date);
        currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
        date.setText(currentDateTimeString);

        url="file:///android_asset/demo.html";
        webView=findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setBackgroundColor(Color.TRANSPARENT);

        url2="http://www.wifaqululama.co.uk/hijri?api=1";
        cal = findViewById(R.id.cal);
        mQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setTitle("Loading");
        pDialog.setMessage("Wait while loading...");
        pDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url2, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String calendar = response.getJSONArray("islamic").toString();

                            cal.setText(calendar);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                pDialog.hide();
            }
        });

// Adding request to request queue
        mQueue.add(jsonObjReq);
        //AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

        toolbar = findViewById(R.id.toolbar);
        nav = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        nav.setNavigationItemSelectedListener(this);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_home:
                Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(Calendar.this, Dashboard.class);
                startActivity(intent);
                break;
            case R.id.time:
                Toast.makeText(getApplicationContext(),"Time",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent1 = new Intent(Calendar.this, Time.class);
                startActivity(intent1);
                break;
            case R.id.qibla:
                Toast.makeText(getApplicationContext(),"Qibla",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent2 = new Intent(Calendar.this, Qibla.class);
                startActivity(intent2);
                break;
            case R.id.surah:
                Toast.makeText(getApplicationContext(),"Surah",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent3 = new Intent(Calendar.this, Surah.class);
                startActivity(intent3);
                break;
            case R.id.hadith:
                Toast.makeText(getApplicationContext(),"Hadith",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent4 = new Intent(Calendar.this, Hadith.class);
                startActivity(intent4);
                break;
            case R.id.mosques:
                Toast.makeText(getApplicationContext(),"Mosque",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent5 = new Intent(Calendar.this, Mosque.class);
                startActivity(intent5);
                break;
            case R.id.calendars:
                Toast.makeText(getApplicationContext(),"Calendars",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent6 = new Intent(Calendar.this, Calendar.class);
                startActivity(intent6);
                break;
            case R.id.Share:
                Toast.makeText(getApplicationContext(),"Share it With Your Friends",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent7 = new Intent(Calendar.this, share.class);
                startActivity(intent7);
                break;
            case R.id.rate:
                Toast.makeText(getApplicationContext(),"Rate Us",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent8 = new Intent(Calendar.this, rate.class);
                startActivity(intent8);
                break;
        }
        return true;
    }
}