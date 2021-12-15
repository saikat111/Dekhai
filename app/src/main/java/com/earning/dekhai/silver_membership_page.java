package com.earning.dekhai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.earning.dekhai.screen.MembarShipActivity;
import com.earning.dekhai.screen.SplashScreen;

public class silver_membership_page extends AppCompatActivity {
    Button active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silver_membership_page);
        active =  findViewById(R.id.work);
        active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MembarShipActivity.class);
                startActivity(intent);
            }
        });
    }
}