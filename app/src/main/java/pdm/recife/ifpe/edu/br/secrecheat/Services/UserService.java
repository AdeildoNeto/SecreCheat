package pdm.recife.ifpe.edu.br.secrecheat.Services;

import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;

import pdm.recife.ifpe.edu.br.secrecheat.Config.FirebaseConfig;
import pdm.recife.ifpe.edu.br.secrecheat.Domain.User;

public class UserService{

    private User user;

    private DatabaseReference fbRef;

    public UserService(){

        user = new User();
        fbRef = FirebaseConfig.getFirebase();

    }

    public void saveUser(User user){
        fbRef.child("users").child(user.getIdentifier()).setValue(user);
    }

    public User getUser() {
        return user;
    }


}
