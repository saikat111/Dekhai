package com.earning.dekhai.authentication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.earning.dekhai.screen.HomePageActivity;
import com.earning.dekhai.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.squareup.picasso.Picasso;

import java.util.Map;

public class LogInPageActivity extends AppCompatActivity {
    TextView sigup, login;
     Button forgot;
    TextInputEditText email, password;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    ImageView image7;
    private DocumentReference currentUserDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page_activity);
        sigup = findViewById(R.id.singup);
        email = findViewById(R.id.email);
        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        image7 = findViewById(R.id.image7);
        forgot = findViewById(R.id.btnForgetPassword);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Verifying data....");
        getImage();
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Available soon" , Toast.LENGTH_SHORT).show();
            }
        });
        sigup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent=new Intent(getApplicationContext(), RegistrationPage.class);
                startActivity(homeIntent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                String tempEmail = email.getText().toString();
                String tempPassword = password.getText().toString();
               mAuth.signInWithEmailAndPassword(tempEmail,tempPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                   @Override
                   public void onSuccess(AuthResult authResult) {
                       progressDialog.dismiss();
                       Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
                       startActivity(intent);
                       finish();
                       overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);

                   }
               }).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                       progressDialog.dismiss();
                   }
               });
            }
        });
    }
    private void getImage() {
        mAuth = FirebaseAuth.getInstance();
        currentUserDb = FirebaseFirestore.getInstance().collection("image").document("display");
        currentUserDb.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error !=null){
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    return;
                }
                if (value.exists()) {
                    Map<String, Object> map = (Map<String, Object>) value.getData();
                    if (map.get("image") != null) {
                        String aboutForDisplay = map.get("image").toString();
                        Picasso.get().load(aboutForDisplay).into(image7);
                    }
                }
            }
        });
    }
}