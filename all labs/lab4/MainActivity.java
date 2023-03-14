package com.example.lab4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b1;
    TextView t;
    ProgressBar p;
    int status=0;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.button);
        t =findViewById(R.id.textStatus);
        p = findViewById(R.id.progressBar);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert();
            }
        });
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (status<100){
//                    status +=1;
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            p.setProgress(status);
////                            tv.setText(status);
//                        }
//                    });
//                    try{
//                        Thread.sleep(200);
//                    }catch(InterruptedException e){
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//        }).start();
    }
    public void showAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("alert");
        builder.setMessage("select yes for progress");
        builder.setIcon(R.mipmap.ic_launcher_round);
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                showProgress();
                dialogInterface.cancel();
            }
        });

        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "clicked no", Toast.LENGTH_SHORT).show();
                dialogInterface.cancel();
            }
        });
        builder.create().show();
    }
    public void showProgress(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (status<100){
                    status +=1;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            p.setProgress(status);
                            t.setText(status+" %");
                        }
                    });
                    try{
                        Thread.sleep(200);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }

        }).start();
   }
}