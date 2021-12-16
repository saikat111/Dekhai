package com.earning.dekhai;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.earning.dekhai.authentication.PhoneNumber;
import com.earning.dekhai.screen.FreeTaskActivity;
import com.earning.dekhai.screen.GoldTaskActivity;
import com.earning.dekhai.screen.MembarShipActivity;
import com.earning.dekhai.screen.NoticeActivity;
import com.earning.dekhai.screen.PremiumTaskActivity;
import com.earning.dekhai.screen.ProfileData;
import com.earning.dekhai.screen.SplashScreen;
import com.earning.dekhai.screen.Wallet;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FirebaseAuth mAuth;
    private CardView freetask, premiumtask;
    String userId;
    private DocumentReference currentUserDb;
    private CollectionReference currentUserDb3;
    private TextView date,fulldate, balance,taskDone;
    private AdView adView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        freetask = findViewById(R.id.freetask);
        premiumtask = findViewById(R.id.premiumtask);
        date = findViewById(R.id.date);
        balance = findViewById(R.id.balance);
        taskDone = findViewById(R.id.taskdone);
        fulldate = findViewById(R.id.fulldate);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        AudienceNetworkAds.initialize(this);
/*        Date date = new Date();
        int today = Integer.parseInt(DateFormat.format("dd",   date).toString());
        Log.d("Calendar", String.valueOf(today));*/



        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        currentUserDb = FirebaseFirestore.getInstance().collection("user").document(userId);
        getDate();
        incomeCheck();

        adView = new AdView(this, getApplicationContext().getString(R.string.fb_banner_ads), AdSize.BANNER_HEIGHT_50);

// Find the Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);

// Add the ad view to your activity layout
        adContainer.addView(adView);

// Request an ad
        adView.loadAd();

//MaterialDrawer

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName(getString(R.string.app_name)).withIcon(getResources().getDrawable(R.drawable.logo))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();
        final PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Profile");
        final PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(1).withName("Membership");
        final PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(2).withName("Wallet");
        final PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(2).withName("Payment History");
        final PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(2).withName("Youtube");
        final PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(2).withName("Support Group");
        final PrimaryDrawerItem item7 = new PrimaryDrawerItem().withIdentifier(2).withName("Logout");
//create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        item1,
                        item2,
                        item3,
                        item4,
                        item5,
                        item6,
                        item7
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem == item1) {

                                Intent homeIntent=new Intent(MainActivity.this, ProfileData.class);
                                startActivity(homeIntent);

                            /*Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.fb)));
                            startActivity(browserIntent);*/
                        }
                        if (drawerItem == item2) {
                            Intent homeIntent=new Intent(getApplicationContext(), membership_page.class);
                            startActivity(homeIntent);
                        }
                        if (drawerItem == item3) {
                            Intent homeIntent=new Intent(MainActivity.this, wallet_to_withdraw.class);
                            startActivity(homeIntent);

                        /*    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.youtube)));
                            startActivity(browserIntent);*/

                        }
                        if (drawerItem == item4) {
                         /*   Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://shulovshopbd.blogspot.com/p/privacy-policy-shulov-shop-bd.html"));
                            startActivity(browserIntent);*/
                        }
                        if (drawerItem == item5) {
                            /*Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://shulovshopbd.blogspot.com/p/terms-conditions-shulov-shop-bd.html"));
                            startActivity(browserIntent);*/
                        }
                        if (drawerItem == item6) {

                        }
                        if (drawerItem == item7){
                            try {
                                mAuth.signOut();
                                Intent intent = new Intent(getApplicationContext(), SplashScreen.class);
                                startActivity(intent);
                                finish();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        return true;
                    }
                })
                .build();

        //MaterialDrawer


        freetask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(taskDone.getText().toString()) >=100){
                    Toast.makeText(getApplicationContext(),"No task available", Toast.LENGTH_LONG).show();

                    return;
                }
                currentUserDb.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error !=null){
                            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (value.exists()) {
                            Map<String, Object> map = (Map<String, Object>) value.getData();
                            if (map.get("membershipname") != null) {
                                String aboutForDisplay = map.get("membershipname").toString();
                               Toast.makeText(getApplicationContext(), "You are in " + aboutForDisplay , Toast.LENGTH_SHORT).show();
                               return;
                            }
                            else{
                                Intent homeIntent=new Intent(getApplicationContext(), FreeTaskActivity.class);
                                homeIntent.putExtra("taskDone", taskDone.getText().toString());
                                homeIntent.putExtra("date", date.getText().toString());
                                startActivity(homeIntent);
                            }

                        }
                    }
                });

            }
        });

        premiumtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(taskDone.getText().toString()) >=100){
                    Toast.makeText(getApplicationContext(),"No task available", Toast.LENGTH_LONG).show();

                    return;
                }
                currentUserDb.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error !=null){
                            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (value.exists()) {
                            Map<String, Object> map = (Map<String, Object>) value.getData();
                            if (map.get("membershipname") != null) {
                                String aboutForDisplay = map.get("membershipname").toString();
                                if(aboutForDisplay.equalsIgnoreCase("Silver Membership")){
                                    Intent homeIntent=new Intent(getApplicationContext(), PremiumTaskActivity.class);
                                    homeIntent.putExtra("taskDone", taskDone.getText().toString());
                                    homeIntent.putExtra("date", date.getText().toString());
                                    startActivity(homeIntent);
                                }
                                else if (aboutForDisplay.equalsIgnoreCase("Gold Membership")){
                                    Intent homeIntent=new Intent(getApplicationContext(), GoldTaskActivity.class);
                                    homeIntent.putExtra("taskDone", taskDone.getText().toString());
                                    homeIntent.putExtra("date", date.getText().toString());
                                    startActivity(homeIntent);
                                }


                                /*Intent homeIntent=new Intent(getApplicationContext(), FreeTaskActivity.class);
                                homeIntent.putExtra("membershipname", aboutForDisplay);
                                homeIntent.putExtra("stk", ".50");
                                homeIntent.putExtra("gtk", "1.00");
                                homeIntent.putExtra("taskDone", taskDone.getText().toString());
                                homeIntent.putExtra("date", date.getText().toString());
                                startActivity(homeIntent);*/

                            }
                            else{
                                Toast.makeText(getApplicationContext(), "You are in free membership" , Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
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
                        saveTodaysDate(aboutForDisplay);
                        creatAdsNode(aboutForDisplay);
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
        userInfo.put("todaydate",datefronadmin );
        currentUserDb.update(userInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

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
    public void incomeCheck(){
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
                    else {
                        mAuth = FirebaseAuth.getInstance();
                        userId = mAuth.getCurrentUser().getUid();
                        currentUserDb = FirebaseFirestore.getInstance().collection("user").document(userId);

                        Map userInfo = new HashMap();
                        userInfo.put("income","0.00" );
                        currentUserDb.update(userInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

               /* Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();*/
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                                Intent homeIntent=new Intent(getApplicationContext(), Wallet.class);
                                startActivity(homeIntent);
                                finish();
                            }
                        });

                    }


                }
            }
        });
    }
    public void creatAdsNode(String datev){
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
                    if (map.get(datev) != null) {
                        String aboutForDisplay = map.get(datev).toString();
                        taskDone.setText(aboutForDisplay);
                        currentUserDb = FirebaseFirestore.getInstance().collection("user").document(userId);
                        int a = Integer.parseInt(datev);
                        int b = a -1;
                        Map userInfo = new HashMap();
                        userInfo.put(String.valueOf(b),"0" );
                        currentUserDb.update(userInfo).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Intent homeIntent=new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(homeIntent);
                                finish();
                            }
                        });



                    }
                    else {
                        mAuth = FirebaseAuth.getInstance();
                        userId = mAuth.getCurrentUser().getUid();
                        currentUserDb = FirebaseFirestore.getInstance().collection("user").document(userId);

                        Map userInfo = new HashMap();
                        userInfo.put(datev,"0" );
                        currentUserDb.update(userInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                currentUserDb = FirebaseFirestore.getInstance().collection("user").document(userId);
                                int a = Integer.parseInt(datev);
                                int b = a -1;
                                Map userInfo = new HashMap();
                                userInfo.put(String.valueOf(b),"0" );
                                currentUserDb.update(userInfo).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Intent homeIntent=new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(homeIntent);
                                        finish();
                                    }
                                });



                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {


                            }
                        });

                    }


                }
            }
        });
    }


    public void wallet(View view) {
        Intent homeIntent=new Intent(MainActivity.this, wallet_to_withdraw.class);
        startActivity(homeIntent);

    }

    public void notice(View view) {
        Intent homeIntent=new Intent(MainActivity.this, NoticeActivity.class);
        startActivity(homeIntent);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    public void home(View view) {
        Intent homeIntent=new Intent(MainActivity.this, HomePageActivity.class);
        startActivity(homeIntent);
        finish();

    }
}