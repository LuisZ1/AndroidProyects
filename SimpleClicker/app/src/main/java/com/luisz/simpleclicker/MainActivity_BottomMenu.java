package com.luisz.simpleclicker;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luisz.simpleclicker.Fragments.HelpFragment;
import com.luisz.simpleclicker.Fragments.HomeFragment;
import com.luisz.simpleclicker.Fragments.SettingsFragment;
import com.luisz.simpleclicker.Fragments.StatsFragment;
import com.luisz.simpleclicker.Fragments.UpgradesFragment;
import com.luisz.simpleclicker.Models.Mejora;
import com.luisz.simpleclicker.Models.MejoraAutoClick;
import com.luisz.simpleclicker.ViewModel.ViewModel;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity_BottomMenu extends AppCompatActivity {

    private Typeface font;
    private ViewModel miViewModel;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_bottom_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new HomeFragment()).addToBackStack(null).commit();
                    return true;
                case R.id.navigation_bottom_ajustes:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new SettingsFragment()).addToBackStack(null).commit();
                    return true;
                case R.id.navigation_bottom_ayuda:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new HelpFragment()).addToBackStack(null).commit();
                    return true;
                case R.id.navigation_bottom_mejoras:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new UpgradesFragment()).addToBackStack(null).commit();
                    return true;
                case R.id.navigation_bottom_stats:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new StatsFragment()).addToBackStack(null).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bottom_menu);

        miViewModel = ViewModelProviders.of(this).get(ViewModel.class);

        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigation.setSelectedItemId(R.id.navigation_bottom_home);
        }
        //TODO boton back

        cargarPartida();
    }

    @Override
    protected void onPause() {
        super.onPause();
        guardarPartida();
    }

    public Fragment getCurrentFragment() {
        return this.getSupportFragmentManager().findFragmentById(R.id.fragment_container);
    }

    @Override
    public void onBackPressed() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        int seletedItemId = bottomNavigationView.getSelectedItemId();
        if (R.id.navigation_bottom_home != seletedItemId) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).addToBackStack(null).commit();
            bottomNavigationView.setSelectedItemId(R.id.navigation_bottom_home);
        } else {
            //super.onBackPressed();
        }
    }

    private void guardarPartida() {
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

    private void cargarPartida() {
        SharedPreferences preferences = getSharedPreferences("partida", MODE_PRIVATE);
        preferences = getSharedPreferences("partida", MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonMejoras = "";
        String jsonMejorasAutoClick = "";
        ArrayList<Mejora> miListaGuardada;
        ArrayList<MejoraAutoClick> miListaGuardadaAutoClick;

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
        Type type2 = new TypeToken<ArrayList<MejoraAutoClick>>() { }.getType();
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


}
