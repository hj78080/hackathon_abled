package com.example.abled.util;

/* ex)
    <busplaName>행복한요양병원</busplaName>
    <cntctNo>1588-1519</cntctNo>
    <compAddr>경상남도 창원시 의창구 북면 천주로 785 (동전리, 행복한병원)</compAddr>
    <empType>계약직</empType>
    <enterType>무관</enterType>
    <jobNm>요양보호사 보조원</jobNm>
    <offerregDt>20231229</offerregDt>
    <regDt>20231229</regDt>
    <regagnName>한국장애인고용공단 경남동부지사</regagnName>
    <reqCareer>무관</reqCareer>
    <reqEduc>무관</reqEduc>
    <rno>2</rno>
    <rnum>2</rnum>
    <salary>9,860</salary>
    <salaryType>시급</salaryType>
    <termDate>2023-12-29~2024-01-12</termDate>
*/

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Job {

    private String busplaName;
    private String compAddr;
    private String empType;
    private String enterType;
    private String jobNm;
    private String offerregDt;
    private String regDt;
    private String regagnName;
    private String reqCareer;
    private String reqEduc;
    private String salary;
    private String salaryType;
    private String termDate;

    public Job(String busplaName, String compAddr, String empType,
               String enterType, String jobNm, String offerregDt, String regDt,
               String regagnName, String reqCareer, String reqEduc, String salary,
               String salaryType, String termDate) {
        this.busplaName = busplaName;
        this.compAddr = compAddr;
        this.empType = empType;
        this.enterType = enterType;
        this.jobNm = jobNm;
        this.offerregDt = offerregDt;
        this.regDt = regDt;
        this.regagnName = regagnName;
        this.reqCareer = reqCareer;
        this.reqEduc = reqEduc;
        this.salary = salary;
        this.salaryType = salaryType;
        this.termDate = termDate;
    }

    public String getBusplaName() { return  busplaName; }
    public String getJobNm(){ return jobNm;}
    public String getCompAddr() { return compAddr; }
    public String getSimpleAddr() {
        String[] addr = compAddr.split(" ");
        return addr[0] +" "+ addr[1];
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

    @Override
    public String toString() {
        return  "직장 : '" + busplaName + '\'';
        //", 주소 : '" + compAddr + '\'';
        //", 업무 : '" + jobNm + '\'';
        //", 모집 일자 : '" + termDate;
    }

}
