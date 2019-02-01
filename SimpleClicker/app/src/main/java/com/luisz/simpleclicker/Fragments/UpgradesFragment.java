package com.luisz.simpleclicker.Fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.luisz.simpleclicker.Adapter.AdapterMejorasAutoClick;
import com.luisz.simpleclicker.Adapter.AdapterMejoras_Per_Maqr_Her;
import com.luisz.simpleclicker.Models.Mejora_AutoClick;
import com.luisz.simpleclicker.R;
import com.luisz.simpleclicker.ViewModel.ViewModel;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class UpgradesFragment extends Fragment {

    private ViewModel miViewModel;
    private AdapterMejorasAutoClick adaptador;
    private AdapterMejoras_Per_Maqr_Her adaptadorPersonal, adaptadorMaquinaria, adaptadorHerramientas;
    private Typeface font;
    private RecyclerView miRecyclerView, miRecyclerViewPersonal, miRecyclerViewMaquinaria, miRecyclerViewHermientas;
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

        //manejando recicler y adapter AUTOCLICK
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
                        //adaptador.notifyChange();
                    }else{
                        Toast.makeText(getActivity().getApplicationContext(), "No puedes comprar esa mejora", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        final Observer<ArrayList<Mejora_AutoClick>> miVMobserver = new Observer<ArrayList<Mejora_AutoClick>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Mejora_AutoClick> listadoMejorasAutoClick) {
                adaptador.setListaMejorasAutoClick(listadoMejorasAutoClick);
            }
        };

        miViewModel.getListaMejoraAutoClickMutable().observe(this, miVMobserver);

        //manejando recicler y adapter PERSONAL
        miRecyclerViewPersonal = view.findViewById(R.id.recyclerMejorasPersonal);
        miRecyclerViewPersonal.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 1, LinearLayoutManager.HORIZONTAL, false));

        adaptadorPersonal = new AdapterMejoras_Per_Maqr_Her(miViewModel.getListaMejoraPersonalMutable().getValue());
        miRecyclerViewPersonal.setAdapter(adaptadorPersonal);

        adaptadorPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mejoraSeleccionada = miRecyclerView.getChildAdapterPosition(view);
                if (mejoraSeleccionada != -1) {
                   if(miViewModel.clickCompraMejora_Per_Maq_Her("personal",mejoraSeleccionada)){
                       Toast.makeText(getActivity().getApplicationContext(), "Mejora comprada" , Toast.LENGTH_SHORT).show();
                       txtPuntos.setText(formatter.format(miViewModel.getPuntos()));
                       adaptadorPersonal.notifyChange();
                   }else{
                       Toast.makeText(getActivity().getApplicationContext(), "No puedes comprar esa mejora" , Toast.LENGTH_SHORT).show();
                   }
                }
            }
        });

        //TODO observador personal

        //manejando recicler y adapter MAQUINARIA
        miRecyclerViewMaquinaria = view.findViewById(R.id.recyclerMejorasMaquinaria);
        miRecyclerViewMaquinaria.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 1, LinearLayoutManager.HORIZONTAL, false));

        adaptadorMaquinaria = new AdapterMejoras_Per_Maqr_Her(miViewModel.getListaMejoraMaquinariaMutable().getValue());
        miRecyclerViewMaquinaria.setAdapter(adaptadorMaquinaria);

        adaptadorMaquinaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mejoraSeleccionada = miRecyclerView.getChildAdapterPosition(view);
                if (mejoraSeleccionada != -1) {
                    if(miViewModel.clickCompraMejora_Per_Maq_Her("maquinaria",mejoraSeleccionada)){
                        Toast.makeText(getActivity().getApplicationContext(), "Mejora comprada" , Toast.LENGTH_SHORT).show();
                        txtPuntos.setText(formatter.format(miViewModel.getPuntos()));
                        adaptadorMaquinaria.notifyChange();
                    }else{
                        Toast.makeText(getActivity().getApplicationContext(), "No puedes comprar esa mejora" , Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        //manejando recicler y adapter HERRAMIENTAS
        miRecyclerViewHermientas = view.findViewById(R.id.recyclerMejorasHerramientas);
        miRecyclerViewHermientas.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 1, LinearLayoutManager.HORIZONTAL, false));

        adaptadorHerramientas = new AdapterMejoras_Per_Maqr_Her(miViewModel.getListaMejoraHerramientasMutable().getValue());
        miRecyclerViewHermientas.setAdapter(adaptadorHerramientas);

        adaptadorHerramientas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mejoraSeleccionada = miRecyclerView.getChildAdapterPosition(view);
                if (mejoraSeleccionada != -1) {
                    if(miViewModel.clickCompraMejora_Per_Maq_Her("herramientas",mejoraSeleccionada)){
                        Toast.makeText(getActivity().getApplicationContext(), "Mejora comprada" , Toast.LENGTH_SHORT).show();
                        txtPuntos.setText(formatter.format(miViewModel.getPuntos()));
                        adaptadorHerramientas.notifyChange();
                    }else{
                        Toast.makeText(getActivity().getApplicationContext(), "No puedes comprar esa mejora" , Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }

}
