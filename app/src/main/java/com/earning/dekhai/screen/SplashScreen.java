package com.earning.dekhai.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.earning.dekhai.MainActivity;
import com.earning.dekhai.R;
import com.earning.dekhai.authentication.PhoneNumber;
import com.earning.dekhai.authentication.VerifyPhoneActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {
    private static int  SPLASH_TIME=3000;
    private String userId;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        firebaseAuth = FirebaseAuth.getInstance();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (FirebaseAuth.getInstance().getCurrentUser()==null){
                    Intent homeIntent=new Intent(SplashScreen.this, PhoneNumber.class);
                    startActivity(homeIntent);
                    finish();
                }else{

                    Intent homeIntent=new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(homeIntent);
                    finish();
                }
            }

        },SPLASH_TIME);
    }
    @Override
    public void onBackPressed(){
        finish();
    }
}