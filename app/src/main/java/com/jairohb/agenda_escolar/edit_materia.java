package com.jairohb.agenda_escolar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Calendar;


public class edit_materia extends Activity {

    EditText tarea, materia1, desc, fecha;
    RadioButton rbpr, rbr, rbt;
    Button btndelete, btnupdate;
    ImageButton imgbtnback;
    DatabaseHelper conn;
    private Session session;


    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private static final String TAG = "edit_materia";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_materia);

        session = new Session(this);
        final String id = getIntent().getStringExtra("id");
        conn=new DatabaseHelper(getApplicationContext());
        tarea=(EditText)findViewById(R.id.txtedittitulo);
        materia1=(EditText)findViewById(R.id.txteditmateria);
        desc=(EditText)findViewById(R.id.txteditdesc);
        fecha=(EditText)findViewById(R.id.txteditfecha);
        rbpr=(RadioButton)findViewById(R.id.rbeditPR);
        rbr=(RadioButton)findViewById(R.id.rbeditR);
        rbt=(RadioButton)findViewById(R.id.rbeditT);
        consultarSql(id);
        btnupdate=(Button)findViewById(R.id.btneditup);
        btndelete=(Button)findViewById(R.id.btneditde);
        imgbtnback=(ImageButton)findViewById(R.id.imgbtneditbackmat);
        imgbtnback.setImageResource(R.drawable.ic_action_back);

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        edit_materia.this,
                        android.R.style.Theme_DeviceDefault_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                fecha.setText(date);
            }
        };

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tar = tarea.getText().toString();
                String mat = materia1.getText().toString();
                String des = desc.getText().toString();
                String fec = fecha.getText().toString();
                Integer userid = Integer.valueOf(session.getid());
                String estado;
                if(rbpr.isChecked()==true){
                    estado = "1";
                }
                else if(rbr.isChecked()==true){
                    estado = "2";
                }
                else if(rbt.isChecked()==true){
                    estado = "3";
                }
                else {
                    estado = "1"; //POR DEFECTO SE PONE 1 PORQUE LA TAREA ESTA POR REALIZARSE
                }

                if(tar.equals("") || mat.equals("") || des.equals("") || fec.equals("")){
                    Toast.makeText(getApplicationContext(),"No dejes ningun campo vacio", Toast.LENGTH_SHORT).show();
                }
                else {
                    actualizarTarea(userid,mat,tar,des,fec,estado,id);
                }
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarTarea(id);
            }
        });

        imgbtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(edit_materia.this, menu.class);
                startActivity(intent);
            }
        });
    }

    private void actualizarTarea(Integer user_fk, String materia, String titulo, String descrip, String fecha_lim, String estado, String id) {
        SQLiteDatabase db=conn.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        Integer user = Integer.valueOf(user_fk);
        contentValues.put("user_fk", user);
        contentValues.put("materia", materia);
        contentValues.put("titulo", titulo);
        contentValues.put("descrip", descrip);
        contentValues.put("fecha_lim", fecha_lim);
        contentValues.put("estado", estado);
        db.update("materias",contentValues,"id=?",new String[]{id});
        Toast.makeText(getApplicationContext(),"Ya se actualizó la Tarea",Toast.LENGTH_LONG).show();
        db.close();
    }

    private void eliminarTarea(String id) {
        SQLiteDatabase db=conn.getWritableDatabase();

        db.delete("materias","id=?",new String[]{id});
        Toast.makeText(getApplicationContext(),"Ya se Eliminó la Tarea",Toast.LENGTH_LONG).show();
        limpiar();
        db.close();
    }

    private void consultarSql(String id) {
        SQLiteDatabase db=conn.getReadableDatabase();
        try {
            //select nombre,telefono from usuario where codigo=?
            Cursor cursor=db.rawQuery("Select * From Materias where id = ?", new String[]{id});

            cursor.moveToFirst();
            tarea.setText(cursor.getString(cursor.getColumnIndex("titulo")));
            materia1.setText(cursor.getString(cursor.getColumnIndex("materia")));
            desc.setText(cursor.getString(cursor.getColumnIndex("descrip")));
            fecha.setText(cursor.getString(cursor.getColumnIndex("fecha_lim")));
            if(cursor.getString(cursor.getColumnIndex("estado")).equals("3")){
                rbt.setChecked(true);
            }
            else if(cursor.getString(cursor.getColumnIndex("estado")).equals("2")){
                rbr.setChecked(true);
            }
            else if(cursor.getString(cursor.getColumnIndex("estado")).equals("1")){
                rbpr.setChecked(true);
            }

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"La tarea no existe",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(edit_materia.this, menu.class);
        startActivity(intent);
        this.finish();
    }

    private void limpiar(){
        tarea.setText("");
        materia1.setText("");
        desc.setText("");
        fecha.setText("");
        rbpr.setChecked(false);
        rbr.setChecked(false);
        rbt.setChecked(false);
    }
}
