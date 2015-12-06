package com.example.anju.quickbuzzer_301;

/**
 * Created by anju on 04/12/15.
 */
import android.app.Dialog;
import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

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


    public void testValidateTimerDialogOnButtonPress() {
        onView(withId(R.id.single_player_button)).perform(click());
        withText("When the button appears,\\n\n" +
                "        click it quickly!\\n\n" +
                "        \\n\n" +
                "        Close this dialog to begin.").matches(isDisplayed());
    }
}