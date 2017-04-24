package com.appdemo.mi_salud.misaludplus_pacientes.UI_dialogos;

import android.app.Activity;
import android.app.Dialog;
import com.appdemo.mi_salud.misaludplus_pacientes.R;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DialogSeguridad extends DialogFragment {
    Activity activity;
    CheckBox opA,opF,aceptar;
    EditText toma;
    Spinner via,frec;
    boolean chooseV,chooseF;
    TextView usosM,advsM;
    String med_u,med_a;

    public static  DialogSeguridad newInstance(int num){
        DialogSeguridad dialogFragment = new DialogSeguridad();
        Bundle bundle = new Bundle();
        bundle.putInt("num", num);
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_save, null);
        activity = this.getActivity();

        med_u=getArguments().getString("uso");
        med_a=getArguments().getString("adv");

        opF=(CheckBox) view.findViewById(R.id.csaveChckF);
        opA=(CheckBox) view.findViewById(R.id.csaveChckA);
        aceptar=(CheckBox) view.findViewById(R.id.csaveAceptar);
        via=(Spinner) view.findViewById(R.id.csaveViAdmin);
        frec=(Spinner) view.findViewById(R.id.csaveFrec2);
        toma=(EditText) view.findViewById(R.id.csaveFrec1);
        usosM=(TextView) view.findViewById(R.id.csaveUso);
        advsM=(TextView) view.findViewById(R.id.csaveAdv);

        opA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(opA.isChecked()){
                    opF.setChecked(false);
                }
            }
        });

        opF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(opF.isChecked()){
                    opA.setChecked(false);
                }
            }
        });

        via.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chooseV = position != 0 ? true : false;
                usosM.setText(med_u);
                advsM.setText(med_a);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

       frec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chooseF = position != 0 ? true : false;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.pst_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //ACCEPT
                        if((!opF.isChecked() && !opA.isChecked()) || !chooseF || !chooseV || toma.getText().toString().isEmpty()){
                            Toast.makeText(getContext(),getResources().getString(R.string.data_empty),Toast.LENGTH_SHORT).show();
                        }else{
                            if(aceptar.isChecked()){
                                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, getActivity().getIntent());
                            }else{
                                Toast.makeText(getContext(),getResources().getString(R.string.pst_toast),Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                })
                .setNegativeButton(R.string.pst_nok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //CANCEL
                        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_CANCELED, getActivity().getIntent());
                    }
                });

        return builder.create();

    }



}
