package com.appdemo.mi_salud.misaludplus_pacientes.UI_medicos;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.appdemo.mi_salud.misaludplus_pacientes.R;
import com.appdemo.mi_salud.misaludplus_pacientes.Datos.Doctor;
import com.appdemo.mi_salud.misaludplus_pacientes.Datos.DoctorContent;

public class DoctorDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    private Doctor mItem;
    Activity activity;
    public DoctorDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = DoctorContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getNombre());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rView = inflater.inflate(R.layout.doctor_detail, container, false);

        TextView slgn=((TextView) rView.findViewById(R.id.doctor_slogan));
        TextView visit=((TextView) rView.findViewById(R.id.doctor_visitas));
        TextView upre=((TextView) rView.findViewById(R.id.doctor_pre));
        TextView upos=((TextView) rView.findViewById(R.id.doctor_pos));
        TextView servs=((TextView) rView.findViewById(R.id.doctor_serv));
        final TextView txt=((TextView) rView.findViewById(R.id.doctor_textImg));
        ImageView pntj=(ImageView) rView.findViewById(R.id.doctor_pnt);
        ImageView foto=(ImageView) rView.findViewById(R.id.doctor_phto);
        final ImageView imgSw=(ImageView) rView.findViewById(R.id.doctor_view);
        ImageButton prv=(ImageButton) rView.findViewById(R.id.doctor_prev);
        ImageButton nxt=(ImageButton) rView.findViewById(R.id.doctor_next);

        imgSw.setImageResource(mItem.getActagrado());
        txt.setText(getResources().getString(R.string.med_acta));
        prv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgSw.setImageResource(mItem.getActagrado());
                txt.setText(getResources().getString(R.string.med_acta));
            }
        });

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgSw.setImageResource(mItem.getDiploma());
                txt.setText(getResources().getString(R.string.med_diploma));
            }
        });

        // Show the dummy content
        if (mItem != null) {
            String numVisitas=getResources().getString(R.string.med_visitas)+" "+String.valueOf(mItem.getVisitas());
            String serv[]=mItem.getServicios();
            String servicios="";
            for (String aServ : serv) {
                servicios += " •" + aServ + ". \n";
            }

            slgn.setText(mItem.getSlogan());
            visit.setText(numVisitas);
            foto.setImageResource(mItem.getImg());
            switch (mItem.getCalificacion()){
                case 1:
                    pntj.setImageResource(R.drawable.star_1);
                    break;
                case 2:
                    pntj.setImageResource(R.drawable.star_2);
                    break;
                case 3:
                    pntj.setImageResource(R.drawable.star_3);
                    break;
                case 4:
                    pntj.setImageResource(R.drawable.star_4);
                    break;
                case 5:
                    pntj.setImageResource(R.drawable.star_5);
                    break;
            }
            upre.setText(mItem.getUpregrado());
            upos.setText(mItem.getUposgrado());
            servs.setText(servicios);
        }
        return rView;
    }


}
