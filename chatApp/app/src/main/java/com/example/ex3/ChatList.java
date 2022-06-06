package com.example.ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ChatList extends AppCompatActivity {
    List<String> contacts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        contacts.add("Mohamed");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        ListView lvItems = findViewById(R.id.lvItems);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, contacts);
        lvItems.setAdapter(adapter);
    }
}