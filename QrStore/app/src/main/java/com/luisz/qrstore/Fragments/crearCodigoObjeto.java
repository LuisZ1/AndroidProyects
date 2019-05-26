package com.luisz.qrstore.Fragments;

import android.content.Context;
import android.net.Uri;
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


public class crearCodigoObjeto extends Fragment {

    View view;
    Button btnCrearCodigoObjeto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_crear_codigo_objeto, container, false);

        btnCrearCodigoObjeto = view.findViewById(R.id.btnCrearObjeto);
        btnCrearCodigoObjeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                //new ScanCode()).addToBackStack(null).commit();

                if (Utilidades.checkPermisos((MainActivity) getActivity())) {
                    //guardar en la base de datos y recuperar la ID
                    Utilidades.crearCodigoQR(view, "T9843");
                } else {
                    DynamicToast.makeError(view.getContext().getApplicationContext(), "No tiene permisos para guardar el código").show();
                }
            }
        });

        return view;
    }
}
