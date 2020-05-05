package com.exm.example.mysalonyproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.HashMap;

public class MakeUpProfActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton r1, r2 ,r3;
    private String checker = "", df="";
    String ch = "";
    String rc= "";
    private InterstitialAd interstitialAd;
    static int PReqCode = 1 ;
    static int REQUESCODE = 1 ;
    private FirebaseAuth mAuth;
    private String getcatgory, category11, des1, type1, owner, address1, phone1, name1,
            subcategory1, price1, savedata, savetime, proudctrandomkey, donlowadimg;
    private Button addnew;
    private EditText address, des, price, name, phone, subcategory, proowner;
    private ImageView inputimage;
    private static final int galaryn = 1;
    private Uri imageuri;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private ProgressDialog loadingBar;
    String radiotext;
    Spinner spinner, spinner1;
    TextView category0;
    String uid ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_up_prof);
        addnew = findViewById(R.id.addpro);
        // category=findViewById(R.id.procat);
        mAuth = FirebaseAuth.getInstance();

        radioGroup = findViewById(R.id.protype);
        category0 = findViewById(R.id.tv30);


        name = findViewById(R.id.proprice);
        address = findViewById(R.id.prodesc);
        des=findViewById(R.id.mdesc);
        price=findViewById(R.id.mprice);
        phone = findViewById(R.id.salonphone);
        spinner1 = (Spinner) findViewById(R.id.subcatspiner);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.city_array, R.layout.it1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter1);


        inputimage = findViewById(R.id.selectpro);
        storageReference = FirebaseStorage.getInstance().getReference().child(" images");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("makeuppp");
        userInfoDisplay(inputimage, name, phone, address,des,price,spinner1);
        loadingBar = new ProgressDialog(this);
        MobileAds.initialize(this, "ca-app-pub-2109284707323352~4830468398");

        // Create the InterstitialAd and set the adUnitId.
        interstitialAd = new InterstitialAd(this);
        // Defined in res/values/strings.xml
        interstitialAd.setAdUnitId("ca-app-pub-2109284707323352/9477178466");

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Toast.makeText(MakeUpProfActivity.this, "onAdLoaded()", Toast.LENGTH_SHORT).show();
                AdRequest adRequest = new AdRequest.Builder().build();
                interstitialAd.loadAd(adRequest);
                interstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(MakeUpProfActivity.this,
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

        addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch=="cl"){

                    if (df.equals("t"))
                    {
                        validaadata();
                    }
                    else
                    {
                        validaadata1();
                    }
                }
                else {
                    validaadata();
                }
            }
        });
        inputimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                opengallry();
            }
        });

    }


    private void opengallry() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/’");
        startActivityForResult(intent, REQUESCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUESCODE && resultCode == RESULT_OK && data != null) {
            imageuri = data.getData();
            inputimage.setImageURI(imageuri);
            df="t";
            checker = "clicked";
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void startGame() {
        // Request a new ad if one isn't already loaded, hide the button, and kick off the timer.
        if (!interstitialAd.isLoading() && !interstitialAd.isLoaded()) {
            AdRequest adRequest = new AdRequest.Builder().build();
            interstitialAd.loadAd(adRequest);
        }


    }
    private void validaadata() {
        address1 = address.getText().toString();
        phone1 = phone.getText().toString();
        name1 = name.getText().toString();

        if (spinner1.getSelectedItem().toString().equals("قص شعر")){
            subcategory1="Haircut";
        }
        else if(spinner1.getSelectedItem().toString().equals("تسريحات شعر")) {
            subcategory1="Hairstyle";
        }
        else if(spinner1.getSelectedItem().toString().equals("لحية")) {
            subcategory1="Beared";
        }
        else if(spinner1.getSelectedItem().toString().equals("كرياتين و بروتين")) {
            subcategory1="Creatine &amp; Protein";
        }

        else if(spinner1.getSelectedItem().toString().equals("حمام زيت")) {
            subcategory1="Oil Bath";
        }

        else if(spinner1.getSelectedItem().toString().equals("غسيل بشرة")) {
            subcategory1="Skin Cleaning";
        }
        else if(spinner1.getSelectedItem().toString().equals("عروض")) {
            subcategory1="Offers";
        }



        else if(spinner1.getSelectedItem().toString().equals("المساج")) {
            subcategory1="Massage";
        }
        else if(spinner1.getSelectedItem().toString().equals("خيوط")) {
            subcategory1="Threading";
        }
        else if(spinner1.getSelectedItem().toString().equals("تجميل الوجه")) {
            subcategory1="Facial";
        }

        else if(spinner1.getSelectedItem().toString().equals("الشعر")) {
            subcategory1="Hair";
        }

        else if(spinner1.getSelectedItem().toString().equals("عروض")) {
            subcategory1="Offers";
        }
        else if(spinner1.getSelectedItem().toString().equals("المكياج")) {
            subcategory1="Make Up";
        }
        else if(spinner1.getSelectedItem().toString().equals("العناية بالجسم")) {
            subcategory1="Body care";
        }
        else if(spinner1.getSelectedItem().toString().equals("رمشة عين")) {
            subcategory1="Eyelash";
        }
        else if(spinner1.getSelectedItem().toString().equals("الأظافر والمكياج")) {
            subcategory1="Nails &amp; Makeup";
        }
        else if(spinner1.getSelectedItem().toString().equals("ماكياج دائم")) {
            subcategory1="Permanent MakeUp";
        }
        else if(spinner1.getSelectedItem().toString().equals("مكياج و شعر العرسان")) {
            subcategory1="Bridal Hair &amp; Makeup";
        }

        else {
            subcategory1=spinner1.getSelectedItem().toString();
        }






        if (TextUtils.isEmpty(address1)) {
            Toast.makeText(this, " Address is Empty", Toast.LENGTH_LONG).show();

        } else if (TextUtils.isEmpty(phone1)) {
            Toast.makeText(this, " Phone is Empty", Toast.LENGTH_LONG).show();

        } else if (phone1.contains("-")) {
            Toast.makeText(this, "Phone  have minus number", Toast.LENGTH_LONG).show();}
            else if (TextUtils.isEmpty(price.getText().toString())) {
                Toast.makeText(this, "Price is Empty", Toast.LENGTH_LONG).show();

            }
        else if (TextUtils.isEmpty(des.getText().toString())) {
            Toast.makeText(this, " description is Empty", Toast.LENGTH_LONG).show();

        }
            else if (phone1.contains("-")) {
                Toast.makeText(this, "Phone  have minus number", Toast.LENGTH_LONG).show();

        } else if (TextUtils.isEmpty(name1)) {
            Toast.makeText(this, "Name is Empty", Toast.LENGTH_LONG).show();

        } else if (TextUtils.isEmpty(subcategory1)) {
            Toast.makeText(this, " City is Empty", Toast.LENGTH_LONG).show();

        } else if (checker != "clicked") {
            Toast.makeText(this, "Please Upload Image", Toast.LENGTH_LONG).show();
        }  else {
            storeproductinfo();
        }
    }
    private void validaadata1() {
        address1 = address.getText().toString();
        phone1 = phone.getText().toString();
        name1 = name.getText().toString();

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


        if (TextUtils.isEmpty(address1)) {
            Toast.makeText(this, "Address is Empty", Toast.LENGTH_LONG).show();

        } else if (TextUtils.isEmpty(phone1)) {
            Toast.makeText(this, "Phone is Empty", Toast.LENGTH_LONG).show();

        } else if (phone1.contains("-")) {
            Toast.makeText(this, " Phone  have minus number", Toast.LENGTH_LONG).show();

        } else if (TextUtils.isEmpty(name1)) {
            Toast.makeText(this, " Name is Empty", Toast.LENGTH_LONG).show();}
              else if (TextUtils.isEmpty(price.getText().toString())) {
                Toast.makeText(this, "Price is Empty", Toast.LENGTH_LONG).show();

            }
            else if (TextUtils.isEmpty(des.getText().toString())) {
                Toast.makeText(this, " description is Empty", Toast.LENGTH_LONG).show();


            } else if (TextUtils.isEmpty(subcategory1)) {
            Toast.makeText(this, "Salon City is Empty", Toast.LENGTH_LONG).show();

        } else if (checker != "clicked") {
            Toast.makeText(this, "Please Upload Image", Toast.LENGTH_LONG).show();
        } else {
            saveprointodatabase1();
        }
    }

    private void storeproductinfo() {
        loadingBar.setTitle("Create Your  Profile");
        loadingBar.setMessage("Please wait, while we are Creating Your Profile.");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();
        Calendar calendar = Calendar.getInstance();

        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(SimpleDateFormat.YEAR_ABBR_MONTH);
        savedata = dateFormat.format(calendar.getTime());

        java.text.SimpleDateFormat dateFormat1 = new java.text.SimpleDateFormat("HH:mm:ss a");
        savetime = dateFormat1.format(calendar.getTime());
        proudctrandomkey = savedata + savetime;

        final StorageReference filepath = storageReference.child(imageuri.getLastPathSegment() + proudctrandomkey + ". jpg");
        final UploadTask uploadTask = filepath.putFile(imageuri);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = e.toString();
                Toast.makeText(MakeUpProfActivity.this, " Error : " + message, Toast.LENGTH_LONG).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(MakeUpProfActivity.this, "  image uploaded successfully ", Toast.LENGTH_LONG).show();
                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();


                        }
                        donlowadimg = filepath.getDownloadUrl().toString();
                        return filepath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            donlowadimg = task.getResult().toString();

                            Toast.makeText(MakeUpProfActivity.this, "  image save to database", Toast.LENGTH_LONG).show();
                            saveprointodatabase();
                        }
                    }
                });
            }
        });


    }


    private void saveprointodatabase() {



        HashMap<String, Object> objectHashMap = new HashMap<>();
        objectHashMap.put("ID", proudctrandomkey);
        objectHashMap.put("data", savedata);
        objectHashMap.put("time", savetime);
        objectHashMap.put("address", address1);
        objectHashMap.put("name", name1);
        objectHashMap.put("owner",mAuth.getCurrentUser().getUid());
        objectHashMap.put("phone", phone1);
        objectHashMap.put("image", donlowadimg);
        objectHashMap.put("city", subcategory1);
        objectHashMap.put("price", price.getText().toString());
        objectHashMap.put("description", des.getText().toString());
        objectHashMap.put("type", "Makeup artist");

        databaseReference.child(mAuth.getCurrentUser().getUid()).updateChildren(objectHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    loadingBar.dismiss();
                    Intent intent = new Intent(MakeUpProfActivity.this, HomeActivity.class);
                    startActivity(intent);


                    Toast.makeText(MakeUpProfActivity.this, " Profile Created", Toast.LENGTH_LONG).show();
                } else {
                    loadingBar.dismiss();
                    Toast.makeText(MakeUpProfActivity.this, " error : " + task.getException().toString(), Toast.LENGTH_LONG).show();

                }

            }
        });


    }
    private void saveprointodatabase1() {
        Calendar calendar = Calendar.getInstance();

        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(SimpleDateFormat.YEAR_ABBR_MONTH);
        savedata = dateFormat.format(calendar.getTime());

        java.text.SimpleDateFormat dateFormat1 = new java.text.SimpleDateFormat("HH:mm:ss a");
        savetime = dateFormat1.format(calendar.getTime());
        proudctrandomkey = savedata + savetime;


        HashMap<String, Object> objectHashMap = new HashMap<>();
        objectHashMap.put("id", proudctrandomkey);
        objectHashMap.put("data", savedata);
        objectHashMap.put("time", savetime);
        objectHashMap.put("address", address1);
        objectHashMap.put("name", name1);
        objectHashMap.put("owner",mAuth.getCurrentUser().getUid());
        objectHashMap.put("phone", phone1);

        objectHashMap.put("city", subcategory1);
        objectHashMap.put("price", price.getText().toString());
        objectHashMap.put("description", des.getText().toString());
        objectHashMap.put("type", "Makeup artist");

        databaseReference.child(mAuth.getCurrentUser().getUid()).updateChildren(objectHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    loadingBar.dismiss();
                    Intent intent = new Intent(MakeUpProfActivity.this, HomeActivity.class);
                    startActivity(intent);


                    Toast.makeText(MakeUpProfActivity.this, " Profile Updated", Toast.LENGTH_LONG).show();
                } else {
                    loadingBar.dismiss();
                    Toast.makeText(MakeUpProfActivity.this, " error : " + task.getException().toString(), Toast.LENGTH_LONG).show();

                }

            }
        });


    }

    private void userInfoDisplay(final ImageView profileImageView, final EditText SalonNmae, final EditText phone, final EditText addressEdit,  final EditText desc ,final EditText price,final Spinner spinner) {


        DatabaseReference UsersRef = FirebaseDatabase.getInstance().getReference().child("makeuppp").child(mAuth.getCurrentUser().getUid() );
        mAuth = FirebaseAuth.getInstance();
        UsersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.child("image").exists()) {
                        String image0 = dataSnapshot.child("image").getValue().toString();
                        String name0 = dataSnapshot.child("name").getValue().toString();
                        String address0 = dataSnapshot.child("address").getValue().toString();
                        String phone0 = dataSnapshot.child("phone").getValue().toString();
                        String price1 = dataSnapshot.child("price").getValue().toString();
                        String desc = dataSnapshot.child("description").getValue().toString();
                        Picasso.get().load(image0).into(profileImageView);
                        checker = "clicked";
                        SalonNmae.setText(name0);
                        phone.setText(phone0);
                        des.setText(desc);
                        price.setText(price1);
                        addressEdit.setText(address0);
                        spinner.setTag(subcategory1);

                        addnew.setText(R.string.update);
                        ch="cl";
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
