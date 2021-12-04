package com.earning.dekhai.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.earning.dekhai.R;

public class LogInPageActivity extends AppCompatActivity {
    TextView sigup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);
        sigup = findViewById(R.id.singup);
        sigup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent=new Intent(getApplicationContext(), RegistrationPage.class);
                startActivity(homeIntent);
            }
        });
    }
}