        package com.example.ex3;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ImageButton;

        import com.example.ex3.adapters.chatAdapter;
        import com.example.ex3.adapters.contactsListAdapter;
        import com.google.android.material.floatingactionbutton.FloatingActionButton;

        import java.util.List;

        public class chat extends AppCompatActivity {
            userDao userDao = loginActivity.userDao;
            private List<Message> messages;
            chatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ImageButton btnReturn = findViewById(R.id.backbuttonofspecificchat);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(chat.this, contactsList.class);
                startActivity(i);
            }
        });
        messages.add(new Message(1,"a","saas",true,"koo"));
        messages.add(new Message(2,"a2","saas",true,"koo"));

        RecyclerView lvItems = findViewById(R.id.recyclerviewofspecific);
        adapter = new chatAdapter(this,messages);
        lvItems.setAdapter(adapter);
        lvItems.setLayoutManager(new LinearLayoutManager(this));

    }
}