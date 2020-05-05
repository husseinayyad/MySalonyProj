package com.exm.example.mysalonyproj;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import com.exm.example.mysalonyproj.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReqActivity extends AppCompatActivity implements View.OnClickListener  {

    ImageView ImgUserPhoto;
    static int PReqCode = 1 ;
    static int REQUESCODE = 1 ;
    TextView regUserPhoto ;
    private VideoView mVideoView;
    Uri pickedImgUri ;
    String ch ="fale";
    private CircleImageView profileImageView;
    private EditText userEmail,userPassword,userPAssword2,userName;
    private ProgressBar loadingProgress;
    private Button regBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req);
        mVideoView = (VideoView) findViewById(R.id.mVideoView);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.omarvideo);
        mVideoView.setVideoURI(uri);
        mVideoView.start();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });

        ImgUserPhoto= findViewById(R.id.imageView5);
        //ini views
        userEmail = findViewById(R.id.regMail);
        userPassword = findViewById(R.id.regPassword);
        userPAssword2 = findViewById(R.id.regPassword2);
        userName = findViewById(R.id.regName);
        loadingProgress = findViewById(R.id.regProgressBar);
        regBtn = findViewById(R.id.regBtn);
        loadingProgress.setVisibility(View.INVISIBLE);


        mAuth = FirebaseAuth.getInstance();


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                regBtn.setVisibility(View.INVISIBLE);
                loadingProgress.setVisibility(View.VISIBLE);
                final String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();
                final String password2 = userPAssword2.getText().toString();
                final String name = userName.getText().toString();

                if( email.isEmpty() & name.isEmpty() & password.isEmpty()  & password2.isEmpty() & ImgUserPhoto.getDrawable()==null ) {


                    // something goes wrong : all fields must be filled
                    // we need to display an error message

                    Toast.makeText(getApplicationContext(),R.string.PleaseVerifyAllField,Toast.LENGTH_LONG).show();
                regBtn.setVisibility(View.VISIBLE);
                loadingProgress.setVisibility(View.INVISIBLE);


            }
                else  if(ImgUserPhoto.getDrawable()==null ) {

                    Toast.makeText(getApplicationContext(),R.string.PleaseUploadImage,Toast.LENGTH_LONG).show();
                    regBtn.setVisibility(View.VISIBLE);
                    loadingProgress.setVisibility(View.INVISIBLE);
                }


                else  if(email.isEmpty()) {

                    Toast.makeText(getApplicationContext(),R.string.PleaseEnterYourEmail,Toast.LENGTH_LONG).show();
                    regBtn.setVisibility(View.VISIBLE);
                    loadingProgress.setVisibility(View.INVISIBLE);
                }
                else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(getApplicationContext(),R.string.PleaseEnterYourValidEmail,Toast.LENGTH_LONG).show();
                    regBtn.setVisibility(View.VISIBLE);
                    loadingProgress.setVisibility(View.INVISIBLE);
                }

                else  if(name.isEmpty()) {

                    Toast.makeText(getApplicationContext(),R.string.PleaseEnterYourName,Toast.LENGTH_LONG).show();
                    regBtn.setVisibility(View.VISIBLE);
                    loadingProgress.setVisibility(View.INVISIBLE);
                }
                else  if(password.isEmpty()) {

                    Toast.makeText(getApplicationContext(),R.string.PleaseEnterYourPassword,Toast.LENGTH_LONG).show();
                    regBtn.setVisibility(View.VISIBLE);
                    loadingProgress.setVisibility(View.INVISIBLE);

                }
                else  if(password2.isEmpty()) {

                    Toast.makeText(getApplicationContext(),R.string.PleaseEnterYourPassword,Toast.LENGTH_LONG).show();
                    regBtn.setVisibility(View.VISIBLE);
                    loadingProgress.setVisibility(View.INVISIBLE);
                }
                else  if(!password.equals(password2)) {

                    Toast.makeText(getApplicationContext(),R.string.PleaseYourPasswordNotMatch,Toast.LENGTH_LONG).show();
                    regBtn.setVisibility(View.VISIBLE);
                    loadingProgress.setVisibility(View.INVISIBLE);
                }
                    else {
                        // everything is ok and all fields are filled now we can start creating user account
                        // CreateUserAccount method will try to create the user if the email is valid

                        CreateUserAccount(email.trim(),name,password);
                    }



            }
        });

        regUserPhoto= findViewById(R.id.textView4);

        regUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= 22) {

                    checkAndRequestForPermission();


                }
                else
                {
                    openGallery();
                }





            }
        });


    }

    private void CreateUserAccount(String email, final String name, String password) {




            // this method create user account with specific email and password

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                // user account created successfully

                                Toast.makeText(getApplicationContext(),R.string.Accountcreated,Toast.LENGTH_LONG).show();

                                // after we created user account we need to update his profile picture and name
                                updateUserInfo(name, pickedImgUri, mAuth.getCurrentUser());


                            } else {

                                // account creation failed

                                Toast.makeText(getApplication(),R.string.accountcreationfailed + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                regBtn.setVisibility(View.VISIBLE);
                                loadingProgress.setVisibility(View.INVISIBLE);

                            }
                        }
                    });


        }






    // update user photo and name
    private void updateUserInfo(final String name, Uri pickedImgUri, final FirebaseUser currentUser) {

        // first we need to upload user photo to firebase storage and get url

        StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("users_photos");
        final StorageReference imageFilePath = mStorage.child(pickedImgUri.getLastPathSegment());


            imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    // image uploaded succesfully
                    // now we can get our image url

                    imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            // uri contain user image url


                            UserProfileChangeRequest profleUpdate = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .setPhotoUri(uri)
                                    .build();


                            currentUser.updateProfile(profleUpdate)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {
                                                // user info updated successfullyR.string.Accountcreated
                                              Toast.makeText(getApplication(),R.string.Accountcreated,Toast.LENGTH_LONG).show();
                                                updateUI();
                                            }

                                        }
                                    });

                        }
                    });


                }
            });
        }






    private void updateUI() {

        Intent homeActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(homeActivity);
        finish();


    }

    // simple method to show toast message
    private void showMessage(String message) {

        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

    }

    private void openGallery() {
        //TODO: open gallery intent and wait for user to pick an image !

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);

    }

    private void checkAndRequestForPermission() {


        if (ContextCompat.checkSelfPermission(ReqActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(ReqActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                Toast.makeText(ReqActivity.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();

            }

            else
            {
                ActivityCompat.requestPermissions(ReqActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqCode);
            }

        }
        else
            openGallery();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null ) {

            // the user has successfully picked an image
            // we need to save its reference to a Uri variable

            pickedImgUri = data.getData() ;

            ImgUserPhoto.setImageURI(pickedImgUri);
            Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.omarvideo);
            mVideoView.setVideoURI(uri);
            mVideoView.start();
            mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.setLooping(true);
                }
            });
        }


    }


    @Override
    public void onClick(View view) {

    }
    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        mVideoView = (VideoView) findViewById(R.id.mVideoView);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.omarvideo);
        mVideoView.setVideoURI(uri);
        mVideoView.start();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mVideoView = (VideoView) findViewById(R.id.mVideoView);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.omarvideo);
        mVideoView.setVideoURI(uri);
        mVideoView.start();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });
    }
}
