package com.github.nickygiorgi.fooddiary.dal.StaticData;

public class Feelings {

    public static final Feeling BAD = new Feeling(0, "bad", "red");
    public static final Feeling OK = new Feeling(1, "ok", "amber");
    public static final Feeling GOOD = new Feeling(2, "good", "green");

    public static final Feeling UNKNOWN = new Feeling(-1, "N.A.", "");

    public static Feeling MapById(int id) {
        switch (id) {
            case 0:
                return BAD;
            case 1:
                return OK;
            case 2:
                return GOOD;
            default:
                return UNKNOWN;
        }
    }
}