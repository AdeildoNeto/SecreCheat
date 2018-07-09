package pdm.recife.ifpe.edu.br.secrecheat.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import pdm.recife.ifpe.edu.br.secrecheat.Config.FirebaseConfig;
import pdm.recife.ifpe.edu.br.secrecheat.Domain.Message;
import pdm.recife.ifpe.edu.br.secrecheat.R;
import pdm.recife.ifpe.edu.br.secrecheat.Shared.Base64Converter;

public class MessageAdapter extends ArrayAdapter<Message>{

    private Context context;

    private ArrayList<Message> messages;



    public MessageAdapter(@NonNull Context context, @NonNull ArrayList<Message> objects) {
        super(context, 0, objects);
        this.context = context;

        this.messages = objects;
    }



    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {

        View view = null;

        String userId = Base64Converter.encode(FirebaseConfig.getAuth().getCurrentUser().getPhoneNumber());

        if(messages != null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            Message message = messages.get(position);


            if(userId.equals(message.getUserId())){
                view = inflater.inflate(R.layout.item_message_sender, parent, false);

            }else
            {
                view = inflater.inflate(R.layout.item_message_receive, parent, false);
            }



            TextView textMessage = view.findViewById(R.id.tv_message_id);

            textMessage.setText(message.getMessage());
        }



        return view;
    }
}
