package com.example.abled.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpRequest{
    private static final String serverUrl = "http://serverip:5000/api";

    public static String sendPageRequest(String text) {
        try {
            // 플라스크 서버의 URL
            String query = String.format("/page?text=%s", URLEncoder.encode(text, "UTF-8"));
            URL url = new URL(serverUrl + query);

            // HttpURLConnection 설정
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // 응답 받기
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 응답이 성공일 때 처리
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    response.append(line);
                }
                bufferedReader.close();

                // 서버에서 받은 응답(response)을 사용
                String serverResponse = response.toString();
                System.out.println("서버 응답: " + serverResponse);
                return serverResponse;

            } else {
                // 응답이 실패일 때 처리
                System.out.println("HTTP 요청 실패. 응답 코드: " + responseCode);
            }
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Bitmap sendImageRequest(int year, String type, String state, String region) throws Exception {

        String requestUrl = serverUrl + "/graph?year=" + year
                + "&type=" + URLEncoder.encode(type, "UTF-8")
                + "&state=" + URLEncoder.encode(state, "UTF-8")
                + "&region=" + URLEncoder.encode(region, "UTF-8");

        // HTTP 연결 설정
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try {
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    response.append(line);
                }

                // Base64로 인코딩된 이미지를 디코딩하여 Bitmap으로 변환
                byte[] imageBytes = Base64.decode(response.toString(), Base64.DEFAULT);
                return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

            } finally {
                connection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}