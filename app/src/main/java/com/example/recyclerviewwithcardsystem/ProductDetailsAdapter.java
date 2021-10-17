package com.example.recyclerviewwithcardsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class ProductDetailsAdapter extends RecyclerView.Adapter<ProductDetailsAdapter.ViewHolder> implements Filterable {
    Context context;
    ArrayList<Product> products;

    ProductDetailsAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }
    @NonNull
    @Override
    public ProductDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View productDetailsView = inflater.inflate(R.layout.list_item_card,parent,false);
        return new ViewHolder(productDetailsView);
    }
    @Override
    public void onBindViewHolder(ProductDetailsAdapter.ViewHolder holder, int position) {
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
            addToCartButton.setOnClickListener(view -> {
                Toast.makeText(itemView.getContext(),"Added to cart",Toast.LENGTH_SHORT).show();
                MainActivity.addedToCart.add(products.get(position));
            });
        }
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            ArrayList<Product> filteredProducts = new ArrayList<>();
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String content = charSequence.toString();
                ArrayList<Product> filteredProductsList = new ArrayList<>();
                if (!content.isEmpty()) {
                    for (Product row : products) {
                        if (row.getName().toLowerCase().contains(content.toLowerCase()) || Integer.toString(row.getId()).contains(content.toLowerCase())) {
                            filteredProductsList.add(row);
                        }
                    }
                    products = filteredProductsList;
                }
                FilterResults filteredValues = new FilterResults();
                filteredValues.values = filteredProducts;
                return filteredValues;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredProducts = (ArrayList<Product>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
    public void setDataBack(ArrayList<Product> data) {
        products = data;
        notifyDataSetChanged();
    }

}
