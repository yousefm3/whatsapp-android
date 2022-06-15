package com.example.ex3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.ex3.adapters.contactsListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
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
        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "appDB")
                .allowMainThreadQueries().build();
        userDao = db.userDao();
        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(view -> {
            Intent i = new Intent(contactsList.this, addContact.class);
            startActivity(i);
        });
        List<Contact> c = new ArrayList<>();
        Contact c1 = new Contact("123","who","helo","dea");
        Contact c2 = new Contact("123","who","sup","dea");

        c.add(c1);
        c.add(c2);
        contacts = userDao.getContacts("who").contacts;
        RecyclerView lvItems = findViewById(R.id.lstPosts);
        final contactsListAdapter adapter = new contactsListAdapter(this);
        lvItems.setAdapter(adapter);
        lvItems.setLayoutManager(new LinearLayoutManager(this));
        adapter.setContacts(c);
    }
}