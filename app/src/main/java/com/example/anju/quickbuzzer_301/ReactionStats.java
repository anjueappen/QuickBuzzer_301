package com.example.anju.quickbuzzer_301;

/**
 * Created by anju on 30/09/15.
 */
public class ReactionStats {
    private Long meanTime;
    private Long medianTime;
    private Long maxTime;
    private Long minTime;
    private String outOf;

    public ReactionStats(Long min, Long max, Long mean, Long median, String outOf) {
        this.minTime = min;
        this.maxTime = max;
        this.meanTime = mean;
        this.medianTime = median;
        this.outOf = outOf;
    }

    public Long getMeanTime() {
        return meanTime;
    }

    public void setMeanTime(Long meanTime) {
        this.meanTime = meanTime;
    }

    public Long getMedianTime() {
        return medianTime;
    }

    public void setMedianTime(Long medianTime) {
        this.medianTime = medianTime;
    }

    public Long getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Long maxTime) {
        this.maxTime = maxTime;
    }

    public Long getMinTime() {
        return minTime;
    }

    public void setMinTime(Long minTime) {
        this.minTime = minTime;
    }

    public String getOutOf() {
        return outOf;
    }

    public void setOutOf(String outOf) {
        this.outOf = outOf;
    }
}
