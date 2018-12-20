package com.luisz.simpleclicker;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txtSumador, txtPuntos;

    ViewModel miViewModel = new ViewModel();
    RecyclerView miRecyclerView;
    AdapterMejoras adaptador;
    Typeface font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        miViewModel = ViewModelProviders.of(this).get(ViewModel.class);
        font = Typeface.createFromAsset(getAssets(), "FontAwesome.ttf");

        txtPuntos = findViewById(R.id.txtPuntos);
        txtSumador = findViewById(R.id.txtSumador);

        miRecyclerView = findViewById(R.id.myRecyclerView);
        miRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.HORIZONTAL, false));

        adaptador = new AdapterMejoras(miViewModel.getListaMejorasMutable().getValue());
        miRecyclerView.setAdapter(adaptador);

        //cargarPartida(); //TODO

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
    protected void onDestroy() {
        super.onDestroy();
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
/*
        //MINERO AUTOMATICO

        private Timer timer;
        private TimerTask timerTask;
        private Handler handler = new Handler();

        public void stopTimer(){
            if(timer != null){
                timer.cancel();
                timer.purge();
            }
        }

        public void startTimer(){
            timer = new Timer();
            timerTask = new TimerTask() {
                public void run() {
                    handler.post(new Runnable() {
                        public void run(){
                            miViewModel.sumatron();
                            displayForPuntos(miViewModel.puntos);
                        }
                    });
                }
            };
            timer.schedule(timerTask, delay, period);
        }

        public void onSwitchClicked(View v){
            //Is the switch on?
            boolean on = ((Switch) v).isChecked();

            if(on)
            {
                startTimer();
            }
            else
            {
                stopTimer();
            }
        }
    */
    private void guardarPartida() {

        SharedPreferences preferences = getSharedPreferences("partida", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putLong("puntos", miViewModel.puntos);
        editor.putLong("sumador", miViewModel.sumador);
        editor.putLong("contadorPulsaciones", miViewModel.contadorPulsaciones);
        editor.putLong("contadorPulsacionesParcial", miViewModel.contadorPulsacionesParcial);
        try {
            editor.putString("listadoDeMejoras", ObjectSerializer.serialize(miViewModel.getListaMejoras()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        editor.commit();

    }

    private void cargarPartida() {
        SharedPreferences preferences = getSharedPreferences("partida", MODE_PRIVATE);
        preferences = getSharedPreferences("partida", MODE_PRIVATE);

        miViewModel.puntos = preferences.getLong("puntos", 0);
        miViewModel.sumador = preferences.getLong("sumador", 1);
        miViewModel.contadorPulsaciones = preferences.getLong("contadorPulsaciones", 0);
        miViewModel.contadorPulsacionesParcial = preferences.getLong("contadorPulsacionesParcial", 0);
        try {
            miViewModel.listaMejoras = (ArrayList<Mejora>) ObjectSerializer.deserialize(preferences.getString("listadoDeMejoras", ObjectSerializer.serialize(new ArrayList<Mejora>())));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
