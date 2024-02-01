package com.example.smashrun;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SmashrunApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        var smashRun = context.getBean(SmashRunApiClient.class);
        var messageService = context.getBean(RunMessageCreatingService.class);
        var dataGetter = context.getBean(RunDataCollectorService.class);
        var activities = smashRun.GetLatestActivities(5);
        for (var activity : activities) {
            var run = dataGetter.GetRunData(activity);
            String message = messageService.createMessage(activity.heartRateAverage, run);
            System.out.println(message);
        }
    }

}


