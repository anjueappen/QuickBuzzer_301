package com.example.anju.quickbuzzer_301;


import android.test.ActivityInstrumentationTestCase2;

import java.util.List;

public class DataBinTest extends ActivityInstrumentationTestCase2 {

    protected Long FAST_REACTION_TIME = new Long(300);
    protected Long SLOW_REACTION_TIME = new Long(20000);
    //private DataBin databin = DataBin.getInstance();
    DataBin databin = DataBin.getInstance();

    public DataBinTest() {
        super(MainActivityFragment.class);
    }

    public void testAddReactionTimeFast(){
        databin.getReactionTimeData().clear(); // start fresh for each test
        assertTrue(databin.getReactionTimeData().isEmpty());
        databin.addReactionTime(FAST_REACTION_TIME);
        List<Long> reactionTimes = databin.getReactionTimeData();
        assertTrue(reactionTimes.contains(FAST_REACTION_TIME));
    }

    public void testAddReactionTimeSlow(){
        databin.getReactionTimeData().clear(); // start fresh for each test
        assertTrue(databin.getReactionTimeData().isEmpty());
        databin.addReactionTime(SLOW_REACTION_TIME);
        List<Long> reactionTimes = databin.getReactionTimeData();
        assertTrue(reactionTimes.contains(SLOW_REACTION_TIME));
    }

    public void testReturnLastestReaction(){

    }

    // reset then get max for different numbers of results
    public void testGetMaxOfLast(){

    }

    // reset then get min
    public void testGetMinOfLast(){

    }

    public void getAverageOfLast(){

    }

    public void getMedianOfLast(){

    }

    // add negative time - impossible given how player uses app
    // but tested for completeness
    public void testAddBadReactionTime(){

    }

}
