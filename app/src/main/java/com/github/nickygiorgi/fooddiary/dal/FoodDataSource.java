package com.github.nickygiorgi.fooddiary.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.github.nickygiorgi.fooddiary.dal.ActiveRecords.Food;
import com.github.nickygiorgi.fooddiary.db.contract;

class FoodDataSource {
    private static String[] allXColumns = {
            contract.X_Foods._ID,
            contract.X_Foods.COLUMN_DESC };

    static Food insertXFood(SQLiteDatabase database, String desc) {
        Food oldFood = FoodDataSource.getXFoodByDescription(database, desc);
        if (oldFood != null) {
            return oldFood;
        }

        ContentValues values = new ContentValues();
        values.put(contract.X_Foods.COLUMN_DESC, desc);
        long insertId = database.insert(contract.X_Foods.TABLE_NAME, null,
                values);
        Cursor cursor = database.query(contract.X_Foods.TABLE_NAME,
                allXColumns, contract.X_Foods._ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Food newFood = cursorToXFood(cursor);
        cursor.close();
        return newFood;
    }

    static Food getXFood(SQLiteDatabase database, long foodId) {
        final String where = contract.X_Foods._ID + " = ? ";
        final String[] id = new String[] {
                Long.toString(foodId)
        };
        return FoodDataSource.getXFoodWithWhereClause(database, where, id);
    }

    static Food getXFoodByDescription(SQLiteDatabase database, String description) {
        final String where = contract.X_Foods.COLUMN_DESC + " = ? ";
        final String[] desc = new String[] {
                description
        };
        return FoodDataSource.getXFoodWithWhereClause(database, where, desc);
    }

    private static Food getXFoodWithWhereClause(SQLiteDatabase database, String where, String[] selectionArgs) {
        Cursor cursor = database.query(contract.X_Foods.TABLE_NAME, allXColumns, where, selectionArgs, null, null, null);
        Food food = null;
        if (cursor.moveToFirst()) {
             food = cursorToXFood(cursor);
        }
        cursor.close();
        return food;
    }

    private static Food cursorToXFood(Cursor cursor) {
        Food food = new Food();
        food.setId(cursor.getLong(0));
        food.setDescription(cursor.getString(1));
        return food;
    }
}
