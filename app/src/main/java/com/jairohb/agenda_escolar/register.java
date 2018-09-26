package com.jairohb.agenda_escolar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {
    DatabaseHelper db;
    EditText username, email, password, password2;
    Button btnregistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        db= new DatabaseHelper(this);
        username=(EditText)findViewById(R.id.txtreg_user);
        email=(EditText)findViewById(R.id.txtreg_email);
        password=(EditText)findViewById(R.id.txtreg_pass);
        password2=(EditText)findViewById(R.id.txtreg_confpass);
        btnregistrar=(Button)findViewById(R.id.btn_reg);

        btnregistrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String usn = username.getText().toString();
                String ema = email.getText().toString();
                String pas = password.getText().toString();
                String pas2 = password2.getText().toString();
                if(usn.equals("") || ema.equals("") || pas.equals("") || pas2.equals("")){
                    Toast.makeText(getApplicationContext(),"No dejes ningun campo vacio", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (pas.equals(pas2)){
                        Boolean chkeusername = db.chkusername(usn);
                        if(chkeusername==true){
                            Boolean chkemail1 = db.chkemail(ema);
                            if(chkemail1==true){
                                Boolean insert1 = db.insert(usn,ema,pas);
                                if(insert1==true){
                                    Toast.makeText(getApplicationContext(),"Registro Con Exito", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Correo Registrado, intenta otro", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Usuario Registrado, intenta otro", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}