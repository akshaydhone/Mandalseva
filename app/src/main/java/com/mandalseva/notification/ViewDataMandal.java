package com.mandalseva.notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mandalseva.notification.Interface.IFirebaseLoadDone;
import com.mandalseva.notification.Model.Clients;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

public class ViewDataMandal extends AppCompatActivity implements IFirebaseLoadDone {
    SearchableSpinner searchableSpinner;
    EditText e1,e2;
Spinner s1;

    Button b1,b2;

    DatabaseReference clientsRef;
    IFirebaseLoadDone iFirebaseLoadDone;
    boolean isFirstTimeClick=true;
    List<Clients>clients;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data_mandal);
        searchableSpinner=(SearchableSpinner)findViewById(R.id.searchable_spinner);
        e1=(EditText)findViewById(R.id.e1);
        e2=(EditText)findViewById(R.id.e2);
        b2=(Button)findViewById(R.id.b2);
        s1 = (Spinner) findViewById(R.id.s1);


        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.tag_arraysup1, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);

        //init db
        clientsRef= FirebaseDatabase.getInstance().getReference("Year2018-19");

        //load interface
        iFirebaseLoadDone=this;

        //get data
        clientsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Clients>clients=new ArrayList<>();
                for(DataSnapshot clientSnapshot:dataSnapshot.getChildren())
                {
                    clients.add(clientSnapshot.getValue(Clients.class));
                }
                iFirebaseLoadDone.onFirebaseLoadSuccess(clients);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                iFirebaseLoadDone.onFirebaseLoadFail(databaseError.getMessage());
            }
        });






        searchableSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if(!isFirstTimeClick){
                    Clients client=clients.get(position);
                    e1.setText(client.getDate());
                    e2.setText(client.getAmount());


                }
                else
                    isFirstTimeClick=false;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e1.setText("");
                e2.setText("");
            }
        });








        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String spinnerValue = s1.getSelectedItem().toString();
                //String spinnerValue1 = s3.getSelectedItem().toString();

                if (spinnerValue.equals("2017\"-\"18"))

                {
                    //s3.setAdapter(adapter1);
                    //s4.setAdapter(adapter2);
                    //NewCall2.s4.setAdapter(adapter2);
                }

                else if (spinnerValue.equals("2018\"-\"19"))
                {

                    //s3.setAdapter(adapter3);
                    //s4.setAdapter(adapter4);
                    //NewCall2.s4.setAdapter(adapter4);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){}
        });
    }

    @Override
    public void onFirebaseLoadSuccess(List<Clients> clientsList) {


        clients=clientsList;
        List<String>names_list=new ArrayList<>();


        for(Clients clients:clientsList)
            names_list.add(clients.getName());



        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,names_list);
        searchableSpinner.setAdapter(adapter);



    }

    @Override
    public void onFirebaseLoadFail(String message) {

    }
}
