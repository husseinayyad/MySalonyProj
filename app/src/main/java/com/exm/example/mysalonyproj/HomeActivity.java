package com.exm.example.mysalonyproj;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;


import com.exm.example.mysalonyproj.EditMyProfile;
import com.google.android.gms.ads.InterstitialAd;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView imageButton1 , imageButton2 ;
    FirebaseAuth mAuth;
    FirebaseUser currentUser ;
    private InterstitialAd interstitialAd;

    Dialog popAddPost ;
    ImageView popupUserImage,popupPostImage,popupAddBtn;
    TextView popupTitle,popupDescription;
    ProgressBar popupClickProgress;
    private Uri pickedImgUri = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imageButton1=findViewById(R.id.btn1);
        mAuth = FirebaseAuth.getInstance();
        imageButton2=findViewById(R.id.btn2);




        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment= new WomenFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.ly2,fragment);

                fragmentTransaction.commit();
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment= new MenFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.ly2,fragment);
                fragmentTransaction.commit();
            }
        });


        updateNavHeader();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Locale locale = new Locale("en");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            Toast.makeText(getApplication()
                    ,"English", Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(HomeActivity.this,HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_settings1) {
            Locale locale = new Locale("ar");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            Toast.makeText(getApplication()
                    ,"العربية", Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(HomeActivity.this,HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            Intent  intent = new Intent(HomeActivity.this, FavActivity.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

            Intent  intent = new Intent(HomeActivity.this, SalonatActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {
            Intent  intent = new Intent(HomeActivity.this, ListSalonsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_tools) {
            Intent  intent = new Intent(HomeActivity.this, EditMyProfile.class);
            startActivity(intent);

        }

        else if (id == R.id.nav_send) {
            mAuth.signOut();
Intent  intent = new Intent(HomeActivity.this, MainActivity.class);
startActivity(intent);
        }
        else if (id == R.id.maa) {

            Intent  intent = new Intent(HomeActivity.this,MakeupPageActivity.class);
            startActivity(intent);
        }
     else if (id == R.id.serc) {

        Intent  intent = new Intent(HomeActivity.this,SercActivity.class);
        startActivity(intent);
    }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void updateNavHeader() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.nav_username);
        TextView navUserMail = headerView.findViewById(R.id.nav_user_mail);
        CircleImageView circleImageView = headerView.findViewById(R.id.profile_image);

        navUserMail.setText(mAuth.getCurrentUser().getEmail());
        navUsername.setText(mAuth.getCurrentUser().getDisplayName());

        // now we will use Glide to load user image
        // first we need to import the library

        Glide.with(this).load(mAuth.getCurrentUser().getPhotoUrl()).into(circleImageView);




    }

    @Override
    protected void onStart() {
        FirebaseUser user = mAuth.getCurrentUser();

        if(user == null) {
            //user is already connected  so we need to redirect him to home page
           Intent intent= new Intent(getApplication(), MainActivity.class);
           startActivity(intent);

        }
        super.onStart();
    }
}
