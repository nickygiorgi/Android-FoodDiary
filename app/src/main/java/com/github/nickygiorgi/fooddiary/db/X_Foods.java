package com.github.nickygiorgi.fooddiary.db;

import android.database.sqlite.SQLiteDatabase;

public class X_Foods {

    static void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + contract.X_Foods.TABLE_NAME
                + "(" + contract.X_Foods._ID + " INTEGER PRIMARY KEY,"
                + contract.X_Foods.COLUMN_DESC + " VARCHAR);");
    }
}
