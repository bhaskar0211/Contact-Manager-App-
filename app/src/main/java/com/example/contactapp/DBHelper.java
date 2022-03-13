package com.example.contactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "SignUp.db";
    private static final String TAG = "ABC";

    public DBHelper(Context context) {
        super(context, "SignUp.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table SignUp(email TEXT primary key, password TEXT,secret TEXT)");
        MyDB.execSQL("create Table Contact(name TEXT ,phone INTEGER PRIMARY KEY, email EMAIL)");
        //

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists /* users*/ SignUp");
        MyDB.execSQL("drop Table if exists /* users*/ Contact");
    }

    public Boolean insertSignUp(String email, String password,String secret){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("email",email );
        contentValues.put("password", password);
        contentValues.put("secret", secret);
        long result = MyDB.insert("SignUp", null, contentValues);
        if(result==-1)
            return false;
        else
            return true;

    }


    public Boolean checkusername(String email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from SignUp where username = ?", new String[]{email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from SignUp where email = ? and password = ?", new String[] {email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean insertContact(String name, String phone, String email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put("name",name);
        cv.put("phone", phone);
        cv.put("email", email);
        MyDB.insert("Contact",null,cv);
       /* if(res == 1)
            return true;
        else
            return false;*/
        return true;

    }


    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Contact", null);
        return cursor;
    }
}





