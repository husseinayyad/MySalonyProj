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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ListSalonsActivity extends AppCompatActivity {
    DatabaseReference databaseReference,databaseReference1;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    String x  , z,subcategory1;
    private Button serc ;
    private EditText inputtext ;
   Button women , men ;
    String serinput;
    Spinner spinner, spinner1;
    private AdView adView;
    private InterstitialAd interstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_salons);
        MobileAds.initialize(this, "ca-app-pub-2109284707323352~4830468398");

        // Create the InterstitialAd and set the adUnitId.
        interstitialAd = new InterstitialAd(this);
        // Defined in res/values/strings.xml
        interstitialAd.setAdUnitId("ca-app-pub-2109284707323352/9477178466");

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Toast.makeText(ListSalonsActivity.this, "onAdLoaded()", Toast.LENGTH_SHORT).show();
                AdRequest adRequest = new AdRequest.Builder().build();
                interstitialAd.loadAd(adRequest);
                interstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(ListSalonsActivity.this,
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

        recyclerView= findViewById(R.id.rys1);
        spinner1 = (Spinner) findViewById(R.id.subcatspiner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.city_array,R.layout.it);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

spinner1.setAdapter(adapter1);
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

        women=findViewById(R.id.btn1);
men=findViewById(R.id.btn2);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Salonat");
        men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                FirebaseRecyclerOptions<Salonat> options = new FirebaseRecyclerOptions.Builder<Salonat>().setQuery(databaseReference.child("Salon For Men").orderByChild("city").equalTo(subcategory1),Salonat.class).build();
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
                                Intent intent= new Intent(ListSalonsActivity.this, SalonServiceActivity.class);
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

        });
women.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
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
        FirebaseRecyclerOptions<Salonat> options = new FirebaseRecyclerOptions.Builder<Salonat>().setQuery(databaseReference.child("Salon For Women").orderByChild("city").equalTo(subcategory1),Salonat.class).build();
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
    }
});


        x=getIntent().getStringExtra("sub");
        z=getIntent().getStringExtra("w");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Salonat");
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        FirebaseRecyclerOptions<Salonat> options = new FirebaseRecyclerOptions.Builder<Salonat>().setQuery(databaseReference.child("Salon For Women").orderByChild("city").equalTo(subcategory1),Salonat.class).build();
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

