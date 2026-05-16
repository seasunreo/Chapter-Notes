package com.example.finals_project;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class d_add_review extends AppCompatActivity {

    // Declare views
    private EditText bookTitle, author, remarks;

    // DatabaseHelper instance
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_add_review); // Ensure your XML file matches this name

        // Initialize views
        bookTitle = findViewById(R.id.bookTitle);
        author = findViewById(R.id.author);
        remarks = findViewById(R.id.remarks);
        Button updateButton = findViewById(R.id.updateButton);

        // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Set click listener for the button
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReview();
            }
        });
    }

    private void addReview() {
        // Get input values
        String title = bookTitle.getText().toString().trim();
        String bookAuthor = author.getText().toString().trim();
        String userRemarks = remarks.getText().toString().trim();

        // Validate inputs
        if (TextUtils.isEmpty(title)) {
            bookTitle.setError("Title is required");
            return;
        }
        if (TextUtils.isEmpty(bookAuthor)) {
            author.setError("Author is required");
            return;
        }
        if (TextUtils.isEmpty(userRemarks)) {
            remarks.setError("Remarks are required");
            return;
        }

        // Insert the review into SQLite
        boolean isInserted = dbHelper.insertReview(title, bookAuthor, userRemarks);
        if (isInserted) {
            Toast.makeText(d_add_review.this, "Review added successfully!", Toast.LENGTH_SHORT).show();
            clearFields();
        } else {
            Toast.makeText(d_add_review.this, "Failed to add review. Try again.", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        bookTitle.setText("");
        author.setText("");
        remarks.setText("");
    }
}
