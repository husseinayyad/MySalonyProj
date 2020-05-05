package com.exm.example.mysalonyproj;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;

public class MakeupPageActivity extends AppCompatActivity {
    TextView add, salonprof,myservice ;
    FirebaseAuth mAuth;
    String test="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makeup_page);
        add=findViewById(R.id.Dresses);
        salonprof=findViewById(R.id.Suits);
        mAuth = FirebaseAuth.getInstance();
        myservice =findViewById(R.id.Accessories);



        salonprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplication(),MakeUpProfActivity.class);
                startActivity(intent);
            }
        });
        add .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplication() , MakeAppActivity.class);
                startActivity(intent);
            }
        });
    }
    }

