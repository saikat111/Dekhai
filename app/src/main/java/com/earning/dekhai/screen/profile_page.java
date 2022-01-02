package com.earning.dekhai.screen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.earning.dekhai.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Map;

public class profile_page extends AppCompatActivity {
    TextView tvName,tvPhone,tvEmail,tvPostCode ,username;
    private FirebaseAuth mAuth;
    private DocumentReference currentUserDb;
    Button btnEdit , btnSave;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page_activity);
        tvEmail = findViewById(R.id.tvEmail);
        tvName = findViewById(R.id.tvName);
        tvPhone = findViewById(R.id.tvPhone);
        tvPostCode = findViewById(R.id.tvPostCode);
        username = findViewById(R.id.username);
        btnEdit = findViewById(R.id.btnEdit);
        btnSave = findViewById(R.id.btnSave);
        getDetails();
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Available soon" , Toast.LENGTH_SHORT).show();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Available soon" , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDetails() {
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
                        tvName.setText(aboutForDisplay);
                        username.setText(aboutForDisplay);
                    }
                    if (map.get("phone") != null) {
                        String aboutForDisplay = map.get("phone").toString();
                        tvPhone.setText(aboutForDisplay);
                    }
                    if (map.get("email") != null) {
                        String aboutForDisplay = map.get("email").toString();
                        tvEmail.setText(aboutForDisplay);
                    }
                    if (map.get("zip") != null) {
                        String aboutForDisplay = map.get("zip").toString();
                        tvPostCode.setText(aboutForDisplay);
                    }
                }
            }
        });
    }
}