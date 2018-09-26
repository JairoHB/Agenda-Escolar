package com.jairohb.agenda_escolar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    public DatabaseHelper(Context context) {
        super(context, "agenda.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user (user_id INTEGER PRIMARY KEY AUTOINCREMENT, user_name Text, user_email Text, user_password Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }

    public boolean insert(String user_name, String user_email, String user_password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_name", user_name);
        contentValues.put("user_email", user_email);
        contentValues.put("user_password", user_password);
        long ins = db.insert("user", null, contentValues);
        if(ins==1) return false;
        else return true;
    }
    public Boolean chkusername (String user_name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where user_name = ?", new String[]{user_name});
        if(cursor.getCount()>0) return false;
        else return true;
    }

    public Boolean chkemail (String user_email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where user_email = ?", new String[]{user_email});
        if(cursor.getCount()>0) return false;
        else return true;
    }

    public Boolean login_name (String user_name, String user_password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor1 = db.rawQuery("Select * from user where user_name = ? and user_password = ?", new String[]{user_name, user_password});
        if(cursor1.getCount()>0) return false;
        else return true;
    }
}
