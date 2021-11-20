package com.earning.dekhai.screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.earning.dekhai.R;
import com.earning.dekhai.adapter.NoticeAdapter;
import com.earning.dekhai.model.NoticeModel;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class NoticeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    NoticeAdapter noticeAdapter;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        recyclerView =findViewById(R.id.recyclerview);
        adView = new AdView(this, getApplicationContext().getString(R.string.fb_banner_ads), AdSize.BANNER_HEIGHT_50);

// Find the Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);

// Add the ad view to your activity layout
        adContainer.addView(adView);

// Request an ad
        adView.loadAd();
        FirebaseFirestore find = FirebaseFirestore.getInstance();
        CollectionReference collection = find.collection("notice");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirestoreRecyclerOptions<NoticeModel> options7 =
                new FirestoreRecyclerOptions.Builder<NoticeModel>()
                        .setQuery(collection, NoticeModel.class)
                        .build();
        noticeAdapter = new NoticeAdapter(options7);
        recyclerView.setAdapter(noticeAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        noticeAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        noticeAdapter.stopListening();
    }
    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}