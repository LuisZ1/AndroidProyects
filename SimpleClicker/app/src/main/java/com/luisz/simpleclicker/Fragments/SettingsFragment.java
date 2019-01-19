package com.luisz.simpleclicker.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.luisz.simpleclicker.MainActivity;
import com.luisz.simpleclicker.R;
import com.luisz.simpleclicker.ViewModel.ViewModel;

import static android.content.Context.MODE_PRIVATE;

public class SettingsFragment extends Fragment {

    private Button btnReiniciar, btnTema, btnReiniciarEstadisticas;
    private ViewModel miViewModel;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);
        getActivity().setTitle(getActivity().getApplicationContext().getString(R.string.ajustes));

        btnReiniciar = view.findViewById(R.id.btnReiniciarPartida);
        btnReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.myDialog));

            builder.setCancelable(true);
            builder.setTitle("Reiniciar partida");
            builder.setMessage("¿Estás seguro de que quieres reiniciar la partida?");

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if(miViewModel.reiniciarPartida()){
                        Toast.makeText(getActivity(), "OK", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
                    };
                }
            });
            builder.show();
            }
        });

        btnTema = view.findViewById(R.id.btnCambiarTema);
        btnTema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Próximamente", Toast.LENGTH_SHORT).show();
//                saveFlag(!getFlag());
//
//                Intent intent = new Intent(getActivity(), MainActivity.class);
//                startActivity(intent);
//                getActivity().finish();
            }
        });

        btnReiniciarEstadisticas = view.findViewById(R.id.btnReiniciarEstadisticas);
        btnReiniciarEstadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.myDialog));

            builder.setCancelable(true);
            builder.setTitle("Reiniciar estadísticas");
            builder.setMessage("¿Estás seguro de que quieres reiniciar las estadísticas globales?");

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if(miViewModel.reiniciarEstadisticasTotales()){
                        Toast.makeText(getActivity(), "OK", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
                    };
                }
            });
            builder.show();


            }
        });

        return view;
    }

    public void saveFlag(boolean flag){
        SharedPreferences preferences = getActivity().getSharedPreferences("partida", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("dark", flag);
        editor.commit();
    }

    public boolean getFlag(){
        SharedPreferences preferences = getActivity().getSharedPreferences("partida", MODE_PRIVATE);
        return preferences.getBoolean("dark",false);
    }


}
