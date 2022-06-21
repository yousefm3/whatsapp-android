package com.example.ex3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ex3.adapters.chatAdapter;
import com.example.ex3.adapters.contactsListAdapter;
import com.example.ex3.viewmodels.ChatViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class chat extends AppCompatActivity {
    userDao userDao = loginActivity.userDao;
    private List<Message> messages;
    chatAdapter adapter;
    private ChatViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Bundle extras = getIntent().getExtras();
        String ContactId = extras.getString("contactId");
        viewModel = new ChatViewModel(ContactId);
        System.out.println(ContactId);

        TextView textView = findViewById(R.id.Nameofspecificuser);
        textView.setText(loginActivity.usersDao2.getUser(ContactId).getName());
        ImageButton btnReturn = findViewById(R.id.backbuttonofspecificchat);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(chat.this, contactsList.class);
                startActivity(i);
                finish();
            }
        });
        ImageButton btnSend = findViewById(R.id.imageviewsendmessage);

        AppDB db_contact = Room.databaseBuilder(getApplicationContext(), AppDB.class, ContactId)
                .allowMainThreadQueries().build();
        userDao contactDao = db_contact.userDao();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
                EditText content = findViewById(R.id.getmessage);
                Message msgSender = new Message(content.getText().toString(),time,true,ContactId);
                Message msgReciever = new Message(content.getText().toString(),time,false,loginActivity.userName);
                //Message msgReviever = new Message();
                /**
                 * ...
                 */
                userDao.insertMessage(msgSender);
                contactDao.insertMessage(msgReciever);
                ContactWithMessages c = userDao.getMessages(ContactId);
                System.out.println(c.messages.size());
                viewModel.get();
                // userDao.insertMessage(msgReviever);
            }
        });
        RecyclerView lvItems = findViewById(R.id.recyclerviewofspecific);
        adapter = new chatAdapter(this,viewModel.get().getValue());
        lvItems.setAdapter(adapter);
        lvItems.setLayoutManager(new LinearLayoutManager(this));
        viewModel.get().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                adapter.setMessagesArrayList(messages);
            }
        });
    }
}