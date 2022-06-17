package com.example.ex3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class SettingsActivity extends AppCompatActivity {
    private Switch aSwitch;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        aSwitch = findViewById(R.id.mode);
        TextView bar = findViewById(R.id.Bar);
        TextView toolbar = findViewById(R.id.toolbar);
        Button addBtn = findViewById(R.id.btnAdd);
        Button settings = findViewById(R.id.settingBtn);
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            aSwitch.setChecked(true);
        }
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(aSwitch.isChecked()) {
                    bar.setBackgroundColor(Color.parseColor("#3262C4"));
//                    toolbar.setBackgroundColor(Color.parseColor("#3262C4"));
//                    settings.setBackgroundColor(Color.parseColor("#3262C4"));
//                    addBtn.setBackgroundColor(Color.parseColor("#3262C4"));
                } else {
                    bar.setBackgroundColor(Color.parseColor("#075e54"));
//                    toolbar.setBackgroundColor(Color.parseColor("#075e54"));
//                    settings.setBackgroundColor(Color.parseColor("#075e54"));
//                    addBtn.setBackgroundColor(Color.parseColor("#075e54"));
                }
            }
        });
    }

}