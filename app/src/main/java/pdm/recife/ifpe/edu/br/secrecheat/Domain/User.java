package pdm.recife.ifpe.edu.br.secrecheat.Domain;

import com.google.firebase.database.Exclude;

public class User {


    private String userName;

    private String name;

    private String phoneNumber;

    @Exclude
    private String Uid;

    public String getuserName() {
        return userName;
    }

    public void setuserName(String userName) {
        this.userName = userName;
    }

    public String getUid() {return Uid; }

    public void setUid(String uid) { this.Uid = uid; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
