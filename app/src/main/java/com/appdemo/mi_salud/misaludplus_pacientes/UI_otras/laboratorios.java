package com.appdemo.mi_salud.misaludplus_pacientes.UI_otras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.appdemo.mi_salud.misaludplus_pacientes.R;

public class laboratorios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.tittle_lab);
        setContentView(R.layout.activity_laboratorios);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


    }

    /*   @Override
       public boolean onCreateOptionsMenu(Menu menu) {
           getMenuInflater().inflate(R.menu.menu_main, menu);
           return true;
       }
   */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
//                Intent i = new Intent(ambulancias.this, MainActivity.class);
//                startActivity(i);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }


}
