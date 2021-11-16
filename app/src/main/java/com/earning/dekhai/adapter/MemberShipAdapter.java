package com.earning.dekhai.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.earning.dekhai.R;
import com.earning.dekhai.model.MembershipModel;
import com.earning.dekhai.screen.PaymentActivity;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class MemberShipAdapter extends FirestoreRecyclerAdapter<MembershipModel, MemberShipAdapter.DataViewHolder> {

    public MemberShipAdapter(@NonNull FirestoreRecyclerOptions<MembershipModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MemberShipAdapter.DataViewHolder holder, int position, @NonNull MembershipModel model) {
        holder.text.setText(model.getTitle());
        holder.price.setText(model.getBDT());

    }
    @NonNull
    @Override
    public MemberShipAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.membershipitem, parent, false);
        return new MemberShipAdapter.DataViewHolder(view);
    }
    public class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView text, price;
        public DataViewHolder(@NonNull final View itemView) {
            super(itemView);
                   itemView.setOnClickListener(this);
            text = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);


        }
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), PaymentActivity.class);
            intent.putExtra("title", text.getText().toString());
            view.getContext().startActivity(intent);
        }
    }
}
