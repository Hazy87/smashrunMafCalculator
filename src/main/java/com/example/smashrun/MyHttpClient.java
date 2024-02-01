package com.example.smashrun;

import com.example.smashrun.models.SmashRunActivitySummary;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class MyHttpClient{

    public String Get(String baseUrl) {
        try {
            URL url = new URL(baseUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            //System.out.println("Response code: " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return baseUrl;
    }
    public <T> T GetFromJson(String url, Class<T> type){
        var json =  Get(url);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            var obj = objectMapper.readValue(json, type);
            return obj;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

