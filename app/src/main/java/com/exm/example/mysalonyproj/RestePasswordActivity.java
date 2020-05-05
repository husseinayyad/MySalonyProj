package com.exm.example.mysalonyproj;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RestePasswordActivity extends AppCompatActivity {
    private EditText mEditTextName, mEditTextPhoto, mEditTextEmail, mEditTextPassword, mEditTextEmailReset;
    private FirebaseAuth mAuth;
     Button rest;
    private VideoView mVideoView;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reste_password);

        mAuth = FirebaseAuth.getInstance();
        mEditTextEmailReset = findViewById(R.id.field_email_reset);
        rest=findViewById(R.id.send_password_reset_button);
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
        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateEmail(mEditTextEmailReset)) {
                    sendPasswordReset();
                }
               /* else  if (!validateEmail(mEditTextEmailReset)){
                    String email = mEditTextEmailReset.getText().toString();
                    if (TextUtils.isEmpty(email)) {
                        mEditTextEmailReset.setError("Required.");

                    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        mEditTextEmailReset.setError("Invalid.");

                    }
                }*/
            }
        });
    }
    private boolean validateEmail(EditText edt) {
        String email = edt.getText().toString();
        if (TextUtils.isEmpty(email)) {
            edt.setError("Required.");
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edt.setError("Invalid.");
            return false;
        } else {
            edt.setError(null);
            return true;
        }
    }
    private void sendPasswordReset() {

        mAuth.sendPasswordResetEmail(mEditTextEmailReset.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RestePasswordActivity.this, "Email Sent", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(RestePasswordActivity.this,MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(RestePasswordActivity.this, R.string.PleaseEnterYourValidEmail, Toast.LENGTH_SHORT).show();
                }

            }
        });
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
