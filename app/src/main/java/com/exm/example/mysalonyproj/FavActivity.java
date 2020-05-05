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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class FavActivity extends AppCompatActivity {
    DatabaseReference databaseReference,databaseReference1;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    String x  , z;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        mAuth= FirebaseAuth.getInstance();
        recyclerView= findViewById(R.id.rys1);
        x=getIntent().getStringExtra("sub");
        z=getIntent().getStringExtra("w");

        databaseReference=FirebaseDatabase.getInstance().getReference().child("Fav").child(mAuth.getCurrentUser().getUid());
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
                FirebaseDatabase.getInstance().getReference().child("Service").child("Category").child(model.getType()).child(model.getSubcategory()).child(model.getId()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){

                            Salonat  salonat= dataSnapshot.getValue(Salonat.class);
                            holder.proname.setText(salonat.getName());
                            holder.prodesc.setText(salonat.getDescription());
                            holder.proprice.setText("Price : " +salonat.getPrice()+"JD");
                            holder.protype.setText("Address :" +salonat.getAddress());
                            holder.proowner.setText("Phone :"+salonat.getPhone());
                            holder.city.setText("City : "+ salonat.getCity());
                            Picasso.get().load(salonat.getImage()).into(holder.proimg);
                        }
                        else
                        {databaseReference.child(model.getId()).removeValue();}
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                holder.call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String s = model.getPhone();
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", s, null));
                        startActivity(intent);
                    }
                });
                Picasso.get().load(model.getImage()).into(holder.proimg);
holder.fav.setImageResource(R.drawable.tttt);
                holder.fav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        databaseReference.child(model.getId()).removeValue();

                    }
                });

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
