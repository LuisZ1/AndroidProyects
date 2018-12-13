package com.luisz.flama.clicker.clickerflama;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
//        Toast.makeText(this, "Oro: En construccion", Toast.LENGTH_SHORT).show();
    }

    public void onClickSumadorPlatino(View view){
        miViewModel.sumadorPlatino();
        displayForPuntos(miViewModel.contador);
//        Toast.makeText(this, "Platino: En construccion", Toast.LENGTH_SHORT).show();
    }

    public void onClickSumadorDiamante(View view){
        miViewModel.sumadorDiamante();
        displayForPuntos(miViewModel.contador);
//        Toast.makeText(this, "Diamante: En construccion", Toast.LENGTH_SHORT).show();
    }


    public void displayForPuntos(long puntos){

        TextView txtPuntos = findViewById(R.id.txtPuntos);
        txtPuntos.setText(String.valueOf(puntos));

        TextView txtSumador = findViewById(R.id.txtSumador);
        txtSumador.setText(String.valueOf(miViewModel.sumador));

        //Precios

        TextView txtPrecioCobre = findViewById(R.id.txtPrecioCobre);
        txtPrecioCobre.setText(String.valueOf(miViewModel.precioCobre));

        TextView txtPrecioBronce = findViewById(R.id.txtPrecioBronce);
        txtPrecioBronce.setText(String.valueOf(miViewModel.precioBronce));

        TextView txtPrecioPlata = findViewById(R.id.txtPrecioPlata);
        txtPrecioPlata.setText(String.valueOf(miViewModel.precioPlata));

        TextView txtPrecioOro = findViewById(R.id.txtPrecioOro);
        txtPrecioOro.setText(String.valueOf(miViewModel.precioOro));

        TextView txtPrecioPlatino = findViewById(R.id.txtPrecioPlatino);
        txtPrecioPlatino.setText(String.valueOf(miViewModel.precioPlatino));

        TextView txtPrecioDiamante = findViewById(R.id.txtPrecioDiamante);
        txtPrecioDiamante.setText(String.valueOf(miViewModel.precioDiamante));

        //Contadores

        TextView txtContadorPulsaciones = findViewById(R.id.txtNPulsaciones);
        txtContadorPulsaciones.setText(String.valueOf(miViewModel.contadorPulsaciones));

        TextView txtContadorParcial = findViewById(R.id.txtPulsacionesParcial);
        txtContadorParcial.setText(String.valueOf(miViewModel.contadorPulsacionesParcial));

        TextView txtContadorCobre = findViewById(R.id.txtPulsacionesCobre);
        txtContadorCobre.setText(String.valueOf(miViewModel.contadorCobre));

        TextView txtContadorBronce = findViewById(R.id.txtPulsacionesBronce);
        txtContadorBronce.setText(String.valueOf(miViewModel.contadorBronce));

        TextView txtContadorPlata = findViewById(R.id.txtPulsacionesPlata);
        txtContadorPlata.setText(String.valueOf(miViewModel.contadorPlata));

    }
}
