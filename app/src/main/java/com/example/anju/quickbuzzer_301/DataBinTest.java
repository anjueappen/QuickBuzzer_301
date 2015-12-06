package com.example.anju.quickbuzzer_301;


import android.test.ActivityInstrumentationTestCase2;

import java.util.List;

public class DataBinTest extends ActivityInstrumentationTestCase2 {

    protected Long FAST_REACTION_TIME = new Long(300);
    protected Long SLOW_REACTION_TIME = new Long(20000);
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
        databin.getReactionTimeData().clear(); // start fresh for each test
        assertTrue(databin.getReactionTimeData().isEmpty());
        databin.addReactionTime(SLOW_REACTION_TIME);
        databin.addReactionTime(FAST_REACTION_TIME);
        assertTrue(databin.returnLatestReactionTime()==FAST_REACTION_TIME);
    }

    /*
    * Test branches and functionality
    * */

    // return max for empty list
    public void testGetMaxOfLastEmpty(){
        databin.getReactionTimeData().clear(); // start fresh for each test
        assertTrue(databin.getReactionTimeData().isEmpty());
        assertTrue(databin.getMaxTimeOfLast(1) == 0L);
    }

    // get max when input is equal to or less than size of list
    public void testGetMaxOfLastIndex(){
        databin.getReactionTimeData().clear(); // start fresh for each test
        assertTrue(databin.getReactionTimeData().isEmpty());
        databin.addReactionTime(new Long(450));
        databin.addReactionTime(new Long(601));
        databin.addReactionTime(new Long(555));
        assertTrue(databin.getMaxTimeOfLast(3)==new Long(601));  // index equal to size
    }

    // get max when input is index greater than size of list
    public void testGetMaxOfLastIndexGreater(){
        databin.getReactionTimeData().clear(); // start fresh for each test
        assertTrue(databin.getReactionTimeData().isEmpty());
        databin.addReactionTime(new Long(450));
        databin.addReactionTime(new Long(601));
        databin.addReactionTime(new Long(555));
        databin.addReactionTime(new Long(1000));
        databin.addReactionTime(new Long(556));
        databin.addReactionTime(new Long(705));
        assertTrue(databin.getMaxTimeOfLast(10) == new Long(1000));  // index greater than size

    }

    // TODO negative index?

    // reset then get min
    public void testGetMinOfLast(){
        databin.getReactionTimeData().clear(); // start fresh for each test
        assertTrue(databin.getReactionTimeData().isEmpty());
    }

    public void getAverageOfLast(){
        databin.getReactionTimeData().clear(); // start fresh for each test
        assertTrue(databin.getReactionTimeData().isEmpty());
    }

    public void getMedianOfLast(){
        databin.getReactionTimeData().clear(); // start fresh for each test
        assertTrue(databin.getReactionTimeData().isEmpty());
    }

    // add negative time - impossible given how player uses app
    // but tested for completeness
    public void testAddBadReactionTime(){
        databin.getReactionTimeData().clear(); // start fresh for each test
        assertTrue(databin.getReactionTimeData().isEmpty());
    }

}
