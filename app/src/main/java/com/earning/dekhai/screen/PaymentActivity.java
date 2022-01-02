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
    TextView  numberb, n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        title = getIntent().getExtras().getString("title");
        submit = findViewById(R.id.submit);
        number = findViewById(R.id.number);
        td = findViewById(R.id.transactionid);
        numberb = findViewById(R.id.numberb);
        n = findViewById(R.id.n);

        radioGroup = findViewById(R.id.radioGroup);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("saving your info....");
        getNumber();
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
    public void getNumber(){
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        currentUserDb = FirebaseFirestore.getInstance().collection("ads").document("number");
        currentUserDb.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error !=null){
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    return;
                }
                if (value.exists()) {
                    Map<String, Object> map = (Map<String, Object>) value.getData();
                    if (map.get("bkash") != null) {
                        String aboutForDisplay = map.get("bkash").toString();
                        numberb.setText(aboutForDisplay);
                        n.setText(aboutForDisplay);
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent homeIntent=new Intent(getApplicationContext(), MainActivity.class);
        startActivity(homeIntent);
        finish();
    }
}