package com.example.anju.quickbuzzer_301.java;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import java.util.List;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;

import com.example.anju.quickbuzzer_301.DataBin;
import com.example.anju.quickbuzzer_301.MainActivityFragment;


/**
 * Created by mmabuyo on 2015-12-04.
 */
public class DataBinTest extends ActivityInstrumentationTestCase2 {
    protected DataBin databin;

    protected Long FAST_REACTION_TIME = new Long(300);
    protected Long SLOW_REACTION_TIME = new Long(20000);

    public DataBinTest() {
        super(MainActivityFragment.class);
    }

    public void setUp() throws Exception {
        databin = new DataBin();
    }


    public void testAddReactionTime() {
        databin.addReactionTime(FAST_REACTION_TIME);
        List<Long> reactionTimes = databin.getReactionTimeData();
        assertTrue(reactionTimes.contains(FAST_REACTION_TIME));
    }


}
