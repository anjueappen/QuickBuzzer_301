package com.example.anju.quickbuzzer_301;

import android.widget.TextView;


/**
 * Created by anju on 02/10/15.
 */
public class ReactionGame {

    private ReactionTimeList timeList = new ReactionTimeList();
    private int buttonDelay;
    private ReactionTime currReactionTime;

    public void showReactionTime(TextView tv){
    }

    public void startGame(){
        currReactionTime = new ReactionTime();
    }

    public void endGame(){
        currReactionTime.setDuration();
        timeList.add(currReactionTime);
        currReactionTime = null;
    }

    public ReactionTimeList getTimeList(){
        return this.timeList;
    }

    public ReactionTime getLastReactionTime(){
        if(currReactionTime == null){
            return timeList.get(timeList.size()-1);
        }
        return currReactionTime;
    }

}
