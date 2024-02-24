package job_api;
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

    public String getCompAddr() { return compAddr; }

    @Override
    public String toString() {
        return  "직장 : '" + busplaName + '\'';
                //", 주소 : '" + compAddr + '\'';
                //", 업무 : '" + jobNm + '\'';
                //", 모집 일자 : '" + termDate;
    }
    
}
