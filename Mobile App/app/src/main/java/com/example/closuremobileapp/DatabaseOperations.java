package com.example.closuremobileapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.SQLOutput;

public class DatabaseOperations extends SQLiteOpenHelper {

    public static final String USERS_TABLE = "USERS_TABLE";
    public static final String USER_SETTINGS_TABLE = "USER_SETTINGS_TABLE";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String TYPE = "USER_TYPE";
    public static final String MODE = "MODE";
    public static final String WAKE_T = "WAKE_UP_TIME";
    public static final String SLEEP_T = "SLEEP_TIME";

    public DatabaseOperations(@Nullable Context context) {
        super(context, "closureMobile.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//      String createUsersTable = "CREATE TABLE "+ USERS_TABLE+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+USERNAME+" TEXT, "+PASSWORD+" TEXT)";
        String createUsersTable = "CREATE TABLE "+ USERS_TABLE+" ("+USERNAME+" TEXT PRIMARY KEY, "+PASSWORD+" TEXT)";
        String createUserSettingsTable = "CREATE TABLE "+ USER_SETTINGS_TABLE+" ("+USERNAME+" TEXT, "+TYPE+" TEXT, "+MODE+" TEXT, "+WAKE_T+" TEXT, "+SLEEP_T+" TEXT, FOREIGN KEY (USERNAME) REFERENCES USERS_TABLE(USERNAME))";

        db.execSQL(createUsersTable);
        db.execSQL(createUserSettingsTable);

        db.execSQL("INSERT INTO USERS_TABLE VALUES('Lasith','Las12S4')");
        db.execSQL("INSERT INTO USERS_TABLE VALUES('Siyath','SiY52f5')");
        db.execSQL("INSERT INTO USERS_TABLE VALUES('Akila','Ak1J3k')");
        db.execSQL("INSERT INTO USERS_TABLE VALUES('Nethmin','NethMI2142')");
        db.execSQL("INSERT INTO USERS_TABLE VALUES('Wickie','wicksISU2')");

        db.execSQL("INSERT INTO USER_SETTINGS_TABLE VALUES('Lasith','Admin','Auto','6.00AM','7.00PM')");
        db.execSQL("INSERT INTO USER_SETTINGS_TABLE VALUES('Siyath','Non','Manual','5.30AM','6.00PM')");
        db.execSQL("INSERT INTO USER_SETTINGS_TABLE VALUES('Akila','Non','Manual','7.00AM','5.30PM')");
        db.execSQL("INSERT INTO USER_SETTINGS_TABLE VALUES('Nethmin','Non','Auto','9.00AM','5.40PM')");
        db.execSQL("INSERT INTO USER_SETTINGS_TABLE VALUES('Wickie','Non','Auto','7.45AM','8.00PM')");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Boolean getPassword(String typed_username, String typed_password){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor resultSet = db.rawQuery("Select PASSWORD from USERS_TABLE WHERE USERNAME='"+typed_username+"'",null);
        resultSet.moveToFirst();
        String db_password = resultSet.getString(0);
        if(db_password.equals(typed_password)){
            return true;
        }else{
            return false;
        }
    }

    public Cursor getTimes(String username){
        SQLiteDatabase db =this.getReadableDatabase();

        Cursor result = db.rawQuery("Select WAKE_UP_TIME,SLEEP_TIME from USER_SETTINGS_TABLE WHERE USERNAME='"+username+"'",null);
        return result;
    }

    public void setSleepTime(String username, String time){
        SQLiteDatabase db =this.getReadableDatabase();
        db.execSQL("Update USER_SETTINGS_TABLE SET SLEEP_TIME='"+time+"' WHERE USERNAME='"+username+"'");

    }

    public void setWakeTime(String username, String time){
        SQLiteDatabase db =this.getReadableDatabase();
        db.execSQL("Update USER_SETTINGS_TABLE SET WAKE_UP_TIME='"+time+"' WHERE USERNAME='"+username+"'");

    }

    public Cursor getMode(String username){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor modeResult = db.rawQuery("Select MODE from USER_SETTINGS_TABLE WHERE USERNAME='"+username+"'",null);
        return modeResult;
    }

    public void setMode(String username, String mode){
        SQLiteDatabase db =this.getReadableDatabase();
        db.execSQL("Update USER_SETTINGS_TABLE SET MODE='"+mode+"' WHERE USERNAME='"+username+"'");
    }
}
