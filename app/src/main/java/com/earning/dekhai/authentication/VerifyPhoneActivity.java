package com.earning.dekhai.authentication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.earning.dekhai.MainActivity;
import com.earning.dekhai.R;
import com.earning.dekhai.screen.ProfileData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class VerifyPhoneActivity extends AppCompatActivity {

    private String verificationId;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private EditText editText;
    private String phonenumber;
    private String id ,category;
    private DocumentReference currentUserDb;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);

        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);
        editText = findViewById(R.id.editTextCode);
        phonenumber = getIntent().getStringExtra("phonenumber");
        sendVerificationCode(phonenumber);
        findViewById(R.id.buttonSignIn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try{
                    String code = editText.getText().toString().trim();
                    if (code.isEmpty() || code.length() < 6) {
                        editText.setError("Enter code...");
                        editText.requestFocus();
                        return;
                    }
                    verifyCode(code);
                } catch (Exception e) {
                    Toast.makeText(VerifyPhoneActivity.this, "Error", Toast.LENGTH_LONG).show();
                }

            }
        });

    }


    private void verifyCode(String code) {
        try {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
            signInWithCredential(credential);
        }
        catch (Exception e){
            Toast.makeText(this, "Error, try againg",Toast.LENGTH_SHORT).show();
        }
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
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

                                        Intent intent = new Intent(VerifyPhoneActivity.this, ProfileData.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        mAuth = FirebaseAuth.getInstance();
                                        userId = mAuth.getCurrentUser().getUid();
                                        currentUserDb = FirebaseFirestore.getInstance().collection("user").document(userId);


                                        Map userInfo = new HashMap();
                                        userInfo.put("name","" );
                                        userInfo.put("number","" );
                                        userInfo.put("city","" );
                                        userInfo.put("userid","");
                                        currentUserDb.set(userInfo).addOnSuccessListener(new OnSuccessListener() {
                                            @Override
                                            public void onSuccess(Object o) {
                                                Intent intent = new Intent(VerifyPhoneActivity.this, ProfileData.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                                finish();
                                                return;
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();

                                            }
                                        });


                                    }

                                }
                            });


                        }
                        else {
                            Toast.makeText(VerifyPhoneActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(VerifyPhoneActivity.this, PhoneNumber.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }

    private void sendVerificationCode(String number) {
        progressBar.setVisibility(View.VISIBLE);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                VerifyPhoneActivity.this,
                mCallBack
        );

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                editText.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(VerifyPhoneActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };


}