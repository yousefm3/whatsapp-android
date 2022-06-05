package com.example.ex3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import java.io.FileNotFoundException;
import java.io.IOException;

public class registerActivity extends AppCompatActivity {
    int SELECT_PHOTO = 1;
    Uri uri;
    ImageView imageView;
    EditText usernameIt, displayNameIt, passIt, passConfirmationIt;
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
                     Toast.makeText(getApplicationContext(),"Registeration succeed",Toast.LENGTH_SHORT).show();
                     Intent intent = new Intent(registerActivity.this, loginActivity.class);
                     startActivity(intent);
                 } else{
                     Toast.makeText(getApplicationContext(),"Registeration failed",Toast.LENGTH_SHORT).show();
                 }

            }
        });
        //add image button
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

    private boolean validateInfo(String userName, String displayName, String pass, String passConfirmation) {
        if (userName.length() == 0){
            usernameIt.requestFocus();
            usernameIt.setError("Field cannot be empty");
            return false;
        } else if(displayName.length() == 0){
            displayNameIt.requestFocus();
            displayNameIt.setError("Field cannot be empty");
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