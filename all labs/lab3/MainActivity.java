package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment1 f1 = new Fragment1();
        Fragment2 f2 = new Fragment2();
        FragmentManager mf = getSupportFragmentManager();

        FragmentTransaction ft = mf.beginTransaction();

        ft.add(R.id.frameLayout,f1);
//add the Fragment
        Button b1 = findViewById(R.id.button);
        Button b2 = findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager mf = getSupportFragmentManager();
                FragmentTransaction ft = mf.beginTransaction();
                ft.replace(R.id.frameLayout,f1);
//replace the Fragment
                Toast.makeText(MainActivity.this,"Changed to Fragment1",Toast.LENGTH_LONG).show();
                ft.commit();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager maf = getSupportFragmentManager();
                FragmentTransaction ft = mf.beginTransaction();
                ft.replace(R.id.frameLayout,f2);
                Toast.makeText(MainActivity.this,"Changed to Fragment1",Toast.LENGTH_LONG).show();
                        ft.commit();
            }
        });
    }
}