package com.earning.dekhai.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.earning.dekhai.R;
import com.earning.dekhai.authentication.PhoneNumber;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
    public void Edit(View view) {
        Intent homeIntent=new Intent(Profile.this, ProfileData.class);
        startActivity(homeIntent);
    }
}