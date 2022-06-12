package com.example.ex3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class contactsList extends AppCompatActivity {
    private AppDB db;
    private userDao userDao;
    private List<Contact> contacts;
    ArrayAdapter<Contact> adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);
        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "DB")
                .allowMainThreadQueries().build();
        userDao = db.userDao();
        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(view -> {
            Intent i = new Intent(contactsList.this, addContact.class);
            startActivity(i);
        });
        contacts = userDao.getContacts("who").contacts;
        ListView lvItems = findViewById(R.id.lv_items);
        adp = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, contacts);
        lvItems.setAdapter(adp);
        lvItems.setOnItemClickListener((adapterView, view, i, l) -> {
              Intent intent = new Intent(getApplicationContext(), chat.class);
              startActivity(intent);

        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        contacts.clear();
        contacts.addAll(userDao.getContacts("who").contacts);
        adp.notifyDataSetChanged();


    }
}