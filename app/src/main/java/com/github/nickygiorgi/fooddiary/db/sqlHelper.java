package com.github.nickygiorgi.fooddiary.db;

import java.util.Date;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class sqlHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FoodDiary.db";

    public sqlHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        DAT_Pages.createTable(db);
        X_Feelings.createTable(db);
        X_Feelings.fillTable(db);
        X_Poo_Consistency.createTable(db);
        X_Poo_Consistency.fillTable(db);
        DAT_Poos.createTable(db);
        X_Foods.createTable(db);
        DAT_Foods.createTable(db);
        X_Meds.createTable(db);
        DAT_Meds.createTable(db);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + contract.DAT_Pages.TABLE_NAME + ";");
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public static Long persistDate(Date date) {
        if (date != null) {
            return date.getTime();
        }
        return null;
    }

    public static Date loadDate(long dateRecord) {
        if (dateRecord <= 0) {
            return null;
        }
        return new Date(dateRecord);
    }

}
