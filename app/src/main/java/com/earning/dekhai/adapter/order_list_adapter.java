package com.earning.dekhai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.earning.dekhai.R;
import com.earning.dekhai.model.order_list_model;

import java.util.List;

public class order_list_adapter extends RecyclerView.Adapter<order_list_adapter.ViewHolder> {

    Context context;
    List<order_list_model> order_list;

    public order_list_adapter(Context context,List<order_list_model> order_list) {
        this.context = context;
        this.order_list= order_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.order_list_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(order_list!=null && order_list.size()>0){
            order_list_model model=order_list.get(position);

            holder.tvOrderNo.setText(model.getOrder_no());
            holder.tvamount.setText(model.getAmount());
            holder.tvOrderDate.setText(model.getOrder_date());
            holder.tvDeliverDate.setText(model.getDeliver_date());
            holder.tvStatus.setText(model.getStatus());

        }else {
            return;
        }

    }

    @Override
    public int getItemCount() {
        return order_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderNo,tvamount,tvOrderDate,tvDeliverDate,tvStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvOrderNo=itemView.findViewById(R.id.tvOrderNo);
            tvamount=itemView.findViewById(R.id.tvamount);
            tvOrderDate=itemView.findViewById(R.id.tvOrderDate);
            tvDeliverDate=itemView.findViewById(R.id.tvDeliverDate);
            tvStatus=itemView.findViewById(R.id.tvStatus);

        }
    }
}
