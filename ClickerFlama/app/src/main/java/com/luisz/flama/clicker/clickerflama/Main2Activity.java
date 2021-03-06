package com.luisz.flama.clicker.clickerflama;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.luisz.flama.clicker.clickerflama.ViewModels.ViewModel;
import com.luisz.flama.clicker.clickerflama.adaptadores.AdapterMejoras;
import com.luisz.flama.clicker.clickerflama.modelos.mejora;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Main2Activity extends AppCompatActivity {

    TextView txtSumador, txtPuntos;

    ViewModel miViewModel = new ViewModel();
    RecyclerView miRecyclerView;
    AdapterMejoras adaptador;
    //ArrayList<mejora> listaMejoras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_v2);
        miViewModel = ViewModelProviders.of(this).get(ViewModel.class);

        txtPuntos = findViewById(R.id.txtPuntos);
        txtSumador = findViewById(R.id.txtSumador);

        miRecyclerView = findViewById(R.id.myRecyclerView);
        miRecyclerView.setLayoutManager(new GridLayoutManager(this,2,LinearLayoutManager.HORIZONTAL,false));

        adaptador = new AdapterMejoras(miViewModel.getListaMejoras().getValue());
        miRecyclerView.setAdapter(adaptador);

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mejoraSeleccionada = miRecyclerView.getChildAdapterPosition(view);
                gestionClickRecycler ( miViewModel.getListaMejoras().getValue().get(mejoraSeleccionada) ) ;
            }
        });


        final Observer<ArrayList<mejora>> miVMobserver = new Observer<ArrayList<mejora>>() {
            @Override
            public void onChanged(@Nullable ArrayList<mejora> listadoMejoras) {
                adaptador.setListaMejoras(listadoMejoras);
            }
        };

        miViewModel.getListaMejoras().observe(this, miVMobserver);

        displayForPuntos(miViewModel.puntos);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // guardarPartida();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //guardarPartida();
    }

    public void onClickSumar(View view){
        miViewModel.sumatron();
        displayForPuntos(miViewModel.puntos);
//        comprobarPrecios();
    }

    public void gestionClickRecycler(mejora item){
        if(!miViewModel.sumadorMejora(item)){
            Toast.makeText(getApplicationContext(), "Ere un tieso", Toast.LENGTH_SHORT).show();
        }else{
            //Toast.makeText(getApplicationContext(), "Comprado", Toast.LENGTH_SHORT).show();
        }
        displayForPuntos(miViewModel.puntos);
    }

    public void displayForPuntos(long puntos){

        //comprobarPrecios();

        DecimalFormat formatter = new DecimalFormat("###,###,###,###,###,###,###,###,###");

        TextView txtPuntos = findViewById(R.id.txtPuntos);
        txtPuntos.setText(formatter.format(puntos));

        TextView txtSumador = findViewById(R.id.txtSumador);
        txtSumador.setText(formatter.format(miViewModel.sumador));

        //Precios
/*
        TextView txtPrecioCobre = findViewById(R.id.txtPrecioCobre);
        txtPrecioCobre.setText(formatter.format(miViewModel.precioCobre));

        TextView txtPrecioBronce = findViewById(R.id.txtPrecioBronce);
        txtPrecioBronce.setText(formatter.format(miViewModel.precioBronce));

        TextView txtPrecioPlata = findViewById(R.id.txtPrecioPlata);
        txtPrecioPlata.setText(formatter.format(miViewModel.precioPlata));

        TextView txtPrecioOro = findViewById(R.id.txtPrecioOro);
        txtPrecioOro.setText(formatter.format(miViewModel.precioOro));

        TextView txtPrecioPlatino = findViewById(R.id.txtPrecioPlatino);
        txtPrecioPlatino.setText(formatter.format(miViewModel.precioPlatino));

        TextView txtPrecioDiamante = findViewById(R.id.txtPrecioDiamante);
        txtPrecioDiamante.setText(formatter.format(miViewModel.precioDiamante));
*/
        //Contadores

        TextView txtContadorPulsaciones = findViewById(R.id.txtPulsaciones);
        txtContadorPulsaciones.setText(formatter.format(miViewModel.contadorPulsaciones));

    }
/*
    public void comprobarPrecios(){
        if(miViewModel.puntos < miViewModel.precioCobre){
            btnCobre.setEnabled(false);
        }else{
            btnCobre.setEnabled(true);
        }

        if(miViewModel.puntos < miViewModel.precioBronce){
            btnBronce.setEnabled(false);
        }else{
            btnBronce.setEnabled(true);
        }

        if(miViewModel.puntos < miViewModel.precioPlata){
            btnPlata.setEnabled(false);
        }else{
            btnPlata.setEnabled(true);
        }

        if(miViewModel.puntos < miViewModel.precioOro){
            btnOro.setEnabled(false);
        }else{
            btnOro.setEnabled(true);
        }

        if(miViewModel.puntos < miViewModel.precioPlatino){
            btnPlatino.setEnabled(false);
        }else{
            btnPlatino.setEnabled(true);
        }

        if(miViewModel.puntos < miViewModel.precioDiamante){
            btnDiamante.setEnabled(false);
        }else{
            btnDiamante.setEnabled(true);
        }
    }

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

    private void guardarPartida(){
        SharedPreferences preferences = getSharedPreferences("partida",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putLong("puntos", miViewModel.puntos);
        editor.putLong("sumador", miViewModel.sumador);
        editor.putLong("precioCobre", miViewModel.precioCobre);
        editor.putLong("contadorCobre", miViewModel.contadorCobre);
        editor.putLong("precioBronce", miViewModel.precioBronce);
        editor.putLong("contadorBronce", miViewModel.contadorBronce);
        editor.putLong("precioPlata", miViewModel.precioPlata);
        editor.putLong("contadorPlata", miViewModel.contadorPlata);
        editor.putLong("precioOro", miViewModel.precioOro);
        editor.putLong("contadorOro", miViewModel.contadorOro);
        editor.putLong("precioPlatino", miViewModel.precioPlatino);
        editor.putLong("contadorPlatino", miViewModel.contadorPlatino);
        editor.putLong("precioDiamante", miViewModel.precioDiamante);
        editor.putLong("contadorDiamante", miViewModel.contadorDiamante);
        editor.putLong("contadorPulsaciones", miViewModel.contadorPulsaciones);
        editor.putLong("contadorPulsacionesParcial", miViewModel.contadorPulsacionesParcial);

        editor.commit();

    }

    private void cargarPartida(){
        SharedPreferences preferences = getSharedPreferences("partida",MODE_PRIVATE);

        miViewModel.puntos = preferences.getLong("puntos",0);
        miViewModel.sumador = preferences.getLong("sumador",1);
        miViewModel.precioCobre = preferences.getLong("precioCobre",cons.BASE_PRECIO_COBRE);
        miViewModel.contadorCobre = preferences.getLong("contadorCobre",0);
        miViewModel.precioBronce = preferences.getLong("precioBronce",cons.BASE_PRECIO_BRONCE);
        miViewModel.contadorBronce = preferences.getLong("contadorBronce",0);
        miViewModel.precioPlata = preferences.getLong("precioPlata",cons.BASE_PRECIO_PLATA);
        miViewModel.contadorPlata = preferences.getLong("contadorPlata",0);
        miViewModel.precioOro = preferences.getLong("precioOro",cons.BASE_PRECIO_ORO);
        miViewModel.contadorOro = preferences.getLong("contadorOro",0);
        miViewModel.precioPlatino = preferences.getLong("precioPlatino",cons.BASE_PRECIO_PLATINO);
        miViewModel.contadorPlatino = preferences.getLong("contadorPlatino",0);
        miViewModel.precioDiamante = preferences.getLong("precioDiamante",cons.BASE_PRECIO_DIAMANTE);
        miViewModel.contadorDiamante = preferences.getLong("contadorDiamante",0);
        miViewModel.contadorPulsaciones = preferences.getLong("contadorPulsaciones",0);
        miViewModel.contadorPulsacionesParcial = preferences.getLong("contadorPulsacionesParcial",0);
    }*/
}
