package com.appdemo.mi_salud.misaludplus_pacientes.UI_otras;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.appdemo.mi_salud.misaludplus_pacientes.DatabaseHelper;
import com.appdemo.mi_salud.misaludplus_pacientes.Datos.*;
import com.appdemo.mi_salud.misaludplus_pacientes.UI_dialogos.*;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.appdemo.mi_salud.misaludplus_pacientes.R;

import java.util.Calendar;
import java.util.Random;

public class registroPaciente extends AppCompatActivity implements DialogCalendar.DialogListener, DialogName.NameListener, DialogDireccion.DirListener {

    //Objetos de la interfaz
    LinearLayout v2,v3,v4,v5,v6,v7,v8,v9,v10;
    Button btn;
    ImageButton data,name,direc;
    TextView txt,txtN,txtD;
    Spinner spn, spn1,spnDis, tipdocm, sex, est, estr, gre, eps, arl, reg, ocp;
    EditText etDis,em1,em2,fijo1,fijo2,docm,cel,etOcp;

    int anio;
    private String[] lstMunc;

    //Objeto de tipo Paciente, para almacenar la información del paciente
    datosPaciente pacnt;

    //FireBase
    private DatabaseReference mDB;
    private FirebaseDatabase fbDB;
    private static final String TAG_pacientes = "Pacientes";
    private static final String TAG = "registroPaciente";
    //SQL
    DatabaseHelper dbHelper;
    boolean docExist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.tittle_reg);
        setContentView(R.layout.activity_registro_paciente);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //Firebase
        fbDB=FirebaseDatabase.getInstance();
        docExist=true;
        //SQl
        dbHelper=new DatabaseHelper(this);
        //_______________________Instancia Objeto Paciente
        pacnt = new datosPaciente();
        //_____________________Instancia Objetos de la interfaz
        Calendar calendar = Calendar.getInstance();
        anio = calendar.get(Calendar.YEAR);
        //Se inicializa Paciente
        pacnt.setFnAnio(anio + 1);

        //SE INSTANCIAN LOS OBJETOS DE LA INTERFAZ
        v2= (LinearLayout) findViewById(R.id.vistaFecha);
        v3= (LinearLayout) findViewById(R.id.vistaDocumento);
        v4= (LinearLayout) findViewById(R.id.vistaGnEC);
        v5= (LinearLayout) findViewById(R.id.vistaResidencia);
        v6= (LinearLayout) findViewById(R.id.vistaOcupacion);
        v7= (LinearLayout) findViewById(R.id.vistaTelefonos);
        v8= (LinearLayout) findViewById(R.id.vistaVarios);
        v9= (LinearLayout) findViewById(R.id.vistaCorreo);
        v10= (LinearLayout) findViewById(R.id.vistaDiscapacidad);

        btn = (Button) findViewById(R.id.regSend);//Se asocia el boton de la interfaz de hacer regsitro
        txtN = (TextView) findViewById(R.id.regName);//Se asocia el TextView del Nombre Completo
        name = (ImageButton) findViewById(R.id.btnName);//Se asocia el boton de la interfaz de abrir el editor del Nombre
        txt = (TextView) findViewById(R.id.regFech);//Se asocia el TextView de la fecha de nacimiento de la interfaz
        data = (ImageButton) findViewById(R.id.btnFech);//Se asocia el boton de la interfaz de abrir calendario
        tipdocm = (Spinner) findViewById(R.id.regTipoD);//Se asocia el Spinner del tipo de documento
        docm= (EditText) findViewById(R.id.regDoc);//Se asocia el EditTExt de Documento de Identidad
        sex = (Spinner) findViewById(R.id.regSex);//Se asocia el Spinner del genero
        est = (Spinner) findViewById(R.id.regEst);//Se asocia el Spinner del Estado Civil
        spn = (Spinner) findViewById(R.id.regDepr);//Se asocia el Spinner de los departamentos de la interfaz
        spn1 = (Spinner) findViewById(R.id.regMunc);//Se asocia el Spinner de los municipios de la interfaz
        txtD = (TextView) findViewById(R.id.regDir);//Se asocia el TextView de la Dirección
        direc = (ImageButton) findViewById(R.id.btnDir);//Se asocia el boton de la interfaz de abrir el editor de la dirección
        estr = (Spinner) findViewById(R.id.regEstr);//Se asocia el Spinner del Estrato
        gre = (Spinner) findViewById(R.id.regGrE);//Se asocia el Spinner del Grupo Etnico
        ocp = (Spinner) findViewById(R.id.regOcup);//Se asocia el Spinner de Ocupacion
        etOcp= (EditText) findViewById(R.id.regEtOcup);//Se asocia el EditTExt de Otra Profesion
        cel= (EditText) findViewById(R.id.regCel);//Se asocia el EditTExt del Celular
        fijo1= (EditText) findViewById(R.id.regFijo1);//Se asocia el EditTExt de Fijo1
        fijo2= (EditText) findViewById(R.id.regFijo2);//Se asocia el EditTExt de Fijo2
        eps = (Spinner) findViewById(R.id.regEPS);//Se asocia el Spinner de la EPS
        arl = (Spinner) findViewById(R.id.regARL);//Se asocia el Spinner de la ARL
        reg = (Spinner) findViewById(R.id.regReg);//Se asocia el Spinner del Regimen
        em1= (EditText) findViewById(R.id.regEmail1);//Se asocia el EditTExt de Email1
        em2= (EditText) findViewById(R.id.regEmail2);//Se asocia el EditTExt de Email2
        spnDis = (Spinner) findViewById(R.id.regDisc);//Se asocia el Spinner de Discapacidades
        etDis= (EditText) findViewById(R.id.regEtDisc);//Se asocia el EditTExt de Otra Discapacidad

        //Se asocia la acción de volver a la ventana de Logueo al dar CLICK en el boton de registro
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int val=0;
                if(!txtN.getText().toString().isEmpty()){//Nombre
                    val++;
                }
                if(!txt.getText().toString().isEmpty()){//Fecha de Nacimiento
                    val++;
                }
                if(tipdocm.getSelectedItemPosition()!=0){//Tipo Doc
                    val++;
                }
                if(!docm.getText().toString().isEmpty()){//Documento
                    val++;
                    pacnt.setNumDoc(docm.getText().toString());
                }
                if(sex.getSelectedItemPosition()!=0){//Genero
                    val++;
                }
                if(est.getSelectedItemPosition()!=0){//Estado Civil
                    val++;
                }
                if(spn.getSelectedItemPosition()!=0){//Departamento
                    val++;
                }
                if(spn1.getSelectedItemPosition()!=0){//Municipio
                    val++;
                }
                if(!txtD.getText().toString().isEmpty()){//Direccion
                    val++;
                }
                if(estr.getSelectedItemPosition()!=0){//Estrato
                    val++;
                }
                if(ocp.getSelectedItemPosition()!=0){//Ocupacion
                    if(etOcp.isEnabled()){
                        if(!etOcp.getText().toString().isEmpty()){
                            val++;
                            pacnt.setOcupacion(etOcp.getText().toString());
                        }
                    }else{
                        val++;
                    }
                }
                if(!cel.getText().toString().isEmpty()){//Celular
                    val++;
                    pacnt.setCelular(cel.getText().toString());
                }
                if(!fijo1.getText().toString().isEmpty()){//Fijo 1
                    val++;
                    pacnt.setFijo1(fijo1.getText().toString());
                }
                if(!fijo2.getText().toString().isEmpty()){//Fijo 2
                    pacnt.setFijo2(fijo2.getText().toString());
                }
                if(eps.getSelectedItemPosition()!=0){//EPS
                    val++;
                }
                if(arl.getSelectedItemPosition()!=0){//ARL
                    val++;
                }
                if(!em1.getText().toString().isEmpty()){//Correo 1
                    val++;
                    pacnt.setCorreo1(em1.getText().toString());
                }
                if(!em2.getText().toString().isEmpty()){//Correo 2
                    pacnt.setCorreo2(em2.getText().toString());
                }
                if(spnDis.getSelectedItemPosition()!=0){//Discapacidad
                    if(etDis.isEnabled()){
                        if(!etDis.getText().toString().isEmpty()){
                            val++;
                            pacnt.setDiscapacidad(etDis.getText().toString());
                        }
                    }else{
                        val++;
                    }
                }

                if(val==17){
                    //Se crea la contraseña usando un numero aleatorio de 4 digitos
                    Random rnd= new Random();
                    int psw=(int)(rnd.nextDouble()*10000);
                    while (psw<1000){//Garantiza que la contraseña generada sea de 4 digitos
                        psw=(int)(rnd.nextDouble()*10000);
                    }
                    pacnt.setPsswrd(String.valueOf(psw));
                    //Se guarda en FIREBASE la información
                    writeNewUser();
                    //Se guarda en SQL la información de logueo
                    dbHelper.insertContact(pacnt);
                    //Se regresa a la actividad de logueo
                    Intent i = new Intent(registroPaciente.this, logueo.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),"Su contraseña es: "+String.valueOf(pacnt.getPsswrd()),Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"¡Faltan datos!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Se asocia la acción de abrir el Editor de Nombre al dar CLICK en el boton de editar
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNameDialog();
            }
        });

        //Se asocia la acción de abrir el Editor de Nombre al dar CLICK en el TextView que muestra el nombre completo
        txtN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNameDialog();
            }
        });

        //Se asocia la acción de abrir el calendario al dar CLICK en el boton de calendario
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendarDialog();
            }
        });

        //Se asocia la acción de abrir el calendario al dar CLICK en el TextView que muestra la fecha de nacimiento
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendarDialog();
            }
        });

        //Se asocia la acción de abrir el Editor de la Dirección al dar CLICK en el TextView que muestra el nombre completo
        direc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDirDialog();
            }
        });

        //Se asocia la acción de abrir el Editor de la Dirección al dar CLICK en el TextView que muestra el nombre completo
        txtD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDirDialog();
            }
        });

        //Según el departamento escogido se carga una lista de los municipios
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                ArrayAdapter<CharSequence> adapter;
                String[] lista=getResources().getStringArray(R.array.departamentos);
                pacnt.setDepartamento(lista[arg2]);
                switch (arg2) {
                    case 1:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun1, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun1);
                        break;
                    case 2:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun2, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun2);
                        break;
                    case 3:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun3, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun3);
                        break;
                    case 4:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun4, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun4);
                        break;
                    case 5:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun5, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun5);
                        break;
                    case 6:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun6, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun6);
                        break;
                    case 7:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun7, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun7);
                        break;
                    case 8:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun8, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun8);
                        break;
                    case 9:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun9, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun9);
                        break;
                    case 10:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun10, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun10);
                        break;
                    case 11:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun11, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun11);
                        break;
                    case 12:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun12, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun12);
                        break;
                    case 13:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun13, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun13);
                        break;
                    case 14:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun14, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun14);
                        break;
                    case 15:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun15, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun15);
                        break;
                    case 16:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun16, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun16);
                        break;
                    case 17:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun17, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun17);
                        break;
                    case 18:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun18, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun18);
                        break;
                    case 19:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun19, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun19);
                        break;
                    case 20:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun20, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun20);
                        break;
                    case 21:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun21, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun21);
                        break;
                    case 22:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun22, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun22);
                        break;
                    case 23:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun23, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun23);
                        break;
                    case 24:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun24, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun24);
                        break;
                    case 25:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun25, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun25);
                        break;
                    case 26:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun26, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun26);
                        break;
                    case 27:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun27, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun27);
                        break;
                    case 28:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun28, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun28);
                        break;
                    case 29:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun29, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun29);
                        break;
                    case 30:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun30, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun30);
                        break;
                    case 31:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun31, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun31);
                        break;
                    case 32:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registroPaciente.this, R.array.mun32, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn1.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun32);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        //Se guarda el municipio
        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    pacnt.setMunicipio(lstMunc[position]);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Se guarda el tipo de documento
        tipdocm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] lista=getResources().getStringArray(R.array.tipdoc);
                if(position!=0){
                    pacnt.setTpDoc(lista[position]);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        docm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                v4.setVisibility(View.VISIBLE);
            }
        });

        //Se guarda el genero
        sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                validarDoc(docm.getText().toString());
                String[] lista=getResources().getStringArray(R.array.genero);
                if(position!=0){
                    pacnt.setGenero(lista[position]);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Se guarda el Estado civil
        est.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] lista=getResources().getStringArray(R.array.estado);
                if(position!=0){
                    if(!docExist){
                        docm.setText("");
                        Toast.makeText(registroPaciente.this,getResources().getString(R.string.reg_wrongDoc),Toast.LENGTH_SHORT).show();
                        est.setSelection(0);
                        sex.setSelection(0);
                        v4.setVisibility(View.GONE);
                    }else{
                        pacnt.setStsCivil(lista[position]);
                        v5.setVisibility(View.VISIBLE);
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Se guarda el Estrato
        estr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] lista=getResources().getStringArray(R.array.estrato);
                if(position!=0){
                    pacnt.setEstrato(Integer.valueOf(lista[position]));
                    v6.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Se guarda el Grupo Etnico
        gre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] lista=getResources().getStringArray(R.array.grupoe);
                if(position!=0){
                    pacnt.setGrupoEt(lista[position]);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Se activa la opción de escribir una Ocupación cuando se escoge la opción "Otra"
        ocp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] lista=getResources().getStringArray(R.array.ocupacion);
                int k=lista.length;
                if(position==k-1){
                    etOcp.setEnabled(true);
                    etOcp.setVisibility(View.VISIBLE);
                }else{
                    v7.setVisibility(View.VISIBLE);
                    etOcp.setEnabled(false);
                    etOcp.setVisibility(View.GONE);
                    pacnt.setOcupacion(lista[position]);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        etOcp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                v7.setVisibility(View.VISIBLE);
            }
        });

        fijo1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                v8.setVisibility(View.VISIBLE);
            }
        });

        //Se guarda la EPS
        eps.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] lista=getResources().getStringArray(R.array.EPSlist);
                if(position!=0){
                    pacnt.setEps(lista[position]);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Se guarda la ARL
        arl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] lista=getResources().getStringArray(R.array.ARLlist);
                if(position!=0){
                    pacnt.setArl(lista[position]);
                    v9.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Se guarda el Regimen
        reg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] lista=getResources().getStringArray(R.array.regimen);
                if(position!=0){
                    pacnt.setRegimen(lista[position]);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        em1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                v10.setVisibility(View.VISIBLE);
            }
        });

        //Se activa la opción de escribir una Discapacidad cuando se escoge la opción "Otra"
        spnDis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] lista=getResources().getStringArray(R.array.discapacidades);
                int k=lista.length;
                if(position==k-1){
                    etDis.setVisibility(View.VISIBLE);
                    etDis.setEnabled(true);
                }else{
                    etDis.setVisibility(View.GONE);
                    etDis.setEnabled(false);
                    pacnt.setDiscapacidad(lista[position]);
                    btn.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        etDis.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                btn.setVisibility(View.VISIBLE);
            }
        });
    }

    public void showCalendarDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DialogCalendar();
        Bundle bundle = new Bundle();
        bundle.putInt("year", pacnt.getFnAnio());
        bundle.putInt("month", pacnt.getFnMes());
        bundle.putInt("day", pacnt.getFnDia());
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), "Fecha de Nacimiento");
    }

    public void showNameDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DialogName();
        Bundle bundle = new Bundle();
        bundle.putString("a1", pacnt.getApellido1());
        bundle.putString("a2", pacnt.getApellido2());
        bundle.putString("n1", pacnt.getNombre1());
        bundle.putString("n2", pacnt.getNombre2());
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), "Nombre Completo");
    }

    public void showDirDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DialogDireccion();
        Bundle bundle = new Bundle();
        bundle.putString("dir", pacnt.getDireccion());
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), "Dirección de Residencia");
    }

    @Override
    public void onCalendarPositiveClick(DialogFragment dialog, int d, int m, int y) {
        String data;
        if (d == 0) {
            data = "-- / ";
        } else {
            data = String.valueOf(d) + " / ";
        }
        if (m == 0) {
            data = data + "-- / ";
        } else {
            data = data + String.valueOf(m) + " / ";
        }
        if (anio - y == -1) {
            data = data + "-- ";
        } else {
            data = data + String.valueOf(y) + " ";
        }
        txt.setText(data);
        pacnt.setFnDia(d);
        pacnt.setFnMes(m);
        pacnt.setFnAnio(y);
        v3.setVisibility(View.VISIBLE);
        dialog.dismiss();
    }

    @Override
    public void onCalendarNegativeClick(DialogFragment dialog) {
        dialog.dismiss();
    }

    @Override
    public void onNamePositive(DialogFragment dialog, String a1, String a2, String n1, String n2) {
        String data = n1 + " " + n2 + " " + a1 + " " + a2 + " ";
        pacnt.setNombre1(n1);
        pacnt.setNombre2(n2);
        pacnt.setApellido1(a1);
        pacnt.setApellido2(a2);
        txtN.setText(data);
        v2.setVisibility(View.VISIBLE);
        dialog.dismiss();
    }

    @Override
    public void onNameNegative(DialogFragment dialog) {
        dialog.dismiss();
    }

    @Override
    public void onDirPositive(DialogFragment dialog, String dir) {
        pacnt.setDireccion(dir);
        txtD.setText(dir);
        dialog.dismiss();
    }

    @Override
    public void onDirNegative(DialogFragment dialog) {
        dialog.dismiss();
    }

    //Método para registrar paciente
    private void writeNewUser() {
        mDB=fbDB.getReference();
        mDB.child(TAG_pacientes).child(String.valueOf(pacnt.getNumDoc())).setValue(pacnt);
    }

    //Método para validar existencia de la cedula
    private  void validarDoc(String documento){
        mDB=fbDB.getReference().child(TAG_pacientes).child(documento);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                datosPaciente post = dataSnapshot.getValue(datosPaciente.class);
                docExist= post==null;
                /*if(post==null){
                    docExist=true;
                }else{
                    docExist=false;
                }*/
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                Toast.makeText(registroPaciente.this, "Failed to load data.",Toast.LENGTH_SHORT).show();
            }
        };
        mDB.addValueEventListener(postListener);

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
