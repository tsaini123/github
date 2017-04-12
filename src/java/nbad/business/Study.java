package nbad.business;

import java.io.Serializable;
import java.util.ArrayList;

public class Study implements Serializable {
    private String studyName;
    private int studyCode;
    private String dateCreated;
    private String emailOfCreator;
    private String question;
    private String imageURL;
    private int reqParticipants;
    private int numOfParticipants;
    private String description;
    private String status;
    private String ansType; // Text/Numeric
    private ArrayList choiceOfAns;
    private double average;
    private double minimum;
    private double maximum;
    private double sD;

    public Study() {
        this.studyName = "";
        this.studyCode = 0;
        this.dateCreated = "";
        this.emailOfCreator = "";
        this.question = "";
        this.imageURL = "";
        this.reqParticipants = 0;
        this.numOfParticipants = 0;
        this.description = "";
        this.status = "";
        this.ansType = "";
        this.choiceOfAns = new ArrayList();
    }

    public String getStudyName() {
        return studyName;
    }

    public void setStudyName(String studyName) {
        this.studyName = studyName;
    }

    public int getStudyCode() {
        return studyCode;
    }

    public void setStudyCode(int studyCode) {
        this.studyCode = studyCode;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getEmailOfCreator() {
        return emailOfCreator;
    }

    public void setEmailOfCreator(String emailOfCreator) {
        this.emailOfCreator = emailOfCreator;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getReqParticipants() {
        return reqParticipants;
    }

    public void setReqParticipants(int reqParticipants) {
        this.reqParticipants = reqParticipants;
    }

    public int getNumOfParticipants() {
        return numOfParticipants;
    }

    public void setNumOfParticipants(int numOfParticipants) {
        this.numOfParticipants = numOfParticipants;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAnsType() {
        return ansType;
    }

    public void setAnsType(String ansType) {
        this.ansType = ansType;
    }

    public ArrayList getChoiceOfAns() {
        return choiceOfAns;
    }

    public void setChoiceOfAns(ArrayList choiceOfAns) {
        this.choiceOfAns = choiceOfAns;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public double getAverage() {
        return average;
    }

    public double getMinimum() {
        return minimum;
    }

    public double getMaximum() {
        return maximum;
    }

    public double getsD() {
        return sD;
    }     
    
}
