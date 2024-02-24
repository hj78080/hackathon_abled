package job_api;
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


    public int getTotalCount(){
        String apiUrl = String.format("%s?serviceKey=%s&pageNo=%d&numOfRows=%d", endPoint, apiKey, 1, 1);
        StringBuilder response = new StringBuilder();
        int totalCount = 1;

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

            } else {
                System.out.println("HTTP request failed: " + responseCode);
                return totalCount;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return totalCount;
        }

        try {
            // XML 파서 생성
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // XML 문자열을 InputStream으로 변환
            ByteArrayInputStream input = new ByteArrayInputStream(response.toString().getBytes("UTF-8"));

            // XML 파싱
            Document document = builder.parse(input);

            // totalCount 추출
            Element totalCountElement = (Element) document.getElementsByTagName("totalCount").item(0);
            totalCount = Integer.parseInt(totalCountElement.getTextContent());

            System.out.println("Total Count: " + totalCount);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalCount;
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
