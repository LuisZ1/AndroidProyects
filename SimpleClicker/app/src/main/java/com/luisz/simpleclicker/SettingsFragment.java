package com.luisz.simpleclicker;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.luisz.simpleclicker.R;
import com.luisz.simpleclicker.ViewModel.ViewModel;

public class SettingsFragment extends Fragment {

    Button btnReiniciar;
    ViewModel miViewModel;
    View view;
    private TextView toolbarTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);

//        toolbarTextView = view.findViewById(R.id.toolbarTextView);
//        toolbarTextView.setText(getActivity().getApplicationContext().getString(R.string.ajustes));
        getActivity().setTitle(getActivity().getApplicationContext().getString(R.string.ajustes));

        btnReiniciar = view.findViewById(R.id.btnReiniciarPartida);
        btnReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(miViewModel.reiniciarPartida()){
                    Toast.makeText(getActivity(), "Partida reiniciada con éxito", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "No hemos podido reiniciar la partida", Toast.LENGTH_SHORT).show();
                };
            }
        });

        return view;
    }


}
