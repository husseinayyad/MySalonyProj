package com.exm.example.mysalonyproj;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class SercActivity extends AppCompatActivity {
    private SearchView serc ;
    private EditText inputtext ;
    private RecyclerView recyclerView;
    String serinput;
    RecyclerView.LayoutManager layoutManager;
    DatabaseReference databaseReference,databaseReference1;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serc);
       serc=findViewById(R.id.serc);
        recyclerView= findViewById(R.id.rys11);
        mAuth= FirebaseAuth.getInstance();
       databaseReference =  FirebaseDatabase.getInstance().getReference().child("SalonatP");
       // databaseReference = FirebaseDatabase.getInstance().getReference().child("myservice").child(mAuth.getCurrentUser().getUid());
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        serc.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processQuery(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processQuery(s);
                return false;
            }
        });



    }
    private void processQuery(String query) {
        // in real app you'd have it instantiated just once
        FirebaseRecyclerOptions<Salonat> options = new FirebaseRecyclerOptions.Builder<Salonat>().setQuery(databaseReference.orderByChild("name").equalTo(query),Salonat.class).build();
        FirebaseRecyclerAdapter<Salonat,SalonHolder> adapter = new FirebaseRecyclerAdapter<Salonat, SalonHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull SalonHolder holder, int position, @NonNull final Salonat model) {
                holder.name.setText(model.getName());
                holder.adress.setText(model.getCity() + " :  "  +model.getAddress());
                holder.phone.setText(model.getPhone());
                Picasso.get().load(model.getImage()).into(holder.proimg);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent= new Intent(SercActivity.this,SalonServiceActivity.class);
                        intent.putExtra("uid",model.getOwner());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public SalonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.salont_prof,parent , false);
                SalonHolder holder = new SalonHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

 /*   @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Salonat> options = new FirebaseRecyclerOptions.Builder<Salonat>().setQuery(databaseReference.orderByChild("name").equalTo(serinput),Salonat.class).build();
        FirebaseRecyclerAdapter<Salonat,SalonHolder> adapter = new FirebaseRecyclerAdapter<Salonat, SalonHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull SalonHolder holder, int position, @NonNull final Salonat model) {
                holder.name.setText(model.getName());
                holder.adress.setText(model.getCity() + " :  "  +model.getAddress());
                holder.phone.setText(model.getPhone());
                Picasso.get().load(model.getImage()).into(holder.proimg);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent= new Intent(SercActivity.this,SalonServiceActivity.class);
                        intent.putExtra("uid",model.getOwner());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public SalonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.salont_prof,parent , false);
                SalonHolder holder = new SalonHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }*/
}

