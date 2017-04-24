package com.appdemo.mi_salud.misaludplus_pacientes;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

import com.appdemo.mi_salud.misaludplus_pacientes.UI_medicamentos.medicamentos;
import com.appdemo.mi_salud.misaludplus_pacientes.UI_medicos.medicos;
import com.appdemo.mi_salud.misaludplus_pacientes.UI_otras.laboratorios;
import com.appdemo.mi_salud.misaludplus_pacientes.UI_otras.logueo;
import com.appdemo.mi_salud.misaludplus_pacientes.UI_otras.noticias;
import com.appdemo.mi_salud.misaludplus_pacientes.UI_otras.odontologia;
import com.appdemo.mi_salud.misaludplus_pacientes.UI_otras.otros;
import com.appdemo.mi_salud.misaludplus_pacientes.UI_otras.perfilUsuario;
import com.appdemo.mi_salud.misaludplus_pacientes.UI_otras.terapias;
import com.appdemo.mi_salud.misaludplus_pacientes.UI_otras.urgencias;

public class MainActivity extends AppCompatActivity {

    ImageButton btnM;
    ImageButton btnS;
    ImageButton btnP;
    ImageButton btnL;
    ImageButton btnA;
    ImageButton btnT;
    ImageButton btnO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//Se esconde el Titulo de la app

        setContentView(R.layout.activity_main);

        btnM=(ImageButton) findViewById(R.id.btnMed);//Boton de Medicos
        btnS=(ImageButton) findViewById(R.id.btnSos);//Boton de Urgencias
        btnP=(ImageButton) findViewById(R.id.btnPast);//Boton de Pastillas
        btnL=(ImageButton) findViewById(R.id.btnLab);//Boton de Laboratorios
        btnA=(ImageButton) findViewById(R.id.btnAmb);//Boton de Ambulancias
        btnT=(ImageButton) findViewById(R.id.btnTer);//Boton de Terapias
        btnO=(ImageButton) findViewById(R.id.btnOth);//Boton de Otros Servicios


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        noticias fragment = new noticias();
        ft.add(R.id.idNws, fragment);
        ft.commit();

        // Se crea la acción de al dar click en el Boton de Medicos se pase a la actividad de Medicos
        btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, medicos.class);
                startActivity(i);
            }
        });

        // Se crea la acción de al dar click en el Boton de Urgencias se pase a la actividad de Urgencias
        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, urgencias.class);
                startActivity(i);
            }
        });

        // Se crea la acción de al dar click en el Boton de Medicamentos se pase a la actividad de Medicamentos
        btnP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, medicamentos.class);
                startActivity(i);
            }
        });

        // Se crea la acción de al dar click en el Boton de Laboratorios se pase a la actividad de Laboratorios
        btnL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, laboratorios.class);
                startActivity(i);
            }
        });

        // Se crea la acción de al dar click en el Boton de Odontologos se pase a la actividad de Ambulancias
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, odontologia.class);
                startActivity(i);
            }
        });

        // Se crea la acción de al dar click en el Boton de Terapias se pase a la actividad de Terapias
        btnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, terapias.class);
                startActivity(i);
            }
        });

        // Se crea la acción de al dar click en el Boton de Otros se pase a la actividad de Otros Servicios
        btnO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, otros.class);
                startActivity(i);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_perfil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actPerfil:
                startActivity(new Intent(MainActivity.this, perfilUsuario.class));
                return true;
            case R.id.actSalir:
                Intent i=new Intent(MainActivity.this, logueo.class);
                i.putExtra("close", true);
                startActivity(i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }



}
