package com.github.nickygiorgi.fooddiary.dal.ActiveRecords;

public class Feeling {
    private long id;
    private String description;

    public long getId() { return id; }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { description = description; }

    @Override
    public String toString() {
        return description;
    }
}