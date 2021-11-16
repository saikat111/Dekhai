package com.earning.dekhai.screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.earning.dekhai.R;
import com.earning.dekhai.adapter.MemberShipAdapter;
import com.earning.dekhai.adapter.NoticeAdapter;
import com.earning.dekhai.model.MembershipModel;
import com.earning.dekhai.model.NoticeModel;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MembarShipActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MemberShipAdapter memberShipAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membar_ship);
        recyclerView =findViewById(R.id.recyclerview);
        FirebaseFirestore find = FirebaseFirestore.getInstance();
        CollectionReference collection = find.collection("membership");
        Query query = collection.orderBy("order");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirestoreRecyclerOptions<MembershipModel> options7 =
                new FirestoreRecyclerOptions.Builder<MembershipModel>()
                        .setQuery(query, MembershipModel.class)
                        .build();
        memberShipAdapter = new MemberShipAdapter(options7);
        recyclerView.setAdapter(memberShipAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        memberShipAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        memberShipAdapter.stopListening();
    }
}