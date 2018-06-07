package pdm.recife.ifpe.edu.br.secrecheat.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import pdm.recife.ifpe.edu.br.secrecheat.Domain.User;
import pdm.recife.ifpe.edu.br.secrecheat.R;

public class MainActivity extends AppCompatActivity {


    private DatabaseReference firebase = FirebaseDatabase.getInstance().getReference();

    FirebaseAuth auth = FirebaseAuth.getInstance();

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (auth.getCurrentUser() != null) {



        } else {
            Intent signupIt = new Intent(this, SignupActivity.class);
            startActivity(signupIt);
            finish();

        }

    }



}
