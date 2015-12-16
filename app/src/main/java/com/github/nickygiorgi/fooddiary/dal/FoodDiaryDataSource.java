package com.github.nickygiorgi.fooddiary.dal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
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

    public void createPage(long foodId, long feelingId) {
        PageDataSource.createPage(database, foodId, feelingId);
    }

    public List<Feeling> getAllFeelings() {
        return FeelingDataSource.getAllFeelings(database);
    }

    public Feeling getAFeeling(long feelingId) {
        return FeelingDataSource.getAFeeling(database, feelingId);
    }

}