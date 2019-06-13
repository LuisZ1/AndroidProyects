package com.luisz.qrstore.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.luisz.qrstore.R;
import com.luisz.qrstore.Viewmodel.ViewModel;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class Home extends Fragment {

    private View view;
    private ViewModel miViewModel;
    private LinearLayout btnConsultarTodo;
    private ImageView imgScanCode;
    private ImageView imgCreateEstanteria;
    private ImageView imgCrearCaja;
    private ImageView imgCrearObjeto;
    private ImageView imgCerrarSesion;
    private TextView nombreUsuario;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);

        nombreUsuario = view.findViewById(R.id.txtNombreUsuario);
        nombreUsuario.setText(miViewModel.getNombreUsuario());

        imgCerrarSesion = (ImageView) view.findViewById(R.id.imgCerrarSesion);
        imgCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PantallaPrincipal()).commit();
            }
        });

        imgScanCode = (ImageView) view.findViewById(R.id.imgScanCode);
        imgScanCode.setImageResource(R.drawable.codigo);
        imgScanCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ScanCode()).addToBackStack(null).commit();
            }
        });

        imgCreateEstanteria = (ImageView) view.findViewById(R.id.imgvEstanteria);
        imgCreateEstanteria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CrearCodigoEstanteria()).addToBackStack(null).commit();
            }
        });

        imgCrearCaja = (ImageView) view.findViewById(R.id.imgvCaja);
        imgCrearCaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CrearCodigoCaja()).addToBackStack(null).commit();
            }
        });

        imgCrearObjeto = (ImageView) view.findViewById(R.id.imgvObjeto);
        imgCrearObjeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CrearCodigoObjeto()).addToBackStack(null).commit();
            }
        });

        btnConsultarTodo = (LinearLayout) view.findViewById(R.id.btnConsultarTodo);
        btnConsultarTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MenuConsultarTodo()).addToBackStack(null).commit();
            }
        });

        return view;
    }
}