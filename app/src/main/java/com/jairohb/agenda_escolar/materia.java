package com.jairohb.agenda_escolar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageButton;

public class materia extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_materia);


        ImageButton btnback = (ImageButton) findViewById(R.id.imgbtnbackmat);
        btnback.setImageResource(R.drawable.ic_action_back);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(materia.this, menu.class);
                startActivity(intent);
            }
        });
    }
}
