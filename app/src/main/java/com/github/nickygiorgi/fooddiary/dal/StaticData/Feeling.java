package com.github.nickygiorgi.fooddiary.dal.StaticData;

public class Feeling {
    public int Id;
    public int descriptionAsStaticResource;
    public int colorAsStaticResource;

    public Feeling(int id, int desc, int color) {
        this.Id = id;
        this.descriptionAsStaticResource = desc;
        this.colorAsStaticResource = color;
    }
}
