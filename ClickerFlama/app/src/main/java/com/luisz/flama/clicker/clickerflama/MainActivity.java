package com.luisz.flama.clicker.clickerflama;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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


    public void displayForPuntos(long puntos){

        TextView txtPuntos = findViewById(R.id.txtPuntos);
        txtPuntos.setText(String.valueOf(puntos));

        TextView txtSumador = findViewById(R.id.txtSumador);
        txtSumador.setText(String.valueOf(miViewModel.sumador));

        TextView txtPrecioCobre = findViewById(R.id.txtPrecioCobre);
        txtPrecioCobre.setText(String.valueOf(miViewModel.precioCobre));

        TextView txtPrecioBronce = findViewById(R.id.txtPrecioBronce);
        txtPrecioBronce.setText(String.valueOf(miViewModel.precioBronce));

        TextView txtPrecioPlata = findViewById(R.id.txtPrecioPlata);
        txtPrecioPlata.setText(String.valueOf(miViewModel.precioPlata));

        TextView txtContadorPulsaciones = findViewById(R.id.txtNPulsaciones);
        txtContadorPulsaciones.setText(String.valueOf(miViewModel.contadorPulsaciones));

        TextView txtContadorCobre = findViewById(R.id.txtPulsacionesCobre);
        txtContadorCobre.setText(String.valueOf(miViewModel.contadorCobre));

        TextView txtContadorBronce = findViewById(R.id.txtPulsacionesBronce);
        txtContadorBronce.setText(String.valueOf(miViewModel.contadorBronce));

//        if(miViewModel.contador > 0) {
//            TextView txtPrecioPulsacionesCobre = findViewById(R.id.txtPrecioClickCobre);
//            txtPrecioPulsacionesCobre.setText(String.valueOf(miViewModel.precioCobre / miViewModel.sumador));
//
//            TextView txtPrecioPulsacionesBronce = findViewById(R.id.txtPrecioClickBronce);
//            txtPrecioPulsacionesBronce.setText(String.valueOf(miViewModel.precioBronce / miViewModel.sumador));
//
//            TextView txtPrecioPulsacionesPlata = findViewById(R.id.txtPrecioClickPlata);
//            txtPrecioPulsacionesPlata.setText(String.valueOf(miViewModel.precioPlata / miViewModel.sumador));
//        }
    }
}
