package com.example.anju.quickbuzzer_301;


/**
 * Created by anju on 02/10/15.
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
