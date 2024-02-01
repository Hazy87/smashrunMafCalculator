package com.example.smashrun;

import com.example.smashrun.Maf.MafCalculator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class SmashrunApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        var smashRun = context.getBean(SmashRunApiClient.class);
        var mafCals = context.getBean(MafCalculator.class);
        var timeFormatter = context.getBean(TimeFormatter.class);
        var pbCalculator = context.getBean(PbCalculator.class);
        var activities = smashRun.GetLatestActivities(5);
        for (var activity : activities) {
            var detailedActivity = smashRun.GetActivity(activity.activityId);
            var hrIndex = detailedActivity.recordingKeys.indexOf("heartRate");
            var heartRates = detailedActivity.recordingValues.get(hrIndex);
            var clockIndex = detailedActivity.recordingKeys.indexOf("clock");
            var timeInMaf = mafCals.CalculateTimeInMaf(heartRates, detailedActivity.recordingValues.get(clockIndex));
            var distanceIndex = detailedActivity.recordingKeys.indexOf("distance");

            var tenKmTime = pbCalculator.GetFastestTime(detailedActivity.recordingValues.get(clockIndex), detailedActivity.recordingValues.get(distanceIndex), 10);
            var oneKmTime = pbCalculator.GetFastestTime(detailedActivity.recordingValues.get(clockIndex), detailedActivity.recordingValues.get(distanceIndex), 1);
            var fiveKmTime = pbCalculator.GetFastestTime(detailedActivity.recordingValues.get(clockIndex), detailedActivity.recordingValues.get(distanceIndex), 5);
            String message = String.format("Average Heart Rate : %s,\t Time In Maf : %s%%,\t 1Km Time : %s", activity.heartRateAverage, Math.round(timeInMaf), timeFormatter.FormatSeconds(oneKmTime));

            if(fiveKmTime != Double.MAX_VALUE)
                message = String.format("%s\t 5km Time: %s", message, timeFormatter.FormatSeconds(fiveKmTime));

            if(tenKmTime != Double.MAX_VALUE)
                message = String.format("%s\t 10km Time: %s", message, timeFormatter.FormatSeconds(tenKmTime));
            System.out.println(message);
        }
    }

}


