package com.example.anju.quickbuzzer_301;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by anju on 03/10/15.
 */
public class ReactionTimeBin {
    private static ReactionTimeBin ourInstance = new ReactionTimeBin();
    private List<Long> times;

    public static ReactionTimeBin getInstance() {
        return ourInstance;
    }

    private ReactionTimeBin() {
        times = new ArrayList<Long>();
    }

    public List<Long> getData(){
        return times;
    }

    public void addAll(List<Long>list){
        times.addAll(list);
    }
    public void setData(List<Long> list){
        times = list;
    }

    /*MATH METHODS*/
    public Long getMaxTimeOfLast(int lastNum){
        return Collections.max(times.subList(times.size() - 1, times.size() - 1 - lastNum));
    }

    public Long getMinTimeOfLast(int lastNum){
        return Collections.min(times.subList((times.size() - 1), (times.size() - 1 - lastNum)));
    }

    public double getAverageTimeOfLast(int lastNum){
        Collection<Long> sublist = times.subList((times.size() - 1), (times.size() - 1 - lastNum));
        double sum = 0;
        for(Long r : sublist){
            sum += r;
        }
        return sum/sublist.size();
    }

    public Long getMedianTimeOfLast(int lastNum){
        //Taken and adapted from http://stackoverflow.com/questions/11955728/how-to-calculate-the-median-of-an-array
        //by lynn

        List<Long> sublist = times.subList((times.size()-1), (times.size()-1 -lastNum));
        Collections.sort(sublist);
        Long median;
        if (sublist.size()% 2 == 0)
            return (sublist.get(sublist.size()/2)+ sublist.get(sublist.size()/2 - 1))/2;
        else
            return sublist.get(sublist.size() / 2);
    }
}
