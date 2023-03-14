package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username,password,repassword;
    Button signIn,signUp;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        db = new DBHelper(this);
        signIn = findViewById(R.id.signIn);
        signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                if(user.equals("")||pass.equals("")||repass.equals("")){
                    Toast.makeText(MainActivity.this, "Enter all field", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(repass)) {
                        Boolean check = db.insertData(user, pass);
                        if (check == true) {
                            Toast.makeText(MainActivity.this, "Registered new user", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), Home.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(MainActivity.this, "Invalid", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Password doesnot match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if(user.equals("")||pass.equals("")){
                    Toast.makeText(MainActivity.this, "Enter all field", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean check =db.checkUsernamePassword(user,pass);
                    if(check==true){
                        Toast.makeText(MainActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),Home.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}