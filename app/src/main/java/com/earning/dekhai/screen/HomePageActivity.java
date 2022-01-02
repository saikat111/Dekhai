package com.earning.dekhai.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Toast;

import com.earning.dekhai.R;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.earning.dekhai.databinding.ActivityHomePageBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.squareup.picasso.Picasso;

import java.util.Map;

public class HomePageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
//    initializing variable
    MeowBottomNavigation bottomNavigation;
    ConstraintLayout notice, earning ,support_chat ,learning , how_to_earn, essential_links, shopping;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomePageBinding binding;
    private FirebaseAuth mAuth;

    ImageView image7;
    private DocumentReference currentUserDb;

    private Menu menu;
    private MenuItem nav_home,nav_membership,nav_earn_cash,nav_wallet,nav_order_list,nav_payment_history,nav_youtube,nav_support_group,nav_logout;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();

//        assign variable
        bottomNavigation=findViewById(R.id.bottom_navigation);
        notice=findViewById(R.id.notice);
        earning=findViewById(R.id.earning);
        support_chat=findViewById(R.id.support_chat);
        learning=findViewById(R.id.learning);
        how_to_earn=findViewById(R.id.how_to_earn);
        essential_links=findViewById(R.id.essential_links);
        shopping=findViewById(R.id.shopping);
        image7 = findViewById(R.id.image7);

        getImage();

        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent=new Intent(getApplicationContext(), ShoppingPage.class);
                startActivity(homeIntent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);
            }
        });
        how_to_earn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent=new Intent(getApplicationContext(), membership_page.class);
                startActivity(homeIntent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);
            }
        });
        essential_links.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Available soon" , Toast.LENGTH_SHORT).show();
            }
        });
        learning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent=new Intent(getApplicationContext(), LearningPageHome.class);
                startActivity(homeIntent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);

            }
        });
        support_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent=new Intent(getApplicationContext(), contact_us.class);
                startActivity(homeIntent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);


            }
        });
        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent=new Intent(getApplicationContext(), NoticeActivity.class);
                startActivity(homeIntent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);


            }
        });
        earning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent=new Intent(getApplicationContext(), earn_cash.class);
                startActivity(homeIntent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);


            }
        });
//        initialize side navigation
        NavigationView navigationView1 = findViewById(R.id.nav_view);
        menu = navigationView1.getMenu();
        nav_home = menu.findItem(R.id.nav_home);
        nav_membership = menu.findItem(R.id.nav_membership);
        nav_earn_cash = menu.findItem(R.id.nav_earn_cash);
        nav_wallet = menu.findItem(R.id.nav_wallet);
        nav_order_list = menu.findItem(R.id.nav_order_list);
        nav_payment_history = menu.findItem(R.id.nav_payment_history);
        nav_youtube = menu.findItem(R.id.nav_youtube);
        nav_support_group = menu.findItem(R.id.nav_support_group);
        nav_logout = menu.findItem(R.id.nav_logout);




//        add menu item
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_notifications));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_info));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
//                initialize fragment
                Fragment fragment=null;
                switch (item.getId()){
                    case 1:
                        //                        when id is 1
                        fragment= new NotificationFragment();
                        break;
                    case 2:
                        //                        when id is 2
                        fragment= new HomeFragment();
                        break;
                    case 3:
                        //                        when id is 3
                        fragment= new InfoFragment();
                        break;
                }
//                load fragment
                loadFragment(fragment);
            }
        });

//        set notification count
        bottomNavigation.setCount(1, String.valueOf(10));
//        set fragment initially selected
        bottomNavigation.show(2,true);
         bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
             @Override
             public void onClickItem(MeowBottomNavigation.Model item) {
//                 display toast
                /* Toast.makeText(getApplicationContext(), "You clicked"+item.getId(), Toast.LENGTH_SHORT).show();*/
                 Toast.makeText(getApplicationContext(), "Available soon" , Toast.LENGTH_SHORT).show();
             }
         });


         bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
             @Override
             public void onReselectItem(MeowBottomNavigation.Model item) {
                 Toast.makeText(getApplicationContext(), "You re-clicked"+item.getId(), Toast.LENGTH_SHORT).show();
             }
         });


        setSupportActionBar(binding.appBarHomePage.toolbar);
//        binding.appBarHomePage.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        DrawerLayout drawer = binding.drawerLayout;
//        NavigationView navigationView = binding.navView;
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_membership, R.id.nav_earn_cash)
//                .setOpenableLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_page);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);


        drawerLayout = findViewById(R.id.drawer_layout);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_membership, R.id.nav_earn_cash)
                .setOpenableLayout(drawerLayout)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_page);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView1, navController);

        if (navigationView1 != null) {
            navigationView1.setNavigationItemSelectedListener(this);
        }




    }





    private void loadFragment(Fragment fragment) {
//        replace fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.nav_membership) {

            Intent intentMembership = new Intent(this, membership_page.class);
            startActivity(intentMembership);
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);

        }else if(id == R.id.nav_earn_cash){

            Intent intentearncash = new Intent(this, earn_cash.class);
            startActivity(intentearncash);
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);

        }else if(id == R.id.nav_wallet){
            Intent intentwallet_to_withdraw = new Intent(this, wallet_to_withdraw.class);
            startActivity(intentwallet_to_withdraw);
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);

        }else if(id == R.id.nav_order_list){
            Toast.makeText(getApplicationContext(), "Available soon" , Toast.LENGTH_SHORT).show();
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);

        }else if(id == R.id.nav_payment_history){
            Toast.makeText(getApplicationContext(), "Available soon" , Toast.LENGTH_SHORT).show();
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);

        }else if(id == R.id.nav_youtube){
            Toast.makeText(getApplicationContext(), "Available soon" , Toast.LENGTH_SHORT).show();
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);

        }else if(id == R.id.nav_support_group){
            Toast.makeText(getApplicationContext(), "Available soon" , Toast.LENGTH_SHORT).show();
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);

        }else if(id == R.id.nav_logout){
            try {
                mAuth.signOut();
                Intent intent = new Intent(getApplicationContext(), SplashScreen.class);
                startActivity(intent);
                finish();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        closeOptionsMenu();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_page);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void getImage() {
        mAuth = FirebaseAuth.getInstance();
        currentUserDb = FirebaseFirestore.getInstance().collection("image").document("display");
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
                        Picasso.get().load(aboutForDisplay).into(image7);
                    }
                }
            }
        });
    }
}

