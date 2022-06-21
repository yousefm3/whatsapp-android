package com.example.ex3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class addContact extends AppCompatActivity {
    private AppDB db;
    private userDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        db = loginActivity.db;
        userDao = loginActivity.userDao;

        Bundle extras = getIntent().getExtras();
        String username = extras.getString("username");

        Button btnSave = findViewById(R.id.saveBtn);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etItem = findViewById(R.id.etItem);
                //autuGenerate id + friend username
                String contactName = etItem.getText().toString();
                AppDB db2 = Room.databaseBuilder(getApplicationContext(), AppDB.class, "appDB")
                        .allowMainThreadQueries().build();
                userDao userDao2 = db2.userDao();
                AppDB db_contact = Room.databaseBuilder(getApplicationContext(), AppDB.class, contactName)
                        .allowMainThreadQueries().build();
                userDao contactDao = db_contact.userDao();
                if (userDao2.getUser(contactName)!=null) {
                    if (userDao.getContact(contactName) == null) {
                        Contact con = new Contact(contactName, username,
                                userDao2.getUser(contactName).getName(), "A", 1);
                        userDao.insertContact(con);
                        contactDao.insertContact(new Contact(loginActivity.userName,contactName,
                                userDao.getUser(loginActivity.userName).getName(),"A",1));
                        Toast.makeText(getApplicationContext(),"Add succeed",Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        etItem.requestFocus();
                        etItem.setError("contact already exist");
                    }
                }else {
                    etItem.requestFocus();
                    etItem.setError("user not found");
                }
            }
        });
    }
}