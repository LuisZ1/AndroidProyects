package com.luisz.simpleclicker.Fragments;

import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.content.Intent;
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
import com.luisz.simpleclicker.MainActivity_BottomMenu;
import com.luisz.simpleclicker.R;
import com.luisz.simpleclicker.ViewModel.ViewModel;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

public class SettingsFragment extends Fragment {

    private Button btnReiniciar, btnTutorial, btnReiniciarEstadisticas;
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
                builder.setTitle(R.string.dialog_reiniciar_partida_titulo);
                builder.setMessage(R.string.dialog_reiniciar_partida_mensaje);

                builder.setNegativeButton(R.string.dialog_reiniciar_partida_opt_negativo, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.setPositiveButton(R.string.dialog_reiniciar_partida_opt_afirmativa, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (miViewModel.reiniciarPartida()) {
                            DynamicToast.makeSuccess(getActivity().getApplicationContext(), getString(R.string.partida_reiniciada_OK)).show();
                        } else {
                            DynamicToast.makeError(getActivity().getApplicationContext(), getString(R.string.partida_reiniciada_ERROR)).show();
                        }
                    }
                });
                builder.show();
            }
        });

        btnTutorial = view.findViewById(R.id.btnTutorial);
        btnTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DynamicToast.makeWarning(getActivity().getApplicationContext(), getString(R.string.toast_tutorial)).show();
                miViewModel.setFirstLauch(true);
                Intent intent = new Intent(getActivity(), MainActivity_BottomMenu.class);
                startActivity(intent);
            }
        });

        btnReiniciarEstadisticas = view.findViewById(R.id.btnReiniciarEstadisticas);
        btnReiniciarEstadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.myDialog));

                builder.setCancelable(true);
                builder.setTitle(R.string.dialog_reiniciar_estadisticas_titulo);
                builder.setMessage(R.string.dialog_reiniciar_estadisticas_mensaje);

                builder.setNegativeButton(R.string.dialog_reiniciar_estadisticas_opt_negativo, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.setPositiveButton(R.string.dialog_reiniciar_estadisticas_opt_afirmativa, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (miViewModel.reiniciarEstadisticasTotales()) {
                            DynamicToast.makeSuccess(getActivity().getApplicationContext(), getString(R.string.estadisticas_reiniciadas_OK)).show();
                        } else {
                            DynamicToast.makeError(getActivity().getApplicationContext(), getString(R.string.estadisticas_reiniciadas_ERROR)).show();
                        }
                    }
                });
                builder.show();


            }
        });

        return view;
    }
}
