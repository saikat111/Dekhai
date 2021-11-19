package com.earning.dekhai.screen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.earning.dekhai.MainActivity;
import com.earning.dekhai.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class Wallet extends AppCompatActivity {
    String userId;
    private Button submit;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private DocumentReference currentUserDb;
    private EditText number;
    RadioGroup radioGroup;
    TextView tk;
    RadioButton typerradioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        submit = findViewById(R.id.withdraw);
        number = findViewById(R.id.number);
        tk = findViewById(R.id.tk);
        radioGroup = findViewById(R.id.radioGroup);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("saving your info....");
        getIncome();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                int selectedId = radioGroup.getCheckedRadioButtonId();
                typerradioButton = (RadioButton) findViewById(selectedId);
                if(selectedId==-1){
                    Toast.makeText(getApplicationContext(),"Nothing selected", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }
                String t = tk.getText().toString();

                if(Integer.parseInt(t) < 499){
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Not enough balance", Toast.LENGTH_SHORT).show();
                    return;
                }
                /*else{
                    Toast.makeText(getApplicationContext(),typerradioButton.getText(), Toast.LENGTH_SHORT).show();
                }*/
                mAuth = FirebaseAuth.getInstance();
                userId = mAuth.getCurrentUser().getUid();
                currentUserDb = FirebaseFirestore.getInstance().collection("withdraw_request").document(userId);
                String n = number.getText().toString();

                Map userInfo = new HashMap();
                userInfo.put("number",n );
                userInfo.put("userid",userId);
                userInfo.put("tk",t);
                userInfo.put("payment",typerradioButton.getText());
                currentUserDb.set(userInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        mAuth = FirebaseAuth.getInstance();
                        userId = mAuth.getCurrentUser().getUid();
                        currentUserDb = FirebaseFirestore.getInstance().collection("user").document(userId);
                        Map userInfo = new HashMap();
                        userInfo.put("income","0" );
                        currentUserDb.update(userInfo).addOnSuccessListener(new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {
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
    public void getIncome(){
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
                    if (map.get("income") != null) {
                        String aboutForDisplay = map.get("income").toString();
                        tk.setText(aboutForDisplay);
                    }
                }
            }
        });
    }
}