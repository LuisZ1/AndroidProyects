package com.luisz.qrstore.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.luisz.qrstore.R;

import androidx.fragment.app.Fragment;

public class MenuConsultarTodo extends Fragment {

    private View view;
    private ImageView btnObjetos, btnCajas, btnEstanterias;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu_consultar_todo, container, false);

        btnObjetos = view.findViewById(R.id.imgConsultarTodosObjetos);
        btnCajas = view.findViewById(R.id.imgConsultarTodasCajas);
        btnEstanterias = view.findViewById(R.id.imgConsultarTodasEstanterias);

        btnObjetos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ConsultarTodosObjetos()).addToBackStack(null).commit();
            }
        });

        btnCajas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ConsultarTodasCajas()).addToBackStack(null).commit();
            }
        });

        btnEstanterias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ConsultarTodasEstanterias()).addToBackStack(null).commit();
            }
        });

        return view;
    }
}
