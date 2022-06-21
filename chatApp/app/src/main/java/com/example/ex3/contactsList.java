package com.example.ex3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ex3.adapters.contactsListAdapter;
import com.example.ex3.api.ContactAPI;
import com.example.ex3.api.UserAPI;
import com.example.ex3.viewmodels.contactsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class contactsList extends AppCompatActivity implements RecyclerViewItem{
    EditText usernameET;
    String userName = loginActivity.userName;
    contactsListAdapter adapter;
    contactsListAdapter a;
    userDao userDao = loginActivity.userDao;
    user u = loginActivity.loggedIn;
    private contactsViewModel view = new contactsViewModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);
        //list of contacts from API
//        List<Contact> APIcontacts;
//        ContactAPI contactAPI= new ContactAPI();
//        APIcontacts = contactAPI.get(u.getUsername());
        //
        usernameET = findViewById(R.id.login_username);
        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(view -> {
            Intent i = new Intent(contactsList.this, addContact.class);
            i.putExtra("username",userName);
            startActivity(i);
            finish();
        });

        ImageButton logout = findViewById(R.id.backtologin);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(contactsList.this, loginActivity.class);
                startActivity(i);
                finish();
            }
        });

        FloatingActionButton settings = findViewById(R.id.settingBtn);
        settings.setOnClickListener(view -> {
            Intent i = new Intent(contactsList.this, SettingsActivity.class);
            startActivity(i);
            finish();
        });
        RecyclerView lvItems = findViewById(R.id.lstPosts);
        adapter = new contactsListAdapter(this, this);
        lvItems.setAdapter(adapter);
        lvItems.setLayoutManager(new LinearLayoutManager(this));
        view.get().observe(this, contacts -> {
            adapter.setContacts(contacts);
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent i = new Intent(contactsList.this, chat.class);
        i.putExtra("contactId",userDao.getContacts2().get(position).getContactUsername());
        startActivity(i);
        finish();
    }
}