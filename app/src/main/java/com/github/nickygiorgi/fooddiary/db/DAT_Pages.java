package com.github.nickygiorgi.fooddiary.db;

import android.database.sqlite.SQLiteDatabase;

public class DAT_Pages {

    static void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + contract.DAT_Pages.TABLE_NAME
                + "(" + contract.DAT_Pages._ID + " INTEGER PRIMARY KEY,"
                + contract.DAT_Pages.COLUMN_DATE + " INTEGER,"
                + contract.DAT_Pages.COLUMN_FOOD_ID + " INTEGER,"
                + contract.DAT_Pages.COLUMN_FEELING_ID + " INTEGER);");
    }
}
