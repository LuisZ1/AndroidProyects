package com.luisz.qrstore.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.luisz.qrstore.Models.Objeto;
import com.luisz.qrstore.R;
import com.luisz.qrstore.Viewmodel.ViewModel;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

public class menuConsultarTodo extends Fragment {

    private View view;
    private ViewModel miViewModel;
    private FragmentManager fragmentManager = getFragmentManager();
    private Objeto objeto;
    private ImageView btnObjetos, btnCajas, btnEstanterias;

    public menuConsultarTodo() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu_consultar_todo, container, false);
        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);

        objeto = miViewModel.getObjetoEscaneado();

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
                DynamicToast.makeWarning(view.getContext().getApplicationContext(), "En construcción").show();
//                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new ConsultarTodosObjetos()).addToBackStack(null).commit();
            }
        });

        btnEstanterias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DynamicToast.makeWarning(view.getContext().getApplicationContext(), "En construcción").show();
//                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new ConsultarTodosObjetos()).addToBackStack(null).commit();
            }
        });

        return view;
    }
}
