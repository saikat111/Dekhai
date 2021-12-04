package com.earning.dekhai.screen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.earning.dekhai.MainActivity;
import com.earning.dekhai.R;
import com.earning.dekhai.authentication.LogInPageActivity;
import com.earning.dekhai.authentication.PhoneNumber;
import com.earning.dekhai.authentication.VerifyPhoneActivity;
import com.earning.dekhai.utils.Tools;
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

        Tools.setSystemBarLight(this);
        Tools.setSystemBarColor(this, R.color.welcome);
        MotionLayout motionLayout=findViewById(R.id.motionLayout);
        motionLayout.addTransitionListener(new MotionLayout.TransitionListener() {
            public void onTransitionStarted(@Nullable MotionLayout p0, int p1, int p2) {
            }

            public void onTransitionChange(@Nullable MotionLayout p0, int p1, int p2, float p3) {
            }

            public void onTransitionCompleted(@Nullable MotionLayout p0, int p1) {
         /*       Intent homeIntent=new Intent(SplashScreen.this, LogInPageActivity.class);
                startActivity(homeIntent);*/
                if (FirebaseAuth.getInstance().getCurrentUser()==null){
                Intent homeIntent=new Intent(getApplicationContext(), PhoneNumber.class);
                startActivity(homeIntent);
                finish();
            }else{

                Intent homeIntent=new Intent(SplashScreen.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }

            }

            public void onTransitionTrigger(@Nullable MotionLayout p0, int p1, boolean p2, float p3) {
            }
        });
    }
    @Override
    public void onBackPressed(){
        finish();
    }
}