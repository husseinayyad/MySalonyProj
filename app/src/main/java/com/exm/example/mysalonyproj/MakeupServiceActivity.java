package com.exm.example.mysalonyproj;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;



import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MakeupServiceActivity extends AppCompatActivity {
    DatabaseReference databaseReference,databaseReference1;
    private RecyclerView recyclerView;
    private FirebaseAuth mAuth;

    RecyclerView.LayoutManager layoutManager;
    String x  , z;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makeup_service);
        mAuth= FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("myservice").child("makeuppp").child(mAuth.getCurrentUser().getUid());
        recyclerView= findViewById(R.id.rys1);
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FirebaseRecyclerOptions<Salonat> options = new FirebaseRecyclerOptions.Builder<Salonat>().setQuery(databaseReference,Salonat.class).build();
        FirebaseRecyclerAdapter<Salonat,MyservHolder> adapter = new FirebaseRecyclerAdapter<Salonat, MyservHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyservHolder holder, int position, @NonNull final Salonat model) {
                holder.proname.setText(model.getName());
                holder.prodesc.setText(model.getDescription());
                holder.proprice.setText("Price :" +model.getPrice()+"JD");
                holder.protype.setText("Address :" +model.getAddress());
                holder.proowner.setText("Phone :"+model.getPhone());
                holder.city.setText("City : "+ model.getCity());
                holder.call.setText("Remove");
                holder.fav.setText("Edit");
                holder.fav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent= new Intent(MakeupServiceActivity.this, MkaeUpEDitActivity.class);
                        intent.putExtra("price",model.getPrice());
                        intent.putExtra("desc",model.getDescription());
                        intent.putExtra("img",model.getImage());
                        intent.putExtra("id",model.getId());
                        intent.putExtra("type",model.getType());
                        intent.putExtra("supcat",model.getSubcategory());
                        startActivity(intent);
                    }
                });
                holder.call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        databaseReference.child(model.getId()).removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                FirebaseDatabase.getInstance().getReference().child("Service").child("makeu[").child(model.getType()).child(model.getSubcategory()).child(model.getId()).removeValue();
                                Toast.makeText(getApplicationContext(),"Done Your Service is Removed ",Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                });
                Picasso.get().load(model.getImage()).into(holder.proimg);

            }

            @NonNull
            @Override
            public MyservHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myserv_item,parent , false);
                MyservHolder holder = new MyservHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
    }

