package com.example.chatApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addContact extends AppCompatActivity {
    private appDB db;
    private contactDao contactDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        db = Room.databaseBuilder(getApplicationContext(), appDB.class, "FooDB")
                .allowMainThreadQueries().build();

        contactDao = db.contactDao();
        Button btnSave = findViewById(R.id.saveBtn);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etItem = findViewById(R.id.etItem);
                //autuGenerate id + friend username
                Contact con = new Contact(0,etItem.getText().toString());
                contactDao.insert(con);
                finish();
            }
        });
    }
}