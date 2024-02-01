package com.example.smashrun.Maf;

import java.util.List;

public class MafCalculator{
    private final MafRangeProvider mafRangeProvider;

    public MafCalculator(MafRangeProvider mafRangeProvider) {
        this.mafRangeProvider = mafRangeProvider;
    }

    public double CalculateTimeInMaf(List<Integer> heartRates){
            int lowerBound = this.mafRangeProvider.GetMafRange().MinLimit; // example lower bound
            int upperBound = this.mafRangeProvider.GetMafRange().MaxLimit; // example lower bound; // example upper bound
            int count = 0; // count of heart rates in range
            for (int hr : heartRates) {
                if (hr >= lowerBound && hr <= upperBound) {
                    count++;
                }
            }
            double percentage = ((double) count / heartRates.stream().count()) * 100;
            return percentage;
        }
}
