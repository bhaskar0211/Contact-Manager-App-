package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tvSignIn,tvSignUp,tvEmail,tvPass;
    EditText etEmailId,etPassword;
    Button btButton;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSignUp=(TextView) findViewById(R.id.tvSignUp);
        etEmailId = (EditText) findViewById(R.id.etEmailId);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btButton = (Button)findViewById(R.id.btButton);
        DB=new DBHelper(this);

        btButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = etEmailId.getText().toString();
                String pass = etPassword.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass == true){
                        Toast.makeText(MainActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), CreateContact.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),SignUp.class);
                startActivity(i);
                setContentView(R.layout.activity_sign_up);
            }
        });
}
}