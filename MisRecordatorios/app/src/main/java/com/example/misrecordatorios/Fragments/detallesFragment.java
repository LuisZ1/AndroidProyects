package com.example.misrecordatorios.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.misrecordatorios.MainActivity;
import com.example.misrecordatorios.Models.Recordatorio;
import com.example.misrecordatorios.R;
import com.example.misrecordatorios.ViewModel.ViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class detallesFragment extends Fragment {

    private ViewModel miViewModel;
    private Recordatorio miRec;
    private TextView txt_fecha, txt_descripcion;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_detail, container, false);

        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);

        miRec = getArguments().getParcelable("recordatorio");

        txt_fecha = view.findViewById(R.id.txtFecha);
        txt_descripcion = view.findViewById(R.id.txtContenido);

        txt_fecha.setText(miRec.getFecha().toString());
        txt_descripcion.setText(miRec.getContenido().toString());


        return view;
    }

    public static detallesFragment newInstance(Recordatorio r) {
        detallesFragment myFragment = new detallesFragment();

        Bundle args = new Bundle();
        args.putParcelable("recordatorio", r);
        myFragment.setArguments(args);

        return myFragment;
    }
}
