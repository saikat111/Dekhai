package com.earning.dekhai.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.earning.dekhai.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationPage extends AppCompatActivity {
    TextInputEditText email, password,name,address,zip,phone;
    Button submit;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        address = findViewById(R.id.address);
        zip = findViewById(R.id.zip);
        phone = findViewById(R.id.phone);
        submit = findViewById(R.id.submit);
        mAuth =FirebaseAuth.getInstance();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString() == null){
                    Toast.makeText(getApplicationContext(), "Fill email box", Toast.LENGTH_LONG).show();
                    return;
                }
                if(phone.getText().toString().equals(null)){
                    Toast.makeText(getApplicationContext(), "Fill  phone box", Toast.LENGTH_LONG).show();
                    return;
                }
                if(address.getText().toString().equals(null)){
                    Toast.makeText(getApplicationContext(), "Fill address box", Toast.LENGTH_LONG).show();
                    return;
                }
                if(zip.getText().toString().equals(null)){
                    Toast.makeText(getApplicationContext(), "Fill zip box", Toast.LENGTH_LONG).show();
                    return;
                }
                if(password.getText().toString().equals(null)){
                    Toast.makeText(getApplicationContext(), "Fill  password box", Toast.LENGTH_LONG).show();
                    return;
                }
                if(name.getText().toString().equals(null)){
                    Toast.makeText(getApplicationContext(), "Fill  name box", Toast.LENGTH_LONG).show();
                    return;
                }
                String tempEmail = email.getText().toString();
                String tempPassword = password.getText().toString();
                mAuth.createUserWithEmailAndPassword(tempEmail,tempPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        String phoneNumber = "+" + 88 + phone.getText().toString();
                        Intent homeIntent=new Intent(getApplicationContext(), OtpActivity.class);
                        homeIntent.putExtra("email", email.getText().toString());
                        homeIntent.putExtra("phone", phoneNumber);
                        homeIntent.putExtra("address", address.getText().toString());
                        homeIntent.putExtra("zip", zip.getText().toString());
                        homeIntent.putExtra("name", name.getText().toString());
                        homeIntent.putExtra("password", password.getText().toString());
                        startActivity(homeIntent);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });


            }
        });

    }
}