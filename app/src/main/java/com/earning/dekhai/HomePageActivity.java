package com.earning.dekhai;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.earning.dekhai.screen.NoticeActivity;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.earning.dekhai.databinding.ActivityHomePageBinding;

public class HomePageActivity extends AppCompatActivity {
//    initializing variable
    MeowBottomNavigation bottomNavigation;
    ConstraintLayout notice, earning ,support_chat;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomePageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        assign variable
        bottomNavigation=findViewById(R.id.bottom_navigation);
        notice=findViewById(R.id.notice);
        earning=findViewById(R.id.earning);
        support_chat=findViewById(R.id.support_chat);
        support_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent=new Intent(getApplicationContext(), contact_us.class);
                startActivity(homeIntent);

            }
        });
        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent=new Intent(getApplicationContext(), NoticeActivity.class);
                startActivity(homeIntent);

            }
        });
        earning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent=new Intent(getApplicationContext(), earn_cash.class);
                startActivity(homeIntent);

            }
        });

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
                 Toast.makeText(getApplicationContext(), "You clicked"+item.getId(), Toast.LENGTH_SHORT).show();
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
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_page);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
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

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_page);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}

