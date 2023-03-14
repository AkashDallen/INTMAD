package com.example.sms;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.Manifest;

public class MainActivity extends AppCompatActivity {
    EditText name,number,mess;
    Button send;
    ImageButton ib;
    String nam,num,mes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent pickContact=new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        name = findViewById(R.id.name);
        number = findViewById(R.id.number);
        mess = findViewById(R.id.mess);
        send = findViewById(R.id.send);
        ib = findViewById(R.id.contact);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(pickContact,1);
            }
        });
        requestForPermissions();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num=number.getText().toString();
                mes=mess.getText().toString();
                if (num.equalsIgnoreCase("")||mes.equalsIgnoreCase("")){
                    Toast.makeText(MainActivity.this, "enter all field", Toast.LENGTH_SHORT).show();
                    name.requestFocus();
                }else{
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(num,null,mes,null,null);
                }
            }
        });
    }
    private void requestForPermissions(){
        if(!hasContactPermission()){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},1);
        }
        if(!hasSmsPermission()){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);
        }
    }
    private boolean hasContactPermission(){
      return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED;
    }
    private  boolean hasSmsPermission(){
        return ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!= Activity.RESULT_OK) return;
        Uri contactUri = data.getData();
        Cursor cursor = this.getContentResolver().query(contactUri,new String[]{"display_name","data1"},null,null,null);
        try{
            if(cursor.getCount()==0)return;
            cursor.moveToFirst();
            name.setText(cursor.getString(0));
            number.setText(cursor.getString(1));
        }finally {
            System.out.println("contact cursor");
        }
    }
}