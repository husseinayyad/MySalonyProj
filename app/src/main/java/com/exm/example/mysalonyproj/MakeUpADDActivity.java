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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.exm.example.mysalonyproj.HomeActivity;
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

import java.util.Calendar;
import java.util.HashMap;

public class MakeUpADDActivity extends AppCompatActivity {
    private Button addnew, btnmaps;
    int d;
    private EditText name, des, price, address;
    private static final String TAG = "MainActivity";
    String type, city, owner, address0 ,phone , name0;
    private static final int ERROR_DIALOG_REQUEST = 9001;
    static int PReqCode = 1;
    static int REQUESCODE = 1;
    private FirebaseAuth mAuth;

    String name1, des1, price1, address1, subcategory1, savedata, savetime, proudctrandomkey, donlowadimg, Citys;
    private ImageView inputimage;
    private static final int galaryn = 1;
    private Uri imageuri;
    FirebaseAuth firebaseAuth;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private ProgressDialog loadingBar;

    Spinner spinner, spinner1;
    TextView titel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_up_add);
        addnew = findViewById(R.id.addpro1);
        des = findViewById(R.id.prodesc);
        mAuth = FirebaseAuth.getInstance();
        price = findViewById(R.id.proprice);
        spinner1 = findViewById(R.id.subcatspiner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getBaseContext(),
                R.array.women_array, android.R.layout.simple_spinner_item);

        spinner1.setAdapter(adapter2);


        inputimage = findViewById(R.id.selectpro);

        storageReference = FirebaseStorage.getInstance().getReference().child(" imagesService");


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Service").child("makeuppp");
        loadingBar = new ProgressDialog(this);


        addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validaadata();
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

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void validaadata() {
        des1 = des.getText().toString();
        price1 = price.getText().toString();


        if (spinner1.getSelectedItem().toString().equals("قص شعر")){
            subcategory1="Haircut";
        }

        else if(spinner1.getSelectedItem().toString().equals("ازالة الشعر")) {
            subcategory1="Waxing";
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

        else if(spinner1.getSelectedItem().toString().equals("تنظيف بشرة")) {
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
        else if(spinner1.getSelectedItem().toString().equals("الرموش")) {
            subcategory1="Eyelash";
        }
        else if(spinner1.getSelectedItem().toString().equals("الأظافر")) {
            subcategory1="Nails";
        }
        else if(spinner1.getSelectedItem().toString().equals("مكياج دائم")) {
            subcategory1="Permanent MakeUp";
        }
        else if(spinner1.getSelectedItem().toString().equals("مكياج و شعر العرسان")) {
            subcategory1="Bridal Hair &amp; Makeup";
        }

        else {
            subcategory1=spinner1.getSelectedItem().toString();
        }

        if (TextUtils.isEmpty(des1) & TextUtils.isEmpty(price1)){
            Toast.makeText(this, R.string.PleaseVerifyAllField, Toast.LENGTH_LONG).show();

        }
        else if (TextUtils.isEmpty(des1)) {
            Toast.makeText(this, R.string.salondescriptionisempty, Toast.LENGTH_LONG).show();

        } else if (price1.contains("-")) {
            Toast.makeText(this, R.string.pricehaveminusnumber, Toast.LENGTH_LONG).show();

        } else if (TextUtils.isEmpty(price1)) {
            Toast.makeText(this, R.string.Productpriceisempty, Toast.LENGTH_LONG).show();

        }










        if (TextUtils.isEmpty(des1)) {
            Toast.makeText(this, R.string.salondescriptionisempty, Toast.LENGTH_LONG).show();

        } else if (price1.contains("-")) {
            Toast.makeText(this, R.string.pricehaveminusnumber, Toast.LENGTH_LONG).show();

        } else if (TextUtils.isEmpty(price1)) {
            Toast.makeText(this, R.string.Productpriceisempty, Toast.LENGTH_LONG).show();

        }


        else {
            storeproductinfo();
        }
    }

    private void storeproductinfo() {

        loadingBar.setTitle(R.string.addingnewservice);
        loadingBar.setMessage(R.string.Pleasewaitwhileweareaddingnewservice +" ");
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
                Toast.makeText(getApplication(), " error: " + message, Toast.LENGTH_LONG).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getApplication(), "  image uploaded successfully ", Toast.LENGTH_LONG).show();
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

                            Toast.makeText(getApplication(), "  image save to database", Toast.LENGTH_LONG).show();
                            saveprointodatabase();
                        }
                    }
                });
            }
        });


    }


    private void saveprointodatabase() {




        DatabaseReference UsersRef = FirebaseDatabase.getInstance().getReference().child("makeuppp").child(mAuth.getCurrentUser().getUid());
        final DatabaseReference UsersRef1 = FirebaseDatabase.getInstance().getReference().child("myservice").child("makeuppp").child(mAuth.getCurrentUser().getUid());

        UsersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                owner = dataSnapshot.child("owner").getValue().toString();
                city = dataSnapshot.child("city").getValue().toString();
                address0 = dataSnapshot.child("address").getValue().toString();
                type = dataSnapshot.child("type").getValue().toString();
                phone=dataSnapshot.child("phone").getValue().toString();
                name0=dataSnapshot.child("name").getValue().toString();
                final HashMap<String, Object> objectHashMap = new HashMap<>();
                objectHashMap.put("id", proudctrandomkey);
                objectHashMap.put("data", savedata);
                objectHashMap.put("time", savetime);
                objectHashMap.put("description", des1);
                objectHashMap.put("type", type);
                objectHashMap.put("phone",phone);
                objectHashMap.put("name",name0);
                objectHashMap.put("address", address0);
                objectHashMap.put("city", city);
                objectHashMap.put("price", price1);
                objectHashMap.put("image", donlowadimg);

                objectHashMap.put("owner", owner);
                objectHashMap.put("subcategory", subcategory1);
                databaseReference.child(type).child(subcategory1).child(proudctrandomkey).updateChildren(objectHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            UsersRef1.child(proudctrandomkey).updateChildren(objectHashMap);
                            loadingBar.dismiss();
                            Intent intent = new Intent(getApplication(), HomeActivity.class);
                            startActivity(intent);


                            Toast.makeText(getApplication(), R.string.Serviceisadded, Toast.LENGTH_LONG).show();
                        } else {
                            loadingBar.dismiss();
                            Toast.makeText(getApplication(), "error  : " + task.getException().toString(), Toast.LENGTH_LONG).show();

                        }

                    }
                });


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
    }

