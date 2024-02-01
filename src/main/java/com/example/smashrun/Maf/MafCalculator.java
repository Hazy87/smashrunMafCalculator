package com.example.smashrun.Maf;

import java.util.List;

public class MafCalculator{
    private final MafRangeProvider mafRangeProvider;
    private final double lowerBound;
    private final double upperBound;

    public MafCalculator(MafRangeProvider mafRangeProvider) {
        this.mafRangeProvider = mafRangeProvider;
        lowerBound = this.mafRangeProvider.GetMafRange().MinLimit; // example lower bound
        upperBound = this.mafRangeProvider.GetMafRange().MaxLimit; // examp
    }

    public double CalculateTimeInMaf(List<Double> heartRates, List<Double> clock){
            var minArraySize = Math.min(heartRates.size(), clock.size());
            var percentage = 0.0;
            for (int i = 0; i <= minArraySize-2; i++) {
                var middleNumber = GetMiddleNumber(heartRates.get(i), heartRates.get(i+1));
                if(middleNumber >= lowerBound && middleNumber <= upperBound){
                    percentage +=getPercentage(clock, i, minArraySize, percentage);
                }
            }
            return percentage;
        }

    private static double getPercentage(List<Double> clock, int i, int minArraySize, double percentage) {
        double timeFrame = clock.get(i +1) - clock.get(i);
        double totalTime = clock.get(minArraySize -1) - clock.get(0);
        return timeFrame / totalTime * 100;
    }

    private double GetMiddleNumber(double a, double b){
            return (a + b) /2;
        }
}
