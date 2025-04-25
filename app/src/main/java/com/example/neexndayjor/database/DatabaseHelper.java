package com.example.neexndayjor.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "neexndayjor.db";
    public static final int DATABASE_VERSION = 1;

    // Menu Table
    public static final String TABLE_MENU = "menu";
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "name";
    public static final String COL_PRICE = "price";
    public static final String COL_DESC = "description";
    public static final String COL_IMAGE = "image";
    public static final String COL_RATING = "rating";
    public static final String COL_CATEGORY = "category";

    // User Table
    public static final String TABLE_USER = "users";
    public static final String COL_USER_ID = "_id";
    public static final String COL_USER_NAME = "name";
    public static final String COL_USER_EMAIL = "email";
    public static final String COL_USER_PASSWORD = "password";
    public static final String COL_USER_CREATED_AT = "created_at";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Menu Table
        String CREATE_MENU_TABLE = "CREATE TABLE " + TABLE_MENU + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                COL_PRICE + " TEXT, " +
                COL_DESC + " TEXT, " +
                COL_IMAGE + " INTEGER, " +
                COL_RATING + " REAL, " +
                COL_CATEGORY + " TEXT)";

        // Create User Table
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + " (" +
                COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USER_NAME + " TEXT, " +
                COL_USER_EMAIL + " TEXT, " +
                COL_USER_PASSWORD + " TEXT, " +
                COL_USER_CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

        db.execSQL(CREATE_MENU_TABLE);
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }
}
