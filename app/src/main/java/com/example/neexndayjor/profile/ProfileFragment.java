package com.example.neexndayjor.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.neexndayjor.R;

public class ProfileFragment extends Fragment {

    // Step 1: Declare UI components
    private ImageView profileImage;
    private TextView userNameTextView, userEmailTextView, userPhoneTextView;
    private Button editProfileButton, logoutButton;

    public ProfileFragment() {
        // Required empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Step 2: Inflate layout
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Step 3: Link XML views using findViewById
        profileImage = view.findViewById(R.id.profileImage);
        userNameTextView = view.findViewById(R.id.userNameTextView);
        userEmailTextView = view.findViewById(R.id.userEmailTextView);
        userPhoneTextView = view.findViewById(R.id.userPhoneTextView);
        editProfileButton = view.findViewById(R.id.editProfileButton);
        logoutButton = view.findViewById(R.id.logoutButton);

        // Step 4: Simulate dynamic user data
        // You can later fetch this from SharedPreferences or Firebase
        User currentUser = getUser();

        userNameTextView.setText(currentUser.getName());
        userEmailTextView.setText(currentUser.getEmail());
        userPhoneTextView.setText(currentUser.getPhone());
        profileImage.setImageResource(R.drawable.ic_profile_placeholder); // default profile image

        // Step 5: Button click listeners
        editProfileButton.setOnClickListener(v ->
                Toast.makeText(getContext(), "Edit Profile clicked!", Toast.LENGTH_SHORT).show());

        logoutButton.setOnClickListener(v ->
                Toast.makeText(getContext(), "Logging out...", Toast.LENGTH_SHORT).show());

        return view;
    }

    // Step 6: Temporary mock user
    private User getUser() {
        return new User(
                "Aminata Sall",
                "aminata@example.com",
                "+1 929 227 1511"
        );
    }

    // Step 7: Local User class (replace with Firebase later)
    private static class User {
        private final String name, email, phone;

        public User(String name, String email, String phone) {
            this.name = name;
            this.email = email;
            this.phone = phone;
        }

        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getPhone() { return phone; }
    }
}
