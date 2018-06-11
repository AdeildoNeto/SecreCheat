package pdm.recife.ifpe.edu.br.secrecheat.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import pdm.recife.ifpe.edu.br.secrecheat.Config.FirebaseConfig;
import pdm.recife.ifpe.edu.br.secrecheat.R;
import pdm.recife.ifpe.edu.br.secrecheat.Services.UserService;

public class SignupStepTwoActivity extends AppCompatActivity {


    private FirebaseUser user = FirebaseConfig.getAuth().getCurrentUser();

    private EditText username;

    private Button signupBtn;

    private UserService userService;


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

        String formatedUserNamer = username.getText().toString().replace(" ", "");

        if (formatedUserNamer.isEmpty()) {
            Toast.makeText(SignupStepTwoActivity.this, "Preencha o campo!", Toast.LENGTH_SHORT).show();

        } else {

            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(formatedUserNamer).build();

            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {

                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignupStepTwoActivity.this, "Usuário cadastrado!", Toast.LENGTH_SHORT).show();
                                userService = new UserService();

                                userService.getUser().setUid(user.getUid());
                                userService.getUser().setuserName(user.getDisplayName());
                                userService.getUser().setPhoneNumber(user.getPhoneNumber());

                                userService.saveUser(userService.getUser());

                                Intent mainIntent = new Intent(SignupStepTwoActivity.this, MainActivity.class);
                                startActivity(mainIntent);
                                finish();
                            } else {
                                Toast.makeText(SignupStepTwoActivity.this, "Algo deu errado!", Toast.LENGTH_SHORT).show();


                            }
                        }
                    });
        }

    }

}
