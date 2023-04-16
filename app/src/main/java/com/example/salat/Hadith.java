package com.example.salat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Hadith extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerView;
    DrawerLayout drawer;
    NavigationView nav;
    Toolbar toolbar;
    ListView listView;
    DatabaseReference databaseReference;
    List<putPDF> uploadedPDF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrive_pdf);

        toolbar = findViewById(R.id.toolbar);
        nav = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer);
        setSupportActionBar(toolbar);


        listView=findViewById(R.id.listView);
        uploadedPDF=new ArrayList<>();

        retrivePDFfiles();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                putPDF putPDF = uploadedPDF.get(position);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setType("application/pdf");
                intent.setData(Uri.parse(putPDF.getUrl()));
                startActivity(intent);
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        nav.setNavigationItemSelectedListener(this);

    }
    private void retrivePDFfiles() {
        databaseReference = FirebaseDatabase.getInstance().getReference("uploadPDF");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    putPDF putPDF=ds.getValue(com.example.salat.putPDF.class);
                    uploadedPDF.add(putPDF);

                }

                String[] uploadsName =new String[uploadedPDF.size()];
                for(int i=0;i<uploadsName.length;i++){
                    uploadsName[i]=uploadedPDF.get(i).getName();
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,uploadsName){
                    @Nullable
                    @Override
                    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent){
                        View view =super.getView(position,convertView,parent);
                        TextView textView =(TextView) view.findViewById(android.R.id.text1);
                        textView.setTextColor(Color.BLACK);
                        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
                        textView.setTextSize(20);
                        return view;
                    }
                };
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_home:
                Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(Hadith.this, Dashboard.class);
                startActivity(intent);
                break;
            case R.id.time:
                Toast.makeText(getApplicationContext(),"Time",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent1 = new Intent(Hadith.this, Time.class);
                startActivity(intent1);
                break;
            case R.id.qibla:
                Toast.makeText(getApplicationContext(),"Qibla",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent2 = new Intent(Hadith.this, Qibla.class);
                startActivity(intent2);
                break;
            case R.id.surah:
                Toast.makeText(getApplicationContext(),"Surah",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent3 = new Intent(Hadith.this, Surah.class);
                startActivity(intent3);
                break;
            case R.id.hadith:
                Toast.makeText(getApplicationContext(),"Hadith",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent4 = new Intent(Hadith.this, Hadith.class);
                startActivity(intent4);
                break;
            case R.id.mosques:
                Toast.makeText(getApplicationContext(),"Mosque",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent5 = new Intent(Hadith.this, Mosque.class);
                startActivity(intent5);
                break;
            case R.id.calendars:
                Toast.makeText(getApplicationContext(),"Calendars",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent6 = new Intent(Hadith.this, Calendar.class);
                startActivity(intent6);
                break;
            case R.id.Share:
                Toast.makeText(getApplicationContext(),"Share it With Your Friends",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent7 = new Intent(Hadith.this, share.class);
                startActivity(intent7);
                break;
            case R.id.rate:
                Toast.makeText(getApplicationContext(),"Rate Us",Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                Intent intent8 = new Intent(Hadith.this, rate.class);
                startActivity(intent8);
                break;
        }
        return true;
    }

    public void retrivePDF(View view){
        startActivity(new Intent(getApplicationContext(),RetrivePdf.class));
    }
}