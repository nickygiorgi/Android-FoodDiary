package com.github.nickygiorgi.fooddiary.dal;

import java.util.Date;

public class Page {
    private long id;
    private Date date;
    private long foodId; //todo: reference to object food?
    private long feelingId; //todo: reference to object feeling?

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getFood() {
        return foodId;
    }

    public void setFood(long foodId) {
        this.foodId = foodId;
    }

    public long getFeeling_id() {
        return feelingId;
    }

    public void setFeeling_id(long feelingId) {
        this.feelingId = feelingId;
    }

    @Override
    public String toString() {
        return date.toString() + " - today I had " + foodId + " and it felt " + feelingId; //feeling object?
    }
}
