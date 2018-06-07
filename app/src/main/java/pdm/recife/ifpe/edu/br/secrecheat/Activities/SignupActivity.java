package pdm.recife.ifpe.edu.br.secrecheat.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

import pdm.recife.ifpe.edu.br.secrecheat.R;

public class SignupActivity extends Activity {



    FirebaseAuth auth = FirebaseAuth.getInstance();

    public static final int RC_SIGN_IN = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        putPhoneMask();

    }


    private void putPhoneMask(){
        EditText phoneNumber;
        phoneNumber = findViewById(R.id.txt_phone_id);
        SimpleMaskFormatter smPhone =  new SimpleMaskFormatter("NNNNN-NNNN");
        MaskTextWatcher maskPhone = new MaskTextWatcher(phoneNumber, smPhone);
        phoneNumber.addTextChangedListener(maskPhone);

        EditText phoneDDD = findViewById(R.id.txt_phone_ddd_id);

        SimpleMaskFormatter smDDD = new SimpleMaskFormatter("NN");
        MaskTextWatcher maskDDD = new MaskTextWatcher(phoneDDD, smDDD);
        phoneDDD.addTextChangedListener(maskDDD);

        EditText phoneCountryCode = findViewById(R.id.txt_phone_country_code_id);

        SimpleMaskFormatter smCountryCode = new SimpleMaskFormatter("+NN");
        MaskTextWatcher maskCountryCode = new MaskTextWatcher(phoneCountryCode, smCountryCode);
        phoneCountryCode.addTextChangedListener(maskCountryCode);

    }

    private void signUp(){

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
