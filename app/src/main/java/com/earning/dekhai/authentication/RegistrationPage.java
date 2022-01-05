package com.earning.dekhai.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.earning.dekhai.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistrationPage extends AppCompatActivity {
    TextInputEditText email, password,name,address,zip,phone;
    Button submit;
    FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private DocumentReference currentUserDb;
    String userId ;

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
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Verifying data....");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                if (TextUtils.isEmpty(email.getText().toString()))
                {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),
                            "Fill email box",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(phone.getText().toString()))
                {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),
                            "Fill  phone box",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(address.getText().toString()))
                {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),
                            "Fill address box",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(zip.getText().toString()))
                {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),
                            "Fill zip box",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password.getText().toString()))
                {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),
                            "Fill password box",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(name.getText().toString()))
                {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),
                            "Fill name box",
                            Toast.LENGTH_SHORT).show();
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
                        userId = mAuth.getCurrentUser().getUid();
                        currentUserDb = FirebaseFirestore.getInstance().collection("user").document(userId);
                        Map userInfo = new HashMap();
                        userInfo.put("name",name.getText().toString() );
                        userInfo.put("email",email.getText().toString() );
                        userInfo.put("password",password.getText().toString() );
                        userInfo.put("zip",zip.getText().toString());
                        userInfo.put("address",address.getText().toString());
                        userInfo.put("phone",phoneNumber);
                        currentUserDb.set(userInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                progressDialog.dismiss();
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
                                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }
                        });

                       /* progressDialog.dismiss();
                        String phoneNumber = "+" + 88 + phone.getText().toString();
                        Intent homeIntent=new Intent(getApplicationContext(), OtpActivity.class);
                        homeIntent.putExtra("email", email.getText().toString());
                        homeIntent.putExtra("phone", phoneNumber);
                        homeIntent.putExtra("address", address.getText().toString());
                        homeIntent.putExtra("zip", zip.getText().toString());
                        homeIntent.putExtra("name", name.getText().toString());
                        homeIntent.putExtra("password", password.getText().toString());
                        startActivity(homeIntent);
                        finish();*/

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                    }
                });


            }
        });

    }

}