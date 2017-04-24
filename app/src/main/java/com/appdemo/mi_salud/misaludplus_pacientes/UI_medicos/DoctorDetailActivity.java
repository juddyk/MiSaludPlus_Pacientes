package com.appdemo.mi_salud.misaludplus_pacientes.UI_medicos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import com.appdemo.mi_salud.misaludplus_pacientes.R;
import com.appdemo.mi_salud.misaludplus_pacientes.UI_dialogos.dialogCitas;

public class DoctorDetailActivity extends AppCompatActivity implements dialogCitas.citasListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });


        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(DoctorDetailFragment.ARG_ITEM_ID,getIntent().getStringExtra(DoctorDetailFragment.ARG_ITEM_ID));
            DoctorDetailFragment fragment = new DoctorDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().add(R.id.doctor_detail_container, fragment).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, DoctorListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showDateDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new dialogCitas();
        dialog.show(getSupportFragmentManager(), "Agendamiento de Citas");

    }

    public void onDatePositive(DialogFragment dialog, int year, int month, int dayOfMonth){
        dialog.dismiss();
    }

    public void onDateNegative(DialogFragment dialog){
        dialog.dismiss();
    }
}