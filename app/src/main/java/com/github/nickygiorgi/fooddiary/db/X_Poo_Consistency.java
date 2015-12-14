package com.github.nickygiorgi.fooddiary.db;

import android.database.sqlite.SQLiteDatabase;

public class X_Poo_Consistency {

    static void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + contract.X_Poo_Consistency.TABLE_NAME
                + "(" + contract.X_Poo_Consistency._ID + " INTEGER PRIMARY KEY,"
                + contract.X_Poo_Consistency.COLUMN_DESC + " VARCHAR);");
    }

    static void fillTable(SQLiteDatabase db) {
        db.execSQL("INSERT INTO " + contract.X_Poo_Consistency.TABLE_NAME
                + " values (" + contract.X_Poo_Consistency.RECORD_HARD_ID
                + ", \"" + contract.X_Poo_Consistency.RECORD_HARD_DESC + "\");");
        db.execSQL("INSERT INTO " + contract.X_Poo_Consistency.TABLE_NAME
                + " values (" + contract.X_Poo_Consistency.RECORD_SOFT_ID
                + ", \"" + contract.X_Poo_Consistency.RECORD_SOFT_DESC + "\");");
        db.execSQL("INSERT INTO " + contract.X_Poo_Consistency.TABLE_NAME
                + " values (" + contract.X_Poo_Consistency.RECORD_RUNNY_ID
                + ", \"" + contract.X_Poo_Consistency.RECORD_RUNNY_DESC + "\");");
    }

}
