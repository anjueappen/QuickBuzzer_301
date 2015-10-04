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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by anju on 03/10/15.
 */
public class DataBin {

    private static final String FILENAME = "file.sav";
    private static DataBin ourInstance = new DataBin();

    HashMap<String, ArrayList> mainMap = new HashMap<String, ArrayList>();

    private List<Integer> p2Wins;
    private List<Integer> p3Wins;
    private List<Integer> p4Wins;
    private List<Long> times;
    private Boolean needToSave;

    public static DataBin getInstance() {
        return ourInstance;
    }

    private DataBin() {
        initializeMap(mainMap);
    }

    private void initializeMap(HashMap<String, ArrayList> map){
        map.put("times", new ArrayList<Long>());
        map.put("p2Wins", new ArrayList<Integer>(Arrays.asList(0, 0)));
        map.put("p3Wins", new ArrayList<Integer>(Arrays.asList(0, 0, 0)));
        map.put("p4Wins", new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0)));
    }
    public List<Long> getData(){
        return times;
    }

    public void addReactionTime(Long time){
        times.add(time);
        needToSave = Boolean.TRUE;
    }

    public void addPlayerWin(int numPlayers, int winner){
        int winIndex = winner - 1;
        ArrayList<Integer> winlist;
        switch (numPlayers){
            case 2:
                winlist = mainMap.get("p2Wins");
                break;
            case 3:
                winlist = mainMap.get("p3Wins");
                break;
            default:
                winlist = mainMap.get("p4Wins");
                break;
        }
        int score = winlist.get(winIndex);
        winlist.set(winIndex, score+1);
        needToSave = Boolean.TRUE;
    }

    public void clearAll(Context c){
        times.clear();
        saveInFile(c);

    }

    public Long returnLatest(){
        return times.get(times.size()-1);
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


    /*Saving Methods*/

    public void saveInFile(Context c) {
        if(!needToSave){
            return;
        }
        try {
            FileOutputStream fos = c.openFileOutput(FILENAME, 0);
            BufferedWriter out =  new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(mainMap, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
        needToSave = Boolean.FALSE;
    }

    public void loadFromFile(Context c) {
        try {

            FileInputStream fis = c.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            //https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
            Type dataCollectionType = new TypeToken<HashMap<String, ArrayList>>() {}.getType();
            Gson gson = new Gson();
            mainMap = gson.fromJson(in, dataCollectionType);

        } catch (FileNotFoundException e) {
            mainMap = new HashMap<>();
            initializeMap(mainMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
