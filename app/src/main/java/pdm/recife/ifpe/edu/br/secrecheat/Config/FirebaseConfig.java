package pdm.recife.ifpe.edu.br.secrecheat.Config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public final class FirebaseConfig {

    private static DatabaseReference firebaseReference;

    private static FirebaseAuth firebaseAuth;

    public static DatabaseReference getFirebase(){

        if(firebaseReference == null){
            firebaseReference = FirebaseDatabase.getInstance().getReference();
        }

        return  firebaseReference;
    }

    public static FirebaseAuth getAuth(){
        if(firebaseAuth == null){

            firebaseAuth = FirebaseAuth.getInstance();

        }

        return firebaseAuth;
    }
}
