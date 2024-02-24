package com.example.abled.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;

public class ApiRequest {

    private String apiKey;
    private String endPoint;

    public ApiRequest (String apiKey, String endPoint){
        this.apiKey = apiKey;
        this.endPoint = endPoint;
    }

    public String requestData(int totalCount){
        String apiUrl = String.format("%s?serviceKey=%s&pageNo=%d&numOfRows=%d", endPoint, apiKey, 1, totalCount);
        StringBuilder response = new StringBuilder();

        try {
            // URL 연결 설정
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // 응답 받기
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response.toString());

            } else {
                System.out.println("HTTP request failed: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response.toString();
    }

}
