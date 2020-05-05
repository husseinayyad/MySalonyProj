package com.exm.example.mysalonyproj;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;



import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SalonatActivity extends AppCompatActivity {
    TextView add, salonprof,myservice ;
    FirebaseAuth mAuth;
    String test="" ,fv="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salonat);
        add=findViewById(R.id.Dresses);
        salonprof=findViewById(R.id.Suits);
        mAuth = FirebaseAuth.getInstance();
        myservice =findViewById(R.id.Accessories);
     DatabaseReference   databaseReference1= FirebaseDatabase.getInstance().getReference().child("SalonatP").child(mAuth.getCurrentUser().getUid());
        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    test="true";

                }
                else {
                    test="false";
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
if (test.equals("true")){

    Intent intent= new Intent(getApplication(),
            AddServiceActivity.class);
    startActivity(intent);
}
else {
    Toast.makeText(getApplicationContext(),"Please Create Your Salon Profile Before Adding Service",Toast.LENGTH_LONG).show();
}



            }
        });

        salonprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplication(), MySalonProfActivity.class);
                startActivity(intent);
            }
        });
        myservice .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplication(),MyServiceActivity.class);
                startActivity(intent);
            }
        });
    }
    }

