package com.luisz.flama.clicker.clickerflama;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.luisz.flama.clicker.clickerflama.ViewModels.ViewModel;
import com.luisz.flama.clicker.clickerflama.adaptadores.AdapterMejoras;
import com.luisz.flama.clicker.clickerflama.modelos.mejora;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    TextView txtSumador, txtPuntos;

    ViewModel miViewModel = new ViewModel();
    RecyclerView miRecyclerView;
    ArrayList<mejora> listaMejoras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_v2);
        miViewModel = ViewModelProviders.of(this).get(ViewModel.class);

        txtPuntos = findViewById(R.id.txtPuntos);
        txtSumador = findViewById(R.id.txtSumador);

        miRecyclerView = findViewById(R.id.myRecyclerView);
        miRecyclerView.setLayoutManager(new GridLayoutManager(this,2,LinearLayoutManager.HORIZONTAL,false));

        listaMejoras = miViewModel.getListaMejoras();

        AdapterMejoras adaptador = new AdapterMejoras(listaMejoras);
        miRecyclerView.setAdapter(adaptador);
    }

    public void onClickSumar(View view){
        miViewModel.sumatron();
//        MainActivity.displayForPuntos(miViewModel.puntos);
//        comprobarPrecios();
    }
}
