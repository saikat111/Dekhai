package com.earning.dekhai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class membership_page extends AppCompatActivity {
    Button freeAccount, silverAccount, goldAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.membership_page_activity);
        freeAccount = findViewById(R.id.freeAccount);
        silverAccount = findViewById(R.id.silverAccount);
        goldAccount = findViewById(R.id.goldAccount);
        freeAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent=new Intent(getApplicationContext(), free_membership_page.class);
                startActivity(homeIntent);
            }
        });
        silverAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent=new Intent(getApplicationContext(), silver_membership_page.class);
                startActivity(homeIntent);
            }
        });
        goldAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent=new Intent(getApplicationContext(), gold_membership_page.class);
                startActivity(homeIntent);
            }
        });
    }
}