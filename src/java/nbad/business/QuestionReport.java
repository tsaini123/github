package nbad.business;

import java.beans.*;
import java.io.Serializable;

public class QuestionReport implements Serializable {

    private String reportQuestion;
    private String reportDate;
    private String reportStatus;
    private int studyCode;
    private String email;

    public QuestionReport() {
        reportQuestion = "";
        reportDate = "";
        reportStatus = "";
        studyCode = 00000;
        email = "";
    }

    public String getReportQuestion() {
        return reportQuestion;
    }

    public void setReportQuestion(String reportQuestion) {
        this.reportQuestion = reportQuestion;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    public int getStudyCode() {
        return studyCode;
    }

    public void setStudyCode(int studyCode) {
        this.studyCode = studyCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
