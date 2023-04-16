package com.example.salat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    Button signup,pass,login;
    TextInputLayout username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username=findViewById(R.id.luserName);
        password=findViewById(R.id.lpass);
        signup=findViewById(R.id.l_signup);
        pass=findViewById(R.id.l_fpass);
        login=findViewById(R.id.l_login);

        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=username.getEditText().getText().toString().trim();
                String pass=password.getEditText().getText().toString().trim();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                Query checkUser =reference.orderByChild("userName").equalTo(name);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){

                            username.setError(null);
                            username.setErrorEnabled(false);

                            String passFromDb = snapshot.child(name).child("pass").getValue(String.class);

                            if(passFromDb.equals(pass)){

                                username.setError(null);
                                username.setErrorEnabled(false);

                                String nameFromDb = snapshot.child(name).child("name").getValue(String.class);
                                String userFromDb = snapshot.child(name).child("username").getValue(String.class);
                                String mailFromDb = snapshot.child(name).child("mail").getValue(String.class);
                                String phoneFromDb = snapshot.child(name).child("phone").getValue(String.class);

                                Intent intent = new Intent(getApplicationContext(),Dashboard.class);

                                intent.putExtra("name",nameFromDb);
                                intent.putExtra("pass",passFromDb);
                                intent.putExtra("mail",mailFromDb);
                                intent.putExtra("phone",phoneFromDb);
                                intent.putExtra("username",userFromDb);

                                startActivity(intent);
                            }else{
                                password.setError("Wrong Password! Try Forgot Password");
                                password.requestFocus(); 
                            }
                        }else{
                            username.setError("No Such User Exists! Create One");
                            username.requestFocus();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,SignUp.class);
                startActivity(intent);
                finish();
            }
        });
    }
}