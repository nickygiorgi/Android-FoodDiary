package com.github.nickygiorgi.fooddiary.dal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;

import com.github.nickygiorgi.fooddiary.dal.ActiveRecords.Food;
import com.github.nickygiorgi.fooddiary.dal.ActiveRecords.Page;
import com.github.nickygiorgi.fooddiary.dal.StaticData.Feeling;
import com.github.nickygiorgi.fooddiary.db.sqlHelper;

import java.util.List;

public class FoodDiaryDataSource {

    // Database fields
    private SQLiteDatabase database;
    private sqlHelper dbHelper;

    public FoodDiaryDataSource(Context context) {
        dbHelper = new sqlHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Page insertPage(long foodId, int feelingId) {
        return PageDataSource.insertPage(database, foodId, feelingId);
    }

    public List<Page> getAllPages() {
        return PageDataSource.getAllPages(database);
    }

    public Food insertXFood(String description) {
        return FoodDataSource.insertXFood(database, description);
    }

    public Food getXFood(long id) {
        return FoodDataSource.getXFood(database, id);
    }

    public Food getXFoodByDescription(String desc) {
        return FoodDataSource.getXFoodByDescription(database, desc);
    }

}