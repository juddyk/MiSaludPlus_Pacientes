package com.appdemo.mi_salud.misaludplus_pacientes.UI_dialogos;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;
import com.appdemo.mi_salud.misaludplus_pacientes.R;


public class dialogCitas extends DialogFragment {

    CalendarView cv;
    //TextView tv;
    int y=0,m=0,d=0;

    public interface citasListener {
        void onDatePositive(DialogFragment dialog, int year, int month, int dayOfMonth);
        void onDateNegative(DialogFragment dialog);
    }
    citasListener mListener;
    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (citasListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()+ " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_citas, null);

        cv=(CalendarView) view.findViewById(R.id.calendario);
        //tv=(TextView) view.findViewById(R.id.calendar_msj);
        cv.setFirstDayOfWeek(1);

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth){
                y=year;
                m=month;
                d=dayOfMonth;
            }
        });

        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.pst_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //ACCEPT
                        if(d!=0 && m!=0 && y!=0){
                            Toast.makeText(getContext(),getResources().getString(R.string.med_Selec)+" "+String.valueOf(d)+"/"+String.valueOf(m)+"/"+String.valueOf(y),Toast.LENGTH_SHORT).show();
                            mListener.onDatePositive(dialogCitas.this,y,m,d);
                        }else{
                            Toast.makeText(getContext(),getResources().getString(R.string.med_NotSelec),Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton(R.string.pst_nok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //CANCEL
                        mListener.onDateNegative(dialogCitas.this);
                    }
                });


        return builder.create();




    }



}
