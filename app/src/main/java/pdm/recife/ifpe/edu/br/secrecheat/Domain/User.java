package pdm.recife.ifpe.edu.br.secrecheat.Domain;

import com.google.firebase.database.Exclude;

public class User {


    private String userName;

    private String phoneNumber;

    @Exclude
    private String identifier;

    public String getuserName() {
        return userName;
    }

    public void setuserName(String userName) {
        this.userName = userName;
    }

    public String getIdentifier() {return identifier; }

    public void setIdentifier(String identifier) { this.identifier = identifier; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
