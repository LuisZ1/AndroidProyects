package com.luisz.simpleclicker.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luisz.simpleclicker.R;
import com.luisz.simpleclicker.ViewModel.ViewModel;

import java.text.DecimalFormat;

public class StatsFragment extends Fragment {

    ViewModel miViewModel;
    Typeface font;
    DecimalFormat formatter = new DecimalFormat("###,###,###,###,###,###,###,###,###");

    TextView txtStats_PulsacionesTotales, txtStats_PulsacionesPartida;
    TextView txtStats_MejorasCompradas, txtStats_MejorasPartida;
    TextView txtStats_puntosGastadosTotal, txtStats_puntosGastadosPartida;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        getActivity().setTitle(getActivity().getApplicationContext().getString(R.string.stats));
        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);
        font = Typeface.createFromAsset(getActivity().getAssets(), "awesome.ttf");

        txtStats_PulsacionesTotales = view.findViewById(R.id.txtStats_PulsacionesTotales);
        txtStats_PulsacionesTotales.setText(formatter.format(miViewModel.getContadorPulsacionesTotal()));

        txtStats_PulsacionesPartida = view.findViewById(R.id.txtStats_PulsacionesPartida);
        txtStats_PulsacionesPartida.setText(formatter.format(miViewModel.getContadorPulsacionesPartida()));

        txtStats_MejorasCompradas = view.findViewById(R.id.txtStats_MejorasCompradas);
        txtStats_MejorasCompradas.setText(formatter.format(miViewModel.getContadorMejorasTotal()));

        txtStats_MejorasPartida = view.findViewById(R.id.txtStats_MejorasPartida);
        txtStats_MejorasPartida.setText(formatter.format(miViewModel.getContadorMejorasPartida()));

        txtStats_puntosGastadosTotal = view.findViewById(R.id.txtStats_puntosGastadosTotal);
        txtStats_puntosGastadosTotal.setText(formatter.format(miViewModel.getContadorPuntosGastadosTotal()));

        txtStats_puntosGastadosPartida = view.findViewById(R.id.txtStats_puntosGastadosPartida);
        txtStats_puntosGastadosPartida.setText(formatter.format(miViewModel.getContadorPuntosGastadosPartida()));

        return view;
    }

}
