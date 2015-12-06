package com.example.anju.quickbuzzer_301;

/**
 * Created by anju on 04/12/15.
 */
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.*;

public class ModeSelectionUITest
        extends ActivityInstrumentationTestCase2<ModeSelectionActivity> {

    private ModeSelectionActivity mActivity;

    public ModeSelectionUITest() {
        super(ModeSelectionActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mActivity = getActivity();
    }


    public void testTimerDialogOnButtonPress() {
        onView(withId(R.id.single_player_button)).perform(click());
        withText("When the button appears,\\n\n" +
                "        click it quickly!\\n\n" +
                "        \\n\n" +
                "        Close this dialog to begin.").matches(isDisplayed());
    }
    public void testTimerActivityErrorMessage() {
        onView(withId(R.id.single_player_button)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.button_container)).perform(click());
        withText("Hey! No clicking early!").matches(isDisplayed());
    }
    public void testTimerActivityButtonPress() {
        onView(withId(R.id.single_player_button)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        SystemClock.sleep(5000);
        onView(withId(R.id.game_button)).perform(click());
        withText("Reaction Time:").matches(isDisplayed());
    }

    public void testGameShowButtonPress2Players() {
        onView(withId(R.id.multiplayer_button)).perform(click());
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.button1)).check(matches(isDisplayed()));
        onView(withId(R.id.button2)).check(matches(isDisplayed()));
    }
    public void testGameShowButtonPress3Players() {
        onView(withId(R.id.multiplayer_button)).perform(click());
        onView(withId(R.id.spinner)).perform(click());
        //TODO: finish this
        onView(withId(R.id.submit)).perform(click());
    }
    public void testGameShowButtonPress4Players() {
        onView(withId(R.id.multiplayer_button)).perform(click());
        onView(withId(R.id.spinner)).perform(click());
        //TODO: finish this
        onView(withId(R.id.submit)).perform(click());
    }

}
