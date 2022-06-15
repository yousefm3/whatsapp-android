package com.example.ex3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;

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
//    ImageView contactImage = findViewById(R.id.imageviewofuser);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);
        usernameET = findViewById(R.id.login_username);
        String loggedInUsername = userName;
        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(view -> {
            Intent i = new Intent(contactsList.this, addContact.class);
            startActivity(i);
        });
//        List<Contact> c = new ArrayList<>();
//        Contact c1 = new Contact("dww","asd","helo","dea");
//        Contact c2 = new Contact("qwes","asd","sup","dea");
//        c.add(c1);
//        c.add(c2);
        contacts = userDao.getContacts(loggedInUsername).contacts;
        RecyclerView lvItems = findViewById(R.id.lstPosts);
        adapter = new contactsListAdapter(this);
        lvItems.setAdapter(adapter);
        lvItems.setLayoutManager(new LinearLayoutManager(this));
//        setOnClickListener();
        adapter.setContacts(contacts);
    }
    @Override
    protected void onResume(){
        super.onResume();
        contacts.clear();
        contacts.addAll(userDao.getContacts(userName).contacts);
        adapter.notifyDataSetChanged();
    }
//    private void setOnClickListener(){
//        listener = new contactsListAdapter.RecyclerViewClickListener() {
//            @Override
//            public void onClick(int position) {
//                Intent in = new Intent(contactsList.this, chat.class);
//                startActivity(in);
//            }
//        };
}