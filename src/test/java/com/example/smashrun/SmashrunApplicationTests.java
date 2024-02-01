package com.example.smashrun;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class SmashRunClientTests {

    @Test public void testGetLatestActivities_CallsHttpClient_Once() throws IOException {
        var httpClient = mock(MyHttpClient.class);
        var responseJson = "[{\"activityId\":123,\"heartRateAverage\":80}]";
        when(httpClient.Get(anyString())).thenReturn(responseJson);

        var myClass = new SmashRunApiClient(httpClient);

        var numberOfActivities = 1;
        var activities = myClass.GetLatestActivities(numberOfActivities);

        var expectedUrl = "https://api.smashrun.com/v1/my/activities/search?count=1&access_token=";
        verify(httpClient, times(1)).Get(startsWith(expectedUrl));

        var expectedOutput = 80;
        assertEquals(expectedOutput, activities[0].heartRateAverage);
    }

    @Test public void testGetLatestActivities_Returns_CorrectNumberOfActivities() throws IOException {
        var httpClient = mock(MyHttpClient.class);
        var responseJson = "[{\"activityId\":123,\"heartRateAverage\":80},{\"activityId\":321,\"heartRateAverage\":80}]";
        when(httpClient.Get(anyString())).thenReturn(responseJson);

        var myClass = new SmashRunApiClient(httpClient);

        var numberOfActivities = 1;
        var activities = myClass.GetLatestActivities(numberOfActivities);

        assertEquals(2, Arrays.stream(activities).count());
    }


}


