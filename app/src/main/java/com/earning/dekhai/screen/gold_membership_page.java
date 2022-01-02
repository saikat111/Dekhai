package com.earning.dekhai.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.earning.dekhai.R;
import com.earning.dekhai.screen.MembarShipActivity;

public class gold_membership_page extends AppCompatActivity {
    Button active;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gold_membership_page);
        active =  findViewById(R.id.active);
        active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MembarShipActivity.class);
                startActivity(intent);
            }
        });
    }
}