package pdm.recife.ifpe.edu.br.secrecheat.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pdm.recife.ifpe.edu.br.secrecheat.Domain.Contact;
import pdm.recife.ifpe.edu.br.secrecheat.R;

public class ContactAdapter extends ArrayAdapter<Contact> {


    private ArrayList<Contact> contacts;
    private Context context;


    public ContactAdapter(@NonNull Context c, @NonNull ArrayList<Contact> contacts) {
        super(c, 0, contacts);

        this.context = c;
        this.contacts = contacts;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = null;

        if(contacts.size() > 0){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.list_view_contacts, parent, false);

            TextView contactName = view.findViewById(R.id.tv_name);

            Contact contact = contacts.get(position);
            contactName.setText(contact.getContactName());
        }

        return view;

    }
}
