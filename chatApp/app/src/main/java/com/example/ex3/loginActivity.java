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

public class loginActivity extends AppCompatActivity {
    public static AppDB db;
    public static userDao userDao;
    EditText usernameET, passET;
    public static String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameET = findViewById(R.id.login_username);
        passET = findViewById(R.id.login_password);
        TextView btn = findViewById(R.id.registerlink);
        Button loginButton = findViewById(R.id.loginBtn);
        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "appDB")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        userDao = db.userDao();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passWord = passET.getText().toString();
                userName = usernameET.getText().toString();
                String correctPass = "";
                if(userDao.getUser(userName)!=null) {
                    correctPass = userDao.getUser(userName).getPass();
                }
                if(userDao.getUser(userName)!=null && (correctPass.equals(passWord))) {
                    Intent intent = new Intent(loginActivity.this, contactsList.class);
                    startActivity(intent);
                }else if (userDao.getUser(userName)==null){
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
}