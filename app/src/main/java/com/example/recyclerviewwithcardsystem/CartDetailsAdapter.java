package com.example.recyclerviewwithcardsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class CartDetailsAdapter extends RecyclerView.Adapter<CartDetailsAdapter.ViewHolder>{
    Context context;
    ArrayList<Product> products;

    CartDetailsAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }
    @NonNull
    @Override
    public CartDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View productDetailsView = inflater.inflate(R.layout.list_item_card,parent,false);
        return new ViewHolder(productDetailsView);
    }

    @Override
    public void onBindViewHolder(CartDetailsAdapter.ViewHolder holder, int position) {
        Product product = products.get(position);
        TextView id = holder.id;
        TextView name = holder.name;
        TextView price = holder.price;
        TextView description = holder.description;
        RatingBar ratingBar = holder.ratingBar;
        ImageView productImage = holder.productImage;
        holder.position = holder.getAdapterPosition();
        String text;
        text = "ID "+product.getId();
        id.setText(text);
        text = "Name "+product.getName();
        name.setText(text);
        text = "Price " + String.format(Locale.getDefault(),"%.2f",product.getPrice());
        price.setText(text);
        text = product.getDescription();
        description.setText(text);
        ratingBar.setRating((float)product.getRating());
        productImage.setImageResource(product.getImageId());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id;
        public TextView name;
        public TextView price;
        public TextView description;
        public RatingBar ratingBar;
        public ImageView productImage;
        public ImageView addToCartButton;
        public int position;

        public ViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.product_id);
            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            description = itemView.findViewById(R.id.product_description);
            ratingBar = itemView.findViewById(R.id.product_rating);
            productImage = itemView.findViewById(R.id.product_image);
            addToCartButton = itemView.findViewById(R.id.add_to_cart_button);
            addToCartButton.setVisibility(ImageView.GONE);
        }
    }

}
