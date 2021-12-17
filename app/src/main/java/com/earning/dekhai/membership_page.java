package com.earning.dekhai;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Map;

public class membership_page extends AppCompatActivity {
    Button freeAccount, silverAccount, goldAccount;
    private FirebaseAuth mAuth;
    private DocumentReference currentUserDb;
    String userId;
    TextView username;
    Button btnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.membership_page_activity);
        freeAccount = findViewById(R.id.freeAccount);
        silverAccount = findViewById(R.id.silverAccount);
        goldAccount = findViewById(R.id.goldAccount);
        username = findViewById(R.id.username);
        btnProfile = findViewById(R.id.btnProfile);
        getName();
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent=new Intent(getApplicationContext(), profile_page.class);
                startActivity(homeIntent);
            }
        });
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

    private void getName() {
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        currentUserDb = FirebaseFirestore.getInstance().collection("user").document(userId);
        currentUserDb.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error !=null){
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    return;
                }
                if (value.exists()) {
                    Map<String, Object> map = (Map<String, Object>) value.getData();
                    if (map.get("name") != null) {
                        String aboutForDisplay = map.get("name").toString();
                        username.setText(aboutForDisplay);
                    }
                }
            }
        });

    }
}