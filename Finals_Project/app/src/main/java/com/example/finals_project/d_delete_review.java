package com.example.finals_project;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import com.example.finals_project.databinding.DDeleteReviewBinding; // Ensure correct binding class

public class d_delete_review extends AppCompatActivity {

    private DDeleteReviewBinding binding; // Binding for XML layout
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DDeleteReviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize DatabaseHelper
        db = new DatabaseHelper(this);

        // Set onClickListener for delete button
        binding.deleteButton.setOnClickListener(v -> {
            String bookTitle = binding.deletebookTitle.getText().toString(); // Access EditText
            if (!bookTitle.isEmpty()) {
                // Call method to delete the review
                deleteReview(bookTitle);
            } else {
                Toast.makeText(d_delete_review.this, "Please enter the book title", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to delete a review from SQLite based on book title
    private void deleteReview(String bookTitle) {
        SQLiteDatabase database = db.getWritableDatabase();

        // Delete review where the title matches
        int rowsDeleted = database.delete(DatabaseHelper.TABLE_REVIEWS, DatabaseHelper.COL_TITLE + " = ?", new String[]{bookTitle});

        if (rowsDeleted > 0) {
            binding.deletebookTitle.getText().clear(); // Clear the input field
            Toast.makeText(d_delete_review.this, "Review deleted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(d_delete_review.this, "No review found with the given title", Toast.LENGTH_SHORT).show();
        }
    }
}
