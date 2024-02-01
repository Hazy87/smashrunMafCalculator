package com.example.smashrun.models;

import java.util.Date;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
public class SmashRunActivitySummary {
    public int activityId;
    public String activityType;
    public double duration;
    public double distance;
    public int calories;
    public String notes;
    public Date startDateTimeLocal;
    public String externalId;
    public String source;
    public String appVersion;
    public String deviceType;
    public int hasDetails;
    public int hasDetailsGPS;
    public double startLatitude;
    public double startLongitude;
    public int heartRateMax;
    public int heartRateMin;
    public int heartRateAverage;
    public int cadenceMax;
    public int cadenceMin;
    public int cadenceAverage;
    public String weatherType;
    public double temperature;
    public int humidity;
    public int windSpeed;
    public double temperatureApparent;
    public double temperatureWindChill;
    public String howFelt;
    public String terrain;
    public int isRace;
    public int isTreadmill;
    public int isBadHR;
    public int isBadElevation;
    public Date syncDateTimeUTC;
    public Date dateCreatedUTC;
    public Date dateUpdatedUTC;
}

