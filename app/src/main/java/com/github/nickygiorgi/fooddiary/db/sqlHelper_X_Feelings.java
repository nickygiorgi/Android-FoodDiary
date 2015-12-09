package com.github.nickygiorgi.fooddiary.db;

import android.database.sqlite.SQLiteDatabase;

public class sqlHelper_X_Feelings {

    static void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + contract.X_Feelings.TABLE_NAME
                + "(" + contract.X_Feelings.COLUMN_ID + " INTEGER PRIMARY KEY,"
                + contract.X_Feelings.COLUMN_DESC + " VARCHAR);");
    }
}
