package com.luisz.flama.clicker.clickerflama;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ViewModel miViewModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        miViewModel = ViewModelProviders.of(this).get(ViewModel.class);
        displayForPuntos(miViewModel.contador);
    }

    public void onClickSumar(View view){
        miViewModel.sumatron();
        displayForPuntos(miViewModel.contador);
    }

    public void onClickSumadorCobre(View view){
        miViewModel.sumadorCobre();
        displayForPuntos(miViewModel.contador);
    }

    public void onClickSumadorBronce(View view){
        miViewModel.sumadorBronce();
        displayForPuntos(miViewModel.contador);
    }

    public void onClickSumadorPlata(View view){
        miViewModel.sumadorPlata();
        displayForPuntos(miViewModel.contador);
    }

    public void onClickSumadorOro(View view){
        miViewModel.sumadorOro();
        displayForPuntos(miViewModel.contador);
    }

    public void onClickSumadorPlatino(View view){
        miViewModel.sumadorPlatino();
        displayForPuntos(miViewModel.contador);
    }

    public void onClickSumadorDiamante(View view){
        miViewModel.sumadorDiamante();
        displayForPuntos(miViewModel.contador);
    }

    public void displayForPuntos(long puntos){

        DecimalFormat formatter = new DecimalFormat("###,###,###,###,###,###,###");

        TextView txtPuntos = findViewById(R.id.txtPuntos);
        txtPuntos.setText(formatter.format(puntos));

        TextView txtSumador = findViewById(R.id.txtSumador);
        txtSumador.setText(formatter.format(miViewModel.sumador));

        //Precios

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

        //Contadores

        TextView txtContadorPulsaciones = findViewById(R.id.txtNPulsaciones);
        txtContadorPulsaciones.setText(formatter.format(miViewModel.contadorPulsaciones));

//        TextView txtContadorParcial = findViewById(R.id.txtPulsacionesParcial);
//        txtContadorParcial.setText(formatter.format(miViewModel.contadorPulsacionesParcial));
//
//        TextView txtContadorCobre = findViewById(R.id.txtPulsacionesCobre);
//        txtContadorCobre.setText(String.valueOf(miViewModel.contadorCobre));
//
//        TextView txtContadorBronce = findViewById(R.id.txtPulsacionesBronce);
//        txtContadorBronce.setText(String.valueOf(miViewModel.contadorBronce));
//
//        TextView txtContadorPlata = findViewById(R.id.txtPulsacionesPlata);
//        txtContadorPlata.setText(String.valueOf(miViewModel.contadorPlata));

    }


    //MINERO AUTOMATICO

    private Timer timer;
    private TimerTask timerTask;
    private Handler handler = new Handler();
    private int delay = 1000;
    private int period = delay;
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
                        displayForPuntos(miViewModel.contador);
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
}
