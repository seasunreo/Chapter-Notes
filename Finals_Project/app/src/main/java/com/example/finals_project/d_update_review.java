package com.example.finals_project;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class d_update_review extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText updateBookTitle, updateAuthor, updateRemarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_update_review); // Use your updated XML layout

        // Initialize views
        updateBookTitle = findViewById(R.id.update_booktitle);
        updateAuthor = findViewById(R.id.update_author);
        updateRemarks = findViewById(R.id.update_remarks);

        Button updateButton = findViewById(R.id.updateButton);

        // Initialize the DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Set the listener for the update button
        updateButton.setOnClickListener(v -> {
            String bookTitle = updateBookTitle.getText().toString();
            String author = updateAuthor.getText().toString();
            String remarks = updateRemarks.getText().toString();

            if (!bookTitle.isEmpty() && !author.isEmpty() && !remarks.isEmpty()) {
                updateReviewData(bookTitle, author, remarks);
            } else {
                Toast.makeText(d_update_review.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateReviewData(String bookTitle, String author, String remarks) {
        boolean isUpdated = databaseHelper.updateReview(bookTitle, author, remarks);

        if (isUpdated) {
            // Clear the input fields
            updateBookTitle.setText("");
            updateAuthor.setText("");
            updateRemarks.setText("");

            Toast.makeText(this, "Review Updated Successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to Update Review", Toast.LENGTH_SHORT).show();
        }
    }
}
