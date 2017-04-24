package com.appdemo.mi_salud.misaludplus_pacientes.UI_otras;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appdemo.mi_salud.misaludplus_pacientes.DatabaseHelper;
import com.appdemo.mi_salud.misaludplus_pacientes.Datos.datosPaciente;
import com.appdemo.mi_salud.misaludplus_pacientes.MainActivity;
import com.appdemo.mi_salud.misaludplus_pacientes.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class logueo extends AppCompatActivity {
    Button log;//Se crea boton de logueo
    Button reg;//Se crea boton de registro
    EditText usr,psw;

    //
    private SharedPreferences.Editor preferenceEditor;
    private static final int PREFERENCE_MODE_PRIVATE=0;
    boolean flag=false;

    //FireBase
    private DatabaseReference mDB;
    private static final String TAG_pacientes = "Pacientes";
    private static final String TAG = "logueo";

    public String uname,upass;

    //SQL
    DatabaseHelper DBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//Se esconde el Titulo de la app
        setContentView(R.layout.activity_logueo);

        SharedPreferences preferenceSettings;
        preferenceSettings = getPreferences(PREFERENCE_MODE_PRIVATE);
        preferenceEditor=preferenceSettings.edit();

        if(getIntent().getBooleanExtra("close",false)){
            preferenceEditor.putBoolean("flag",false);
            preferenceEditor.apply();
        }


        if(preferenceSettings.getBoolean("flag",flag)){
            startActivity(new Intent(logueo.this,MainActivity.class));
            finish();
        }

        //SQl
        DBHelper=new DatabaseHelper(this);

        //Instancias de la interfaz
        log = (Button) findViewById(R.id.idBtnLog);//Se asocia con el boton de la interfaz
        reg = (Button) findViewById(R.id.idBtnReg);//Se asocia con el boton de la interfaz
        usr = (EditText) findViewById(R.id.idUSR);//Se asocia con el editText de la interfaz
        psw = (EditText) findViewById(R.id.idPSW);//Se asocia con el editText de la interfaz

        //Se asocia la acción de abrir la vista principal cuando se da CLICK en el boton de Iniciar Sesión
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usr.getText().toString().isEmpty() || psw.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.log_wrong),Toast.LENGTH_SHORT).show();
                }else{
                    uname=usr.getText().toString();
                    upass=psw.getText().toString();
                    String pssword=DBHelper.searchPass(uname);

                    if(pssword.isEmpty()){
                        Toast.makeText(logueo.this,getResources().getString(R.string.log_fb),Toast.LENGTH_LONG).show();
                        mDB=FirebaseDatabase.getInstance().getReference().child(TAG_pacientes).child(uname);
                        ValueEventListener postListener = new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                // Get Post object and use the values to update the UI
                                datosPaciente post = dataSnapshot.getValue(datosPaciente.class);
                                if(post==null){
                                    Toast.makeText(logueo.this,getResources().getString(R.string.log_uwrong),Toast.LENGTH_SHORT).show();
                                }else{
                                    if(post.getPsswrd().equals(upass)){
                                        preferenceEditor.putBoolean("flag",true);
                                        preferenceEditor.commit();
                                        DBHelper.insertUser(uname,upass);
                                        usr.setText("");
                                        psw.setText("");
                                        startActivity(new Intent(logueo.this, MainActivity.class));
                                        finish();
                                    }else{
                                        Toast.makeText(logueo.this,getResources().getString(R.string.log_pwrong),Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                                Toast.makeText(logueo.this, "Failed to load data.",Toast.LENGTH_SHORT).show();
                            }
                        };
                        mDB.addValueEventListener(postListener);

                    }else{
                        if(upass.equals(pssword)){
                            preferenceEditor.putBoolean("flag",true);
                            preferenceEditor.commit();
                            usr.setText("");
                            psw.setText("");
                            Intent i = new Intent(logueo.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        }else{
                            Toast.makeText(logueo.this,getResources().getString(R.string.log_pwrong),Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        //Se asocia la acción de abrir la vista de Registro cuando se da CLICK en el boton de Registrarse
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i = new Intent(logueo.this, registroPaciente.class);
            startActivity(i);
            }
        });

    }

}
