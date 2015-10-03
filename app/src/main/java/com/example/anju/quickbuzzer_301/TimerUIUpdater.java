package com.example.anju.quickbuzzer_301;

import android.app.ActivityManager;
import android.os.Handler;
import android.os.Looper;

import java.util.Random;

/**
 * Created by anju on 02/10/15.
 * adapted from http://stackoverflow.com/questions/6242268/repeat-a-task-with-a-time-delay by ravemir
 */
public class TimerUIUpdater {

    public Handler getHandler() {
        return handler;
    }

    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable taskRepeater;
    private Runnable taskToRepeat;


    public TimerUIUpdater(final Runnable taskToRepeat){
        this.taskToRepeat = taskToRepeat;
        taskRepeater =  new Runnable() {
            @Override
            public void run() {
                taskToRepeat.run();
                handler.postDelayed(this, makeNewDelay()); //this is the act of repeating taskToRepeat
            }
        };
    }

    public synchronized void startUIUpdates(){
        taskRepeater.run();
    }

    public synchronized void stopUIUpdates(){
        handler.removeCallbacks(taskRepeater);
    }

    public synchronized void interrupt(Runnable interruption){
        handler.removeCallbacks(this.taskRepeater);
        handler.removeCallbacks(this.taskToRepeat);
        interruption.run();
        taskRepeater.run();
    }
    private int makeNewDelay(){
        return (01 + new Random().nextInt(1995));
    }

}
