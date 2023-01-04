package com.example.closuremobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class OperatingMode extends AppCompatActivity {

    ImageView btn_back;
    SwitchCompat modeSwitch;
    public static final String extra = "extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operating_mode);
        modeSwitch = findViewById(R.id.mode_switch);
        DatabaseOperations current_db = new DatabaseOperations(OperatingMode.this);

        btn_back = findViewById(R.id.btn_op_back);

        Intent prev_intent = getIntent();
        String username = prev_intent.getStringExtra(Account.extra);

        Cursor op_mode = current_db.getMode(username);
        if(op_mode != null && op_mode.moveToFirst()){
            if(op_mode.getString(0).equals("Auto")){
                modeSwitch.setChecked(true);
            }else if(op_mode.getString(0).equals("Manual")){
                modeSwitch.setChecked(false);
            }
        }

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent account_page = new Intent(OperatingMode.this, Account.class);
                account_page.putExtra(extra,username);
                startActivity(account_page);
            }
        });

        modeSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean current_mode = modeSwitch.isChecked();
                if(current_mode){
                    current_db.setMode(username,"Auto");
                }else{
                    current_db.setMode(username,"Manual");
                }
            }
        });

    }
}