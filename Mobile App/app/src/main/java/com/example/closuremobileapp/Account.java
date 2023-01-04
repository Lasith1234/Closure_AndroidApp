package com.example.closuremobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Account extends AppCompatActivity {

    Button btn_logout,btn_op_mode, btn_set_times;
    public static final String extra = "extra";
    String username = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        btn_logout = findViewById(R.id.btn_logout);
        btn_op_mode = findViewById(R.id.btn_opMode);
        btn_set_times = findViewById(R.id.btn_wakeUp);

        Intent prev_intent = getIntent();
        username = prev_intent.getStringExtra(Login.str);
        if(username==null){
            username = prev_intent.getStringExtra(OperatingMode.extra);
            if(username==null){
                username = prev_intent.getStringExtra(TimesSet.extra);
            }
        }

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent login_page = new Intent(Account.this, Login.class);
                startActivity(login_page);
            }
        });

        btn_op_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent opMode_page = new Intent(Account.this, OperatingMode.class);
                opMode_page.putExtra(extra,username);
                startActivity(opMode_page);
            }
        });

        btn_set_times.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setTimes_page = new Intent(Account.this, TimesSet.class);
                setTimes_page.putExtra(extra,username);
                startActivity(setTimes_page);
            }
        });
    }
}