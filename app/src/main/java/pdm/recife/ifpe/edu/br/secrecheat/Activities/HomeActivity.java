package pdm.recife.ifpe.edu.br.secrecheat.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import pdm.recife.ifpe.edu.br.secrecheat.Config.FirebaseConfig;
import pdm.recife.ifpe.edu.br.secrecheat.R;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private FirebaseAuth auth = FirebaseConfig.getAuth();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setUpToolbar();
    }


    public void setUpToolbar(){
        toolbar = findViewById(R.id.home_toolbar);
        toolbar.setTitle("Secrecheat");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item_logout:
                logout();
                return true;
            case R.id.item_search:
                return true;
             default:
                 return super.onOptionsItemSelected(item);
        }

    }


    public void logout(){
        auth.signOut();
        Toast.makeText(this, "Usuário deslogado.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LogoutActivity.class);
        startActivity(intent);
        finish();
    }
}
