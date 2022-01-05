package com.earning.dekhai.screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.earning.dekhai.R;
import com.earning.dekhai.adapter.CategoryAdapter;
import com.earning.dekhai.adapter.MemberShipAdapter;
import com.earning.dekhai.adapter.ProductAdapter;
import com.earning.dekhai.adapter.SliderAdapter;
import com.earning.dekhai.adapter.order_list_adapter;
import com.earning.dekhai.model.CategoryModel;
import com.earning.dekhai.model.MembershipModel;
import com.earning.dekhai.model.ProductModel;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class ShoppingPage extends AppCompatActivity {

    RecyclerView shooping_products;
    ProductAdapter productAdapter;
    Button ib_todays_special,ib_smart_phone,ib_home_appliances,ib_daily_bazar,ib_health_beauty;


    MeowBottomNavigation bottomNavigation;

    SliderView sliderView;
    int[] images = {R.drawable.a,
            R.drawable.b,
            R.drawable.c};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_page_activity);

        bottomNavigation=findViewById(R.id.bottom_navigation);

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

        sliderView = findViewById(R.id.shopping_image_slider);

        ib_todays_special = findViewById(R.id.ib_todays_special);
        ib_smart_phone = findViewById(R.id.ib_smart_phone);
        ib_home_appliances = findViewById(R.id.ib_home_appliances);
        ib_daily_bazar = findViewById(R.id.ib_daily_bazar);
        ib_health_beauty = findViewById(R.id.ib_health_beauty);

        SliderAdapter sliderAdapter = new SliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
        shooping_products=findViewById(R.id.shooping_products);

        FirebaseFirestore find1 = FirebaseFirestore.getInstance();
        CollectionReference collection1 = find1.collection("product");
        Query query = collection1.whereEqualTo("category", "todays_special");
        shooping_products.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        FirestoreRecyclerOptions<ProductModel> options71 =
                new FirestoreRecyclerOptions.Builder<ProductModel>()
                        .setQuery(query, ProductModel.class)
                        .build();
        productAdapter = new ProductAdapter(options71);
        shooping_products.setAdapter(productAdapter);

        ib_todays_special.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectionReference collection1 = find1.collection("product");
                Query query = collection1.whereEqualTo("category", "todays_special");
                shooping_products.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false));
                FirestoreRecyclerOptions<ProductModel> options71 =
                        new FirestoreRecyclerOptions.Builder<ProductModel>()
                                .setQuery(query, ProductModel.class)
                                .build();
                productAdapter = new ProductAdapter(options71);
                shooping_products.setAdapter(productAdapter);
                productAdapter.startListening();
            }
        });
        ib_smart_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectionReference collection1 = find1.collection("product");
                Query query = collection1.whereEqualTo("category", "gadget_smart_phone");
                shooping_products.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false));
                FirestoreRecyclerOptions<ProductModel> options71 =
                        new FirestoreRecyclerOptions.Builder<ProductModel>()
                                .setQuery(query, ProductModel.class)
                                .build();
                productAdapter = new ProductAdapter(options71);
                shooping_products.setAdapter(productAdapter);
                productAdapter.startListening();
            }
        });
        ib_home_appliances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectionReference collection1 = find1.collection("product");
                Query query = collection1.whereEqualTo("category", "electronics_home_appliances");
                shooping_products.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false));
                FirestoreRecyclerOptions<ProductModel> options71 =
                        new FirestoreRecyclerOptions.Builder<ProductModel>()
                                .setQuery(query, ProductModel.class)
                                .build();
                productAdapter = new ProductAdapter(options71);
                shooping_products.setAdapter(productAdapter);
                productAdapter.startListening();

            }
        });
        ib_daily_bazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectionReference collection1 = find1.collection("product");
                Query query = collection1.whereEqualTo("category", "daily_bazar");
                shooping_products.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false));
                FirestoreRecyclerOptions<ProductModel> options71 =
                        new FirestoreRecyclerOptions.Builder<ProductModel>()
                                .setQuery(query, ProductModel.class)
                                .build();
                productAdapter = new ProductAdapter(options71);
                shooping_products.setAdapter(productAdapter);
                productAdapter.startListening();
            }
        });
        ib_health_beauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectionReference collection1 = find1.collection("product");
                Query query = collection1.whereEqualTo("category", "fashion_health_beauty");
                shooping_products.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false));
                FirestoreRecyclerOptions<ProductModel> options71 =
                        new FirestoreRecyclerOptions.Builder<ProductModel>()
                                .setQuery(query, ProductModel.class)
                                .build();
                productAdapter = new ProductAdapter(options71);
                shooping_products.setAdapter(productAdapter);
                productAdapter.startListening();
            }
        });









    }

    private void loadFragment(Fragment fragment) {
        //        replace fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
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
}