package com.example.ex3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ex3.AppDB;
import com.example.ex3.api.UserAPI;
import com.example.ex3.contactsList;
import com.example.ex3.registerActivity;
import com.example.ex3.user;
import com.example.ex3.userDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginActivity extends AppCompatActivity {
    public static AppDB db;
    public static com.example.ex3.userDao userDao;
    EditText usernameET, passET;
    public static user loggedIn;
    public static String userName;
    private static AppDB usersDB;
    public static String token;
    public static userDao usersDao2;
    String uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameET = findViewById(R.id.login_username);
        passET = findViewById(R.id.login_password);
        TextView btn = findViewById(R.id.registerlink);
        Button loginButton = findViewById(R.id.loginBtn);
        usersDB = Room.databaseBuilder(getApplicationContext(), AppDB.class, "appDB")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        usersDao2 = usersDB.userDao();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passWord = passET.getText().toString();
                userName = usernameET.getText().toString();
                uri = registerActivity.uri;
                String correctPass = "";
                if(usersDao2.getUser(userName)!=null) {
                    correctPass = usersDao2.getUser(userName).getPassword();
                }
                if(usersDao2.getUser(userName)!=null && (correctPass.equals(passWord))) {
                    loggedIn = usersDao2.getUser(userName);
                    loginUser(userName, passWord);
                    Intent intent = new Intent(loginActivity.this, contactsList.class);
                    db = Room.databaseBuilder(getApplicationContext(), AppDB.class, userName)
                            .allowMainThreadQueries().build();
                    userDao = db.userDao();
                    startActivity(intent);
                    finish();
                }else if (usersDao2.getUser(userName)==null){
                    usernameET.requestFocus();
                    usernameET.setError("Username Not Found");
                    Toast.makeText(getApplicationContext(),"login failed",Toast.LENGTH_SHORT).show();
                }else if(!correctPass.equals(passWord)){
                    passET.requestFocus();
                    passET.setError("Password is not correct");
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginActivity.this, registerActivity.class);
                startActivity(intent);
            }
        });
    }
    private void loginUser(String username, String password) {
        String name = "default";
        if(usersDao2.getUser(username)!=null) {
            name = usersDao2.getUser(username).getName();
        }
    }
}