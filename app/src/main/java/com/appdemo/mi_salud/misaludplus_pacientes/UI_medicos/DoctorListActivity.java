package com.appdemo.mi_salud.misaludplus_pacientes.UI_medicos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.appdemo.mi_salud.misaludplus_pacientes.R;
import com.appdemo.mi_salud.misaludplus_pacientes.Datos.Doctor;
import com.appdemo.mi_salud.misaludplus_pacientes.Datos.DoctorContent;

import java.util.List;

public class DoctorListActivity extends AppCompatActivity {

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (DoctorContent.getDoctorList().isEmpty()) {
            String nms[]=getResources().getStringArray(R.array.medicos);
            String msjs[]=getResources().getStringArray(R.array.medicosMSJ);
            int idsFoto[]={R.drawable.docs,R.drawable.docs,R.drawable.docs,R.drawable.docs,R.drawable.docs};
            String upres[]={"Universidad de Antioquia","Universidad CES","Universidad Pontificia Bolivariana","Remington","XXXXXXX"};
            String uposs[]={"Universidad de Antioquia","Universidad CES","Universidad Pontificia Bolivariana","Remington","XXXXXXX"};
            int idsAG[]={R.drawable.acta,R.drawable.acta,R.drawable.acta,R.drawable.acta,R.drawable.acta};
            int idsD[]={R.drawable.diploma,R.drawable.diploma,R.drawable.diploma,R.drawable.diploma,R.drawable.diploma};
            String servs[][]={{"Servicio 1","Servicio 2","Servicio 3","Servicio 4"},{"Servicio 1","Servicio 2"},{"Servicio 1","Servicio 2","Servicio 3"},{"Servicio 1","Servicio 2","Servicio 4","Servicio 5"},{"Servicio 1"} };
            int visits[]={100,40,75,96,10};
            int calific[]={5,4,3,2,1};

            for(int i=0;i<5;i++){
                //"nombre","Descripcion","foto","puntaje"
                DoctorContent.addDoctor(new Doctor(String.valueOf(i),nms[i],msjs[i],idsFoto[i],upres[i],uposs[i],idsAG[i],idsD[i],servs[i],visits[i],calific[i]));
            }
        }

        View recyclerView = findViewById(R.id.doctor_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        /*if (findViewById(R.id.doctor_detail_container) != null) {
            mTwoPane = true;
        }*/
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(DoctorContent.getDoctorList()));
    }

    private class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Doctor> mValues;

        private SimpleItemRecyclerViewAdapter(List<Doctor> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mNombre.setText(mValues.get(position).getNombre());
            holder.mSlogan.setText(mValues.get(position).getSlogan());
            holder.mFoto.setImageResource(mValues.get(position).getImg());
            switch (mValues.get(position).getCalificacion()){
                case 1:
                    holder.mPuntos.setImageResource(R.drawable.star_1);
                    break;
                case 2:
                    holder.mPuntos.setImageResource(R.drawable.star_2);
                    break;
                case 3:
                    holder.mPuntos.setImageResource(R.drawable.star_3);
                    break;
                case 4:
                    holder.mPuntos.setImageResource(R.drawable.star_4);
                    break;
                case 5:
                    holder.mPuntos.setImageResource(R.drawable.star_5);
                    break;
            }

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(DoctorDetailFragment.ARG_ITEM_ID, holder.mItem.getId());
                        DoctorDetailFragment fragment = new DoctorDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.doctor_detail_container, fragment)
                                .commit();
                    } else {*/
                        Context context = v.getContext();
                        Intent intent = new Intent(context, DoctorDetailActivity.class);
                        intent.putExtra(DoctorDetailFragment.ARG_ITEM_ID, holder.mItem.getId());
                        context.startActivity(intent);
                    /*}*/
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private final View mView;
            private final TextView mNombre;
            private final TextView mSlogan;
            private final ImageView mFoto;
            private final ImageView mPuntos;
            private Doctor mItem;

            private ViewHolder(View view) {
                super(view);
                mView = view;
                mNombre = (TextView) view.findViewById(R.id.person_name);
                mSlogan = (TextView) view.findViewById(R.id.person_slogan);
                mFoto = (ImageView) view.findViewById(R.id.person_img);
                mPuntos = (ImageView) view.findViewById(R.id.person_pnt);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mNombre.getText() + "'";
            }
        }
    }
}