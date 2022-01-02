package com.earning.dekhai.screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.earning.dekhai.R;
import com.earning.dekhai.adapter.payment_history_cashout_adapter;
import com.earning.dekhai.model.order_list_model;
import com.earning.dekhai.model.payment_history_cashout_model;

import java.util.ArrayList;
import java.util.List;

public class payment_history_cashout extends AppCompatActivity {

    RecyclerView rec_payment_history_list;
    payment_history_cashout_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_history_cashout_activity);

        rec_payment_history_list=findViewById(R.id.rec_payment_history_list);

        setPaymentHistoryCashOutListRecyclerView();
    }

    private void setPaymentHistoryCashOutListRecyclerView() {
        rec_payment_history_list.setHasFixedSize(true);
        rec_payment_history_list.setLayoutManager(new LinearLayoutManager(this));
        adapter =new payment_history_cashout_adapter(this,getList());
        rec_payment_history_list.setAdapter(adapter);
    }

    private List<payment_history_cashout_model> getList() {
        List<payment_history_cashout_model> payment_history_cashout_list= new ArrayList<>();
        payment_history_cashout_list.add(new payment_history_cashout_model("1","w1","200","12/12/21","13/12/21","refunded"));

        return payment_history_cashout_list;
    }


}