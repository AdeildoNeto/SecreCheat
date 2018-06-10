package pdm.recife.ifpe.edu.br.secrecheat.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.Random;

import pdm.recife.ifpe.edu.br.secrecheat.Config.FirebaseConfig;
import pdm.recife.ifpe.edu.br.secrecheat.R;

public class SignupActivity extends Activity {


    private  FirebaseAuth auth = FirebaseConfig.getAuth();

    private int token;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        signUp();

    }


    private void signUp(){
        generateToken();

        if(!(auth.getCurrentUser() != null)){
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(Arrays.asList(
                                    new AuthUI.IdpConfig.PhoneBuilder().build()
                            )).setTheme(R.style.AuthTheme)
                            .build(),
                    token);

        }else
        {

            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }
    }



    private void generateToken(){
        Random random = new Random();

        token = random.nextInt(9999 - 1000) + 1000;

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == token) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            if (resultCode == RESULT_OK) {
                Intent stepTwoIntent = new Intent(this, SignupStepTwoActivity.class);
                startActivity(stepTwoIntent);
                finish();
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button

                    return;
                }

                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {

                    return;
                }


            }
        }
    }


}
