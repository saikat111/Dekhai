package com.earning.dekhai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.earning.dekhai.authentication.PhoneNumber;
import com.earning.dekhai.screen.Profile;
import com.earning.dekhai.screen.ProfileData;
import com.earning.dekhai.screen.SplashScreen;
import com.earning.dekhai.screen.Wallet;
import com.google.firebase.auth.FirebaseAuth;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mAuth = FirebaseAuth.getInstance();


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
        final PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Wallet");
        final PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(2).withName("Payment History");
        final PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(2).withName("Youtube");
        final PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(2).withName("Support Group");
        final PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(2).withName("Logout");
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
                        item6
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem == item1) {

                                Intent homeIntent=new Intent(MainActivity.this, Profile.class);
                                startActivity(homeIntent);

                            /*Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.fb)));
                            startActivity(browserIntent);*/
                        }
                        if (drawerItem == item2) {
                            Intent homeIntent=new Intent(MainActivity.this, Wallet.class);
                            startActivity(homeIntent);

                        /*    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.youtube)));
                            startActivity(browserIntent);*/
                        }
                        if (drawerItem == item3) {

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

    }
}