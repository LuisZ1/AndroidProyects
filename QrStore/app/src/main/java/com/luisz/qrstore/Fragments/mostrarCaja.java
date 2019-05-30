package com.luisz.qrstore.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luisz.qrstore.Models.Caja;
import com.luisz.qrstore.Models.Estanteria;
import com.luisz.qrstore.R;
import com.luisz.qrstore.Viewmodel.ViewModel;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

public class mostrarCaja extends Fragment {

    private View view;
    private ViewModel miViewModel;
    private FragmentManager fragmentManager = getFragmentManager();
    private Estanteria estanteria;
    private Caja caja;
    private TextView txtNombreCaja, txtIdCaja;

    public mostrarCaja() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mostrar_caja, container, false);
        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);

        caja = miViewModel.getCajaEscaneada();
        //estanteria = miViewModel.getEstanteriaEscaneada();

        txtNombreCaja = view.findViewById(R.id.txtNombreCajaEscaneada);
        txtNombreCaja.setText(caja.getNombre());
        txtIdCaja = view.findViewById(R.id.txtIdCajaEscaneada);
        txtIdCaja.setText(caja.getIdestanteria());

        return view;
    }
}
