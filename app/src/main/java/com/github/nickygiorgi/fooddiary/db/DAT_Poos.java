package com.github.nickygiorgi.fooddiary.db;

import android.database.sqlite.SQLiteDatabase;

public class DAT_Poos {

    static void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + contract.DAT_Poos.TABLE_NAME
                + "(" + contract.DAT_Poos._ID + " INTEGER PRIMARY KEY,"
                + contract.DAT_Poos.COLUMN_CONSISTENCY_ID + " INTEGER,"
                + contract.DAT_Poos.COLUMN_IS_BLOOD + " BOOL,"
                + contract.DAT_Poos.COLUMN_IS_MUCUS + " BOOL,"
                + contract.DAT_Poos.COLUMN_PAGE_ID + " INTEGER);");
    }
}
