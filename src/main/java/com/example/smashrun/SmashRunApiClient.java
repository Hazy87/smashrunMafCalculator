package com.example.smashrun;
import java.io.IOException;
import java.text.MessageFormat;

import com.example.smashrun.models.SmashRunActivity;
import com.example.smashrun.models.SmashRunActivitySummary;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SmashRunApiClient {
    private final MyHttpClient httpClient;
    @Autowired
    private String access_token;
    private String smashRunApi = "https://api.smashrun.com/v1";

    public SmashRunApiClient(MyHttpClient httpClient) {this.httpClient = httpClient;}

        public SmashRunActivitySummary[] GetLatestActivities(int numberOfActivities){
            String url = String.format("%s/my/activities/search?count=%d&access_token=%s", smashRunApi, numberOfActivities, access_token);
            return httpClient.GetFromJson(url, SmashRunActivitySummary[].class);
        }

    public SmashRunActivity GetActivity(int activityId) {
        String url = String.format("%s/my/activities/%d?access_token=%s", smashRunApi, activityId, access_token);
        return httpClient.GetFromJson(url, SmashRunActivity.class);
    }
}

