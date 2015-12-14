package com.github.nickygiorgi.fooddiary.db;

import android.database.sqlite.SQLiteDatabase;

public class X_Feelings {

    static void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + contract.X_Feelings.TABLE_NAME
                + "(" + contract.X_Feelings._ID + " INTEGER PRIMARY KEY,"
                + contract.X_Feelings.COLUMN_DESC + " VARCHAR);");
    }

    static void fillTable(SQLiteDatabase db) {
        db.execSQL("INSERT INTO " + contract.X_Feelings.TABLE_NAME
                + " values (" + contract.X_Feelings.RECORD_BAD_ID
                + ", \"" + contract.X_Feelings.RECORD_BAD_DESC + "\");");
        db.execSQL("INSERT INTO " + contract.X_Feelings.TABLE_NAME
                + " values (" + contract.X_Feelings.RECORD_OK_ID
                + ", \"" + contract.X_Feelings.RECORD_OK_DESC + "\");");
        db.execSQL("INSERT INTO " + contract.X_Feelings.TABLE_NAME
                + " values (" + contract.X_Feelings.RECORD_GOOD_ID
                + ", \"" + contract.X_Feelings.RECORD_GOOD_DESC + "\");");

    }
}
