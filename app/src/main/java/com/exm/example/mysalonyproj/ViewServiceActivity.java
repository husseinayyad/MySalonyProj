package com.exm.example.mysalonyproj;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;




import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
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

public class ViewServiceActivity extends AppCompatActivity {
    DatabaseReference databaseReference,databaseReference1;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    String x  , z, imgtest="",subcategory1;
    private FirebaseAuth mAuth;
    private InterstitialAd interstitialAd;
    Spinner spinner, spinner1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_service);
        MobileAds.initialize(this, "ca-app-pub-2109284707323352~4830468398");

        // Create the InterstitialAd and set the adUnitId.
        interstitialAd = new InterstitialAd(this);
        // Defined in res/values/strings.xml
        interstitialAd.setAdUnitId("ca-app-pub-2109284707323352/9477178466");

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Toast.makeText(ViewServiceActivity.this, "onAdLoaded()", Toast.LENGTH_SHORT).show();
                AdRequest adRequest = new AdRequest.Builder().build();
                interstitialAd.loadAd(adRequest);
                interstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(ViewServiceActivity.this,
                        "onAdFailedToLoad() with error code: " + errorCode,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClosed() {

            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);
        interstitialAd.show();
        mAuth= FirebaseAuth.getInstance();
        recyclerView= findViewById(R.id.rys1);
        x=getIntent().getStringExtra("sub");
        z=getIntent().getStringExtra("w");
        spinner1 = (Spinner) findViewById(R.id.subcatspiner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.city_array, R.layout.it);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
       spinner1. setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                        Object item = parent.getItemAtPosition(pos);
                        if (item.toString().equals("عمان")){
                            subcategory1="Amman";
                        }

                        else if(item.toString().equals("السلط")) {
                            subcategory1="Salt";
                        }
                        else if(item.toString().equals("اربد")) {
                            subcategory1="Irbid";
                        }
                        else if(item.toString().equals("العقبة")) {
                            subcategory1="Aqaba";
                        }
                        else if(item.toString().equals("معان")) {
                            subcategory1="Ma'an";
                        }
                        else if(item.toString().equals("الطفيلة")) {
                            subcategory1="Tafilah";
                        }
                        else if(item.toString().equals("الزرقاء")) {
                            subcategory1="Zarqa";
                        }
                        else if(item.toString().equals("عجلون")) {
                            subcategory1="Ajloun";
                        }
                        else if(item.toString().equals("الكرك")) {
                            subcategory1="Karak";
                        }
                        else if(item.toString().equals("مادبا")) {
                            subcategory1="Madaba";
                        }
                        else if(item.toString().equals("جرش")) {
                            subcategory1="Jerash";
                        }
                        else if(item.toString().equals("الفحيص")) {
                            subcategory1="Fuhais";
                        }
                        else if(item.toString().equals("المفرق")) {
                            subcategory1="Mafraq";
                        }


                        else {
                            subcategory1=parent.getSelectedItem().toString();
                        }
                        recyclerView.setLayoutManager(layoutManager);
                        FirebaseRecyclerOptions<Salonat> options = new FirebaseRecyclerOptions.Builder<Salonat>().setQuery(databaseReference1.orderByChild("city").equalTo(subcategory1),Salonat.class).build();
                        FirebaseRecyclerAdapter<Salonat, ProductHolder> adapter = new FirebaseRecyclerAdapter<Salonat, ProductHolder>(options) {
                            @Override
                            protected void onBindViewHolder(@NonNull final ProductHolder holder, int position, @NonNull final Salonat model) {
                                holder.proname.setText(model.getName());
                                holder.prodesc.setText(model.getDescription());
                                holder.proprice.setText("Price :" +model.getPrice()+"JD");
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
                                databaseReference.child(model.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
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
                                            databaseReference.child(model.getId()).removeValue();
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
                                            databaseReference.child(model.getId()).updateChildren(objectHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
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
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
      /*  spinner1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getSelectedItem().toString().equals("عمان")){
                    subcategory1="Amman";
                }

                else if(adapterView.getSelectedItem().toString().equals("السلط")) {
                    subcategory1="Salt";
                }
                else if(adapterView.getSelectedItem().toString().equals("اربد")) {
                    subcategory1="Irbid";
                }
                else if(adapterView.getSelectedItem().toString().equals("العقبة")) {
                    subcategory1="Aqaba";
                }
                else if(adapterView.getSelectedItem().toString().equals("معان")) {
                    subcategory1="Ma'an";
                }
                else if(adapterView.getSelectedItem().toString().equals("الطفيلة")) {
                    subcategory1="Tafilah";
                }
                else if(adapterView.getSelectedItem().toString().equals("الزرقاء")) {
                    subcategory1="Zarqa";
                }
                else if(adapterView.getSelectedItem().toString().equals("عجلون")) {
                    subcategory1="Ajloun";
                }
                else if(adapterView.getSelectedItem().toString().equals("الكرك")) {
                    subcategory1="Karak";
                }
                else if(adapterView.getSelectedItem().toString().equals("مادبا")) {
                    subcategory1="Madaba";
                }
                else if(adapterView.getSelectedItem().toString().equals("جرش")) {
                    subcategory1="Jerash";
                }
                else if(adapterView.getSelectedItem().toString().equals("الفحيص")) {
                    subcategory1="Fuhais";
                }
                else if(adapterView.getSelectedItem().toString().equals("المفرق")) {
                    subcategory1="Mafraq";
                }


                else {
                    subcategory1=adapterView.getSelectedItem().toString();
                }
                recyclerView.setLayoutManager(layoutManager);
                FirebaseRecyclerOptions<Salonat> options = new FirebaseRecyclerOptions.Builder<Salonat>().setQuery(databaseReference1.orderByChild("city").equalTo(subcategory1),Salonat.class).build();
                FirebaseRecyclerAdapter<Salonat,ProductHolder> adapter = new FirebaseRecyclerAdapter<Salonat, ProductHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final ProductHolder holder, int position, @NonNull final Salonat model) {
                        holder.proname.setText(model.getName());
                        holder.prodesc.setText(model.getDescription());
                        holder.proprice.setText("Price :" +model.getPrice()+"JD");
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
                        databaseReference.child(model.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
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
                                    databaseReference.child(model.getId()).removeValue();
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
                                    databaseReference.child(model.getId()).updateChildren(objectHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
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
        });*/
        if (spinner1.getSelectedItem().toString().equals("عمان")){
            subcategory1="Amman";
        }

        else if(spinner1.getSelectedItem().toString().equals("السلط")) {
            subcategory1="Salt";
        }
        else if(spinner1.getSelectedItem().toString().equals("اربد")) {
            subcategory1="Irbid";
        }
        else if(spinner1.getSelectedItem().toString().equals("العقبة")) {
            subcategory1="Aqaba";
        }
        else if(spinner1.getSelectedItem().toString().equals("معان")) {
            subcategory1="Ma'an";
        }
        else if(spinner1.getSelectedItem().toString().equals("الطفيلة")) {
            subcategory1="Tafilah";
        }
        else if(spinner1.getSelectedItem().toString().equals("الزرقاء")) {
            subcategory1="Zarqa";
        }
        else if(spinner1.getSelectedItem().toString().equals("عجلون")) {
            subcategory1="Ajloun";
        }
        else if(spinner1.getSelectedItem().toString().equals("الكرك")) {
            subcategory1="Karak";
        }
        else if(spinner1.getSelectedItem().toString().equals("مادبا")) {
            subcategory1="Madaba";
        }
        else if(spinner1.getSelectedItem().toString().equals("جرش")) {
            subcategory1="Jerash";
        }
        else if(spinner1.getSelectedItem().toString().equals("الفحيص")) {
            subcategory1="Fuhais";
        }
        else if(spinner1.getSelectedItem().toString().equals("المفرق")) {
            subcategory1="Mafraq";
        }


        else {
            subcategory1=spinner1.getSelectedItem().toString();
        }
        databaseReference1= FirebaseDatabase.getInstance().getReference().child("Service").child("Category").child(z).child(x);
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Fav").child(mAuth.getCurrentUser().getUid());
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FirebaseRecyclerOptions<Salonat> options = new FirebaseRecyclerOptions.Builder<Salonat>().setQuery(databaseReference1.orderByChild("city").equalTo(subcategory1),Salonat.class).build();
        FirebaseRecyclerAdapter<Salonat,ProductHolder> adapter = new FirebaseRecyclerAdapter<Salonat, ProductHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ProductHolder holder, int position, @NonNull final Salonat model) {
                holder.proname.setText(model.getName());
                holder.prodesc.setText(model.getDescription());
                holder.proprice.setText("Price :" +model.getPrice()+"JD");
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
                databaseReference.child(model.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
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
                            databaseReference.child(model.getId()).removeValue();
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
                            databaseReference.child(model.getId()).updateChildren(objectHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
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
