package com.example.anju.quickbuzzer_301;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by anju on 28/09/15.
 */
public class ReactionTimeCollection extends ArrayList<Long>{

    public void addReactionTime(Long startTime){
        this.add(startTime - System.currentTimeMillis());
    }

    public Long getMaxTimeOfLast(int lastNum){
        return Collections.max(this.subList((this.size() - 1), (this.size() - 1 - lastNum)));
    }

    public Long getMinTimeOfLast(int lastNum){
        return Collections.min(this.subList((this.size() - 1), (this.size() - 1 - lastNum)));
    }

    public double getAverageTimeOfLast(int lastNum){
        Collection<Long> sublist = this.subList((this.size() - 1), (this.size() - 1 - lastNum));
        double sum = 0;
        for(Long d : sublist){
            sum += d;
        }
        return sum/sublist.size();
    }

    public Long getMedianTimeOfLast(int lastNum){
        return 5L;
    }
}
