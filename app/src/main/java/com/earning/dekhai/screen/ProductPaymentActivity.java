package com.earning.dekhai.screen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.earning.dekhai.R;
import com.earning.dekhai.authentication.RegistrationPage;
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

public class ProductPaymentActivity extends AppCompatActivity {
    String userId;
    TextView rsim1 , total;
    String name , price , id;
    private FirebaseAuth mAuth;
    private DocumentReference currentUserDb, currentUserDb2;
    EditText number , address,contact, tx;
    Button order;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_payment);
        name = getIntent().getExtras().getString("name");
        price = getIntent().getExtras().getString("price");
        id = getIntent().getExtras().getString("id");
        rsim1 = findViewById(R.id.rsim1);
        total = findViewById(R.id.total);
        order = findViewById(R.id.order);

        number = findViewById(R.id.number);
        address = findViewById(R.id.address);
        contact = findViewById(R.id.contact);
        tx = findViewById(R.id.tx);

        total.setText(price);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Verifying data....");
        getnumber();
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                String key ;
                currentUserDb2 = FirebaseFirestore.getInstance().collection("productOrder").document();
                key = currentUserDb2.getId();
                currentUserDb = FirebaseFirestore.getInstance().collection("productOrder").document(key);
                mAuth = FirebaseAuth.getInstance();
                userId = mAuth.getCurrentUser().getUid();
                String tempNUm = String.valueOf(number.getText());
                String tempc = String.valueOf(contact.getText());
                String tempaa = String.valueOf(address.getText());
                String tempTx = String.valueOf(tx.getText());
                Map userInfo = new HashMap();
                userInfo.put("price",price );
                userInfo.put("id",id );
                userInfo.put("payment_number",tempNUm );
                userInfo.put("contact",tempc);
                userInfo.put("address",tempaa);
                userInfo.put("transactionid",tempTx);
                userInfo.put("userid",userId);
                userInfo.put("name",name);
                currentUserDb.set(userInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        Intent homeIntent=new Intent(getApplicationContext(), HomePageActivity.class);
                        startActivity(homeIntent);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), e.toString() ,Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void getnumber() {
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
                        rsim1.setText(aboutForDisplay);
                    }
                }
            }
        });
    }
}