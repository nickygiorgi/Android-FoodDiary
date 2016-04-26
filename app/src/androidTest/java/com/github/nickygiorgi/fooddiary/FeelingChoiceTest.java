package com.github.nickygiorgi.fooddiary;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FeelingChoiceTest {

    @Rule
    public ActivityTestRule<FoodDiary> foodDiaryActivityTestRule =
            new ActivityTestRule<>(FoodDiary.class);

    @Test
    public void chooseGoodFeeling_feelingChoiceActivity() {
        chooseFeeling(R.id.goodBtn);
        assertCorrectFeelingIsDisplayed(R.string.home_feeling_presenter_good);
    }

    @Test
    public void chooseOkFeeling_feelingChoiceActivity() {
        chooseFeeling(R.id.okBtn);
        assertCorrectFeelingIsDisplayed(R.string.home_feeling_presenter_ok);
    }

    @Test
    public void chooseBadFeeling_feelingChoiceActivity() {
        chooseFeeling(R.id.badBtn);
        assertCorrectFeelingIsDisplayed(R.string.home_feeling_presenter_bad);
    }

    public void chooseFeeling(int feelingResourceId) {
        onView(withId(R.id.feelingsChoiceBtn))
                .perform(click());
        onView(withId(feelingResourceId))
                .perform(click());
    }

    public void assertCorrectFeelingIsDisplayed(int homeFeelingStringResourceId) {
        onView(withId(R.id.feelingsChoiceBtn))
                .check(matches(withText(homeFeelingStringResourceId)));
    }
}
