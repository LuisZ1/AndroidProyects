package com.luisz.flama.clicker.clickerflama;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    ViewModel miViewModel = null;
    RecyclerView miRecyclerView;
    ArrayList<mejora> listaMejoras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_v2);
        miViewModel = ViewModelProviders.of(this).get(ViewModel.class);


        miRecyclerView = findViewById(R.id.myRecyclerView);
        miRecyclerView.setLayoutManager(new GridLayoutManager(this,2,LinearLayoutManager.HORIZONTAL,false));

        listaMejoras = miViewModel.rellenarListaMejoras();

        AdapterMejoras adaptador = new AdapterMejoras(listaMejoras);


        miRecyclerView.setAdapter(adaptador);


    }
}
