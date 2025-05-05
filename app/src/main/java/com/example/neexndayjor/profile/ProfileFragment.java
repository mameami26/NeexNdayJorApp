package com.example.neexndayjor.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.neexndayjor.R;
import com.example.neexndayjor.auth.LoginActivity;

public class ProfileFragment extends Fragment {

    private TextView userNameTextView, userEmailTextView, userPhoneTextView;
    private TextView btnOrders, btnSettings, btnHelp, btnCredits, btnLoyaltyCards, btnFamily, logoutButton;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize profile information
        userNameTextView = view.findViewById(R.id.userNameTextView);
        userEmailTextView = view.findViewById(R.id.userEmailTextView);
        userPhoneTextView = view.findViewById(R.id.userPhoneTextView);

        // Initialize section links
        btnOrders = view.findViewById(R.id.btnOrders);
        btnSettings = view.findViewById(R.id.btnSettings);
        btnHelp = view.findViewById(R.id.btnHelp);
        btnCredits = view.findViewById(R.id.btnCredits);
        btnLoyaltyCards = view.findViewById(R.id.btnLoyaltyCards);
        btnFamily = view.findViewById(R.id.btnFamily);
        logoutButton = view.findViewById(R.id.logoutButton);

        // Set click listeners
        btnOrders.setOnClickListener(v -> openOrders());
        btnSettings.setOnClickListener(v -> openSettings());
        btnHelp.setOnClickListener(v -> openHelp());
        btnCredits.setOnClickListener(v -> openCredits());
        btnLoyaltyCards.setOnClickListener(v -> openLoyaltyCards());
        btnFamily.setOnClickListener(v -> openFamily());
        logoutButton.setOnClickListener(v -> signOut());

        return view;
    }

    private void openOrders() {
        Toast.makeText(getActivity(), "Opening Orders...", Toast.LENGTH_SHORT).show();
    }

    private void openSettings() {
        Toast.makeText(getActivity(), "Opening Settings...", Toast.LENGTH_SHORT).show();
    }

    private void openHelp() {
        Toast.makeText(getActivity(), "Opening Help...", Toast.LENGTH_SHORT).show();
    }

    private void openCredits() {
        Toast.makeText(getActivity(), "Opening Credits, Promos, and Gift Cards...", Toast.LENGTH_SHORT).show();
    }

    private void openLoyaltyCards() {
        Toast.makeText(getActivity(), "Opening Add Loyalty Cards...", Toast.LENGTH_SHORT).show();
    }

    private void openFamily() {
        Toast.makeText(getActivity(), "Opening Family...", Toast.LENGTH_SHORT).show();
    }

    private void signOut() {
        // Example: Clear user session if you have login system
        // If using Firebase: FirebaseAuth.getInstance().signOut();

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();

        Toast.makeText(getActivity(), "Signed out successfully", Toast.LENGTH_SHORT).show();
    }
}
