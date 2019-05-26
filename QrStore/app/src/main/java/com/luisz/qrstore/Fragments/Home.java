package com.luisz.qrstore.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.luisz.qrstore.R;
import com.luisz.qrstore.activityCamara;

public class Home extends Fragment {

    View view;
    FragmentManager fragmentManager = getFragmentManager();

    ImageView imgScanCode, imgCreateEstanteria, imgCrearCaja, imgCrearObjeto;

    public Home() {}

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        imgScanCode = (ImageView) view.findViewById(R.id.imgScanCode);
        imgScanCode.setImageResource(R.drawable.codigo);
        imgScanCode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /*getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ScanCode()).addToBackStack(null).commit();*/
                Intent intent = new Intent(view.getContext(), activityCamara.class);
                startActivity(intent);
            }
        });

        imgCreateEstanteria = (ImageView) view.findViewById(R.id.imgvEstanteria);
        imgCreateEstanteria.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new crearCodigoEstanteria()).addToBackStack(null).commit();
            }
        });

        imgCrearCaja = (ImageView) view.findViewById(R.id.imgvCaja);
        imgCrearCaja.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new crearCodigoCaja()).addToBackStack(null).commit();
            }
        });

        imgCrearObjeto = (ImageView) view.findViewById(R.id.imgvObjeto);
        imgCrearObjeto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new crearCodigoObjeto()).addToBackStack(null).commit();
            }
        });

        return view;
    }
}
