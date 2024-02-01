package com.example.smashrun;

import com.example.smashrun.Maf.MafCalculator;
import com.example.smashrun.models.SmashRunActivity;
import com.example.smashrun.models.SmashRunActivitySummary;

import java.util.Dictionary;
import java.util.Hashtable;

public class RunDataCollectorService{
    private final PbCalculator pbCalculator;
    private final MafCalculator mafCalculator;
    private final SmashRunApiClient smashRunApiClient;

    public RunDataCollectorService(PbCalculator pbCalculator ,MafCalculator mafCalculator, SmashRunApiClient smashRunApiClient) {
        this.pbCalculator = pbCalculator;
        this.mafCalculator = mafCalculator;
        this.smashRunApiClient = smashRunApiClient;
    }

    public RunDetails GetRunData(SmashRunActivity activity){
        var hrIndex = activity.recordingKeys.indexOf("heartRate");
        var heartRates = activity.recordingValues.get(hrIndex);
        var clockIndex = activity.recordingKeys.indexOf("clock");
        var timeInMaf = mafCalculator.CalculateTimeInMaf(heartRates, activity.recordingValues.get(clockIndex));
        var distanceIndex = activity.recordingKeys.indexOf("distance");

        var tenKmTime = pbCalculator.GetFastestTime(activity.recordingValues.get(clockIndex), activity.recordingValues.get(distanceIndex), 10);
        var oneKmTime = pbCalculator.GetFastestTime(activity.recordingValues.get(clockIndex), activity.recordingValues.get(distanceIndex), 1);
        var fiveKmTime = pbCalculator.GetFastestTime(activity.recordingValues.get(clockIndex), activity.recordingValues.get(distanceIndex), 5);

        Dictionary<Integer, Double> dictionary = new Hashtable<>();
        dictionary.put(10,tenKmTime);
        dictionary.put(5, fiveKmTime);
        dictionary.put(1,oneKmTime);
        return new RunDetails(timeInMaf, dictionary);
    }

    public RunDetails GetRunData(SmashRunActivitySummary activity) {
        return GetRunData(smashRunApiClient.GetActivity(activity.activityId));
    }
}
