package com.example.smashrun;

public class RunMessageCreatingService{
    private final TimeFormatter timeFormatter;

    public RunMessageCreatingService(TimeFormatter timeFormatter) {
        this.timeFormatter = timeFormatter;
    }

    public String createMessage(int heartRateAverage, RunDetails run) {
        String message = String.format("Average Heart Rate : %s,\t Time In Maf : %s%%,\t 1Km Time : %s", heartRateAverage, Math.round(run.getTimeInMaf()), timeFormatter.FormatSeconds(run.getDistancesAndTimes().get(1)));
        var fiveKmTime = run.getDistancesAndTimes().get(5);
        if(fiveKmTime != Double.MAX_VALUE)
            message = String.format("%s\t 5km Time: %s", message, timeFormatter.FormatSeconds(fiveKmTime));
        var tenKmTime = run.getDistancesAndTimes().get(10);
        if(tenKmTime != Double.MAX_VALUE)
            message = String.format("%s\t 10km Time: %s", message, timeFormatter.FormatSeconds(tenKmTime));
        return message;
    }
}
