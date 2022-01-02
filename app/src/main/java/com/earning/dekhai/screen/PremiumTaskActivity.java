package com.earning.dekhai.screen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.earning.dekhai.R;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class PremiumTaskActivity extends AppCompatActivity {
    Button adsshow;
    private final String TAG = FreeTaskActivity.class.getSimpleName();
    private InterstitialAd interstitialAd;
    private FirebaseAuth mAuth;
    String userId;
    private DocumentReference currentUserDb;
    private TextView balance, noTask, date, fulldate;
    String donetask,ndate;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_task);
        donetask = getIntent().getExtras().getString("taskDone");
        ndate = getIntent().getExtras().getString("date");
        AudienceNetworkAds.initialize(this);
        balance = findViewById(R.id.balance);
        noTask = findViewById(R.id.notask);
        date = findViewById(R.id.date);
        fulldate = findViewById(R.id.fulldate);

        adsshow =  findViewById(R.id.adsshow);
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        currentUserDb = FirebaseFirestore.getInstance().collection("user").document(userId);
        getIncome();
        getDate();
        /*       checkCanShowAds();*/


        adsshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                interstitialAd = new InterstitialAd(getApplicationContext(), getApplicationContext().getString(R.string.fb_insterstial_ads));
                // Create listeners for the Interstitial Ad
                InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
                    @Override
                    public void onInterstitialDisplayed(Ad ad) {
                        // Interstitial ad displayed callback
                        Log.e(TAG, "Interstitial ad displayed.");

                    }

                    @Override
                    public void onInterstitialDismissed(Ad ad) {
                        // Interstitial dismissed callback
                        Log.e(TAG, "Interstitial ad dismissed.");
                        mAuth = FirebaseAuth.getInstance();
                        userId = mAuth.getCurrentUser().getUid();
                        currentUserDb = FirebaseFirestore.getInstance().collection("user").document(userId);
                        float incomeTk, nowIncome;
                        incomeTk = Float.parseFloat(balance.getText().toString());
                        nowIncome = (float) (incomeTk + 0.50);
                        float m;
                        m = Float.parseFloat(df.format(nowIncome));
                        int a ;
                        a = Integer.parseInt(donetask) + 1;
                        Map userInfo = new HashMap();
                        userInfo.put("income",String.valueOf(m) );
                        userInfo.put(ndate,String.valueOf(a) );
                        currentUserDb.update(userInfo).addOnSuccessListener(new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });


                    }

                    @Override
                    public void onError(Ad ad, AdError adError) {
                        // Ad error callback
                        Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
                        Intent homeIntent=new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(homeIntent);
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        // Interstitial ad is loaded and ready to be displayed
                        Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                        // Show the ad
                        interstitialAd.show();




                    }

                    @Override
                    public void onAdClicked(Ad ad) {
                        // Ad clicked callback
                        Log.d(TAG, "Interstitial ad clicked!");
                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {
                        // Ad impression logged callback
                        Log.d(TAG, "Interstitial ad impression logged!");




                    }
                };

                // For auto play video ads, it's recommended to load the ad
                // at least 30 seconds before it is shown
                interstitialAd.loadAd(
                        interstitialAd.buildLoadAdConfig()
                                .withAdListener(interstitialAdListener)
                                .build());






            }
        });
    }



    public void getIncome(){
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
                        balance.setText(aboutForDisplay);
                    }
                }
            }
        });
    }



    public void getDate(){
        currentUserDb = FirebaseFirestore.getInstance().collection("ads").document("todayesdate");
        currentUserDb.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error !=null){
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    return;
                }
                if (value.exists()) {
                    Map<String, Object> map = (Map<String, Object>) value.getData();
                    if (map.get("date") != null) {
                        String aboutForDisplay = map.get("date").toString();
                        date.setText(aboutForDisplay);
                        /*saveTodaysDate(aboutForDisplay);*/
                    }
                    if (map.get("fulldate") != null) {
                        String aboutForDisplay = map.get("fulldate").toString();
                        fulldate.setText(aboutForDisplay);
                    }


                }
            }
        });
    }

    public void saveTodaysDate(String datefronadmin){
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        currentUserDb = FirebaseFirestore.getInstance().collection("user").document(userId);

        Map userInfo = new HashMap();
        userInfo.put("temptodaydate",datefronadmin );
        currentUserDb.update(userInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Intent homeIntent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(homeIntent);
                finish();
               /* Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();*/
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
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