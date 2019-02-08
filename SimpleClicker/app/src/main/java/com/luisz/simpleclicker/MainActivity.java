package com.luisz.simpleclicker;

import androidx.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luisz.simpleclicker.Fragments.HelpFragment;
import com.luisz.simpleclicker.Fragments.HomeFragment;
import com.luisz.simpleclicker.Fragments.SettingsFragment;
import com.luisz.simpleclicker.Fragments.StatsFragment;
import com.luisz.simpleclicker.Fragments.UpgradesFragment;
import com.luisz.simpleclicker.Models.Mejora;
import com.luisz.simpleclicker.Models.Mejora_AutoClick;
import com.luisz.simpleclicker.ViewModel.ViewModel;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Typeface font;
    private ViewModel miViewModel;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Temas
        setTheme(getThemeFlag() ? R.style.ThemeOverlay_AppCompat_Dark : R.style.ThemeOverlay_AppCompat_Light);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miViewModel = ViewModelProviders.of(this).get(ViewModel.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        this.getSupportFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        Fragment current = getCurrentFragment();
                        if (current instanceof HomeFragment) {
                            navigationView.setCheckedItem(R.id.nav_inicio);
                        } else if (current instanceof SettingsFragment) {
                            navigationView.setCheckedItem(R.id.nav_ajustes);
                        } else if (current instanceof StatsFragment) {
                            navigationView.setCheckedItem(R.id.nav_stats);
                        } else if (current instanceof HelpFragment) {
                            navigationView.setCheckedItem(R.id.nav_ayuda);
                        } else if (current instanceof UpgradesFragment) {
                            navigationView.setCheckedItem(R.id.nav_mejoras);
                        }
                    }
                });

        font = Typeface.createFromAsset(getAssets(), "awesome.ttf");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.nav_drawer_open, R.string.nav_drawer_close);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_inicio);
            //lastMenuItemSelected = R.id.nav_inicio;
        }

        cargarPartida();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_inicio:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_ajustes:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_ayuda:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HelpFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_mejoras:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new UpgradesFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_stats:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StatsFragment()).addToBackStack(null).commit();
                break;
        }
        //lastMenuItemSelected = menuItem.getItemId();
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

    public Fragment getCurrentFragment() {
        return this.getSupportFragmentManager().findFragmentById(R.id.fragment_container);
    }

    public void guardarPartida() {
        SharedPreferences preferences = getSharedPreferences("partida", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String jsonMejoras = gson.toJson(miViewModel.getListaMejoras());
        String jsonMejorasAutoClick = gson.toJson(miViewModel.getListaMejoraAutoClick());

        editor.putLong("puntos", miViewModel.getPuntos());
        editor.putLong("sumador", miViewModel.getSumador());
        editor.putLong("contadorPulsacionesPartida", miViewModel.getContadorPulsacionesPartida());
        editor.putLong("contadorPulsacionesTotal", miViewModel.getContadorPulsacionesTotal());
        editor.putLong("contadorPulsacionesParcial", miViewModel.getContadorPulsacionesParcial());
        editor.putLong("contadorMejorasPartida", miViewModel.getContadorMejorasPartida());
        editor.putLong("contadorMejorasTotal", miViewModel.getContadorMejorasTotal());
        editor.putLong("puntosGastadosPartida", miViewModel.getContadorPuntosGastadosPartida());
        editor.putLong("puntosGastadosTotal", miViewModel.getContadorPuntosGastadosTotal());
        editor.putLong("puntuacionMaximaPartida", miViewModel.getPuntuacionMaximaPartida());
        editor.putLong("puntuacionMaximaTotal", miViewModel.getPuntuacionMaximaTotal());

        editor.putLong("contadorAluminioTotal", miViewModel.getContadorAluminioTotal());
        editor.putLong("contadorZincTotal", miViewModel.getContadorZincTotal());
        editor.putLong("contadorNiquelTotal", miViewModel.getContadorNiquelTotal());
        editor.putLong("contadorCobreTotal", miViewModel.getContadorCobreTotal());
        editor.putLong("contadorBronceTotal", miViewModel.getContadorBronceTotal());
        editor.putLong("contadorPlataTotal", miViewModel.getContadorPlataTotal());
        editor.putLong("contadorIridioTotal", miViewModel.getContadorIridioTotal());
        editor.putLong("contadorOroTotal", miViewModel.getContadorOroTotal());
        editor.putLong("contadorPlatinoTotal", miViewModel.getContadorPlatinoTotal());
        editor.putLong("contadorUranioTotal", miViewModel.getContadorUranioTotal());

        editor.putLong("contadorAluminioPartida", miViewModel.getContadorAluminioPartida());
        editor.putLong("contadorZincPartida", miViewModel.getContadorZincPartida());
        editor.putLong("contadorNiquelPartida", miViewModel.getContadorNiquelPartida());
        editor.putLong("contadorCobrePartida", miViewModel.getContadorCobrePartida());
        editor.putLong("contadorBroncePartida", miViewModel.getContadorBroncePartida());
        editor.putLong("contadorPlataPartida", miViewModel.getContadorPlataPartida());
        editor.putLong("contadorIridioPartida", miViewModel.getContadorIridioPartida());
        editor.putLong("contadorOroPartida", miViewModel.getContadorOroPartida());
        editor.putLong("contadorPlatinoPartida", miViewModel.getContadorPlatinoPartida());
        editor.putLong("contadorUranioPartida", miViewModel.getContadorUranioPartida());

        editor.putString("listadoDeMejoras", jsonMejoras);
        editor.putString("listadoDeMejorasAutoClick", jsonMejorasAutoClick);
        editor.putBoolean("autoClickComprado", miViewModel.isAutoClickComprado());
        editor.putInt("delay", miViewModel.getDelay());

        editor.commit();
    }

    public void cargarPartida() {
        SharedPreferences preferences = getSharedPreferences("partida", MODE_PRIVATE);
        preferences = getSharedPreferences("partida", MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonMejoras = "";
        String jsonMejorasAutoClick = "";
        ArrayList<Mejora> miListaGuardada;
        ArrayList<Mejora_AutoClick> miListaGuardadaAutoClick;

        //lista mejoras
        jsonMejoras = preferences.getString("listadoDeMejoras", null);
        Type type = new TypeToken<ArrayList<Mejora>>() {
        }.getType();
        miListaGuardada = gson.fromJson(jsonMejoras, type);

        if (miListaGuardada != null) {
            miViewModel.listaMejoras = miListaGuardada;
            miViewModel.listaMejorasMutable.setValue(miListaGuardada);
        }

        //lista mejoras autoclick
        jsonMejorasAutoClick = preferences.getString("listadoDeMejorasAutoClick", null);
        Type type2 = new TypeToken<ArrayList<Mejora_AutoClick>>() { }.getType();
        miListaGuardadaAutoClick = gson.fromJson(jsonMejorasAutoClick, type2);

        if (miListaGuardadaAutoClick != null) {
            miViewModel.listaMejoras = miListaGuardada;
            miViewModel.listaMejoraAutoClick = miListaGuardadaAutoClick;
            miViewModel.listaMejoraAutoClickMutable.setValue(miListaGuardadaAutoClick);
        }

        miViewModel.setDelay(preferences.getInt("delay",100000));
        miViewModel.setAutoClickComprado(preferences.getBoolean("autoClickComprado",false));
        miViewModel.setPuntos(preferences.getLong("puntos", 0));
        miViewModel.setSumador(preferences.getLong("sumador", 1));
        miViewModel.setContadorPulsacionesPartida(preferences.getLong("contadorPulsacionesPartida", 0));
        miViewModel.setContadorPulsacionesTotal(preferences.getLong("contadorPulsacionesTotal", miViewModel.getContadorPulsacionesPartida()));
        miViewModel.setContadorPulsacionesParcial(preferences.getLong("contadorPulsacionesParcial", 0));
        miViewModel.setContadorMejorasPartida(preferences.getLong("contadorMejorasPartida", 0));
        miViewModel.setContadorMejorasTotal(preferences.getLong("contadorMejorasTotal", miViewModel.getContadorMejorasPartida()));
        miViewModel.setContadorPuntosGastadosPartida(preferences.getLong("puntosGastadosPartida", 0));
        miViewModel.setContadorPuntosGastadosTotal(preferences.getLong("puntosGastadosTotal", miViewModel.getContadorPuntosGastadosPartida()));
        miViewModel.setPuntuacionMaximaPartida(preferences.getLong("puntuacionMaximaPartida", miViewModel.getPuntos()));
        miViewModel.setPuntuacionMaximaTotal(preferences.getLong("puntuacionMaximaTotal", miViewModel.getPuntos()));

        miViewModel.setContadorAluminioTotal(preferences.getLong("contadorAluminioTotal", miViewModel.getListaMejoras().get(0).getNivel()));
        miViewModel.setContadorZincTotal(preferences.getLong("contadorZincTotal", miViewModel.getListaMejoras().get(1).getNivel()));
        miViewModel.setContadorNiquelTotal(preferences.getLong("contadorNiquelTotal", miViewModel.getListaMejoras().get(2).getNivel()));
        miViewModel.setContadorCobreTotal(preferences.getLong("contadorCobreTotal", miViewModel.getListaMejoras().get(3).getNivel()));
        miViewModel.setContadorBronceTotal(preferences.getLong("contadorBronceTotal", miViewModel.getListaMejoras().get(4).getNivel()));
        miViewModel.setContadorPlataTotal(preferences.getLong("contadorPlataTotal", miViewModel.getListaMejoras().get(5).getNivel()));
        miViewModel.setContadorIridioTotal(preferences.getLong("contadorIridioTotal", miViewModel.getListaMejoras().get(6).getNivel()));
        miViewModel.setContadorOroTotal(preferences.getLong("contadorOroTotal", miViewModel.getListaMejoras().get(7).getNivel()));
        miViewModel.setContadorPlatinoTotal(preferences.getLong("contadorPlatinoTotal", miViewModel.getListaMejoras().get(8).getNivel()));
        miViewModel.setContadorUranioTotal(preferences.getLong("contadorUranioTotal", miViewModel.getListaMejoras().get(9).getNivel()));

        miViewModel.setContadorAluminioPartida(preferences.getLong("contadorAluminioPartida", miViewModel.getListaMejoras().get(0).getNivel()));
        miViewModel.setContadorZincPartida(preferences.getLong("contadorZincPartida", miViewModel.getListaMejoras().get(1).getNivel()));
        miViewModel.setContadorNiquelPartida(preferences.getLong("contadorNiquelPartida", miViewModel.getListaMejoras().get(2).getNivel()));
        miViewModel.setContadorCobrePartida(preferences.getLong("contadorCobrePartida", miViewModel.getListaMejoras().get(3).getNivel()));
        miViewModel.setContadorBroncePartida(preferences.getLong("contadorBroncePartida", miViewModel.getListaMejoras().get(4).getNivel()));
        miViewModel.setContadorPlataPartida(preferences.getLong("contadorPlataPartida", miViewModel.getListaMejoras().get(5).getNivel()));
        miViewModel.setContadorIridioPartida(preferences.getLong("contadorIridioPartida", miViewModel.getListaMejoras().get(6).getNivel()));
        miViewModel.setContadorOroPartida(preferences.getLong("contadorOroPartida", miViewModel.getListaMejoras().get(7).getNivel()));
        miViewModel.setContadorPlatinoPartida(preferences.getLong("contadorPlatinoPartida", miViewModel.getListaMejoras().get(8).getNivel()));
        miViewModel.setContadorUranioPartida(preferences.getLong("contadorUranioPartida", miViewModel.getListaMejoras().get(9).getNivel()));
    }

    public boolean getThemeFlag() {
        SharedPreferences preferences = getSharedPreferences("partida", MODE_PRIVATE);
        return preferences.getBoolean("dark", false);
    }



}
