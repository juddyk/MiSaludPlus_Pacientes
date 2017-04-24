package com.appdemo.mi_salud.misaludplus_pacientes.UI_medicos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import com.appdemo.mi_salud.misaludplus_pacientes.R;

public class medicos extends AppCompatActivity {

    //Se crean los objetos de la interfaz
    Spinner spnCB1,spnCB2,spnCB3,spnMod,spnTipo;
    LinearLayout vista2,vista3;
    Button continuar;
    CheckBox cbEsp,cbGnr,cbEnf,cbOth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.tittle_med);
        setContentView(R.layout.activity_medicos);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //Se instancian los objetos de la interfaz
        vista2=(LinearLayout) findViewById(R.id.medLayout2);
        vista3=(LinearLayout) findViewById(R.id.medLayout3);
        continuar=(Button) findViewById(R.id.medCont);
        cbEsp=(CheckBox) findViewById(R.id.cbMEsp);
        cbGnr=(CheckBox) findViewById(R.id.cbMGnr);
        cbEnf=(CheckBox) findViewById(R.id.cbMEnf);
        cbOth=(CheckBox) findViewById(R.id.cbMOtr);
        spnCB1=(Spinner) findViewById(R.id.spnMEspec);
        spnCB2=(Spinner) findViewById(R.id.spnMOtros);
        spnCB3=(Spinner) findViewById(R.id.spnMEnf);
        spnMod=(Spinner) findViewById(R.id.medModalidad);
        spnTipo=(Spinner) findViewById(R.id.medtipoA);

        //Controla Checkbox para los Especialistas
        cbEsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Estable lista en el Spinbox de Modalidad de Atención
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(medicos.this, R.array.consulta, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnMod.setAdapter(adapter);
                //Estable lista en el Spinbox de Tipo de Atención
                adapter = ArrayAdapter.createFromResource(medicos.this, R.array.tipoatencion_esp, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnTipo.setAdapter(adapter);

                continuar.setVisibility(View.GONE);
                if(cbEsp.isChecked()){
                    cbEnf.setChecked(false);
                    cbGnr.setChecked(false);
                    cbOth.setChecked(false);
                    spnCB1.setVisibility(View.VISIBLE);
                    spnCB2.setVisibility(View.GONE);
                    spnCB2.setSelection(0);
                    spnCB3.setVisibility(View.GONE);
                    spnCB3.setSelection(0);
                    vista2.setVisibility(View.GONE);
                    spnMod.setSelection(0);
                    vista3.setVisibility(View.GONE);
                    spnTipo.setSelection(0);
                }else{
                    spnCB1.setVisibility(View.GONE);
                    spnCB1.setSelection(0);
                    vista2.setVisibility(View.GONE);
                    spnMod.setSelection(0);
                }
            }
        });

        //Controla el Spinbox de Opciones Medicas para Especialistas
        spnCB1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0 && cbEsp.isChecked()){
                    vista2.setVisibility(View.VISIBLE);
                    spnMod.setSelection(0);
                }else{
                    vista2.setVisibility(View.GONE);
                    spnMod.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                vista2.setVisibility(View.GONE);

            }
        });

        //Controla Checkbox para los Medicos general
        cbGnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Estable lista en el Spinbox de Modalidad de Atención
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(medicos.this, R.array.consulta, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnMod.setAdapter(adapter);
                //Estable lista en el Spinbox de Tipo de Atención
                adapter = ArrayAdapter.createFromResource(medicos.this, R.array.tipoatencion_esp, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnTipo.setAdapter(adapter);
                //Se oculta el boton de Continuar
                continuar.setVisibility(View.GONE);
                if(cbGnr.isChecked()){//Si se seleciono Medico General
                    cbEnf.setChecked(false);
                    cbEsp.setChecked(false);
                    cbOth.setChecked(false);
                    spnCB1.setVisibility(View.GONE);
                    spnCB1.setSelection(0);
                    spnCB2.setVisibility(View.GONE);
                    spnCB2.setSelection(0);
                    spnCB3.setVisibility(View.GONE);
                    spnCB3.setSelection(0);
                    vista2.setVisibility(View.VISIBLE);
                    spnMod.setSelection(0);
                    vista3.setVisibility(View.GONE);
                    spnTipo.setSelection(0);
                }else{//Si no se seleciono Medico General
                    vista2.setVisibility(View.GONE);
                    spnMod.setSelection(0);
                }

            }
        });

        //Controla Checkbox para Enfermería
        cbEnf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Especifica que lista debe cargar en Modalidad de Atencion
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(medicos.this, R.array.consulta_enf, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnMod.setAdapter(adapter);

                continuar.setVisibility(View.GONE);
                if(cbEnf.isChecked()){
                    cbEsp.setChecked(false);
                    cbGnr.setChecked(false);
                    cbOth.setChecked(false);
                    spnCB1.setVisibility(View.GONE);
                    spnCB1.setSelection(0);
                    spnCB2.setVisibility(View.GONE);
                    spnCB2.setSelection(0);
                    spnCB3.setVisibility(View.VISIBLE);
                    spnCB3.setSelection(0);
                    vista2.setVisibility(View.GONE);
                    spnMod.setSelection(0);
                    vista3.setVisibility(View.GONE);
                    spnTipo.setSelection(0);
                }else{
                    vista2.setVisibility(View.GONE);
                    spnMod.setSelection(0);
                }
            }
        });

        //Controla el Spinbox de Opciones Medicas para Enfermería
        spnCB3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0 && cbEnf.isChecked()){
                    vista2.setVisibility(View.VISIBLE);
                    spnMod.setSelection(0);

                }else{
                    vista2.setVisibility(View.GONE);
                    spnMod.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                vista2.setVisibility(View.GONE);

            }
        });

        //Controla Checkbox para Otros profesionales
        cbOth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Especifica que lista debe cargar en Modalidad de Atencion
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(medicos.this, R.array.consulta, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnMod.setAdapter(adapter);

                continuar.setVisibility(View.GONE);
                vista2.setVisibility(View.GONE);
                spnMod.setSelection(0);
                vista3.setVisibility(View.GONE);
                spnTipo.setSelection(0);
                if(cbOth.isChecked()){
                    cbEnf.setChecked(false);
                    cbGnr.setChecked(false);
                    cbEsp.setChecked(false);
                    spnCB1.setVisibility(View.GONE);
                    spnCB1.setSelection(0);
                    spnCB3.setVisibility(View.GONE);
                    spnCB3.setSelection(0);
                    spnCB2.setVisibility(View.VISIBLE);
                }else{
                    spnCB2.setVisibility(View.GONE);
                    spnCB2.setSelection(0);
                }
            }
        });

        //Controla el Spinbox de Opciones Medicas para Otros profesionales
        spnCB2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0 && cbOth.isChecked()){
                    vista2.setVisibility(View.VISIBLE);
                    spnMod.setSelection(0);

                }else{
                    vista2.setVisibility(View.GONE);
                    spnMod.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                vista2.setVisibility(View.GONE);

            }
        });
        //Controla el Spinbox de Modalidad de Atencion
        spnMod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    if(cbEsp.isChecked() || cbGnr.isChecked()){
                        vista3.setVisibility(View.VISIBLE);
                        spnTipo.setSelection(0);
                    }else{
                        continuar.setVisibility(View.VISIBLE);
                    }
                }else{
                    continuar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
               continuar.setVisibility(View.GONE);

            }
        });

        //Controla el Spinbox de Tipo de Atencion
        spnTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                        continuar.setVisibility(View.VISIBLE);
                }else{
                    continuar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                continuar.setVisibility(View.GONE);

            }
        });

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(medicos.this, DoctorListActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
