package com.example.finals_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LoginTabFragment extends Fragment {

    private EditText emailInput, passwordInput;
    private Button loginButton;
    private DatabaseHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_tab, container, false);

        // Initialize UI elements
        emailInput = view.findViewById(R.id.login_email);
        passwordInput = view.findViewById(R.id.login_password);
        loginButton = view.findViewById(R.id.login_button);

        // Initialize DatabaseHelper
        db = new DatabaseHelper(getContext());

        // Set up login button click listener
        loginButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            // Validate inputs
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check user credentials
            try {
                boolean isValid = db.checkUser(email, password);
                if (isValid) {
                    Toast.makeText(getContext(), "Login successful", Toast.LENGTH_SHORT).show();
                    // Redirect to the dashboard
                    Intent intent = new Intent(getActivity(), dashboard_main.class);
                    startActivity(intent);
                    if (getActivity() != null) {
                        getActivity().finish(); // Close the current activity
                    }
                } else {
                    Toast.makeText(getContext(), "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.e("LoginTabFragment", "Error checking user: " + e.getMessage());
                Toast.makeText(getContext(), "An error occurred. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
