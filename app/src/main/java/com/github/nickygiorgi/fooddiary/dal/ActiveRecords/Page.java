package com.github.nickygiorgi.fooddiary.dal.ActiveRecords;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Page {
    private long id;
    private Date date;
    private long foodId; //todo: reference to object food?
    private int feelingId;

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

    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }

    public int getFeelingId() {
        return feelingId;
    }

    public void setFeelingId(int feelingId) {
        this.feelingId = feelingId;
    }

    @Override
    public String toString() {
        return getFormattedDate() + " | " + foodId;
    }
}
