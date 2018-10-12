package com.jairohb.agenda_escolar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.jairohb.agenda_escolar.Entidades.Tareas;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper{

    ArrayList<Tareas> listaTareas = new ArrayList<Tareas>();

    public DatabaseHelper(Context context) {
        super(context, "agenda3.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user (id INTEGER PRIMARY KEY AUTOINCREMENT, user_name Text, user_email Text, user_password Text)");
        db.execSQL("Create table materias (id INTEGER PRIMARY KEY AUTOINCREMENT, user_fk INTEGER not null, materia Text, titulo Text, descrip Text,fecha_lim Text, estado Text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists materias");
    }

    public boolean insertmat(Integer user_fk, String materia, String titulo, String descrip, String fecha_lim, String estado){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Integer user = Integer.valueOf(user_fk);
        contentValues.put("user_fk", user);
        contentValues.put("materia", materia);
        contentValues.put("titulo", titulo);
        contentValues.put("descrip", descrip);
        contentValues.put("fecha_lim", fecha_lim);
        contentValues.put("estado", estado);
        long ins = db.insert("materias", null, contentValues);
        if(ins==1) return false;
        else return true;
    }

    public boolean updatetarea(String id, String materia, String titulo, String descrip, String fecha_lim, String estado){
        SQLiteDatabase db = this.getWritableDatabase();
        String idtar = id;
        ContentValues contentValues = new ContentValues();
        contentValues.put("materia", materia);
        contentValues.put("titulo", titulo);
        contentValues.put("descrip", descrip);
        contentValues.put("fecha_lim", fecha_lim);
        contentValues.put("estado", estado);
        long ins = db.update("materias", contentValues, "id=?", new String[]{idtar});
        if(ins==1) return false;
        else return true;
    }

    public boolean deletetarea(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String idtar = id;
        long ins = db.delete("materias","id=?", new String[]{idtar});
        if(ins==1) return false;
        else return true;
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

    public String login_name (String user_name, String user_password){
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor1 = db.rawQuery("Select * from user where user_name = ? and user_password = ?", new String[]{user_name, user_password});
            cursor1.moveToFirst();
            int value = cursor1.getInt(0);
            String mvalue = String.valueOf(value);
            if(cursor1.moveToFirst()) return mvalue;
            else return "0";
        }
        catch (Exception ex){
            return "0";
        }
    }

    public ArrayList<Tareas> consultarListaTareas(Integer user_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Tareas tareas=null;
        String user = String.valueOf(user_id);
        Cursor cursor=db.rawQuery("SELECT id, estado, titulo, materia, fecha_lim  FROM materias where  user_fk = ?", new String[]{user});
        while (cursor != null && cursor.moveToNext()){
            tareas=new Tareas();
            tareas.setid(cursor.getString(cursor.getColumnIndex("id")));
            tareas.setEstado(cursor.getString(cursor.getColumnIndex("estado")));
            tareas.setTarea(cursor.getString(cursor.getColumnIndex("titulo")));
            tareas.setMateria(cursor.getString(cursor.getColumnIndex("materia")));
            tareas.setFecha(cursor.getString(cursor.getColumnIndex("fecha_lim")));

            listaTareas.add(tareas);
        }
        return listaTareas;
    }
}
