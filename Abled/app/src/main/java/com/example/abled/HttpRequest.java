package com.example.abled;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest{

    public static void sendPostRequest(String text) {
        try {
            // 플라스크 서버의 URL
            URL url = new URL("http://serverip:5000/api?type=text");

            // HttpURLConnection 설정
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);

            // 요청 바디에 텍스트 추가
            String requestBody = "text=" + text;
            OutputStream outputStream = urlConnection.getOutputStream();
            outputStream.write(requestBody.getBytes());
            outputStream.flush();

            // 응답 받기
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 응답이 성공일 때 처리
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    response.append(line);
                }
                bufferedReader.close();

                // 서버에서 받은 응답(response)을 사용
                String serverResponse = response.toString();
                System.out.println("서버 응답: " + serverResponse);
            } else {
                // 응답이 실패일 때 처리
                System.out.println("HTTP 요청 실패. 응답 코드: " + responseCode);
            }

            urlConnection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}