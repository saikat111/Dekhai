package com.earning.dekhai.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.earning.dekhai.R;
import com.earning.dekhai.model.NoticeModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class NoticeAdapter  extends FirestoreRecyclerAdapter<NoticeModel, NoticeAdapter.DataViewHolder> {

    public NoticeAdapter(@NonNull FirestoreRecyclerOptions<NoticeModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DataViewHolder holder, int position, @NonNull NoticeModel model) {
        holder.text.setText(model.getText());

    }
    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.noticecard, parent, false);
        return new NoticeAdapter.DataViewHolder(view);
    }
    public class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView text;
        public DataViewHolder(@NonNull final View itemView) {
            super(itemView);
     /*       itemView.setOnClickListener(this);*/
            text = itemView.findViewById(R.id.text);

        }
        @Override
        public void onClick(View view) {
            /*Intent intent = new Intent(view.getContext(), ProductDetails.class);
            intent.putExtra("id", productid.getText().toString());
            intent.putExtra("category", productcategory.getText().toString());
            view.getContext().startActivity(intent);*/
        }
    }
}
