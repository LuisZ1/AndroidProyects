package com.example.misrecordatorios.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.misrecordatorios.Adapters.RecordatorioAdapter;
import com.example.misrecordatorios.Models.Recordatorio;
import com.example.misrecordatorios.R;
import com.example.misrecordatorios.ViewModel.ViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class listFragment extends Fragment {

    private ViewModel miViewModel;
    private ArrayList<Recordatorio> listadoRecordatorios;
    private RecyclerView miRecycler;
    private RecordatorioAdapter miAdapter;
    private View view = null;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, container, false);

        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);

        miRecycler = view.findViewById(R.id.myRecyclerView);
        miRecycler.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 1, RecyclerView.VERTICAL, false));

        miAdapter = new RecordatorioAdapter(miViewModel.getListadoRecordatoriosMutable().getValue());
        miRecycler.setAdapter(miAdapter);

        miAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemSeleccionado = miRecycler.getChildAdapterPosition(view);
                Snackbar.make(view, "Seleccionado: " + itemSeleccionado, Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new detallesFragment()).addToBackStack(null).commit();
            }
        });

        return view;
    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        FloatingActionButton fab = view.findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new crearRecordatorioFragment()).addToBackStack(null).commit();
//
//                Snackbar.make(view, "Rellena los datos", Snackbar.LENGTH_SHORT)
//                        .setAction("Action", null).show();
//            }
//        });
//    }
}
