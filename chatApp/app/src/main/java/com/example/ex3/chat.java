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
import com.example.ex3.viewmodels.contactsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class chat extends AppCompatActivity implements RecyclerViewItem{
    userDao userDao = loginActivity.userDao;
    private List<Message> messages;
    contactsListAdapter adapter;
    private ChatViewModel viewModel;
    private contactsViewModel _view = new contactsViewModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        RecyclerView _lvItems = findViewById(R.id.lstPosts_horizontal);
        Bundle extras = getIntent().getExtras();
        String ContactId = extras.getString("contactId");
        viewModel = new ChatViewModel(ContactId);
        System.out.println(ContactId);
        chatAdapter chat_adapter;
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
        chat_adapter = new chatAdapter(this,messages);
        lvItems.setAdapter(chat_adapter);
        lvItems.setLayoutManager(new LinearLayoutManager(this));
        viewModel.get().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                chat_adapter.setMessagesArrayList(messages);
            }
        });
        adapter = new contactsListAdapter(this, this);
        _lvItems.setAdapter(adapter);
        _lvItems.setLayoutManager(new LinearLayoutManager(this));
        _view.get().observe(this, contacts -> {
            adapter.setContacts(contacts);
        });
    }


    @Override
    public void onItemClick(int position) {
        TextView textView = findViewById(R.id.Nameofspecificuser);
        textView.setText(userDao.getContacts(loginActivity.userName).contacts.get(position).getContactDisplayName());
    }
}