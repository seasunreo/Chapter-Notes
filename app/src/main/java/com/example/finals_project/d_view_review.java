package com.example.finals_project;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class d_view_review extends AppCompatActivity {

    // UI Elements
    private EditText searchBookTitle;
    private Button searchButton;
    private TextView readTitle, readAuthor, readRemarks;

    // DatabaseHelper instance
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_view_review); // Ensure this matches your XML layout file name

        // Initialize UI elements
        searchBookTitle = findViewById(R.id.searchPhone); // Assuming the ID matches your XML
        searchButton = findViewById(R.id.searchButton);
        readTitle = findViewById(R.id.readName); // Assuming the ID matches your XML
        readAuthor = findViewById(R.id.readOperator); // Assuming the ID matches your XML
        readRemarks = findViewById(R.id.readLocation); // Assuming the ID matches your XML

        // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Set up search button click listener
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookTitle = searchBookTitle.getText().toString().trim();
                if (!TextUtils.isEmpty(bookTitle)) {
                    fetchReview(bookTitle);
                } else {
                    Toast.makeText(d_view_review.this, "Please enter a book title", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fetchReview(String bookTitle) {
        // Query the database for the given book title
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
                "SELECT * FROM Reviews WHERE Title = ?", new String[]{bookTitle});

        if (cursor.moveToFirst()) {
            try {
                // Fetch review details using getColumnIndexOrThrow
                String title = cursor.getString(cursor.getColumnIndexOrThrow("Title"));
                String author = cursor.getString(cursor.getColumnIndexOrThrow("Author"));
                String remarks = cursor.getString(cursor.getColumnIndexOrThrow("Remarks"));

                // Display the fetched data
                readTitle.setText(title);
                readAuthor.setText(author);
                readRemarks.setText(remarks);

                Toast.makeText(this, "Review found!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "Error fetching data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No reviews found for the entered title", Toast.LENGTH_SHORT).show();
            clearFields();
        }
        cursor.close();
    }

    private void clearFields() {
        readTitle.setText("");
        readAuthor.setText("");
        readRemarks.setText("");
    }
}
