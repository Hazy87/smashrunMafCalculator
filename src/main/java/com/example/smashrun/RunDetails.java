package com.example.smashrun;

import java.util.Dictionary;

public class RunDetails{

    private final double timeInMaf;
    private final Dictionary<Integer, Double> DistancesAndTimes;

    public RunDetails(double timeInMaf, Dictionary<Integer, Double> distancesAndTimes) {
        this.timeInMaf = timeInMaf;
        this.DistancesAndTimes = distancesAndTimes;
    }

    public double getTimeInMaf() {
        return timeInMaf;
    }

    public Dictionary<Integer, Double> getDistancesAndTimes() {
        return DistancesAndTimes;
    }
}
