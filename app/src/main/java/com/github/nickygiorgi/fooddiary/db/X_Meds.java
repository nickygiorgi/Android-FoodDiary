package com.github.nickygiorgi.fooddiary.db;

import android.database.sqlite.SQLiteDatabase;

public class X_Meds {

    static void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + contract.X_Meds.TABLE_NAME
                + "(" + contract.X_Meds._ID + " INTEGER PRIMARY KEY,"
                + contract.X_Meds.COLUMN_DESC + " VARCHAR);");
    }

}
