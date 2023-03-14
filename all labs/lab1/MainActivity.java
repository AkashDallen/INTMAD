package com.example.lab1;

import static com.example.lab1.R.id.button;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText nameText,ageText;
    Button b;
    private String name;
    private int age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameText=findViewById(R.id.userName);
        ageText=findViewById(R.id.userAge);
        b=findViewById(button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });
    }
    public void openActivity(){
        name=nameText.getText().toString();
        age=Integer.parseInt(ageText.getText().toString());
        Intent i=new Intent(this,Lab11.class);
        i.putExtra(Lab11.AGE,name);
        i.putExtra(Lab11.AGE,age);
        startActivity(i);
    }
}