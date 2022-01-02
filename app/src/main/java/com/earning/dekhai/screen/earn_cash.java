package com.earning.dekhai.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.earning.dekhai.R;

public class earn_cash extends AppCompatActivity {
    Button btnWatchVideo ,btnSurvey ,btnAppInstall ,btnPromotionalLinkShare,btnAffiliateMarketing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earn_cash_activity);
        btnWatchVideo = findViewById(R.id.btnWatchVideo);
        btnSurvey = findViewById(R.id.btnSurvey);
        btnAppInstall = findViewById(R.id.btnAppInstall);
        btnAffiliateMarketing = findViewById(R.id.btnAffiliateMarketing);
        btnPromotionalLinkShare = findViewById(R.id.btnPromotionalLinkShare);
        btnWatchVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(homeIntent);
            }
        });
        btnSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Available soon" , Toast.LENGTH_SHORT).show();

            }
        });
        btnAppInstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Available soon" , Toast.LENGTH_SHORT).show();

            }
        });
        btnAffiliateMarketing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Available soon" , Toast.LENGTH_SHORT).show();

            }
        });
        btnPromotionalLinkShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Available soon" , Toast.LENGTH_SHORT).show();
            }
        });
    }
}