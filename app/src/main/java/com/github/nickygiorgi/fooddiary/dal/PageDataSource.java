package com.github.nickygiorgi.fooddiary.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

import com.github.nickygiorgi.fooddiary.dal.ActiveRecords.Page;
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

    static Page createPage(SQLiteDatabase database, long foodId, long feelingId) {
        ContentValues values = new ContentValues();
        values.put(contract.DAT_Pages.COLUMN_FOOD_ID, foodId);
        Date currentDate = new Date(System.currentTimeMillis());
        values.put(contract.DAT_Pages.COLUMN_DATE, sqlHelper.persistDate(currentDate));
        values.put(contract.DAT_Pages.COLUMN_FEELING_ID, feelingId);
        long insertId = database.insert(contract.DAT_Pages.TABLE_NAME, null,
                values);
        Cursor cursor = database.query(contract.DAT_Pages.TABLE_NAME,
                allColumns, contract.DAT_Pages._ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Page newPage = cursorToPage(cursor);
        cursor.close();
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

        Cursor cursor = database.query(contract.DAT_Pages.TABLE_NAME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Page page = cursorToPage(cursor);
            pages.add(page);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return pages;
    }

    private static Page cursorToPage(Cursor cursor) {
        Page page = new Page();
        page.setId(cursor.getLong(0));
        page.setDate(sqlHelper.loadDate(cursor.getLong(1)));
        page.setFoodId(cursor.getLong(2));
        page.setFeelingId(cursor.getInt(3));
        return page;
    }
}
