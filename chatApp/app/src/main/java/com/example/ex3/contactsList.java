package com.example.ex3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.widget.EditText;


import com.example.ex3.adapters.contactsListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class contactsList extends AppCompatActivity {
    private List<Contact> contacts;
    EditText usernameET;
    String userName = loginActivity.userName;
    contactsListAdapter adapter;
    AppDB db = loginActivity.db;
    userDao userDao = loginActivity.userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);

        usernameET = findViewById(R.id.login_username);
        String loggedInUsername = userName;
        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(view -> {
            Intent i = new Intent(contactsList.this, addContact.class);
            i.putExtra("username",userName);
            startActivity(i);
        });
        FloatingActionButton settings = findViewById(R.id.settingBtn);
        settings.setOnClickListener(view -> {
            Intent i = new Intent(contactsList.this, SettingsActivity.class);
            startActivity(i);
        });
        contacts = userDao.getContacts(loggedInUsername).contacts;
        RecyclerView lvItems = findViewById(R.id.lstPosts);
        adapter = new contactsListAdapter(this);
        lvItems.setAdapter(adapter);
        lvItems.setLayoutManager(new LinearLayoutManager(this));
        adapter.setContacts(contacts);

    }
}