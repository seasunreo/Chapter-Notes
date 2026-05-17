package com.example.finals_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database properties
    private static final String DATABASE_NAME = "UserDatabase.db";
    private static final int DATABASE_VERSION = 2;

    // Users table
    public static final String TABLE_USERS = "Users";
    public static final String COL_ID = "ID";
    public static final String COL_EMAIL = "Email";
    public static final String COL_PASSWORD = "Password";

    // Reviews table
    public static final String TABLE_REVIEWS = "Reviews";
    public static final String COL_REVIEW_ID = "ReviewID";
    public static final String COL_TITLE = "Title";
    public static final String COL_AUTHOR = "Author";
    public static final String COL_REMARKS = "Remarks";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Users table
        String createUsersTable = "CREATE TABLE " + TABLE_USERS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_EMAIL + " TEXT UNIQUE, " +
                COL_PASSWORD + " TEXT)";
        db.execSQL(createUsersTable);

        // Create Reviews table
        String createReviewsTable = "CREATE TABLE " + TABLE_REVIEWS + " (" +
                COL_REVIEW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TITLE + " TEXT, " +
                COL_AUTHOR + " TEXT, " +
                COL_REMARKS + " TEXT)";
        db.execSQL(createReviewsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop existing tables and recreate
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REVIEWS);
        onCreate(db);
    }

    // Insert a new user
    public boolean insertUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Check if the email already exists
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COL_EMAIL + " = ?", new String[]{email});
        if (cursor.getCount() > 0) {
            cursor.close();
            return false; // Email already exists
        }
        cursor.close();

        // Insert new user
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_PASSWORD, password);

        long result = db.insert(TABLE_USERS, null, contentValues);
        return result != -1; // Return true if the insertion was successful
    }

    // Check if user exists
    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Query to check email and password
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COL_EMAIL + " = ? AND " + COL_PASSWORD + " = ?", new String[]{email, password});
        boolean userExists = cursor.getCount() > 0;
        cursor.close();

        return userExists;
    }

    // Insert a new review
    public boolean insertReview(String title, String author, String remarks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TITLE, title);
        contentValues.put(COL_AUTHOR, author);
        contentValues.put(COL_REMARKS, remarks);

        long result = db.insert(TABLE_REVIEWS, null, contentValues);
        return result != -1;
    }

    // Update a review by title
    public boolean updateReview(String title, String newAuthor, String newRemarks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_AUTHOR, newAuthor);
        contentValues.put(COL_REMARKS, newRemarks);

        int rowsUpdated = db.update(TABLE_REVIEWS, contentValues, COL_TITLE + " = ?", new String[]{title});
        return rowsUpdated > 0;
    }

    // Fetch a review by title
    public Cursor fetchReviewByTitle(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_REVIEWS + " WHERE " + COL_TITLE + " = ?", new String[]{title});
    }

    // Delete a review by title
    public boolean deleteReview(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete(TABLE_REVIEWS, COL_TITLE + " = ?", new String[]{title});
        return rowsDeleted > 0;
    }
}
