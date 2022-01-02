package com.earning.dekhai.screen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.earning.dekhai.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class ProfileData extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DocumentReference currentUserDb;
    private ProgressDialog progressDialog;
    TextInputEditText name ,number ,city;
    private Button save;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.login_page_activity);

        setContentView(R.layout.activity_profile_data);

        number = findViewById(R.id.number);
        name = findViewById(R.id.name);
        city = findViewById(R.id.city);
        save = findViewById(R.id.save);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("saving your info....");
        getUserInfo();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                mAuth = FirebaseAuth.getInstance();
                userId = mAuth.getCurrentUser().getUid();
                currentUserDb = FirebaseFirestore.getInstance().collection("user").document(userId);
                String n = name.getText().toString();
                String num = number.getText().toString();
                String c = city.getText().toString();

                Map userInfo = new HashMap();
                userInfo.put("name",n );
                userInfo.put("number",num );
                userInfo.put("city",c );
                userInfo.put("userid",userId);
                currentUserDb.update(userInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progressDialog.dismiss();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    private void getUserInfo() {
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
                        name.setText(aboutForDisplay);
                    }
                    if (map.get("number") != null) {
                        String aboutForDisplay = map.get("number").toString();
                        number.setText(aboutForDisplay);
                    }
                    if (map.get("city") != null) {
                        String aboutForDisplay = map.get("city").toString();
                        city.setText(aboutForDisplay);
                    }
                }
            }
        });
    }
}