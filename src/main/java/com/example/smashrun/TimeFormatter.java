package com.example.smashrun;

import java.util.concurrent.TimeUnit;

public class TimeFormatter{

    public String FormatSeconds(double seconds){
        long hours = TimeUnit.SECONDS.toHours((long)seconds);
        long minutes = TimeUnit.SECONDS.toMinutes((long)seconds) - TimeUnit.HOURS.toMinutes(hours);
        long remainingSeconds = (long)seconds - TimeUnit.MINUTES.toSeconds(minutes);
        String time = String.format("%d:%02d", minutes, remainingSeconds);
        if(hours > 0) time = time = String.format("%d:%s", hours, time);
        return time;
    }
}
