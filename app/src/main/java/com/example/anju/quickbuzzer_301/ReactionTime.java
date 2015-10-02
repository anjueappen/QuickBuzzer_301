package com.example.anju.quickbuzzer_301;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

/**
 * Created by anju on 01/10/15.
 */
public class ReactionTime implements Parcelable, Comparable<ReactionTime>{
    private Long stime;
    private Long duration;

    public ReactionTime() {
        this.stime = System.currentTimeMillis();
    }

    public Long getDuration() {
        if (duration == null){
            return 0L;
        }
        return duration;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void setDuration(){
        if(duration == null){
           duration = System.currentTimeMillis() - stime;
        }
    }

    public ReactionTime(Parcel in){
        readFromParcel(in);
    }

    private void readFromParcel(Parcel in){
        duration = in.readParcelable(Long.class.getClassLoader());
    }
    @Override
    public int describeContents() {
        return duration.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(duration);
    }

    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public ReactionTime createFromParcel(Parcel in) {
                    return new ReactionTime(in);
                }

                public ReactionTime[] newArray(int size) {
                    return new ReactionTime[size];
                }
            };

    @Override
    public int compareTo(ReactionTime another) {
        return this.duration.compareTo(another.duration);
    }
}
