package com.jairohb.agenda_escolar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    DatabaseHelper db;
    EditText username, password;
    Button btninicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        db= new DatabaseHelper(this);
        username=(EditText)findViewById(R.id.txtuser);
        password=(EditText)findViewById(R.id.txtpass);
        btninicio=(Button)findViewById(R.id.btn_inicio);

        btninicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String usn = username.getText().toString();
                String pas = password.getText().toString();
                Boolean login1 = db.login_name(usn, pas);
                //username=jairo27 pass=123
                if(login1==false){
                    Toast.makeText(getApplicationContext(),"Incio Sesión Con Exito", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this, menu.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Contraseña o Usuario Incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void registeractivity(View v){
        TextView register = (TextView) findViewById(R.id.txtregister);
        register.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
            }
        });
    }
}
