package com.github.nickygiorgi.fooddiary.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

import com.github.nickygiorgi.fooddiary.dal.ActiveRecords.Food;
import com.github.nickygiorgi.fooddiary.dal.ActiveRecords.Page;
import com.github.nickygiorgi.fooddiary.dal.StaticData.Feeling;
import com.github.nickygiorgi.fooddiary.dal.StaticData.Feelings;
import com.github.nickygiorgi.fooddiary.db.contract;
import com.github.nickygiorgi.fooddiary.db.sqlHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class PageDataSource {

    private static String[] allColumns = {
            contract.DAT_Pages._ID,
            contract.DAT_Pages.COLUMN_DATE,
            contract.DAT_Pages.COLUMN_FOOD_ID,
            contract.DAT_Pages.COLUMN_FEELING_ID };

    static Page insertPage(SQLiteDatabase database, long foodId, int feelingId) {
        ContentValues values = new ContentValues();
        values.put(contract.DAT_Pages.COLUMN_FOOD_ID, foodId);
        Date currentDate = new Date(System.currentTimeMillis());
        values.put(contract.DAT_Pages.COLUMN_DATE, sqlHelper.persistDate(currentDate));
        values.put(contract.DAT_Pages.COLUMN_FEELING_ID, feelingId);
        long insertId = database.insert(contract.DAT_Pages.TABLE_NAME, null,
                values);
        Page newPage = new Page();
        newPage.setId(insertId);
        newPage.setDate(currentDate);
        newPage.setFeeling(Feelings.MapById(feelingId));
        return newPage;
    }

    static void deletePage(SQLiteDatabase database, Page page) {
        long id = page.getId();
        System.out.println("Page deleted with id: " + id);
        database.delete(contract.DAT_Pages.TABLE_NAME, contract.DAT_Pages._ID
                + " = " + id, null);
    }

    static List<Page> getAllPages(SQLiteDatabase database) {
        List<Page> pages = new ArrayList<Page>();

        final String selectAllPagesSql = getSelectAllPagesSql();
        Cursor cursor = database.rawQuery(selectAllPagesSql, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Page page = selectAllCursorToPage(cursor);
            pages.add(page);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return pages;
    }

    private static String getSelectAllPagesSql() {
        return "select " +
                "page." + contract.DAT_Pages._ID + " as pageId, "
                + contract.DAT_Pages.COLUMN_DATE + ", "
                + contract.DAT_Pages.COLUMN_FEELING_ID + ", "
                + "food." + contract.X_Foods._ID + " as foodId, "
                + contract.X_Foods.COLUMN_DESC + " from "
                + contract.DAT_Pages.TABLE_NAME + " page "
                + " INNER JOIN "
                + contract.X_Foods.TABLE_NAME + " food "
                + " on page." + contract.DAT_Pages.COLUMN_FOOD_ID + " = foodId"
                + " ORDER BY page." + contract.DAT_Pages.COLUMN_DATE + " DESC";
    }

    private static Page selectAllCursorToPage(Cursor cursor) {
        Page page = new Page();
        page.setId(cursor.getLong(0));
        page.setDate(sqlHelper.loadDate(cursor.getLong(1)));
        int feelingId = cursor.getInt(2);
        page.setFeeling(Feelings.MapById(feelingId));
        int foodId = cursor.getInt(3);
        String foodDescription = cursor.getString(4);
        Food food = new Food();
        food.setId(foodId);
        food.setDescription(foodDescription);
        page.setFood(food);
        return page;
    }
}
