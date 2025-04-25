package com.example.neexndayjor.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.neexndayjor.R;
import com.example.neexndayjor.database.CartRepository;
import com.example.neexndayjor.models.MenuItem;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private final List<MenuItem> cartItems;
    private final Runnable onCartUpdated;
    private final CartRepository repository;

    public CartAdapter(List<MenuItem> cartItems, Runnable onCartUpdated, CartRepository repository) {
        this.cartItems = cartItems;
        this.onCartUpdated = onCartUpdated;
        this.repository = repository;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        MenuItem item = cartItems.get(position);

        holder.foodImage.setImageResource(item.getImageResId());
        holder.nameText.setText(item.getName());
        holder.descText.setText(item.getDescription());
        holder.priceText.setText(item.getPrice());
        holder.ratingBar.setRating(item.getRating());

        holder.removeButton.setOnClickListener(v -> {
            // Remove item from database
            repository.removeItemByName(item.getName());

            // Remove from local list and update adapter
            cartItems.remove(holder.getBindingAdapterPosition());
            notifyItemRemoved(holder.getBindingAdapterPosition());
            notifyItemRangeChanged(holder.getBindingAdapterPosition(), cartItems.size());

            // Notify parent to update total
            onCartUpdated.run();
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {

        ImageView foodImage;
        TextView nameText, descText, priceText;
        RatingBar ratingBar;
        Button removeButton;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.cartItemImage);
            nameText = itemView.findViewById(R.id.cartItemName);
            descText = itemView.findViewById(R.id.cartItemDescription);
            priceText = itemView.findViewById(R.id.cartItemPrice);
            ratingBar = itemView.findViewById(R.id.cartItemRatingBar);
            removeButton = itemView.findViewById(R.id.removeItemButton);
        }
    }
}
