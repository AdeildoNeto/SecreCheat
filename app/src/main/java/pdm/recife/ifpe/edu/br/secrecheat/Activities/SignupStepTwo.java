package pdm.recife.ifpe.edu.br.secrecheat.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import pdm.recife.ifpe.edu.br.secrecheat.R;

public class SignupStepTwo extends AppCompatActivity {


    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

     EditText username;

    Button signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_step_two);

        signupBtn = findViewById(R.id.btn_signup_two_id);

        signupBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setUser();
            }
        });
    }


    private void setUser() {


        username = findViewById(R.id.user_name_id);


        if (username.getText().toString() == "" || username.getText().toString() == null) {
            Toast.makeText(this, "Preencha os campos", Toast.LENGTH_SHORT).show();

        } else {

            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(username.getText().toString()).build();

            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        boolean result = false;
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Intent mainIntent = new Intent(SignupStepTwo.this, MainActivity.class);
                                startActivity(mainIntent);
                                finish();
                            } else {
                                Toast.makeText(SignupStepTwo.this, "Algo deu errado!", Toast.LENGTH_SHORT).show();


                            }
                        }
                    });
        }

    }

}
