package com.github.nickygiorgi.fooddiary.db;

import android.database.sqlite.SQLiteDatabase;

public class DAT_Meds {

    static void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + contract.DAT_Meds.TABLE_NAME
                + "(" + contract.DAT_Meds._ID + " INTEGER PRIMARY KEY,"
                + contract.DAT_Meds.COLUMN_MED_ID + " INTEGER,"
                + contract.DAT_Meds.COLUMN_PAGE_ID + " INTEGER);");
    }

}
