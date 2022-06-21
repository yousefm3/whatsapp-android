package com.example.ex3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ex3.api.UserAPI;

import java.io.FileNotFoundException;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class registerActivity extends AppCompatActivity {
    int SELECT_PHOTO = 1;
    Uri uri;
    ImageView imageView;
    EditText usernameIt, displayNameIt, passIt, passConfirmationIt;
    private AppDB db;
    private userDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernameIt = findViewById(R.id.id_username);
        displayNameIt = findViewById(R.id.id_displayName);
        passIt = findViewById(R.id.id_password);
        passConfirmationIt = findViewById(R.id.id_passConfirmation);
        Button registerBtn = findViewById(R.id.registerBtn);
        //sign in button
        TextView btn = findViewById(R.id.signinlink);
        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "appDB")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        userDao = db.userDao();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registerActivity.this, loginActivity.class);
                startActivity(intent);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = usernameIt.getText().toString();
                String displayName = displayNameIt.getText().toString();
                String pass = passIt.getText().toString();
                String passConfirmation = passConfirmationIt.getText().toString();
                boolean check = validateInfo(userName, displayName, pass, passConfirmation);
                if (check){
                    registerUser(userName, displayName, pass);
                    user u = new user(userName, displayName, pass,1, "server","token");
                    userDao.insertUser(u);
                    Toast.makeText(getApplicationContext(),"Registeration succeed",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(registerActivity.this, loginActivity.class);
                    AppDB db2 = Room.databaseBuilder(getApplicationContext(), AppDB.class, userName)
                            .allowMainThreadQueries().build();
                    userDao userDao2 = db2.userDao();
                    userDao2.insertUser(u);
                    startActivity(intent);
                    finish();
                } else{
                    Toast.makeText(getApplicationContext(),"Registeration failed",Toast.LENGTH_SHORT).show();
                }

            }
        });
        ///add image button
        Button choose = findViewById(R.id.AddImageBtn);
        imageView = findViewById(R.id.image);
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,SELECT_PHOTO);
            }
        });
    }
    private void registerUser(String username, String name, String password) {
        user u = new user(username,name, password, 1,"server","token");
        Call<String> call = UserAPI.getInstance().getApi().register(u);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String responseRegister = response.body();
                if (response.isSuccessful()){
                    Toast.makeText(registerActivity.this,String.valueOf(responseRegister), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(registerActivity.this,String.valueOf(responseRegister), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(registerActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private boolean validateInfo(String userName, String displayName, String pass, String passConfirmation) {
        if (userName.length() == 0){
            usernameIt.requestFocus();
            usernameIt.setError("Field cannot be empty");
            return false;
        } else if(displayName.length() == 0){
            displayNameIt.requestFocus();
            displayNameIt.setError("Field cannot be empty");
            return false;
        } else if (userDao.getUser(userName)!=null) {
            usernameIt.requestFocus();
            usernameIt.setError("username exist");
            return false;
        }
        else if(pass.length() == 0){
            passIt.requestFocus();
            passIt.setError("Field cannot be empty");
            return false;
        }
        else if(passConfirmation.length() == 0){
            passConfirmationIt.requestFocus();
            passConfirmationIt.setError("Field cannot be empty");
            return false;
        }else if(userName.length() < 4){
            usernameIt.requestFocus();
            usernameIt.setError("Username length must be at least 4");
            return false;
        }else if(displayNameIt.length() < 4){
            displayNameIt.requestFocus();
            displayNameIt.setError("display name length must be at least 4");
            return false;
        }
        else if(!pass.matches(".{8,20}")){
            passIt.requestFocus();
            passIt.setError("Password length must be at least 8 and maximum 20");
            return false;
        }else if(!pass.matches(".*\\d+.*")){
            passIt.requestFocus();
            passIt.setError("Password must contain at least one digit");
            return false;
        }else if(!containLowerCase(pass)){
            passIt.requestFocus();
            passIt.setError("Password must contain at least one lower case English letter");
            return false;
        }else if(!containUpperCase(pass)){
            passIt.requestFocus();
            passIt.setError("Password must contain at least one upper case English letter");
            return false;
        }else if(!pass.equals(passConfirmation)){
            passConfirmationIt.requestFocus();
            passConfirmationIt.setError("Password confirmation don't match password");
            return false;
        }
        return true;
    }
    boolean containLowerCase(String str) {
        boolean lowerCaseFlag = false;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            }
        }
        if (lowerCaseFlag) {
            return true;
        }
        return false;
    }
    boolean containUpperCase(String str) {
        boolean upperCaseFlag = false;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isUpperCase(ch)) {
                upperCaseFlag = true;
            }
        }
        if (upperCaseFlag) {
            return true;
        }
        return false;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SELECT_PHOTO && resultCode ==RESULT_OK && data != null && data.getData() != null){
            uri = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}