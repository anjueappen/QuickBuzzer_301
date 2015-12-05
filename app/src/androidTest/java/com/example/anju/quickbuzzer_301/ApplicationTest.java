package com.example.anju.quickbuzzer_301;

import android.app.Application;
import android.test.ApplicationTestCase;

import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
    @Test
    public void validateTimerButtonPress() {
        onView(withId(R.id.single_player_button)).perform(click());
        /*onView(withText("When the button appears,\\n\n" +
                "        click it quickly!\\n\n" +
                "        \\n\n" +
                "        Close this dialog to begin.")).inRoot(isDisplayed(Dialog.class)).check(isDisplayed());*/
    }
}