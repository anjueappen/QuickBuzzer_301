package com.example.anju.quickbuzzer_301;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by anju on 03/10/15.
 */
public class ReactionTimeBin {

    private static final String FILENAME = "file.sav";
    private static ReactionTimeBin ourInstance = new ReactionTimeBin();
    private List<Long> times;
    private Context baseContext;

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
    public void clearAll(){
        times.clear();
    }
    public void setBaseContext(Context c){
        baseContext = c;
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

    public void saveInFile() {
        try {
            FileOutputStream fos = baseContext.openFileOutput(FILENAME, 0);
            BufferedWriter out =  new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(times, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    public void loadFromFile() {
        try {

            FileInputStream fis = baseContext.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            //https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
            Type reactionTimeCollectionType = new TypeToken<List<Long>>() {}.getType();
            Gson gson = new Gson();
            times = gson.fromJson(in, reactionTimeCollectionType);

        } catch (FileNotFoundException e) {
            times = new ArrayList<Long>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
