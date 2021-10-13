package com.example.recyclerviewwithcardsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button cartButton;
    RecyclerView recyclerView;
    ArrayList<Product> products;
    static ArrayList<Product> addedToCart;
    Button backButton;
    SearchView searchView;
    TextView emptyText;
    View backButtonLayout;

    ProductDetailsAdapter productDetailsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        cartButton = findViewById(R.id.cart);
        backButton = findViewById(R.id.back_button);
        searchView = findViewById(R.id.search_bar);
        emptyText = findViewById(R.id.empty_view);
        backButtonLayout = findViewById(R.id.back_button_layout);

        products = new ArrayList<>();
        addedToCart = new ArrayList<>();
        productDetailsAdapter = new ProductDetailsAdapter(MainActivity.this,products);

        for (int i=0;i<20;i++) {
            products.add(new Product(i + 1,"abc","This is a product",R.drawable.ic_launcher_foreground,3.8,3.90));
        }

        backButtonLayout.setVisibility(View.GONE);
        registerForContextMenu(recyclerView);

        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        recyclerView.setAdapter(productDetailsAdapter);

        searchView.setWeightSum((float)2.5);

        cartButton.setOnClickListener(view -> {
            cartButton.setVisibility(View.GONE);
            backButtonLayout.setVisibility(View.VISIBLE);
            searchView.setVisibility(View.GONE);

            if (addedToCart.isEmpty()) {
                emptyText.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);

            } else {
                emptyText.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setLayoutManager( new LinearLayoutManager(this));
                recyclerView.setAdapter(new CartDetailsAdapter(MainActivity.this,addedToCart));
            }
        });

        backButton.setOnClickListener(view -> {
            emptyText.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            cartButton.setVisibility(View.VISIBLE);
            backButtonLayout.setVisibility(View.GONE);
            searchView.setVisibility(View.VISIBLE);

            recyclerView.setLayoutManager( new LinearLayoutManager(this));
            recyclerView.setAdapter(productDetailsAdapter);
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (!s.isEmpty()) {
                    productDetailsAdapter.getFilter().filter(s);
                } else {
                    productDetailsAdapter.setDataBack(products);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(!s.isEmpty()) {
                    productDetailsAdapter.getFilter().filter(s);
                } else {
                    productDetailsAdapter.setDataBack(products);
                }
                return false;
            }
        });
    }
}