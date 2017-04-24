package com.appdemo.mi_salud.misaludplus_pacientes.UI_medicamentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.appdemo.mi_salud.misaludplus_pacientes.R;


public class despensa extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_despensa, container, false);

        return v;
    }
}
