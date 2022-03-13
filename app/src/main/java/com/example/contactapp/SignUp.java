package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    EditText etEmailId, etPassword, etSecret;
    Button btButton;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etEmailId = findViewById(R.id.etEmailId);
        etPassword = findViewById(R.id.etPassword);
        etSecret = findViewById(R.id.etSecret);
        btButton = findViewById(R.id.btButton);
        db = new DBHelper(SignUp.this);

        btButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailId = etEmailId.getText().toString();
                String password = etPassword.getText().toString();
                String secret = etSecret.getText().toString();

                if (emailId.equals("") || password.equals("") || secret.equals(""))
                    Toast.makeText(SignUp.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {

                    boolean insert = db.insertSignUp(emailId, password, secret);
                    if (insert == true) {
                        Toast.makeText(SignUp.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignUp.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}
