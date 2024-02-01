package com.example.smashrun;

import com.example.smashrun.Maf.MafCalculator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

@SpringBootApplication
public class SmashrunApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        var smashRun = context.getBean(SmashRunApiClient.class);
        var mafCals =  context.getBean(MafCalculator.class);
        var activities = smashRun.GetLatestActivities(50);
        for (var activity : activities){
            var detailedActivity = smashRun.GetActivity(activity.activityId);
            var hrIndex = detailedActivity.recordingKeys.indexOf("heartRate");
            var heartRates = detailedActivity.recordingValues.get(hrIndex);
            var timeInMaf = mafCals.CalculateTimeInMaf(heartRates);
            System.out.println(String.format("Average Heart Rate : %s, Time In Maf : %s%%", activity.heartRateAverage, Math.round(timeInMaf)));
        }
    }

}

