package com.exm.example.mysalonyproj;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MakeUpServvvActivity extends AppCompatActivity {
    DatabaseReference databaseReference,databaseReference1;
    private RecyclerView recyclerView;
    private FirebaseAuth mAuth;
    String imgtest="";
    RecyclerView.LayoutManager layoutManager;
    String x  , z;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_up_servvv);
        x=getIntent().getStringExtra("uid");
        mAuth= FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("myservice").child("makeuppp").child(x);

        recyclerView= findViewById(R.id.rys1);
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FirebaseRecyclerOptions<Salonat> options = new FirebaseRecyclerOptions.Builder<Salonat>().setQuery(databaseReference,Salonat.class).build();
        FirebaseRecyclerAdapter<Salonat,ProductHolder> adapter = new FirebaseRecyclerAdapter<Salonat, ProductHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ProductHolder holder, int position, @NonNull final Salonat model) {
                holder.proname.setText(model.getName());
                holder.prodesc.setText(model.getDescription());
                holder.proprice.setText("Price : " +model.getPrice()+"JD");
                holder.protype.setText("Address :" +model.getAddress());
                holder.proowner.setText("Phone :"+model.getPhone());
                holder.city.setText("City : "+ model.getCity());

                holder.call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String s = model.getPhone();
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", s, null));
                        startActivity(intent);
                    }
                });
                Picasso.get().load(model.getImage()).into(holder.proimg);

            holder.fav.setVisibility(View.INVISIBLE);

            }

            @NonNull
            @Override
            public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent , false);
                ProductHolder holder = new ProductHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }
    }

