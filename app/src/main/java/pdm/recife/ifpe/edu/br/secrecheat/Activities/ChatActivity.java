package pdm.recife.ifpe.edu.br.secrecheat.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import pdm.recife.ifpe.edu.br.secrecheat.Adapters.MessageAdapter;
import pdm.recife.ifpe.edu.br.secrecheat.Config.FirebaseConfig;
import pdm.recife.ifpe.edu.br.secrecheat.Domain.Message;
import pdm.recife.ifpe.edu.br.secrecheat.R;
import pdm.recife.ifpe.edu.br.secrecheat.Shared.Base64Converter;

public class ChatActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private String contactName;


    FirebaseUser fbUser = FirebaseConfig.getAuth().getCurrentUser();

    DatabaseReference firebase;

    private EditText editTextMessage;
    private ImageButton sendButton;

    private ListView chatListView;

    private ArrayList<Message> messagesList;

    private ArrayAdapter<Message> adapter;

    private String userIdentifier;

    private String contactIdentifier;

    private ValueEventListener messageEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        userIdentifier = Base64Converter.encode(fbUser.getPhoneNumber());

        toolbar = findViewById(R.id.tb_chat);

        sendButton = findViewById(R.id.btn_send);

        editTextMessage = findViewById(R.id.txt_message);

        Bundle extra = getIntent().getExtras();

        chatListView = findViewById(R.id.lv_chats);

        messagesList = new ArrayList<>();

        adapter = new MessageAdapter(ChatActivity.this, messagesList);

        chatListView.setAdapter(adapter);


        if(extra != null){
            contactName = extra.getString("username");
            contactIdentifier = extra.getString("phone");
        }




        firebase = FirebaseConfig.getFirebase()
                .child("messages").child(userIdentifier).child(contactIdentifier);

        messageEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                messagesList.clear();
                for( DataSnapshot data: dataSnapshot.getChildren()){
                    Message message = data.getValue(Message.class);

                    messagesList.add(message);
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        firebase.addValueEventListener(messageEventListener);



        toolbar.setTitle(contactName);
        toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);
        setSupportActionBar(toolbar);

        sendButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String textMessage = editTextMessage.getText().toString();
                if(textMessage.isEmpty()){
                    Toast.makeText(ChatActivity.this,
                            "Digite uma mensagem para enviar!",
                            Toast.LENGTH_SHORT).show();
                    
                }else
                {
                    Message message = new Message();

                    message.setUserId(userIdentifier);
                    message.setMessage(textMessage);

                   Boolean returnMessageSender = saveMessage(userIdentifier, contactIdentifier, message);

                    if(!returnMessageSender){
                        Toast.makeText(ChatActivity.this,"Problema ao salvar, tente novamente",
                                Toast.LENGTH_SHORT).show();
                    }else
                    {

                        Boolean returnMessageContact =  saveMessage(contactIdentifier, userIdentifier, message);
                        if(!returnMessageContact) {
                            Toast.makeText(ChatActivity.this, "Problema ao salvar, tente novamente",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    editTextMessage.setText("");



                }
            }
        });

    }


    private boolean saveMessage(String userIdentifier, String contactIdentifier, Message message){
        try{

                firebase = FirebaseConfig.getFirebase().child("messages").child(userIdentifier);

                firebase.child(contactIdentifier).push().setValue(message);

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    private boolean saveChat(){
        try {

            firebase = FirebaseConfig.getFirebase().child("chats");
            

            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebase.removeEventListener(messageEventListener);
    }
}
