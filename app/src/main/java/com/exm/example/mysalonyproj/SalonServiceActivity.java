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
import android.widget.Toast;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class SalonServiceActivity extends AppCompatActivity {
    DatabaseReference databaseReference,databaseReference1;
    private RecyclerView recyclerView;
    private FirebaseAuth mAuth;
     String imgtest="";
    RecyclerView.LayoutManager layoutManager;
    String x  , z;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon_service);
        x=getIntent().getStringExtra("uid");
        mAuth= FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("myservice").child(x);
        databaseReference1=FirebaseDatabase.getInstance().getReference().child("Fav").child(mAuth.getCurrentUser().getUid());
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
                databaseReference1.child(model.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            holder.fav.setImageResource(R.drawable.tttt);
                            imgtest="t";
                        }
                        else {
                            imgtest="";
                        }



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                holder.fav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (imgtest=="t"){
                            holder.fav.setImageResource(R.drawable.favv1);
                            databaseReference1.child(model.getId()).removeValue();
                            imgtest="";

                        }

                        else {
                            final HashMap<String, Object> objectHashMap = new HashMap<>();
                            objectHashMap.put("id", model.getId());
                            objectHashMap.put("data", model.getData());
                            objectHashMap.put("time", model.getTime());
                            objectHashMap.put("description", model.getDescription());
                            objectHashMap.put("type", model.getType());
                            objectHashMap.put("phone",model.getPhone());
                            objectHashMap.put("name",model.getName());
                            objectHashMap.put("address", model.getAddress());
                            objectHashMap.put("city", model.getCity());
                            objectHashMap.put("price", model.getPrice());
                            objectHashMap.put("image", model.getImage());

                            objectHashMap.put("owner", model.getOwner());
                            objectHashMap.put("subcategory",model.getSubcategory());
                            databaseReference1.child(model.getId()).updateChildren(objectHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        holder.fav.setImageResource(R.drawable.tttt);
                                        imgtest="t";
                                        Toast.makeText(getApplication(),"Add to your favourite List ",Toast.LENGTH_LONG).show();

                                    }

                                }
                            });

                        }
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
