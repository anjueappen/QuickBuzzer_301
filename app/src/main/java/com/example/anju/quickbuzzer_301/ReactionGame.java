package com.example.anju.quickbuzzer_301;


/**
 * Created by anju on 02/10/15.
 * Purpose: This class is intended as a precursor to calculate the reaction times
 * in the reaction time activity.
 *
 * Design Rationale: This class has some Reaction Timer related responsibilities that don't appear to
 * fit as well with
 *
 * Issues: This class, although made with the intent to follow OO principles, has responsibilitites that
 * may have fit into the DataBin class.
 *
 */
public class ReactionGame {

    private Long sTime;
    private Long currReactionTime;


    public ReactionGame(){
    }

    public void startIteration(){
        sTime = System.currentTimeMillis();
    }

    public void endIteration(){
        currReactionTime =  System.currentTimeMillis() - sTime;
        DataBin.getInstance().addReactionTime(currReactionTime);
        currReactionTime = null;
    }

}
