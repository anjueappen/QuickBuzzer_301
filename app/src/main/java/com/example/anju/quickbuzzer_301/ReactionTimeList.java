package com.example.anju.quickbuzzer_301;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by anju on 28/09/15.
 */
public class ReactionTimeList extends ArrayList<ReactionTime> {

    Comparator<ReactionTime> reactionTimeComparator = new Comparator<ReactionTime>() {
        @Override
        public int compare(ReactionTime lhs, ReactionTime rhs) {
            return lhs.compareTo(rhs);
        }
    };


    public ReactionTime getMaxTimeOfLast(int lastNum){
        return Collections.max(this.subList(this.size() - 1, this.size() - 1 - lastNum), reactionTimeComparator);
    }

    public ReactionTime getMinTimeOfLast(int lastNum){
        return Collections.min(this.subList((this.size() - 1), (this.size() - 1 - lastNum)), reactionTimeComparator);
    }

    public double getAverageTimeOfLast(int lastNum){
        Collection<ReactionTime> sublist = this.subList((this.size() - 1), (this.size() - 1 - lastNum));
        double sum = 0;
        for(ReactionTime r : sublist){
            sum += r.getDuration();
        }
        return sum/sublist.size();
    }

    public Long getMedianTimeOfLast(int lastNum){
        //Taken and adapted from http://stackoverflow.com/questions/11955728/how-to-calculate-the-median-of-an-array
        //by lynn

        ReactionTimeList sublist = (ReactionTimeList) this.subList((this.size()-1), (this.size()-1 -lastNum));
        Collections.sort(sublist);
        Long median;
        if (sublist.size()% 2 == 0)
            return (sublist.get(sublist.size()/2).getDuration() + sublist.get(sublist.size()/2 - 1).getDuration())/2;
        else
            return sublist.get(sublist.size()/2).getDuration();
    }

    /*@Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this);

    }

    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public ReactionTimeList createFromParcel(Parcel in) {
                    return new ReactionTimeList(in);
                }

                public ReactionTimeList[] newArray(int size) {
                    return new ReactionTimeList[size];
                }
            };*/
}
