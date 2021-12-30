package com.earning.dekhai.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.earning.dekhai.R;
import com.earning.dekhai.model.CategoryModel;
import com.earning.dekhai.screen.PaymentActivity;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class CategoryAdapter extends FirestoreRecyclerAdapter<CategoryModel, CategoryAdapter.DataViewHolder> {

public CategoryAdapter(@NonNull FirestoreRecyclerOptions<CategoryModel> options) {
        super(options);
        }

@Override
protected void onBindViewHolder(@NonNull CategoryAdapter.DataViewHolder holder, int position, @NonNull CategoryModel model) {
 /*       holder.text.setText(model.getTitle());
        holder.price.setText(model.getBDT());*/
    Picasso.get().load(model.getImage()).into(holder.image1);
    holder.textView1.setText(model.getName());

        }
@NonNull
@Override
public CategoryAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.shoping_page_category_item, parent, false);
        return new CategoryAdapter.DataViewHolder(view);
        }
public class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView  textView1;
    ImageView image1;
    public DataViewHolder(@NonNull final View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        image1 = itemView.findViewById(R.id.image1);
        textView1 = itemView.findViewById(R.id.textView1);


    }
    @Override
    public void onClick(View view) {
        /*Intent intent = new Intent(view.getContext(), PaymentActivity.class);
        intent.putExtra("title", text.getText().toString());
        view.getContext().startActivity(intent);*/
    }
}
}
