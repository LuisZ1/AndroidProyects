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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luisz.simpleclicker.Fragments.HelpFragment;
import com.luisz.simpleclicker.Fragments.HomeFragment;
import com.luisz.simpleclicker.Fragments.SettingsFragment;
import com.luisz.simpleclicker.Fragments.StatsFragment;
import com.luisz.simpleclicker.Fragments.UpgradesFragment;
import com.luisz.simpleclicker.Models.Mejora;
import com.luisz.simpleclicker.ViewModel.ViewModel;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Typeface font;
    private ViewModel miViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Temas
        setTheme(getFlag() ? R.style.ThemeOverlay_AppCompat_Dark : R.style.ThemeOverlay_AppCompat_Light);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miViewModel = ViewModelProviders.of(this).get(ViewModel.class);

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
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new UpgradesFragment()).commit();
                break;
            case R.id.nav_stats:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StatsFragment()).commit();
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
//            Fragment miFragment = this.getFragmentManager().findFragmentById(R.id.fragment_container);
//            int id = miFragment.getId();
//            if (id != HomeFragment ) {
//                // add your code here
//            }
//            if(){
//
//            }else {
                super.onBackPressed();
//            }
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

        editor.putLong("puntos", miViewModel.getPuntos());
        editor.putLong("sumador", miViewModel.getSumador());
        editor.putLong("contadorPulsacionesPartida", miViewModel.getContadorPulsacionesPartida());
        editor.putLong("contadorPulsacionesTotal", miViewModel.getContadorPulsacionesTotal());
        editor.putLong("contadorPulsacionesParcial", miViewModel.getContadorPulsacionesParcial());
        editor.putLong("contadorMejorasPartida", miViewModel.getContadorMejorasPartida());
        editor.putLong("contadorMejorasTotal", miViewModel.getContadorMejorasTotal());
        editor.putLong("puntosGastadosPartida", miViewModel.getContadorPuntosGastadosPartida());
        editor.putLong("puntosGastadosTotal", miViewModel.getContadorPuntosGastadosTotal());
        editor.putString("listadoDeMejoras", jsonMejoras);

        editor.commit();
    }

    private void cargarPartida() {
        SharedPreferences preferences = getSharedPreferences("partida", MODE_PRIVATE);
        preferences = getSharedPreferences("partida", MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonMejoras = "";
        ArrayList<Mejora> miListaGuardada;

        miViewModel.setPuntos(preferences.getLong("puntos", 0));
        miViewModel.setSumador(preferences.getLong("sumador", 1));
        miViewModel.setContadorPulsacionesPartida(preferences.getLong("contadorPulsacionesPartida", 0));
        miViewModel.setContadorPulsacionesTotal(preferences.getLong("contadorPulsacionesTotal", miViewModel.getContadorPulsacionesTotal()));
        miViewModel.setContadorPulsacionesParcial(preferences.getLong("contadorPulsacionesParcial", 0));
        miViewModel.setContadorMejorasPartida(preferences.getLong("contadorMejorasPartida", 0));
        miViewModel.setContadorMejorasTotal(preferences.getLong("contadorMejorasTotal", miViewModel.getContadorMejorasPartida()));
        miViewModel.setContadorPuntosGastadosPartida(preferences.getLong("puntosGastadosPartida", 0));
        miViewModel.setContadorPuntosGastadosTotal(preferences.getLong("puntosGastadosTotal", miViewModel.getContadorPuntosGastadosPartida()));

        jsonMejoras = preferences.getString("listadoDeMejoras", null);
        Type type = new TypeToken<ArrayList<Mejora>>() {
        }.getType();
        miListaGuardada = gson.fromJson(jsonMejoras, type);

        if (miListaGuardada != null) {
            miViewModel.listaMejoras = miListaGuardada;
            miViewModel.listaMejorasMutable.setValue(miListaGuardada);
        }

    }

    public boolean getFlag(){
        SharedPreferences preferences = getSharedPreferences("partida", MODE_PRIVATE);
        return preferences.getBoolean("dark",false);
    }
}
