package com.luisz.qrstore.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.luisz.qrstore.Models.Estanteria;
import com.luisz.qrstore.R;
import com.luisz.qrstore.Viewmodel.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class mostrarEstanteria extends Fragment {

    private View view;
    private ViewModel miViewModel;
    private FragmentManager fragmentManager = getFragmentManager();
    private Estanteria estanteria;
    private TextView txtNombreEstanteria, txtCajasEstanteria;

    public mostrarEstanteria() {
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mostrar_estanteria, container, false);
        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);

        estanteria = miViewModel.getEstanteriaEscaneada();

        txtNombreEstanteria = view.findViewById(R.id.txtNombreEstanteriaEscaneada);
        txtNombreEstanteria.setText(estanteria.getNombre());
        txtCajasEstanteria = view.findViewById(R.id.txtIdEstanteriaEscaneada);
        txtCajasEstanteria.setText(estanteria.getIdestanteria());

        return view;
    }
}
