package com.luisz.qrstore.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luisz.qrstore.Models.Objeto;
import com.luisz.qrstore.R;
import com.luisz.qrstore.Viewmodel.ViewModel;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

public class menuConsultarTodo extends Fragment {

    private View view;
    private ViewModel miViewModel;
    private FragmentManager fragmentManager = getFragmentManager();
    private Objeto objeto;
    private TextView txtNombreObjeto, txtIdObjeto;

    public menuConsultarTodo() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu_consultar_todo, container, false);
        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);

        objeto = miViewModel.getObjetoEscaneado();

//        txtNombreObjeto = view.findViewById(R.id.txtNombreObjetoEscaneado);
//        txtNombreObjeto.setText(objeto.getNombre());
//        txtIdObjeto = view.findViewById(R.id.txtIdObjetoEscaneado);
//        txtIdObjeto.setText(objeto.getidobjeto());

        return view;
    }
}
