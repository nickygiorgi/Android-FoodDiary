package com.github.nickygiorgi.fooddiary.dal.ActiveRecords;

import com.github.nickygiorgi.fooddiary.dal.StaticData.Feeling;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Page {
    private long id;
    private Date date;
    private Food food;
    private Feeling feeling;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public String getFormattedDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Food getFood() {
        return this.food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Feeling getFeeling() {
        return feeling;
    }

    public void setFeeling(Feeling feeling) {
        this.feeling = feeling;
    }

    @Override
    public String toString() {
        if (this.food == null || this.date == null) {
            return "";
        }
        return getFormattedDate() + " | " + this.food.getDescription();
    }
}
