package com.earning.dekhai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.earning.dekhai.model.order_list_adapter;
import com.earning.dekhai.model.order_list_model;

import java.util.ArrayList;
import java.util.List;

public class order_list_page extends AppCompatActivity {

    RecyclerView rec_order_list;
    order_list_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list_page_activity);

        rec_order_list=findViewById(R.id.rec_order_list);

        setOrderListRecyclerView();
    }

    private void setOrderListRecyclerView() {
        rec_order_list.setHasFixedSize(true);
        rec_order_list.setLayoutManager(new LinearLayoutManager(this));
        adapter =new order_list_adapter(this,getList());
        rec_order_list.setAdapter(adapter);
    }

    private List<order_list_model> getList(){
        List<order_list_model> order_list= new ArrayList<>();

        order_list.add(new order_list_model("1","200","11/12/21","12/12/21","sold"));
        order_list.add(new order_list_model("2","200","12/12/21","13/12/21","not sold"));
        order_list.add(new order_list_model("3","200","13/12/21","14/12/21","refunded"));
        order_list.add(new order_list_model("4","200","14/12/21","15/12/21","cancelled"));


        return order_list;
    }
}