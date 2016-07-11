package com.github.nickygiorgi.fooddiary;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class TestUtilities {

    public void chooseFeeling(int feelingResourceId) {
        onView(withId(R.id.feelingsChoiceBtn))
                .perform(click());
        onView(withId(feelingResourceId))
                .perform(click());
    }

}
