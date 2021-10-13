package com.example.recyclerviewwithcardsystem;

public class Product {
    int id;
    String name;
    String description;
    int imageId;
    double rating;
    double price;

    public Product(int id, String name, String description, int imageId, double rating, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageId = imageId;
        this.rating = rating;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageId() {
        return imageId;
    }

    public double getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }
}
