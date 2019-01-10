package com.luisz.simpleclicker;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luisz.simpleclicker.Adapter.AdapterMejoras;
import com.luisz.simpleclicker.Models.Mejora;
import com.luisz.simpleclicker.ViewModel.ViewModel;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity_OLD extends AppCompatActivity {

    TextView txtSumador, txtPuntos, lblClicks, lblSumador;
    Switch swAutoWalk, swAutoRun;

    int x = 0;


    ViewModel miViewModel;
    RecyclerView miRecyclerView;
    AdapterMejoras adaptador;
    Typeface font;
    int delay = 1000;
    int period = delay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_old);
        miViewModel = ViewModelProviders.of(this).get(ViewModel.class);
        miViewModel = new ViewModel(this.getApplication());
        font = Typeface.createFromAsset(getAssets(), "awesome.ttf");

        txtPuntos = findViewById(R.id.txtPuntos);
        txtSumador = findViewById(R.id.txtSumador);
        lblClicks = findViewById(R.id.lblN_clicks);
        lblSumador = findViewById(R.id.lblSumador);
        swAutoWalk = findViewById(R.id.swAutoWalk);
        swAutoRun = findViewById(R.id.swAutoRun);

        lblClicks.setTypeface(font);
        lblSumador.setTypeface(font);
        swAutoWalk.setTypeface(font);
        swAutoRun.setTypeface(font);

        miRecyclerView = findViewById(R.id.myRecyclerView);
        miRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.HORIZONTAL, false));

        adaptador = new AdapterMejoras(miViewModel.getListaMejorasMutable().getValue());
        miRecyclerView.setAdapter(adaptador);

        cargarPartida();

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mejoraSeleccionada = miRecyclerView.getChildAdapterPosition(view);
                if (mejoraSeleccionada != -1) {
                    gestionClickRecycler(mejoraSeleccionada);
                }
            }
        });


        final Observer<ArrayList<Mejora>> miVMobserver = new Observer<ArrayList<Mejora>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Mejora> listadoMejoras) {
                adaptador.setListaMejoras(listadoMejoras);
            }
        };

        miViewModel.getListaMejorasMutable().observe(this, miVMobserver);

        displayForPuntos(miViewModel.puntos);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        guardarPartida();
    }
    @Override
    protected void onStop() {
        super.onStop();
        guardarPartida();
    }
    @Override
    protected void onPause() {
        super.onPause();
        guardarPartida();
    }

    public void onClickSumar(View view) {
        miViewModel.sumatron();
        displayForPuntos(miViewModel.puntos);
//        comprobarPrecios();
    }

    synchronized void gestionClickRecycler(int item) {
        miViewModel.sumadorMejora(item);
        displayForPuntos(miViewModel.puntos);
    }

    public void displayForPuntos(long puntos) {

        //comprobarPrecios();
        DecimalFormat formatter = new DecimalFormat("###,###,###,###,###,###,###,###,###");

        TextView txtPuntos = findViewById(R.id.txtPuntos);
        txtPuntos.setText(formatter.format(puntos));

        TextView txtSumador = findViewById(R.id.txtSumador);
        txtSumador.setText(formatter.format(miViewModel.sumador));

        TextView txtContadorPulsaciones = findViewById(R.id.txtPulsaciones);
        txtContadorPulsaciones.setText(formatter.format(miViewModel.contadorPulsaciones));

    }

    //MINERO AUTOMATICO

    private Timer timer;
    private TimerTask timerTask;
    private Handler handler = new Handler();

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }

    public void startTimer() {
        timer = new Timer();
        timerTask = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        miViewModel.sumatron();
                        displayForPuntos(miViewModel.puntos);
                    }
                });
            }
        };

        timer.schedule(timerTask, delay, period);
    }

    public void onSwitchWalkClicked(View v) {
        boolean on = ((Switch) v).isChecked();
        swAutoRun.setChecked(false);
        delay = 1000;
        period = 1000;
        if (on) {
            stopTimer();
            startTimer();
        } else {
            stopTimer();
        }
    }

    public void onSwitchRunClicked(View v) {
        boolean on = ((Switch) v).isChecked();
        swAutoWalk.setChecked(false);
        delay = 100;
        period = 100;
        if (on) {
            stopTimer();
            startTimer();
        } else {
            stopTimer();
        }
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
        Type type = new TypeToken<ArrayList<Mejora>>(){}.getType();
        miListaGuardada = gson.fromJson(jsonMejoras,type);

        if(miListaGuardada != null){
            miViewModel.listaMejoras = miListaGuardada;
            miViewModel.listaMejorasMutable.setValue(miListaGuardada);
        }
    }
}
