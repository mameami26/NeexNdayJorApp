package com.example.neexndayjor.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.neexndayjor.R;
import com.example.neexndayjor.database.CartRepository;  // Ensure proper import
import com.example.neexndayjor.models.MenuItem;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private final Context context;
    private final List<MenuItem> menuList;

    public MenuAdapter(Context context, List<MenuItem> menuList) {
        this.context = context;
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuItem item = menuList.get(position);

        holder.nameTextView.setText(item.getName());
        holder.priceTextView.setText(item.getPrice());
        holder.descriptionTextView.setText(item.getDescription());
        holder.foodImageView.setImageResource(item.getImageResId());
        holder.ratingBar.setRating(item.getRating());

        holder.addToCartButton.setOnClickListener(v -> {
            // Add item to the cart using CartRepository
            CartRepository repo = new CartRepository(context);
            repo.addToCart(item);

            Toast.makeText(context, item.getName() + " added to cart!", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {

        ImageView foodImageView;
        TextView nameTextView, priceTextView, descriptionTextView;
        RatingBar ratingBar;
        Button addToCartButton;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImageView = itemView.findViewById(R.id.foodImageView);
            nameTextView = itemView.findViewById(R.id.foodNameTextView);
            priceTextView = itemView.findViewById(R.id.foodPriceTextView);
            descriptionTextView = itemView.findViewById(R.id.foodDescriptionTextView);
            ratingBar = itemView.findViewById(R.id.foodRatingBar);
            addToCartButton = itemView.findViewById(R.id.addToCartButton);
        }
    }
}
