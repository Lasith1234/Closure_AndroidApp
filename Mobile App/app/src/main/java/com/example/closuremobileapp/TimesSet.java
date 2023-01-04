package com.example.closuremobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.database.Cursor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class TimesSet extends AppCompatActivity {

    TextView wakeUpTime;
    TextView sleepTime;
    Button btn_setWakeUp;
    Button btn_setSleep;
    ImageView btn_back;
    String username;
    public static final String extra = "extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times_set);

        DatabaseOperations current_db = new DatabaseOperations(TimesSet.this);

        Intent prev_intent = getIntent();
        username = prev_intent.getStringExtra(Account.extra);

        wakeUpTime = findViewById(R.id.WakeTime);
        sleepTime = findViewById(R.id.set_Sleep_time);
        btn_setWakeUp = findViewById(R.id.btn_setTime);
        btn_setSleep = findViewById(R.id.btn_setSleepTime);
        btn_back = findViewById(R.id.btn_setTimes_back);

//        String[] times = current_db.getTimes(username);
        Cursor times = current_db.getTimes(username);

        if(times != null && times.moveToFirst()){
            wakeUpTime.setText(times.getString(0));
            sleepTime.setText(times.getString(1));
            times.close();
        }


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back_page = new Intent(TimesSet.this, Account.class);
                back_page.putExtra(extra,username);
                startActivity(back_page);
            }
        });
        btn_setSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hours = calendar.get(Calendar.HOUR_OF_DAY);
                int mins = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(TimesSet.this, androidx.appcompat.R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        c.set(Calendar.MINUTE,minute);
                        c.setTimeZone(TimeZone.getDefault());
                        SimpleDateFormat format = new SimpleDateFormat("k:mm a");
                        String time = format.format(c.getTime());
                        sleepTime.setText(time);
                        current_db.setSleepTime(username,time);

                    }
                },hours, mins,false);
                timePickerDialog.show();
            }
        });

        btn_setWakeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hours = calendar.get(Calendar.HOUR_OF_DAY);
                int mins = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(TimesSet.this, androidx.appcompat.R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        c.set(Calendar.MINUTE,minute);
                        c.setTimeZone(TimeZone.getDefault());
                        SimpleDateFormat format = new SimpleDateFormat("k:mm a");
                        String time = format.format(c.getTime());
                        wakeUpTime.setText(time);
                        current_db.setWakeTime(username,time);
                    }
                },hours, mins,false);
                timePickerDialog.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        DatabaseOperations current_db = new DatabaseOperations(TimesSet.this);
        Cursor times = current_db.getTimes(username);
        if(times != null && times.moveToFirst()){
            wakeUpTime.setText(times.getString(0));
            sleepTime.setText(times.getString(1));
            times.close();
        }

    }
}