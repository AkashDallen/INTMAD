package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;



public class Lab11 extends AppCompatActivity {
    public static final String NAME="Name";
    public static final String AGE="Age";
    private TextView nameText,ageText;
    private String name;
    private int age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab11);
        nameText=findViewById(R.id.userNamee);
        ageText= findViewById(R.id.userAgee);
        Intent i=getIntent();
        name=i.getStringExtra(NAME);
        age=i.getIntExtra(AGE,0);
        nameText.setText(name);
        ageText.setText(age);
    }
}