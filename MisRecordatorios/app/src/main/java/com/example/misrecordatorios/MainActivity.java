package com.example.misrecordatorios;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.misrecordatorios.Fragments.crearRecordatorioFragment;
import com.example.misrecordatorios.Fragments.detallesFragment;
import com.example.misrecordatorios.Fragments.listFragment;
import com.example.misrecordatorios.Models.Recordatorio;
import com.example.misrecordatorios.ViewModel.ViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {

    private ViewModel miViewModel;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        miViewModel = ViewModelProviders.of(this).get(ViewModel.class);

        fab = findViewById(R.id.fab);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new listFragment()).commit();

        gestionFloatingButton();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        gestionFloatingButton();
    }

    public void gestionFloatingButton() {
        /*Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (currentFragment instanceof listFragment) {

            fab.setImageResource(R.drawable.ic_add_black_24dp);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new crearRecordatorioFragment()).addToBackStack(null).commit();

                    fab.setImageResource(R.drawable.ic_check_black_24dp);

                    Snackbar.make(view, "Rellena los campos para añadir", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
            });

        } else {
            if (currentFragment instanceof crearRecordatorioFragment) {

                //fab.setImageResource(R.drawable.ic_check_black_24dp);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        EditText miEdit = findViewById(R.id.EditorContenido);
                        Recordatorio rec = new Recordatorio(new Date(), miEdit.getText().toString(), "#2196F3" );

                        miViewModel.listadoRecordatorios.add(rec);

                        Snackbar.make(view, "Cambios guardados", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    }
                });

            } else {

                if (currentFragment instanceof detallesFragment) {
                    fab.setImageResource(R.drawable.ic_check_black_24dp);
                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Snackbar.make(view, "Eso no sirve pa ná", Snackbar.LENGTH_SHORT)
                                    .setAction("Action", null).show();
                        }
                    });
                }

            }
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
