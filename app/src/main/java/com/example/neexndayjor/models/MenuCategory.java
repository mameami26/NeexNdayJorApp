package com.example.neexndayjor.models;

import java.util.List;

public class MenuCategory {

    private String title;
    private List<MenuItem> items;

    public MenuCategory(String title, List<MenuItem> items) {
        this.title = title;
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public List<MenuItem> getItems() {
        return items;
    }
}
