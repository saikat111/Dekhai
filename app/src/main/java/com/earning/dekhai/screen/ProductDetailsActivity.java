package com.earning.dekhai.screen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.earning.dekhai.R;
import com.earning.dekhai.adapter.ProductAdapter;
import com.earning.dekhai.model.ProductModel;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

import java.util.Map;

public class ProductDetailsActivity extends AppCompatActivity {
    String name , price , category ,id,  details;
    TextView pricef,detailsf, model;
    private FirebaseAuth mAuth;
    private DocumentReference currentUserDb;
    ImageView imageproduct;
    String userId;
    RecyclerView recyclerview;
    ProductAdapter productAdapter;
    Button addcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        name = getIntent().getExtras().getString("model");
        price = getIntent().getExtras().getString("price");
        category = getIntent().getExtras().getString("category");
        id = getIntent().getExtras().getString("id");
        details = getIntent().getExtras().getString("getDescription");
        pricef = findViewById(R.id.price);
        detailsf =  findViewById(R.id.details);
        addcard =  findViewById(R.id.addcard);
        imageproduct =  findViewById(R.id.imageproduct);
        recyclerview =  findViewById(R.id.recyclerview);
        model =  findViewById(R.id.model);
        pricef.setText(price);
        detailsf.setText(details);
        model.setText(name);
        addcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductPaymentActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("price", price);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        getImage();



        FirebaseFirestore find1 = FirebaseFirestore.getInstance();
        CollectionReference collection1 = find1.collection("product");
        Query query = collection1.whereEqualTo("category", category);
        recyclerview.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false));
        FirestoreRecyclerOptions<ProductModel> options71 =
                new FirestoreRecyclerOptions.Builder<ProductModel>()
                        .setQuery(query, ProductModel.class)
                        .build();
        productAdapter = new ProductAdapter(options71);
        recyclerview.setAdapter(productAdapter);




    }
   void getImage(){

       mAuth = FirebaseAuth.getInstance();
       userId = mAuth.getCurrentUser().getUid();
       currentUserDb = FirebaseFirestore.getInstance().collection("product").document(id);
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
                      Picasso.get().load(aboutForDisplay).into(imageproduct);
                   }
               }
           }
       });
    }
    @Override
    protected void onStart() {
        super.onStart();
        productAdapter.startListening();


    }

    @Override
    protected void onStop() {
        super.onStop();
        productAdapter.stopListening();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), ShoppingPage.class);
        startActivity(intent);
    }
}