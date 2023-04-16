package com.example.salat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.prefs.PreferenceChangeEvent;

public class SignUp extends AppCompatActivity {


     TextInputLayout regName,regUserName,regMail,regPhone,regPass;
     Button signUp,login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        login=findViewById(R.id.s_login);
        signUp=findViewById(R.id.s_signUp);
        regName=findViewById(R.id.name);
        regUserName=findViewById(R.id.username);
        regMail=findViewById(R.id.email);
        regPhone=findViewById(R.id.phone);
        regPass=findViewById(R.id.lpass);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        
        signUp.setOnClickListener(v -> {

            String name= regName.getEditText().getText().toString();
            String username= regUserName.getEditText().getText().toString();
            String mail= regMail.getEditText().getText().toString();
            String phone= regPhone.getEditText().getText().toString();
            String password= regPass.getEditText().getText().toString();

           UserHelperClass helperClass= new UserHelperClass(name,username,mail,phone,password);

            myRef.child(username).setValue(helperClass);
            Intent intent =new Intent(SignUp.this,Login.class);
            startActivity(intent);
            finish();


        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SignUp.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

    }
}