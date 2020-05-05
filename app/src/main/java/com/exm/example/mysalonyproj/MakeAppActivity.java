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
import android.widget.Button;
import android.widget.EditText;



import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MakeAppActivity extends AppCompatActivity {
    DatabaseReference databaseReference,databaseReference1;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    String x  , z;
    private Button serc ;
    private EditText inputtext ;
    String serinput;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_app);
        recyclerView= findViewById(R.id.rys1);
        mAuth=FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        x=getIntent().getStringExtra("sub");
        z=getIntent().getStringExtra("w");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("makeuppp");
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        FirebaseRecyclerOptions<Salonat> options = new FirebaseRecyclerOptions.Builder<Salonat>().setQuery(databaseReference,Salonat.class).build();
        FirebaseRecyclerAdapter<Salonat,SalonHolder> adapter = new FirebaseRecyclerAdapter<Salonat, SalonHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull SalonHolder holder, int position, @NonNull final Salonat model) {
                holder.name.setText(model.getName());
                holder.adress.setText(model.getCity() + " :  "  +model.getAddress());
                holder.phone.setText(model.getPhone());
                holder.desc.setText(model.getDescription());
                holder.price.setText(model.getPrice());
                Picasso.get().load(model.getImage()).into(holder.proimg);
            holder.b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String s = model.getPhone();
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", s, null));
                    startActivity(intent);
                }
            });

            }

            @NonNull
            @Override
            public SalonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.salont_prof1,parent , false);
                SalonHolder holder = new SalonHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();


    }
    /*@Override
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
                        Intent intent= new Intent(ListSalonsActivity.this,SalonServiceActivity.class);
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

