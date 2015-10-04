package com.example.anju.quickbuzzer_301;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by anju on 02/10/15.
 */
public class ReactionGame {

    private List<Long> timeList;
    private Long sTime;
    private Long currReactionTime;


    public ReactionGame(){
        timeList = new ArrayList<Long>();
    }

    public void startGame(){
        sTime = System.currentTimeMillis();
    }

    public void endGame(){
        currReactionTime =  System.currentTimeMillis() - sTime;
        timeList.add(currReactionTime);
        currReactionTime = null;
    }

    public List<Long> getTimeList(){
        return this.timeList;
    }

    public Long getLastReactionTime(){
        if(currReactionTime == null){
            return timeList.get(timeList.size()-1);
        }
        return currReactionTime;
    }

    public void storeTimesInBin(){
        ReactionTimeBin.getInstance().addAll(timeList);
    }

}
