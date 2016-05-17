package com.github.nickygiorgi.fooddiary.dal.ActiveRecords;

public class Food {
    private long id;
    private String description;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
