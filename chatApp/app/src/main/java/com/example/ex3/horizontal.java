package com.example.ex3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ex3.adapters.contactsListAdapter;
import com.example.ex3.viewmodels.contactsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class horizontal extends AppCompatActivity implements RecyclerViewItem {
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
        setContentView(R.layout.activity_horizontal);
        //list of contacts from API
//        List<Contact> APIcontacts;
//        ContactAPI contactAPI= new ContactAPI();
//        APIcontacts = contactAPI.get(u.getUsername());
        //
        usernameET = findViewById(R.id.login_username);
        String loggedInUsername = userName;
        RecyclerView lvItems = findViewById(R.id.lstPosts);
        adapter = new contactsListAdapter(this, this);
        lvItems.setAdapter(adapter);
        lvItems.setLayoutManager(new LinearLayoutManager(this));
        view.get().observe(this, contacts -> {
            adapter.setContacts(contacts);
        });
    }
    int row_index = -1;
    @Override
    public void onItemClick(int position) {
        TextView name = findViewById(R.id.Nameofspecificuser_horizontal);
        name.setText(userDao.getContacts(loginActivity.userName).contacts.get(position).getContactDisplayName());
    }
}