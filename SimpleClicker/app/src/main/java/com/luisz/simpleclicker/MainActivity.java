package com.luisz.simpleclicker;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luisz.simpleclicker.Models.Mejora;
import com.luisz.simpleclicker.ViewModel.ViewModel;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Typeface font;
    public static ViewModel miViewModel;

    /*
    https://www.youtube.com/watch?v=zYVEMCiDcmY
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miViewModel = ViewModelProviders.of(this).get(ViewModel.class);
        //miViewModel = new ViewModel(getApplication());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        font = Typeface.createFromAsset(getAssets(), "awesome.ttf");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.nav_drawer_open, R.string.nav_drawer_close);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_inicio);
        }

        cargarPartida();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_inicio:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;
            case R.id.nav_ajustes:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsFragment()).commit();
                break;
            case R.id.nav_ayuda:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HelpFragment()).commit();
                break;
            case R.id.nav_mejoras:
                Toast.makeText(this, "En construcción", Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        guardarPartida();
    }

    private void guardarPartida() {
        SharedPreferences preferences = getSharedPreferences("partida", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String jsonMejoras = gson.toJson(miViewModel.getListaMejoras());

        editor.putLong("puntos", miViewModel.puntos);
        editor.putLong("sumador", miViewModel.sumador);
        editor.putLong("contadorPulsaciones", miViewModel.contadorPulsaciones);
        editor.putLong("contadorPulsacionesParcial", miViewModel.contadorPulsacionesParcial);
        editor.putString("listadoDeMejoras", jsonMejoras);

        editor.commit();
    }

    private void cargarPartida() {
        SharedPreferences preferences = getSharedPreferences("partida", MODE_PRIVATE);
        preferences = getSharedPreferences("partida", MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonMejoras = "";
        ArrayList<Mejora> miListaGuardada;

        miViewModel.puntos = preferences.getLong("puntos", 0);
        miViewModel.sumador = preferences.getLong("sumador", 1);
        miViewModel.contadorPulsaciones = preferences.getLong("contadorPulsaciones", 0);
        miViewModel.contadorPulsacionesParcial = preferences.getLong("contadorPulsacionesParcial", 0);

        jsonMejoras = preferences.getString("listadoDeMejoras", null);
        Type type = new TypeToken<ArrayList<Mejora>>() {
        }.getType();
        miListaGuardada = gson.fromJson(jsonMejoras, type);

        if (miListaGuardada != null) {
            miViewModel.listaMejoras = miListaGuardada;
            miViewModel.listaMejorasMutable.setValue(miListaGuardada);
        }

    }
}
