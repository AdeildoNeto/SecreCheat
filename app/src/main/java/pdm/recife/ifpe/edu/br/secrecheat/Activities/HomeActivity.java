package pdm.recife.ifpe.edu.br.secrecheat.Activities;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.provider.ContactsContract.Data;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import pdm.recife.ifpe.edu.br.secrecheat.Adapters.TabAdapter;
import pdm.recife.ifpe.edu.br.secrecheat.Config.FirebaseConfig;
import pdm.recife.ifpe.edu.br.secrecheat.Domain.Contact;
import pdm.recife.ifpe.edu.br.secrecheat.Domain.User;
import pdm.recife.ifpe.edu.br.secrecheat.R;
import pdm.recife.ifpe.edu.br.secrecheat.Shared.Base64Converter;
import pdm.recife.ifpe.edu.br.secrecheat.Shared.SlidingTabLayout;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private FirebaseAuth auth = FirebaseConfig.getAuth();

    private SlidingTabLayout stl;

    private ViewPager vp;

    private String userContactIdentifier;

    private DatabaseReference firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setUpToolbar();
        setUpTabs();
    }


    public void setUpTabs(){

        stl = findViewById(R.id.sltl_home_id);
        vp = findViewById(R.id.vp_page_id);

        stl.setDistributeEvenly(true);

        stl.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.colorAccent));

        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        vp.setAdapter(tabAdapter);
        stl.setViewPager(vp);

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

            case R.id.item_add_person:
                openAddContact();
                return true;
             default:
                 return super.onOptionsItemSelected(item);
        }

    }


    private void openAddContact(){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeActivity.this);

        alertDialog.setTitle("Novo contato");
        alertDialog.setMessage("Telefone: ");
        alertDialog.setCancelable(false);

        final EditText userContactText = new EditText(HomeActivity.this);

        alertDialog.setView(userContactText);

        alertDialog.setPositiveButton("Cadastrar", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkUserExistence(userContactText);

            }
        });

        alertDialog.setNegativeButton("Cancelar", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkUserExistence(userContactText);

            }
        });

        alertDialog.create();
        alertDialog.show();

    }



    private void checkUserExistence(EditText userContactText){
        String userContact = userContactText.getText().toString().replace(" ", "");
        final String currentUserIdentifier = Base64Converter.encode(auth.getCurrentUser().getPhoneNumber());

        if(userContact.isEmpty()){
            Toast.makeText(HomeActivity.this, "Preencha o nome do usuário!", Toast.LENGTH_SHORT).show();
        }else{
            userContactIdentifier = Base64Converter.encode(userContact);

            firebase = FirebaseConfig.getFirebase().child("users").child(userContactIdentifier);
            firebase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if(dataSnapshot.getValue() != null){


                        User contactUser = dataSnapshot.getValue(User.class);

                        firebase = FirebaseConfig.getFirebase();
                        firebase = firebase.child("users").child(currentUserIdentifier)
                                .child("contacts").child(userContactIdentifier);


                        Contact contact = new Contact();

                        contact.setContactIdentifier(userContactIdentifier);
                        contact.setContactName(contactUser.getuserName());

                        if(firebase.setValue(contact).isSuccessful()){
                            Toast.makeText(HomeActivity.this, "Contato adicionado!", Toast.LENGTH_SHORT).show();

                        }else
                        {
                            Toast.makeText(HomeActivity.this, "Houve um erro, tente novamente!", Toast.LENGTH_SHORT).show();

                        }

                    }else
                    {
                        Toast.makeText(HomeActivity.this, "Este usuário não existe!", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }




    private void logout(){
        auth.signOut();
        Toast.makeText(this, "Usuário deslogado.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LogoutActivity.class);
        startActivity(intent);
        finish();
    }
}
