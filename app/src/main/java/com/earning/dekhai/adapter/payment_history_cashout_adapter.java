package com.earning.dekhai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.earning.dekhai.R;
import com.earning.dekhai.model.payment_history_cashout_model;

import java.util.List;

public class payment_history_cashout_adapter extends RecyclerView.Adapter<payment_history_cashout_adapter.ViewHolder>{


    Context context;
    List<payment_history_cashout_model> payment_history_list;

    public payment_history_cashout_adapter(Context context,List<payment_history_cashout_model> payment_history_list) {
        this.context = context;
        this.payment_history_list= payment_history_list;
    }

    @NonNull
    @Override
    public payment_history_cashout_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.payment_history_cashout_item,parent,false);
        return new payment_history_cashout_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull payment_history_cashout_adapter.ViewHolder holder, int position) {

        if(payment_history_list!=null && payment_history_list.size()>0){
            payment_history_cashout_model model=payment_history_list.get(position);

            holder.tvDekhaiID.setText(model.getTvDekhaiID());
            holder.tvWalletIdP.setText(model.getTvWalletIdP());
            holder.tvAmountP.setText(model.getTvAmountP());
            holder.tvReqDate.setText(model.getTvReqDate());
            holder.tvConfDate.setText(model.getTvConfDate());
            holder.tvStatusP.setText(model.getTvStatusP());

        }else {
            return;
        }

    }

    @Override
    public int getItemCount() {
        return payment_history_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvDekhaiID,tvWalletIdP,tvAmountP,tvReqDate,tvConfDate, tvStatusP;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDekhaiID=itemView.findViewById(R.id.tvDekhaiID);
            tvWalletIdP=itemView.findViewById(R.id.tvWalletIdP);
            tvAmountP=itemView.findViewById(R.id.tvAmountP);
            tvReqDate=itemView.findViewById(R.id.tvReqDate);
            tvConfDate=itemView.findViewById(R.id.tvConfDate);
            tvStatusP=itemView.findViewById(R.id.tvStatusP);

        }
    }

}
