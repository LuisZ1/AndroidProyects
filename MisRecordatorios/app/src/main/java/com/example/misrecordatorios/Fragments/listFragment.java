package com.example.misrecordatorios.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.misrecordatorios.Adapters.RecordatorioAdapter;
import com.example.misrecordatorios.Models.Recordatorio;
import com.example.misrecordatorios.R;
import com.example.misrecordatorios.ViewModel.ViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class listFragment extends Fragment {

    private ViewModel miViewModel;
    private List<Recordatorio> listadoRecordatorios;
    private RecyclerView miRecycler;
    private RecordatorioAdapter miAdapter;
    private View view = null;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, container, false);

        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);

        miRecycler = view.findViewById(R.id.myRecyclerView);
        miRecycler.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 1, RecyclerView.VERTICAL, false));

        //listadoRecordatorios = miViewModel.getListadoRecordatorios().getValue();
        miAdapter = new RecordatorioAdapter();

        miRecycler.setAdapter(miAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        miViewModel.recuperarRecordatorios().observe(this, new Observer<List<Recordatorio>>() {
            @Override
            public void onChanged(@Nullable List<Recordatorio> recordatorios) {
                //miAdapter = new RecordatorioAdapter(recordatorios);
                miAdapter.setListaRecordatorios(recordatorios);

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

            }
        });

    }
}
