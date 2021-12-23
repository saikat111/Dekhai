package com.earning.dekhai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.earning.dekhai.adapter.order_list_adapter;

public class ShoppingPage extends AppCompatActivity {

    RecyclerView shooping_category,shooping_products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_page_activity);

        shooping_category=findViewById(R.id.shooping_category);
        shooping_products=findViewById(R.id.shooping_products);

        setShoppingRecyclerView();
    }

    private void setShoppingRecyclerView() {
        shooping_category.setHasFixedSize(true);
        shooping_category.setLayoutManager(new LinearLayoutManager(this));

        shooping_products.setHasFixedSize(true);
        shooping_products.setLayoutManager(new LinearLayoutManager(this));
    }
}