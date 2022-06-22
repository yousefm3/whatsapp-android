package com.example.ex3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class imagePicker extends AppCompatActivity {
    int SELECT_IMAGE_CODE = 1;
    public static Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_picker);
        Button selectImage = findViewById(R.id.selectBtn);
        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "title"),SELECT_IMAGE_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        de.hdodenhof.circleimageview.CircleImageView pickedImage = findViewById(R.id.pickedImage);
        if (requestCode == 1){
            uri = data.getData();
            pickedImage.setImageURI(uri);
        }
    }
}