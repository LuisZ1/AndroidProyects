package com.luisz.qrstore.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.luisz.qrstore.Adapter.ListadoCajasAdapter;
import com.luisz.qrstore.Models.Estanteria;
import com.luisz.qrstore.R;
import com.luisz.qrstore.Viewmodel.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class mostrarEstanteria extends Fragment {

    private View view;
    private ViewModel miViewModel;
    private FragmentManager fragmentManager = getFragmentManager();
    private Estanteria estanteria;
    private TextView txtNombreEstanteria, txtIdEstanteria, txtnumerocajas;
    private RecyclerView miRecyclerView;
    private ListadoCajasAdapter adaptador;

    public mostrarEstanteria() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mostrar_estanteria, container, false);
        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);

        estanteria = miViewModel.getEstanteriaEscaneada();

        txtNombreEstanteria = view.findViewById(R.id.txtNombreEstanteriaEscaneada);
        txtNombreEstanteria.setText(estanteria.getNombre());
        txtIdEstanteria = view.findViewById(R.id.txtIdEstanteriaEscaneada);
        txtIdEstanteria.setText(estanteria.getIdestanteria());
        txtnumerocajas = view.findViewById(R.id.txtCajasEstanteriaEscaneada);
        txtnumerocajas.setText(String.valueOf(miViewModel.getListadoCajas().size()));

        miRecyclerView = view.findViewById(R.id.recyclerCajas);
        miRecyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 1, RecyclerView.VERTICAL, false));
        adaptador = new ListadoCajasAdapter(miViewModel.getListadoCajas());
        miRecyclerView.setAdapter(adaptador);

        return view;
    }
}
