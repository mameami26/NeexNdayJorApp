package com.example.neexndayjor.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.neexndayjor.R;
import com.example.neexndayjor.database.CartRepository;  // Ensure proper import
import com.example.neexndayjor.models.MenuItem;

import java.util.List;

public class CartFragment extends Fragment {

    private RecyclerView cartRecyclerView;
    private TextView totalPriceTextView;
    private Button checkoutButton;
    private CartAdapter adapter;
    private CartRepository repository;
    private List<MenuItem> cartItems;

    public CartFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        cartRecyclerView = view.findViewById(R.id.cartRecyclerView);
        totalPriceTextView = view.findViewById(R.id.totalPriceTextView);
        checkoutButton = view.findViewById(R.id.checkoutButton);

        cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        repository = new CartRepository(requireContext());  // CartRepository initialized here

        loadCartItems();

        checkoutButton.setOnClickListener(v -> placeOrder());

        return view;
    }

    private void loadCartItems() {
        cartItems = repository.getCartItems();

        // If the cart is empty, disable the checkout button
        if (cartItems.isEmpty()) {
            checkoutButton.setEnabled(false);
        } else {
            checkoutButton.setEnabled(true);
        }

        adapter = new CartAdapter(cartItems, this::updateTotalPrice, repository);
        cartRecyclerView.setAdapter(adapter);
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        double total = 0.0;
        for (MenuItem item : cartItems) {
            try {
                total += Double.parseDouble(item.getPrice().replace("$", ""));
            } catch (NumberFormatException ignored) {}
        }
        totalPriceTextView.setText("Total: $" + String.format("%.2f", total));
    }

    private void placeOrder() {
        if (cartItems.isEmpty()) {
            Toast.makeText(getContext(), "Your cart is empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Clear cart from DB
        repository.clearCart();

        // Clear local list & update UI
        cartItems.clear();
        adapter.notifyDataSetChanged();
        updateTotalPrice();

        Toast.makeText(getContext(), "Order placed successfully!", Toast.LENGTH_LONG).show();
    }
}
