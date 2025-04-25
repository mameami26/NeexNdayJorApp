package com.example.neexndayjor.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.neexndayjor.R;
import com.example.neexndayjor.database.MenuRepository;
import com.example.neexndayjor.menu.MenuAdapter;
import com.example.neexndayjor.models.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView menuRecyclerView;
    private MenuAdapter adapter;
    private MenuRepository repository;
    private List<MenuItem> allItems = new ArrayList<>();
    private List<MenuItem> filteredItems = new ArrayList<>();

    public HomeFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        repository = new MenuRepository(requireContext());
        menuRecyclerView = view.findViewById(R.id.menuRecyclerView);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        allItems = repository.getMenuItemsByCategory("All");

        // Optional: seed initial data if database is empty
        if (allItems.isEmpty()) {
            seedData(); // ← only runs once
            allItems = repository.getMenuItemsByCategory("All");
        }

        filteredItems.addAll(allItems);
        adapter = new MenuAdapter(getContext(), filteredItems);
        menuRecyclerView.setAdapter(adapter);

        // Filter by categories
        view.findViewById(R.id.breakfastCategory).setOnClickListener(v -> filterByCategory("Breakfast"));
        view.findViewById(R.id.lunchCategory).setOnClickListener(v -> filterByCategory("Lunch"));
        view.findViewById(R.id.dinnerCategory).setOnClickListener(v -> filterByCategory("Dinner"));
        view.findViewById(R.id.drinksCategory).setOnClickListener(v -> filterByCategory("Drinks"));
        view.findViewById(R.id.dessertsCategory).setOnClickListener(v -> filterByCategory("Desserts"));
        view.findViewById(R.id.allCategory).setOnClickListener(v -> filterByCategory("All"));

        return view;
    }

    private void filterByCategory(String category) {
        filteredItems.clear();
        filteredItems.addAll(repository.getMenuItemsByCategory(category));
        adapter.notifyDataSetChanged();
    }

    private void seedData() {
        repository.insertMenuItem(new MenuItem("Thiebou Djeun", "$12.99", "Fish & rice", R.drawable.menu_1, 4.5f, "Lunch"));
        repository.insertMenuItem(new MenuItem("Yassa Poulet", "$10.99", "Chicken lemon sauce", R.drawable.menu_2, 4.0f, "Dinner"));
        repository.insertMenuItem(new MenuItem("Thiakry", "$5.00", "Sweet millet couscous", R.drawable.menu_3, 4.8f, "Desserts"));
        repository.insertMenuItem(new MenuItem("Lakh", "$4.50", "Millet porridge", R.drawable.menu_4, 4.2f, "Breakfast"));
        repository.insertMenuItem(new MenuItem("Bissap", "$3.00", "Hibiscus juice", R.drawable.menu_5, 4.7f, "Drinks"));
        repository.insertMenuItem(new MenuItem("Bouye", "$3.00", "Baobab drink", R.drawable.menu_5, 4.6f, "Drinks"));
    }
}
