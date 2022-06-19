        package com.example.ex3;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.lifecycle.Observer;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

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
            private ChatViewModel view = new ChatViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Bundle extras = getIntent().getExtras();
        String ContactId = extras.getString("contactId");
        System.out.println(ContactId);

        TextView textView = findViewById(R.id.Nameofspecificuser);
        textView.setText(userDao.getUser(ContactId).getName());
        ImageButton btnReturn = findViewById(R.id.backbuttonofspecificchat);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(chat.this, contactsList.class);
                startActivity(i);
            }
        });
        ImageButton btnSend = findViewById(R.id.imageviewsendmessage);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
                EditText content = findViewById(R.id.getmessage);
                Message msgSender = new Message(content.getText().toString(),time,true,loginActivity.userName);
                //Message msgReviever = new Message();
                /**
                 * ...
                 */
                System.out.println(msgSender);
                userDao.insertMessage(msgSender);
                // userDao.insertMessage(msgReviever);
            }
        });
        RecyclerView lvItems = findViewById(R.id.recyclerviewofspecific);
        adapter = new chatAdapter(this,messages);
        lvItems.setAdapter(adapter);
        lvItems.setLayoutManager(new LinearLayoutManager(this));
        view.get().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                adapter.setMessagesArrayList(messages);
            }
        });
    }
}