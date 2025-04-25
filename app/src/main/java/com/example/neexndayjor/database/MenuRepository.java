package com.example.neexndayjor.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.neexndayjor.models.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuRepository {
    private final DatabaseHelper dbHelper;

    public MenuRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void insertMenuItem(MenuItem item) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_NAME, item.getName());
        values.put(DatabaseHelper.COL_PRICE, item.getPrice());
        values.put(DatabaseHelper.COL_DESC, item.getDescription());
        values.put(DatabaseHelper.COL_IMAGE, item.getImageResId());
        values.put(DatabaseHelper.COL_RATING, item.getRating());
        values.put(DatabaseHelper.COL_CATEGORY, item.getCategory());
        db.insert(DatabaseHelper.TABLE_MENU, null, values);
        db.close();
    }

    public List<MenuItem> getMenuItemsByCategory(String category) {
        List<MenuItem> items = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor;
        if (category.equalsIgnoreCase("All")) {
            cursor = db.query(DatabaseHelper.TABLE_MENU, null, null, null, null, null, null);
        } else {
            cursor = db.query(DatabaseHelper.TABLE_MENU, null,
                    DatabaseHelper.COL_CATEGORY + "=?", new String[]{category},
                    null, null, null);
        }

        if (cursor.moveToFirst()) {
            do {
                MenuItem item = new MenuItem(
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_PRICE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_DESC)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_IMAGE)),
                        cursor.getFloat(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_RATING)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_CATEGORY))
                );
                items.add(item);
            } while (cursor.moveToNext());
            cursor.close();
        }

        db.close();
        return items;
    }

    public void deleteAll() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_MENU, null, null);
        db.close();
    }
}
