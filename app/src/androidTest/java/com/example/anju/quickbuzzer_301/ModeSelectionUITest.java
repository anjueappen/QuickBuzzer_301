package com.example.anju.quickbuzzer_301;

/**
 * Created by anju on 04/12/15.
 */
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static org.hamcrest.Matchers.*;

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
        onView(withId(R.id.button1)).perform(click());
        withText("Game Show Results\\n\n" +
                " Player 1 Wins!\\n\n" +
                "  Number of Players: 2").matches(isDisplayed());
    }
    public void testGameShowButtonPress3Players() {
        onView(withId(R.id.multiplayer_button)).perform(click());
        onView(withId(R.id.spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("3"))).perform(click());
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.button1)).check(matches(isDisplayed()));
        onView(withId(R.id.button2)).check(matches(isDisplayed()));
        onView(withId(R.id.button3)).check(matches(isDisplayed()));
        onView(withId(R.id.button2)).perform(click());
        withText("Game Show Results\\n\n" +
                " Player 2 Wins!\\n\n" +
                "  Number of Players: 3").matches(isDisplayed());
    }
    public void testGameShowButtonPress4Players() {
        onView(withId(R.id.multiplayer_button)).perform(click());
        onView(withId(R.id.spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("4"))).perform(click());
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.button1)).check(matches(isDisplayed()));
        onView(withId(R.id.button2)).check(matches(isDisplayed()));
        onView(withId(R.id.button3)).check(matches(isDisplayed()));
        onView(withId(R.id.button4)).check(matches(isDisplayed()));
        //onView(withId(R.id.button4)).perform(click()); -errs out because not displayed within expresso's 90% constraint
        onView(withId(R.id.button4)).perform(scrollTo(), click()); //also errors out - mention in report
        withText("Game Show Results\\n\n" +
                " Player 4 Wins!\\n\n" +
                "  Number of Players: 4").matches(isDisplayed());
    }

    public void testStatistics(){
        onView(withId(R.id.statistics_button)).perform(click());
        withText("Last 10 Reactions").matches(isDisplayed());
        withText("Last 100 Reactions").matches(isDisplayed());
        withText("All Reactions").matches(isDisplayed());

    }
    public void testStatisticsClear(){
        onView(withId(R.id.statistics_button)).perform(click());
        withText("Last 10 Reactions").matches(isDisplayed());
        withText("Last 100 Reactions").matches(isDisplayed());
        withText("All Reactions").matches(isDisplayed());
        onView(withId(R.id.clear_stats_button)).perform(scrollTo(), click());

    }

    public void testStatsEmail(){
        onView(withId(R.id.statistics_button)).perform(click());
        withText("Last 10 Reactions").matches(isDisplayed());
        withText("Last 100 Reactions").matches(isDisplayed());
        withText("All Reactions").matches(isDisplayed());
        onView(withId(R.id.email_stats_button)).perform(scrollTo(), click());
        withText("You are about to leave this application for the email application.").matches(isDisplayed());
        //onView(withId(R.id.buttonSend)).perform(click());

    }
}
