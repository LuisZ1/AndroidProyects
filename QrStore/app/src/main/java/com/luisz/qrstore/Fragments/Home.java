package com.luisz.qrstore.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.luisz.qrstore.R;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

public class Home extends Fragment {

    private View view;
    private FragmentManager fragmentManager = getFragmentManager();
    private LinearLayout btnConsultarTodo;
    private ImageView imgScanCode, imgCreateEstanteria, imgCrearCaja, imgCrearObjeto;

    public Home() {
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

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
                        new crearCodigoEstanteria()).addToBackStack(null).commit();
            }
        });

        imgCrearCaja = (ImageView) view.findViewById(R.id.imgvCaja);
        imgCrearCaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new crearCodigoCaja()).addToBackStack(null).commit();
            }
        });

        imgCrearObjeto = (ImageView) view.findViewById(R.id.imgvObjeto);
        imgCrearObjeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new crearCodigoObjeto()).addToBackStack(null).commit();
            }
        });

        btnConsultarTodo  = (LinearLayout) view.findViewById(R.id.btnConsultarTodo);
        btnConsultarTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DynamicToast.makeSuccess(view.getContext().getApplicationContext(), "Hola").show();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new menuConsultarTodo()).addToBackStack(null).commit();
            }
        });

        return view;
    }
}