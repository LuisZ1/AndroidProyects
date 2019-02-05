package com.luisz.simpleclicker.Fragments;

import androidx.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.luisz.simpleclicker.R;
import com.luisz.simpleclicker.ViewModel.ViewModel;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

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
                        DynamicToast.makeSuccess(getActivity().getApplicationContext(), getString(R.string.partida_reiniciada_OK)).show();
                    }else{
                        DynamicToast.makeError(getActivity().getApplicationContext(), getString(R.string.partida_reiniciada_ERROR)).show();
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
                //Toast.makeText(getActivity(), "Próximamente", Toast.LENGTH_SHORT).show();
                DynamicToast.makeWarning(getActivity().getApplicationContext(), getString(R.string.proximamente)).show();
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
                        DynamicToast.makeSuccess(getActivity().getApplicationContext(), getString(R.string.estadisticas_reiniciadas_OK)).show();
                    }else{
                        DynamicToast.makeError(getActivity().getApplicationContext(), getString(R.string.estadisticas_reiniciadas_ERROR)).show();
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
