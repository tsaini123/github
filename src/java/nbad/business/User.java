package nbad.business;

import java.io.Serializable;

public class User implements Serializable {

    private String name;
    private String email;
    private String type;
    private int numCoins;
    private int numPostedStudies;
    private int numParticipation;
    private int numOfTotalParticipants;

    public User() {
        this.name = "";
        this.email = "";
        this.type = "";
        this.numCoins = 0;
        this.numPostedStudies = 0;
        this.numParticipation = 0;
        this.numOfTotalParticipants = 0;
    }

    public User(String name, String email, String type, int numCoins, int numPostedStudies, int numParticipation) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.numCoins = numCoins;
        this.numPostedStudies = numPostedStudies;
        this.numParticipation = numParticipation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumCoins() {
        return numCoins;
    }

    public void setNumCoins(int numCoins) {
        this.numCoins = numCoins;
    }

    public int getNumPostedStudies() {
        return numPostedStudies;
    }

    public void setNumPostedStudies(int numPostedStudies) {
        this.numPostedStudies = numPostedStudies;
    }

    public int getNumParticipation() {
        return numParticipation;
    }

    public void setNumParticipation(int numParticipation) {
        this.numParticipation = numParticipation;
    }

    public int getNumOfTotalParticipants() {
        return numOfTotalParticipants;
    }

    public void setNumOfTotalParticipants(int numOfTotalParticipants) {
        this.numOfTotalParticipants = numOfTotalParticipants;
    }

}
