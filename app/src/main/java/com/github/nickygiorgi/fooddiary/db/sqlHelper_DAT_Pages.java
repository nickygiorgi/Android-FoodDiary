package com.github.nickygiorgi.fooddiary.db;

import android.database.sqlite.SQLiteDatabase;

public class sqlHelper_DAT_Pages {

    static void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + contract.DAT_Pages.TABLE_NAME
                + "(" + contract.DAT_Pages.COLUMN_ID + " INTEGER PRIMARY KEY,"
                + contract.DAT_Pages.COLUMN_DATE + " DATETIME,"
                + contract.DAT_Pages.COLUMN_FOOD + " VARCHAR,"
                + contract.DAT_Pages.COLUMN_FEELING_ID + " INTEGER);");
    }
}
