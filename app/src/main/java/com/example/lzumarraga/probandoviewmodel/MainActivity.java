package com.example.lzumarraga.probandoviewmodel;

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

    public void onClickSumadorx2(View view){
        miViewModel.sumadorx2();
        displayForPuntos(miViewModel.contador);
    }

    public void onClickSumadorx4(View view){
        miViewModel.sumadorx4();
        displayForPuntos(miViewModel.contador);
    }

    public void displayForPuntos(long puntos){
        TextView txtPuntos = findViewById(R.id.txtPuntos);
        TextView txtSumador = findViewById(R.id.txtSumador);
        TextView txtPreciox2 = findViewById(R.id.txtPreciox2);
        TextView txtPreciox4 = findViewById(R.id.txtPreciox4);
        txtPuntos.setText(String.valueOf(puntos));
        txtSumador.setText(String.valueOf(miViewModel.sumador));
        txtPreciox2.setText(String.valueOf(miViewModel.preciox2));
        txtPreciox4.setText(String.valueOf(miViewModel.preciox4));
    }

}
