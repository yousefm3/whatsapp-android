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

import com.example.ex3.api.UserAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginActivity extends AppCompatActivity {
    public static AppDB db;
    public static userDao userDao;
    EditText usernameET, passET;
    public static user loggedIn;
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
                    correctPass = userDao.getUser(userName).getPassword();
                }
                if(userDao.getUser(userName)!=null && (correctPass.equals(passWord))) {
                    loggedIn = userDao.getUser(userName);
                    loginUser(userName, passWord);
                    Intent intent = new Intent(loginActivity.this, horizontal.class);
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
    private void loginUser(String username, String password) {
        String name = "default";
        if(userDao.getUser(username)!=null) {
            name = userDao.getUser(username).getName();
        }
        user u = new user(username,name, password,1,"server", "token");
        Call<String> call = UserAPI.getInstance().getApi().login(u);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String resp = response.body();
                if (response.isSuccessful()){
                    Toast.makeText(loginActivity.this,"welcome" + u.getName(), Toast.LENGTH_SHORT).show();
                    u.setToken(String.valueOf(resp));
                }
                else{
                    Toast.makeText(loginActivity.this,String.valueOf(resp), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(loginActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}