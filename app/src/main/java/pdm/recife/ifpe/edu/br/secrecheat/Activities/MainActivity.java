package pdm.recife.ifpe.edu.br.secrecheat.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import pdm.recife.ifpe.edu.br.secrecheat.Config.FirebaseConfig;
import pdm.recife.ifpe.edu.br.secrecheat.Domain.User;
import pdm.recife.ifpe.edu.br.secrecheat.R;

public class MainActivity extends AppCompatActivity {


    private FirebaseAuth auth = FirebaseConfig.getAuth();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(auth.getCurrentUser() != null ){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
