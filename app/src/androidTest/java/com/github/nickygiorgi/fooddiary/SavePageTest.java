package com.github.nickygiorgi.fooddiary;


import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SavePageTest {

    TestUtilities testUtilities = new TestUtilities();
    final String food = "food1";

    @Rule
    public IntentsTestRule<FoodDiary> foodDiaryActivityTestRule =
            new IntentsTestRule<>(FoodDiary.class);

    @Test
    public void SavePageTakesYouToHistory() {

        onView(withId(R.id.foodEditText))
                .perform(typeText(food));
        testUtilities.chooseFeeling(R.id.goodBtn);

        onView(withId(R.id.saveBtn))
                .perform(click());

        AssertHistory();
    }

    public void AssertHistory() {
        intended(hasComponent(ListHistoryActivity.class.getName()));
    }
}
