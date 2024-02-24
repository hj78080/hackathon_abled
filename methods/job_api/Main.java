package job_api;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String apiKey = "service key";
        String endpoint = "https://apis.data.go.kr/B552583/job/job_list_env";

        ApiRequest ar = new ApiRequest(apiKey, endpoint);
        
        int totalCount = ar.getTotalCount();
        String data = ar.requestData(totalCount);

        List<Job> jobs = parseXml(data);

        System.out.print("\n지역을 입력해주세요 : ");
        String inputRegion = sc.nextLine();
        System.out.println();

        for (Job job : jobs) {
            String region = job.getCompAddr();

            if(region.contains(inputRegion))
                System.out.println(job);
        }

        sc.close();
    }

    public static List<Job> parseXml(String xmlData) {
        List<Job> jobs = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // XML 문자열을 InputStream으로 변환
            ByteArrayInputStream input = new ByteArrayInputStream(xmlData.getBytes("UTF-8"));

            // XML 파싱
            Document document = builder.parse(input);

            // <item> 엘리먼트들을 가져오기
            NodeList itemNodes = document.getElementsByTagName("item");

            for (int i = 0; i < itemNodes.getLength(); i++) {
                Element itemElement = (Element) itemNodes.item(i);

                // 각 필드 값 추출
                String busplaName = itemElement.getElementsByTagName("busplaName").item(0).getTextContent();
                String compAddr = itemElement.getElementsByTagName("compAddr").item(0).getTextContent();
                String empType = itemElement.getElementsByTagName("empType").item(0).getTextContent();
                String enterType = itemElement.getElementsByTagName("enterType").item(0).getTextContent();
                String jobNm = itemElement.getElementsByTagName("jobNm").item(0).getTextContent();
                String offerregDt = itemElement.getElementsByTagName("offerregDt").item(0).getTextContent();
                String regDt = itemElement.getElementsByTagName("regDt").item(0).getTextContent();
                String regagnName = itemElement.getElementsByTagName("regagnName").item(0).getTextContent();
                String reqCareer = itemElement.getElementsByTagName("reqCareer").item(0).getTextContent();
                String reqEduc = itemElement.getElementsByTagName("reqEduc").item(0).getTextContent();
                String salary = itemElement.getElementsByTagName("salary").item(0).getTextContent();
                String salaryType = itemElement.getElementsByTagName("salaryType").item(0).getTextContent();
                String termDate = itemElement.getElementsByTagName("termDate").item(0).getTextContent();

                // Job 객체 생성 및 리스트에 추가
                Job job = new Job(busplaName, compAddr, empType, enterType, jobNm, offerregDt,
                        regDt, regagnName, reqCareer, reqEduc, salary, salaryType, termDate);
                jobs.add(job);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jobs;
    }
}