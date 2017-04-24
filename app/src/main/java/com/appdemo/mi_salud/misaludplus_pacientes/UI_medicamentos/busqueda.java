package com.appdemo.mi_salud.misaludplus_pacientes.UI_medicamentos;

import com.appdemo.mi_salud.misaludplus_pacientes.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.appdemo.mi_salud.misaludplus_pacientes.UI_dialogos.DialogSeguridad;

public class busqueda extends Fragment {

    AutoCompleteTextView actv;
    Button btnContniuar;
    ImageButton btnBuscar;
    LinearLayout vista1;
    RelativeLayout vista2;
    FragmentManager fragmentManager;
    FragmentTransaction ft;
    String[] opciones,usos,advs;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_busqueda, container, false);

        actv = (AutoCompleteTextView) v.findViewById(R.id.acMed);
        btnContniuar = (Button) v.findViewById(R.id.csaveSend);
        btnBuscar = (ImageButton) v.findViewById(R.id.btnBsc);
        vista1 = (LinearLayout) v.findViewById(R.id.PARTEI);
        vista2 = (RelativeLayout) v.findViewById(R.id.idMps);

        fragmentManager = getFragmentManager();
        ft = fragmentManager.beginTransaction();

        opciones = getResources().getStringArray(R.array.nombre_med);
        usos = getResources().getStringArray(R.array.usos_med);
        advs = getResources().getStringArray(R.array.advertencias_med);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, opciones);

        //Se carga el segundo Fragment en la segunda vista
        busqueda2 fragment = new busqueda2();
        ft.add(R.id.idMps, fragment);
        ft.commit();

        //Propiedades para el autocompletado
        actv.setThreshold(1);//Sugiere a partir de 1 caracter
        actv.setAdapter(adapter);

        btnContniuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(actv.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), getResources().getString(R.string.data_empty), Toast.LENGTH_SHORT).show();
                }else{
                    String medicamento=actv.getText().toString();
                    int len= opciones.length;
                    int pos=-1;
                    for(int i=0;i<len;i++){
                        if(medicamento.equals(opciones[i])){
                            pos=i;
                            break;
                        }
                    }
                    if(pos!=-1){
                        showSecurityDialog(advs[pos],usos[pos]);
                    }else{
                        showSecurityDialog("No hay información","No hay Información");
                    }
                }
            }
        });

        //Se oprime el boton de BUSCAR
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actv.setText("");
                vista2.setVisibility(View.GONE);
                btnBuscar.setVisibility(View.GONE);
                vista1.setVisibility(View.VISIBLE);
            }
        });


        return v;
    }

    public void showSecurityDialog(String adv, String uso) {
        // Create an instance of the dialog fragment and show it
        ft.addToBackStack(null);

        DialogFragment dialogFrag = DialogSeguridad.newInstance(123);
        dialogFrag.setTargetFragment(this,1);
        Bundle bundle = new Bundle();
        bundle.putString("adv", adv);
        bundle.putString("uso", uso);
        dialogFrag.setArguments(bundle);
        dialogFrag.show(getFragmentManager().beginTransaction(), "Seguridad");

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 1:

                if (resultCode == Activity.RESULT_OK) {
                    //Se muestra la segunda vista que contiene el fragment
                    vista2.setVisibility(View.VISIBLE);
                    //Se esconde la primera vista
                    vista1.setVisibility(View.GONE);
                    //Se muestra el boton de buscar
                    btnBuscar.setVisibility(View.VISIBLE);
                } else if (resultCode == Activity.RESULT_CANCELED){

                }

                break;
        }
    }




}
