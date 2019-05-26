package com.luisz.qrstore.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.luisz.qrstore.MainActivity;
import com.luisz.qrstore.R;
import com.luisz.qrstore.Util.Utilidades;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

public class crearCodigoEstanteria extends Fragment {

    View view;
    Button btnCrearCodigoEstanteria;

    public crearCodigoEstanteria() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_crear_codigo_estanteria, container, false);

        btnCrearCodigoEstanteria = view.findViewById(R.id.btnCrearCaja);
        btnCrearCodigoEstanteria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                //new ScanCode()).addToBackStack(null).commit();

                if (Utilidades.checkPermisos((MainActivity) getActivity())) {
                    //guardar en la base de datos y recuperar la ID
                    Utilidades.crearCodigoQR(view, "E12345");
                } else {
                    DynamicToast.makeError(view.getContext().getApplicationContext(), "No tiene permisos para guardar el código").show();
                }
            }
        });

        return view;
    }
}
