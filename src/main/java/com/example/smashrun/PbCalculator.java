package com.example.smashrun;

import java.util.List;

public class PbCalculator {

    public double GetFastestTime(List<Double> clock, List<Double> distances, int requiredDistance){
        double currentBestTime = Double.MAX_VALUE;
        long length = distances.size();
        for (int i = 0; i < length; i++) {
            for (int end = 1; end < length; end++) {
                if(distances.get(end) - distances.get(i) >= requiredDistance) {
                        var time = clock.get(end) - clock.get(i);
                        if (currentBestTime > time)
                            currentBestTime = time;
                        break;
                    }
            }
        }
        return currentBestTime;
    }
}
