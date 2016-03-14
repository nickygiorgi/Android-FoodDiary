package com.github.nickygiorgi.fooddiary.dal.StaticData;

import com.github.nickygiorgi.fooddiary.R;

public class Feelings {

    public static final Feeling BAD = new Feeling(0, R.string.home_feeling_presenter_bad, R.color.red);
    public static final Feeling OK = new Feeling(1, R.string.home_feeling_presenter_ok, R.color.amber);
    public static final Feeling GOOD = new Feeling(2, R.string.home_feeling_presenter_good, R.color.green);

    public static Feeling MapById(int id) {
        switch (id) {
            case 0:
                return BAD;
            case 1:
                return OK;
            case 2:
                return GOOD;
            default:
                throw new TypeNotPresentException("Feeling not recognised", null);
        }
    }
}