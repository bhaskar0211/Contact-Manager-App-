package com.example.contactapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class CreateContact extends AppCompatActivity {
    EditText etName,etPhone,etEmail;
    Button btSave,btView;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        etName=(EditText)findViewById(R.id.etName);
        etPhone=(EditText)findViewById(R.id.etPhone);
        etEmail=(EditText)findViewById(R.id.etEmail);
        btSave=(Button)findViewById(R.id.btSave);
        btView=(Button)findViewById(R.id.btView);
        db = new DBHelper(CreateContact.this);

       btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();
                String email = etEmail.getText().toString();

                if(name.equals("")||phone.equals("")||email.equals(""))
                    Toast.makeText(CreateContact.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{



                            Boolean insert = db.insertContact(name,phone,email);
                            if(insert == true){
                                Toast.makeText(CreateContact.this, "Saved successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),CreateContact.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(CreateContact.this, "Saved failed", Toast.LENGTH_SHORT).show();
                            }
                        }




            }
        });

       btView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Cursor res = db.getdata();
               if(res.getCount()==0){
                   Toast.makeText(CreateContact.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                   return;
               }
               StringBuffer buffer = new StringBuffer();
               while(res.moveToNext()){
                   buffer.append("Name :"+res.getString(0)+"\n");
                   buffer.append("Contact :"+res.getString(1)+"\n");
                   buffer.append("Email :"+res.getString(2)+"\n\n");
               }

               AlertDialog.Builder builder = new AlertDialog.Builder(CreateContact.this);
               builder.setCancelable(true);
               builder.setTitle("User Entries");
               builder.setMessage(buffer.toString());
               builder.show();
           }
       });



    }}


