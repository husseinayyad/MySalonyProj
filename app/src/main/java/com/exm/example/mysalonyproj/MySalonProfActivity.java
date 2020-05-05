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
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.HashMap;

public class MySalonProfActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton r1, r2 ,r3;
    private String checker = "" , df="";
    String ch = "";
    String rc= "";
    static int PReqCode = 1 ;
    static int REQUESCODE = 1 ;
    private FirebaseAuth mAuth;
    String xc ;
    private String getcatgory, category11, des1, type1, owner, address1, phone1, name1,
            subcategory1, price1, savedata, savetime, proudctrandomkey, donlowadimg;
    private Button addnew;
    private EditText address, des, price, name, phone, subcategory, proowner;
    private ImageView inputimage;
    private static final int galaryn = 1;
    private Uri imageuri;
    private StorageReference storageReference;
    private DatabaseReference databaseReference ,databaseReference1;
    private ProgressDialog loadingBar;
     String radiotext , radio;
    Spinner spinner, spinner1;
    TextView category0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_salon_prof);
        addnew = findViewById(R.id.addpro);
        // category=findViewById(R.id.procat);
        mAuth = FirebaseAuth.getInstance();
        radioGroup = findViewById(R.id.protype);
        category0 = findViewById(R.id.tv30);
        r1 = findViewById(R.id.rent);
        r2 = findViewById(R.id.sale);

        name = findViewById(R.id.proprice);
        address = findViewById(R.id.prodesc);
        phone = findViewById(R.id.salonphone);
        spinner1 = (Spinner) findViewById(R.id.subcatspiner);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.city_array, R.layout.it1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter1);


        inputimage = findViewById(R.id.selectpro);
        storageReference = FirebaseStorage.getInstance().getReference().child(" images");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Salonat");
        databaseReference1= FirebaseDatabase.getInstance().getReference().child("SalonatP");
        userInfoDisplay(inputimage, name, phone, address,spinner1);
        loadingBar = new ProgressDialog(this);
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
            Toast.makeText(this, "Salon Address is Empty", Toast.LENGTH_LONG).show();

        } else if (TextUtils.isEmpty(phone1)) {
            Toast.makeText(this, "Salon Phone is Empty", Toast.LENGTH_LONG).show();

        } else if (phone1.contains("-")) {
            Toast.makeText(this, "Salon Phone  have minus number", Toast.LENGTH_LONG).show();

        } else if (TextUtils.isEmpty(name1)) {
            Toast.makeText(this, "Salon Name is Empty", Toast.LENGTH_LONG).show();

        } else if (TextUtils.isEmpty(subcategory1)) {
            Toast.makeText(this, "Salon City is Empty", Toast.LENGTH_LONG).show();

        } else if (checker != "clicked") {
            Toast.makeText(this, "Please Upload Image", Toast.LENGTH_LONG).show();
        } else if (!r1.isChecked() && !r2.isChecked()&& !r3.isChecked()) {
            Toast.makeText(this, "Please select Are Your Salon For Men or For Women  or makeuppp artist", Toast.LENGTH_LONG).show();

        } else {
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
            Toast.makeText(this, "Salon Address is Empty", Toast.LENGTH_LONG).show();

        } else if (TextUtils.isEmpty(phone1)) {
            Toast.makeText(this, "Salon Phone is Empty", Toast.LENGTH_LONG).show();

        } else if (phone1.contains("-")) {
            Toast.makeText(this, "Salon Phone  have minus number", Toast.LENGTH_LONG).show();

        } else if (TextUtils.isEmpty(name1)) {
            Toast.makeText(this, "Salon Name is Empty", Toast.LENGTH_LONG).show();

        } else if (TextUtils.isEmpty(subcategory1)) {
            Toast.makeText(this, "Salon City is Empty", Toast.LENGTH_LONG).show();

        } else if (checker != "clicked") {
            Toast.makeText(this, "Please Upload Image", Toast.LENGTH_LONG).show();
        } else if (!r1.isChecked() && !r2.isChecked()&& !r3.isChecked()) {
            Toast.makeText(this, "Please select Are Your Salon For Men or For Women  or makeuppp artist ", Toast.LENGTH_LONG).show();

        } else {
       saveprointodatabase1();
        }
    }

    private void storeproductinfo() {
        loadingBar.setTitle("Create Your Salon Profile");
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
                Toast.makeText(MySalonProfActivity.this, " Error : " + message, Toast.LENGTH_LONG).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(MySalonProfActivity.this, "  image uploaded successfully ", Toast.LENGTH_LONG).show();
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

                            Toast.makeText(MySalonProfActivity.this, "  image save to database", Toast.LENGTH_LONG).show();
                            saveprointodatabase();
                        }
                    }
                });
            }
        });


    }


    private void saveprointodatabase() {

        if (ch=="cl"){
            databaseReference1.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    radiotext =dataSnapshot.child("type").getValue().toString();

                    final HashMap<String, Object> objectHashMap = new HashMap<>();
                    objectHashMap.put("ID", proudctrandomkey);
                    objectHashMap.put("data", savedata);
                    objectHashMap.put("time", savetime);
                    objectHashMap.put("address", address1);
                    objectHashMap.put("name", name1);
                    objectHashMap.put("owner",mAuth.getCurrentUser().getUid());
                    objectHashMap.put("phone", phone1);
                    objectHashMap.put("image", donlowadimg);
                    objectHashMap.put("city", subcategory1);

                    objectHashMap.put("type", radiotext);

                    databaseReference.child(radiotext).child(mAuth.getCurrentUser().getUid()).updateChildren(objectHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                loadingBar.dismiss();
                                databaseReference1.child(mAuth.getCurrentUser().getUid()).updateChildren(objectHashMap);
                                Intent intent = new Intent(MySalonProfActivity.this, HomeActivity.class);
                                startActivity(intent);


                                Toast.makeText(MySalonProfActivity.this, " Profile Created", Toast.LENGTH_LONG).show();
                            } else {
                                loadingBar.dismiss();
                                Toast.makeText(MySalonProfActivity.this, " error : " + task.getException().toString(), Toast.LENGTH_LONG).show();

                            }

                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        else {

            if (r1.isChecked()) {
                if (r1.getText().toString().equals("صالون للنساء")) {
                    rc = "rch1";
                    radiotext = "Salon For Women";
                } else {
                    rc = "rch1";
                    radiotext = "Salon For Women";
                }

            } else if (r2.isChecked()) {

                if (r2.getText().toString().equals("صالون للرجال")) {
                    rc = "rch2";
                    radiotext = "Salon For Men";
                } else {
                    rc = "rch2";
                    radiotext = "Salon For Men";
                }
            }


            final HashMap<String, Object> objectHashMap = new HashMap<>();
            objectHashMap.put("ID", proudctrandomkey);
            objectHashMap.put("data", savedata);
            objectHashMap.put("time", savetime);
            objectHashMap.put("address", address1);
            objectHashMap.put("name", name1);
            objectHashMap.put("owner", mAuth.getCurrentUser().getUid());
            objectHashMap.put("phone", phone1);
            objectHashMap.put("image", donlowadimg);
            objectHashMap.put("city", subcategory1);

            objectHashMap.put("type", radiotext);

            databaseReference.child(radiotext).child(mAuth.getCurrentUser().getUid()).updateChildren(objectHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        loadingBar.dismiss();
                        databaseReference1.child(mAuth.getCurrentUser().getUid()).updateChildren(objectHashMap);
                        Intent intent = new Intent(MySalonProfActivity.this, HomeActivity.class);
                        startActivity(intent);


                        Toast.makeText(MySalonProfActivity.this, " Profile Created", Toast.LENGTH_LONG).show();
                    } else {
                        loadingBar.dismiss();
                        Toast.makeText(MySalonProfActivity.this, " error : " + task.getException().toString(), Toast.LENGTH_LONG).show();

                    }

                }
            });

        }
    }
    private void saveprointodatabase1() {

        Calendar calendar = Calendar.getInstance();

        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(SimpleDateFormat.YEAR_ABBR_MONTH);
        savedata = dateFormat.format(calendar.getTime());

        java.text.SimpleDateFormat dateFormat1 = new java.text.SimpleDateFormat("HH:mm:ss a");
        savetime = dateFormat1.format(calendar.getTime());
        proudctrandomkey = savedata + savetime;

        if (r1.isChecked()) {
            if (r1.getText().toString().equals("صالون للنساء")) {

                radiotext = "Salon For Women";
            }
            else {
                radiotext = "Salon For Women";
            }

        }

        else if (r2.isChecked()) {

            if (r2.getText().toString().equals("صالون للرجال")) {

                radiotext = "Salon For Men";
            }
            else {
                radiotext = "Salon For Men";
            }
        }
        databaseReference1.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String ty =dataSnapshot.child("type").getValue().toString();

                final HashMap<String, Object> objectHashMap = new HashMap<>();
                objectHashMap.put("id", proudctrandomkey);
                objectHashMap.put("data", savedata);
                objectHashMap.put("time", savetime);
                objectHashMap.put("address", address1);
                objectHashMap.put("name", name1);
                objectHashMap.put("owner",mAuth.getCurrentUser().getUid());
                objectHashMap.put("phone", phone1);

                objectHashMap.put("city", subcategory1);

                objectHashMap.put("type",ty);

                databaseReference.child(ty).child(mAuth.getCurrentUser().getUid()).updateChildren(objectHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            loadingBar.dismiss();
                            databaseReference1.child(mAuth.getCurrentUser().getUid()).updateChildren(objectHashMap);
                            Intent intent = new Intent(MySalonProfActivity.this, HomeActivity.class);
                            startActivity(intent);


                            Toast.makeText(MySalonProfActivity.this, " Profile Updated", Toast.LENGTH_LONG).show();
                        } else {
                            loadingBar.dismiss();
                            Toast.makeText(MySalonProfActivity.this, " error : " + task.getException().toString(), Toast.LENGTH_LONG).show();

                        }

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void userInfoDisplay(final ImageView profileImageView, final EditText SalonNmae, final EditText phone, final EditText addressEdit, final Spinner spinner) {

        DatabaseReference UsersRef = FirebaseDatabase.getInstance().getReference().child("Salonat").child("Salon For Women").child( mAuth.getCurrentUser().getUid());
        DatabaseReference UsersRef1 = FirebaseDatabase.getInstance().getReference().child("Salonat").child("Salon For Men").child( mAuth.getCurrentUser().getUid());
        databaseReference1.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if (dataSnapshot.exists()) {
            if (dataSnapshot.child("image").exists()) {
                String image0 = dataSnapshot.child("image").getValue().toString();
                String name0 = dataSnapshot.child("name").getValue().toString();
                String address0 = dataSnapshot.child("address").getValue().toString();
                String phone0 = dataSnapshot.child("phone").getValue().toString();
                Picasso.get().load(image0).into(profileImageView);
                checker = "clicked";
                SalonNmae.setText(name0);
                phone.setText(phone0);
                addressEdit.setText(address0);
                spinner.setTag(subcategory1);
                if (rc=="rch1"){
                    r1.isEnabled();
                }
                else if (rc=="rch2"){
                    r2.isEnabled();
                }
                addnew.setText(R.string.update);
                r1.setVisibility(View.INVISIBLE);
                r2.setVisibility(View.INVISIBLE);
                radioGroup.setVisibility(View.INVISIBLE);
                ch="cl";

            }
        }
    }


    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});
    }

}
