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
    TextView txtStats_puntuacionMaximaTotal, txtStats_puntuacionMaximaPartida;
    TextView txtStats_mejorasAluminioTotal, txtStats_mejorasZincTotal, txtStats_mejorasCobreTotal, txtStats_mejorasNiquelTotal, txtStats_mejorasBronceTotal,
            txtStats_mejorasPlataTotal, txtStats_mejorasIridioTotal, txtStats_mejorasOroTotal, txtStats_mejorasPlatinoTotal, txtStats_mejorasUranioTotal;


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

        txtStats_puntuacionMaximaTotal = view.findViewById(R.id.txtStats_puntuacionMaximaTotal);
        txtStats_puntuacionMaximaTotal.setText(formatter.format(miViewModel.getPuntuacionMaximaTotal()));

        txtStats_puntuacionMaximaPartida = view.findViewById(R.id.txtStats_puntuacionMaximaPartida);
        txtStats_puntuacionMaximaPartida.setText(formatter.format(miViewModel.getPuntuacionMaximaPartida()));


        //mejoras compradas

        txtStats_mejorasAluminioTotal = view.findViewById(R.id.txtStats_mejorasAluminioTotal);
        txtStats_mejorasAluminioTotal.setText(formatter.format(miViewModel.getContadorAluminioTotal()));

        txtStats_mejorasZincTotal = view.findViewById(R.id.txtStats_mejorasZincTotal);
        txtStats_mejorasZincTotal.setText(formatter.format(miViewModel.getContadorZincTotal()));

        txtStats_mejorasCobreTotal = view.findViewById(R.id.txtStats_mejorasCobreTotal);
        txtStats_mejorasCobreTotal.setText(formatter.format(miViewModel.getContadorCobreTotal()));

        txtStats_mejorasNiquelTotal = view.findViewById(R.id.txtStats_mejorasNiquelTotal);
        txtStats_mejorasNiquelTotal.setText(formatter.format(miViewModel.getContadorNiquelTotal()));

        txtStats_mejorasBronceTotal = view.findViewById(R.id.txtStats_mejorasBronceTotal);
        txtStats_mejorasBronceTotal.setText(formatter.format(miViewModel.getContadorBronceTotal()));

        txtStats_mejorasPlataTotal = view.findViewById(R.id.txtStats_MejorasPlataTotal);
        txtStats_mejorasPlataTotal.setText(formatter.format(miViewModel.getContadorPlataTotal()));

        txtStats_mejorasIridioTotal = view.findViewById(R.id.txtStats_mejorasIridioTotal);
        txtStats_mejorasIridioTotal.setText(formatter.format(miViewModel.getContadorIridioTotal()));

        txtStats_mejorasOroTotal = view.findViewById(R.id.txtStats_mejorasOroTotal);
        txtStats_mejorasOroTotal.setText(formatter.format(miViewModel.getContadorOroTotal()));

        txtStats_mejorasPlatinoTotal = view.findViewById(R.id.txtStats_mejorasPlatinoTotal);
        txtStats_mejorasPlatinoTotal.setText(formatter.format(miViewModel.getContadorPlatinoTotal()));

        txtStats_mejorasUranioTotal = view.findViewById(R.id.txtStats_mejorasUranioTotal);
        txtStats_mejorasUranioTotal.setText(formatter.format(miViewModel.getContadorUranioTotal()));


        return view;
    }

}
