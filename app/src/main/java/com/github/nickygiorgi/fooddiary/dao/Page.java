package com.github.nickygiorgi.fooddiary.dao;

import java.util.Date;

public class Page {
    private long id;
    private Date date;
    private String food;
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

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public long getFeeling_id() {
        return feelingId;
    }

    public void setFeeling_id(long feelingId) {
        this.feelingId = feelingId;
    }

    @Override
    public String toString() {
        return date.toString() + " - today I had " + food + " and it felt " + feelingId; //feeling object?
    }
}
