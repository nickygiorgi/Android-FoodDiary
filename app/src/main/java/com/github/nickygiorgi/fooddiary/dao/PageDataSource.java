package com.github.nickygiorgi.fooddiary.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import android.content.ContentValues;

import com.github.nickygiorgi.fooddiary.db.contract;
import com.github.nickygiorgi.fooddiary.db.sqlHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PageDataSource {

    // Database fields
    private SQLiteDatabase database;
    private sqlHelper dbHelper;
    private String[] allColumns = {
            contract.DAT_Pages._ID,
            contract.DAT_Pages.COLUMN_DATE,
            contract.DAT_Pages.COLUMN_FOOD_ID,
            contract.DAT_Pages.COLUMN_FEELING_ID };

    public PageDataSource(Context context) {
        dbHelper = new sqlHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Page createPage(long food_id, long feeling_id) {
        ContentValues values = new ContentValues();
        values.put(contract.DAT_Pages.COLUMN_FOOD_ID, food_id);
        Date currentDate = new Date(System.currentTimeMillis());
        values.put(contract.DAT_Pages.COLUMN_DATE, sqlHelper.persistDate(currentDate));
        values.put(contract.DAT_Pages.COLUMN_FEELING_ID, feeling_id);
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

    public void deletePage(Page page) {
        long id = page.getId();
        System.out.println("Page deleted with id: " + id);
        database.delete(contract.DAT_Pages.TABLE_NAME, contract.DAT_Pages._ID
                + " = " + id, null);
    }

    public List<Page> getAllPages() {
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

    private Page cursorToPage(Cursor cursor) {
        Page page = new Page();
        page.setId(cursor.getLong(0));
        page.setDate(sqlHelper.loadDate(cursor, 1));
        page.setFood(cursor.getLong(2));
        page.setFeeling_id(cursor.getLong(3));
        return page;
    }
}
