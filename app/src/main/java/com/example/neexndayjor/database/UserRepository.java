package com.example.neexndayjor.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.neexndayjor.models.User;
import com.example.neexndayjor.utils.PasswordUtils;

public class UserRepository {
    private final DatabaseHelper dbHelper;

    public UserRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public boolean insertUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_USER_NAME, user.getName());
        values.put(DatabaseHelper.COL_USER_EMAIL, user.getEmail());
        values.put(DatabaseHelper.COL_USER_PASSWORD, user.getPassword());  // Store hashed password
        long result = db.insert(DatabaseHelper.TABLE_USER, null, values);
        db.close();
        return result != -1;  // Return true if insertion was successful
    }

    public User getUserByEmail(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_USER, null,
                DatabaseHelper.COL_USER_EMAIL + "=?", new String[]{email},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            User user = new User(
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_USER_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_USER_EMAIL)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_USER_PASSWORD))
            );
            cursor.close();
            db.close();
            return user;
        }

        db.close();
        return null;  // Return null if no user is found
    }
}
