package com.earning.dekhai.screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.earning.dekhai.R;
import com.earning.dekhai.adapter.CategoryAdapter;
import com.earning.dekhai.adapter.MemberShipAdapter;
import com.earning.dekhai.adapter.ProductAdapter;
import com.earning.dekhai.adapter.SliderAdapter;
import com.earning.dekhai.adapter.order_list_adapter;
import com.earning.dekhai.model.CategoryModel;
import com.earning.dekhai.model.MembershipModel;
import com.earning.dekhai.model.ProductModel;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class ShoppingPage extends AppCompatActivity {

    RecyclerView shooping_category,shooping_products;
    CategoryAdapter  ca;
    ProductAdapter productAdapter;

    SliderView sliderView;
    int[] images = {R.drawable.banner_img,
            R.drawable.banner_img,
            R.drawable.banner_img};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_page_activity);

        sliderView = findViewById(R.id.shopping_image_slider);

        SliderAdapter sliderAdapter = new SliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();


        shooping_category=findViewById(R.id.shooping_category);
        shooping_products=findViewById(R.id.shooping_products);




        FirebaseFirestore find = FirebaseFirestore.getInstance();
        CollectionReference collection = find.collection("membership");
        Query query = collection.orderBy("order");
        shooping_category.setLayoutManager(new LinearLayoutManager(this));
        FirestoreRecyclerOptions<CategoryModel> options7 =
                new FirestoreRecyclerOptions.Builder<CategoryModel>()
                        .setQuery(query, CategoryModel.class)
                        .build();
        ca = new CategoryAdapter(options7);
        shooping_category.setAdapter(ca);

        FirebaseFirestore find1 = FirebaseFirestore.getInstance();
        CollectionReference collection1 = find1.collection("product");
        shooping_products.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        FirestoreRecyclerOptions<ProductModel> options71 =
                new FirestoreRecyclerOptions.Builder<ProductModel>()
                        .setQuery(collection1, ProductModel.class)
                        .build();
        productAdapter = new ProductAdapter(options71);
        shooping_products.setAdapter(productAdapter);


    }
    @Override
    protected void onStart() {
        super.onStart();
        ca.startListening();
        productAdapter.startListening();


    }

    @Override
    protected void onStop() {
        super.onStop();
        ca.stopListening();
        productAdapter.stopListening();
    }
}