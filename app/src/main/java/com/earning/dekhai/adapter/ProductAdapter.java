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
import com.earning.dekhai.model.ProductModel;
import com.earning.dekhai.screen.ProductDetailsActivity;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

public class ProductAdapter extends FirestoreRecyclerAdapter<ProductModel, ProductAdapter.DataViewHolder> {

    public ProductAdapter(@NonNull FirestoreRecyclerOptions<ProductModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ProductAdapter.DataViewHolder holder, int position, @NonNull ProductModel model) {
 /*       holder.text.setText(model.getTitle());
        holder.price.setText(model.getBDT());*/
        Picasso.get().load(model.getImage()).into(holder.imageView);
        holder.model.setText(model.getName());
        holder.price.setText(model.getPrice());
        holder.id.setText(model.getId());
        holder.category.setText(model.getCategory());
        holder.d.setText(model.getDescription());

    }
    @NonNull
    @Override
    public ProductAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shoping_page_products_item, parent, false);
        return new ProductAdapter.DataViewHolder(view);
    }
    public class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView model, price, id, category,d;
        ImageView imageView;
        public DataViewHolder(@NonNull final View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = itemView.findViewById(R.id.imageView);
            model = itemView.findViewById(R.id.model);
            price = itemView.findViewById(R.id.price);
            id = itemView.findViewById(R.id.id);
            category = itemView.findViewById(R.id.category);
            d = itemView.findViewById(R.id.d);


        }
        @Override
        public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), ProductDetailsActivity.class);
            intent.putExtra("price", price.getText().toString());
            intent.putExtra("model", model.getText().toString());
            intent.putExtra("id", id.getText().toString());
            intent.putExtra("category", category.getText().toString());
            intent.putExtra("getDescription", d.getText().toString());
        view.getContext().startActivity(intent);
        }
    }
}

