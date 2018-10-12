package com.jairohb.agenda_escolar;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.jairohb.agenda_escolar.Adaptadores.ListaTareasAdapter;
import com.jairohb.agenda_escolar.Entidades.Tareas;
import com.jairohb.agenda_escolar.utils.PreferenceUtils;

import java.util.ArrayList;

public class menu extends Activity {

    private Session session;
    DatabaseHelper bd;
    ArrayList<Tareas> listaTareas = null;
    RecyclerView recyclerViewTareas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        session = new Session(this);
        bd= new DatabaseHelper(this);

        recyclerViewTareas= (RecyclerView) findViewById(R.id.recyclerTareas);
        recyclerViewTareas.setLayoutManager(new LinearLayoutManager(this));
        Integer userid = Integer.valueOf(session.getid());
        listaTareas= bd.consultarListaTareas(userid);

        if(listaTareas.size()>=1){
            ListaTareasAdapter adapter = new ListaTareasAdapter(this, listaTareas);
            recyclerViewTareas.setAdapter(adapter);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"¡Registra tus tareas!", Toast.LENGTH_SHORT).show();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_addmat_task);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu.this, materia.class);
                startActivity(intent);
            }
        });

        ImageButton btnlogout = (ImageButton) findViewById(R.id.btnlogout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceUtils.savePassword(null, getApplicationContext());
                PreferenceUtils.saveuser(null, getApplicationContext());
                Intent intent = new Intent(menu.this, login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}