package pdm.recife.ifpe.edu.br.secrecheat.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import pdm.recife.ifpe.edu.br.secrecheat.Activities.HomeActivity;
import pdm.recife.ifpe.edu.br.secrecheat.Config.FirebaseConfig;
import pdm.recife.ifpe.edu.br.secrecheat.Domain.Contact;
import pdm.recife.ifpe.edu.br.secrecheat.R;
import pdm.recife.ifpe.edu.br.secrecheat.Shared.Base64Converter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {


    private ListView contactsListView;
    private ArrayAdapter adapter;
    private ArrayList<String> contacts;
    private DatabaseReference firebase;
    private FirebaseAuth auth;

    public ContactsFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        contacts = new ArrayList<>();

        getContacts();

        View view =  inflater.inflate(R.layout.fragment_contacts, container, false);

        contactsListView = view.findViewById(R.id.lv_contacts);
        adapter = new ArrayAdapter(getActivity(), R.layout.list_view_contacts,contacts );

        contactsListView.setAdapter(adapter);

        return view;

    }


    public void getContacts(){

        auth = FirebaseConfig.getAuth();

        firebase = FirebaseConfig.getFirebase().child("users")
                .child(Base64Converter.encode(auth.getCurrentUser().getPhoneNumber()))
                .child("contacts");

        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                contacts.clear();

                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    Contact contact = data.getValue(Contact.class);

                    contacts.add(contact.getContactName());
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
