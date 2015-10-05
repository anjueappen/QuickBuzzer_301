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
 *
 * Purpose: The purpose of this class to have a consolidated place for all the data in the app to be
 * stored. This class manages accepting and storing of the data while the app is alive, and saving
 * and loading the data from file. This app also processes the data to some degree.
 *
 * Design Rationale: Since there is data incoming from two activities in the app, to be processed and
 * displayed in another activity, it appeared to be a good idea to have the data stored in a common place
 * where both activities could access it. Since the data already exists in this class, it seemed like a
 * good idea to process it when the statistics activity requests data.
 *
 * Issues: However, in restrospect, it does not appear to be complete OO when the class storing the data
 * and the class processing the data are the same class. It would have been better to split this class
 * into one that strictly stores the data (called by activities that produce data) and a class that
 * processes the data (called by the activity that displays the data).
 *
 */
public class DataBin {

    private static final String FILENAME = "file.sav";
    private static DataBin ourInstance = new DataBin();

    HashMap<String, ArrayList<Long>> mainMap = new HashMap<String, ArrayList<Long>>();

    private Boolean needToSave;

    public static DataBin getInstance() {
        return ourInstance;
    }

    private DataBin() {
        initializeMap(mainMap);
        needToSave = Boolean.FALSE;
    }

    private void initializeMap(HashMap<String, ArrayList<Long>> map){
        map.put("times", new ArrayList<Long>());
        map.put("twoWins", new ArrayList<Long>(Arrays.asList(0L, 0L)));
        map.put("threeWins", new ArrayList<Long>(Arrays.asList(0L, 0L, 0L)));
        map.put("fourWins", new ArrayList<Long>(Arrays.asList(0L, 0L, 0L, 0L)));
    }
    public List<Long> getReactionTimeData(){
        return mainMap.get("times");
    }
    public HashMap<String, ArrayList<Long>> getAllData(){
        return mainMap;
    }

    public void addReactionTime(Long time){
        mainMap.get("times").add(time);
        needToSave = Boolean.TRUE;
    }

    public void addPlayerWin(int numPlayers, int winner){
        int winIndex = winner - 1;
        ArrayList<Long> winlist = getWinList(numPlayers);
        Long score = winlist.get(winIndex);
        winlist.set(winIndex, score + 1L);
        needToSave = Boolean.TRUE;
    }

    public Long getPlayerWin(int numPlayers, int player){
        int pIndex = player - 1;
        ArrayList<Long> list = getWinList(numPlayers);
        return list.get(pIndex);
    }

    private ArrayList<Long> getWinList(int numPlayers){
        ArrayList<Long> winlist;
        switch (numPlayers){
            case 2:
                winlist = mainMap.get("twoWins");
                break;
            case 3:
                winlist = mainMap.get("threeWins");
                break;
            default:
                winlist = mainMap.get("fourWins");
                break;
        }
        return winlist;
    }

    public void clearAll(Context c){
        initializeMap(mainMap);
        needToSave = Boolean.TRUE;
        saveInFile(c);
    }

    public Long returnLatest(){

        ArrayList<Long> times = mainMap.get("times");
        return times.get(times.size() - 1);
    }


    /*MATH METHODS*/
    public Long getMaxTimeOfLast(int lastNum){
        ArrayList<Long> times = mainMap.get("times");
        if(times.size() == 0L){
            return 0L;
        }
        if (lastNum > times.size()){
            lastNum = times.size();
        }
        List<Long> sub = times.subList(times.size() - lastNum, times.size() - 1);
        Long a = sub.get(1);
        return Collections.max(sub);
    }

    public Long getMinTimeOfLast(int lastNum){
        ArrayList<Long> times = mainMap.get("times");
        if(times.size() == 0L){
            return 0L;
        }
        if (lastNum > times.size()){
            lastNum = times.size();
        }
        return Collections.min(times.subList(times.size() - lastNum, times.size() - 1));
    }

    public Double getAverageTimeOfLast(int lastNum){
        ArrayList<Long> times = mainMap.get("times");
        if(times.size() == 0L){
            return 0.0;
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
        ArrayList<Long> times = mainMap.get("times");
        if(times.size() == 0L){
            return 0L;
        }
        //Taken and adapted from http://stackoverflow.com/questions/11955728/how-to-calculate-the-median-of-an-array
        //by lynn
        if (lastNum > times.size()){
            lastNum = times.size();
        }
        List<Long> sublist = times.subList((times.size() - lastNum), (times.size() - 1));
        Collections.sort(sublist);
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
            Type dataCollectionType = new TypeToken<HashMap<String, ArrayList<Long>>>() {}.getType();
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
