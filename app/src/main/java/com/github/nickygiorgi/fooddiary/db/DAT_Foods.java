package com.github.nickygiorgi.fooddiary.db;

import android.database.sqlite.SQLiteDatabase;

public class DAT_Foods {

    static void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + contract.DAT_Foods.TABLE_NAME
                + "(" + contract.DAT_Foods._ID + " INTEGER PRIMARY KEY,"
                + contract.DAT_Foods.COLUMN_FOOD_ID + " INTEGER, "
                + contract.DAT_Foods.COLUMN_PAGE_ID + " INTEGER);");
    }
}
