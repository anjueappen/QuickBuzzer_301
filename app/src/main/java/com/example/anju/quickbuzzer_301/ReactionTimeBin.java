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
        if(times.size() == 0){
            times.add(0l);
        }
        if (lastNum > times.size()){
            lastNum = times.size();
        }
        return Collections.max(times.subList(times.size() - lastNum, times.size() - 1));
    }

    public Long getMinTimeOfLast(int lastNum){
        if(times.size() == 0){
            times.add(0l);
        }
        if (lastNum > times.size()){
            lastNum = times.size();
        }
        return Collections.min(times.subList(times.size() - lastNum, times.size() - 1));
    }

    public Double getAverageTimeOfLast(int lastNum){
        if(times.size() == 0){
            times.add(0l);
        }
        if (lastNum > times.size()){
            lastNum = times.size();
        }
        Collection<Long> sublist = times.subList((times.size() - lastNum), (times.size() - 1));
        double sum = 0;
        for(Long r : sublist){
            sum += r;
        }
        return sum/sublist.size();
    }

    public Long getMedianTimeOfLast(int lastNum){
        if(times.size() == 0){
            times.add(0l);
        }
        //Taken and adapted from http://stackoverflow.com/questions/11955728/how-to-calculate-the-median-of-an-array
        //by lynn
        if (lastNum > times.size()){
            lastNum = times.size();
        }
        List<Long> sublist = times.subList((times.size() -lastNum), (times.size()-1));
        Collections.sort(sublist);
        Long median;
        if (sublist.size()% 2 == 0)
            return (sublist.get(sublist.size()/2)+ sublist.get(sublist.size()/2 - 1))/2;
        else
            return sublist.get(sublist.size() / 2);
    }
}
