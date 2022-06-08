package com.example.chatApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class contactsList extends AppCompatActivity {
     private appDB db;
     private contactDao contactDao;
     private List<Contact> contacts;
     ArrayAdapter<Contact> adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);
        db = Room.databaseBuilder(getApplicationContext(), appDB.class, "FooDB")
         .allowMainThreadQueries().build();
        contactDao = db.contactDao();
        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(contactsList.this, addContact.class);
                startActivity(i);
            }
        });
        contacts = contactDao.index();
        ListView lvItems = findViewById(R.id.lv_items);
        adp = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, contacts);
        lvItems.setAdapter(adp);
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(getApplicationContext(), );

            }
        });
        }
    @Override
    protected void onResume(){
        super.onResume();
        contacts.clear();
        contacts.addAll(contactDao.index());
        adp.notifyDataSetChanged();


    }
}