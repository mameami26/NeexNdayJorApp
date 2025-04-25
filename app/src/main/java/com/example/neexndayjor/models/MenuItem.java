package com.example.neexndayjor.models;

public class MenuItem {
    private String name;
    private String price;
    private String description;
    private int imageResId;
    private float rating;
    private String category;

    public MenuItem(String name, String price, String description, int imageResId, float rating, String category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageResId = imageResId;
        this.rating = rating;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }

    public float getRating() {
        return rating;
    }

    public String getCategory() {
        return category;
    }
}
