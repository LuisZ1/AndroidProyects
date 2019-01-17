package com.luisz.simpleclicker.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.luisz.simpleclicker.Adapter.AdapterMejoras;
import com.luisz.simpleclicker.Adapter.AdapterMejorasAutoClick;
import com.luisz.simpleclicker.Models.Mejora;
import com.luisz.simpleclicker.Models.MejoraAutoClick;
import com.luisz.simpleclicker.R;
import com.luisz.simpleclicker.ViewModel.ViewModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class UpgradesFragment extends Fragment {

    private ViewModel miViewModel;
    private AdapterMejorasAutoClick adaptador;
    private Typeface font;
    private RecyclerView miRecyclerView;
    private DecimalFormat formatter = new DecimalFormat("###,###,###,###,###,###,###,###,###");

    private TextView txtPuntos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upgrades, container, false);
        getActivity().setTitle(getActivity().getApplicationContext().getString(R.string.mejoras));

        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);
        font = Typeface.createFromAsset(getActivity().getAssets(), "awesome.ttf");

        txtPuntos = view.findViewById(R.id.txtPuntos);
        txtPuntos.setText(formatter.format(miViewModel.getPuntos()));

        //manejando recicler y adapter
        miRecyclerView = view.findViewById(R.id.recyclerMejorasAutoClick);
        miRecyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 1, LinearLayoutManager.HORIZONTAL, false));

        adaptador = new AdapterMejorasAutoClick(miViewModel.getListaMejoraAutoClickMutable().getValue());
        miRecyclerView.setAdapter(adaptador);

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mejoraSeleccionada = miRecyclerView.getChildAdapterPosition(view);
                if (mejoraSeleccionada != -1) {
                    if(miViewModel.clickCompraMejoraAutoClick(mejoraSeleccionada)) {
                        Toast.makeText(getActivity().getApplicationContext(), "Mejora comprada" , Toast.LENGTH_SHORT).show();
                        adaptador.eliminarMejoraComprada(mejoraSeleccionada);
                        txtPuntos.setText(formatter.format(miViewModel.getPuntos()));
                    }else{
                        Toast.makeText(getActivity().getApplicationContext(), "No puedes comprar esa mejora", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        final Observer<ArrayList<MejoraAutoClick>> miVMobserver = new Observer<ArrayList<MejoraAutoClick>>() {
            @Override
            public void onChanged(@Nullable ArrayList<MejoraAutoClick> listadoMejorasAutoClick) {
                adaptador.setListaMejorasAutoClick(listadoMejorasAutoClick);
            }
        };

        miViewModel.getListaMejoraAutoClickMutable().observe(this, miVMobserver);


        return view;
    }

}
