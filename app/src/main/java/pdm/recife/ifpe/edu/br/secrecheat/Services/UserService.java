package pdm.recife.ifpe.edu.br.secrecheat.Services;

import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;

import pdm.recife.ifpe.edu.br.secrecheat.Config.FirebaseConfig;
import pdm.recife.ifpe.edu.br.secrecheat.Domain.User;
import pdm.recife.ifpe.edu.br.secrecheat.Shared.Base64Converter;

public class UserService{

    private User user;

    private DatabaseReference fbRef;

    public UserService(){

        user = new User();
        fbRef = FirebaseConfig.getFirebase();

    }

    public void saveUser(User user){

        String formattedPhone = user.getPhoneNumber().replace(" ", "");

        String identifier = Base64Converter.encode(formattedPhone);

        fbRef.child("users").child(identifier).setValue(user);
    }

    public User getUser() {
        return user;
    }


}
