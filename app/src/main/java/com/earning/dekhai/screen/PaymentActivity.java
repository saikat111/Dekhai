package com.earning.dekhai.screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.earning.dekhai.MainActivity;
import com.earning.dekhai.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PaymentActivity extends AppCompatActivity {
    String userId;
    String title;
    private Button submit;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private DocumentReference currentUserDb;
    private EditText  number, td;
    RadioGroup radioGroup;
    String type;
    RadioButton typerradioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        title = getIntent().getExtras().getString("title");
        submit = findViewById(R.id.submit);
        number = findViewById(R.id.number);
        td = findViewById(R.id.transactionid);
        radioGroup = findViewById(R.id.radioGroup);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("saving your info....");
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
                /*else{
                    Toast.makeText(getApplicationContext(),typerradioButton.getText(), Toast.LENGTH_SHORT).show();
                }*/
                mAuth = FirebaseAuth.getInstance();
                userId = mAuth.getCurrentUser().getUid();
                currentUserDb = FirebaseFirestore.getInstance().collection("membershiprequest").document(userId);
                String n = number.getText().toString();
                String t = td.getText().toString();

                Map userInfo = new HashMap();
                userInfo.put("number",n );
                userInfo.put("type",title );
                userInfo.put("transactionid",t);
                userInfo.put("userid",userId);
                userInfo.put("payment",typerradioButton.getText());
                currentUserDb.set(userInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
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
}