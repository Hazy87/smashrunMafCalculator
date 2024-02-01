package com.example.smashrun;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class MyHttpClient{

    private String Get(String baseUrl) {
        try {
            var url = new URL(baseUrl);
            var conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            var in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            var response = new StringBuffer();

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
        var objectMapper = new ObjectMapper();
        try {
            var obj = objectMapper.readValue(json, type);
            return obj;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

