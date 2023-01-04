package com.example.closuremobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Button login;
    ImageButton back;
    ImageView btn_back;
    public static final String str = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.btn_login);
        //back = findViewById(R.id.back_main);
        btn_back = findViewById(R.id.btn_op_back);

        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent account_intent = new Intent(Login.this, Account.class);
                DatabaseOperations dbOperations = new DatabaseOperations(Login.this);

                String input_username = username.getText().toString();
                String input_password = password.getText().toString();
//              boolean success = dbOperations.getPassword(username.getText().toString(),password.getText().toString());
                boolean success = dbOperations.getPassword(input_username,input_password);

                if(success){

                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    account_intent.putExtra(str,input_username);
                    startActivity(account_intent);
                }else{
                    Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_intent = new Intent(Login.this, MainActivity.class);
                startActivity(main_intent);
            }
        });
    }
}