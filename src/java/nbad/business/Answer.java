package nbad.business;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Answer implements Serializable {
    
    private String emailOfParticipant;
    private String choice;
    private String submissionDate;

    public Answer() {
        this.emailOfParticipant = "";
        this.choice = "";
        this.submissionDate = "";
    }

    public String getEmailOfParticipant() {
        return emailOfParticipant;
    }

    public void setEmailOfParticipant(String emailOfParticipant) {
        this.emailOfParticipant = emailOfParticipant;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }
    
     
}
