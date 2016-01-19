package com.github.nickygiorgi.fooddiary.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.github.nickygiorgi.fooddiary.dal.ActiveRecords.Feeling;
import com.github.nickygiorgi.fooddiary.db.contract;

import java.util.ArrayList;
import java.util.List;

class FeelingDataSource {

    private static String[] allColumns = {
            contract.X_Feelings._ID,
            contract.X_Feelings.COLUMN_DESC };

    static List<Feeling> getAllFeelings(SQLiteDatabase database) {
        List<Feeling> feelings = new ArrayList<Feeling>();

        Cursor cursor = database.query(contract.X_Feelings.TABLE_NAME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Feeling feeling = cursorToFeeling(cursor);
            feelings.add(feeling);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return feelings;
    }

    static Feeling getAFeeling(SQLiteDatabase database, long feelingId) {
        Feeling feeling = new Feeling();

        String[] whereArgs = new String[] { String.valueOf(feelingId) };

        Cursor cursor = database.query(contract.X_Feelings.TABLE_NAME,
                allColumns, contract.X_Feelings._ID + " = ?", whereArgs, null, null, null);

        cursor.moveToFirst();
        feeling = cursorToFeeling(cursor);
        cursor.close();

        return feeling;
    }

    private static Feeling cursorToFeeling(Cursor cursor) {
        Feeling feeling = new Feeling();
        feeling.setId(cursor.getLong(0));
        feeling.setDescription(cursor.getString(1));
        return feeling;
    }
}
