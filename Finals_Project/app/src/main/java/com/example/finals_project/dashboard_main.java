package com.example.finals_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class dashboard_main extends AppCompatActivity {
    private static final String TAG = "dashboard_main";

    // Declare all CardViews
    CardView addReviewCard, viewReviewCard, updateReviewCard, deleteCard, logoutCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_main);

        // Retrieve email and password from Intent
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");

        // Log the retrieved data for debugging
        Log.d(TAG, "Email received: " + email);
        Log.d(TAG, "Password received: " + password);

        // Initialize CardViews
        addReviewCard = findViewById(R.id.imageCard);
        viewReviewCard = findViewById(R.id.videoCard);
        updateReviewCard = findViewById(R.id.docCard);
        deleteCard = findViewById(R.id.deleteCard);
        logoutCard = findViewById(R.id.downloadCard);

        // Set up click listeners for each card
        addReviewCard.setOnClickListener(view -> {
            Intent addReviewIntent = new Intent(dashboard_main.this, d_add_review.class);
            startActivity(addReviewIntent);
        });

        viewReviewCard.setOnClickListener(view -> {
            Intent viewReviewIntent = new Intent(dashboard_main.this, d_view_review.class);
            startActivity(viewReviewIntent);
        });

        updateReviewCard.setOnClickListener(view -> {
            Intent updateReviewIntent = new Intent(dashboard_main.this, d_update_review.class);
            startActivity(updateReviewIntent);
        });

        deleteCard.setOnClickListener(view -> {
            Intent deleteIntent = new Intent(dashboard_main.this, d_delete_review.class);
            startActivity(deleteIntent);
        });

        logoutCard.setOnClickListener(view -> {
            Intent logoutIntent = new Intent(dashboard_main.this, MainActivity.class);
            startActivity(logoutIntent);
            finish();
        });
    }
}
