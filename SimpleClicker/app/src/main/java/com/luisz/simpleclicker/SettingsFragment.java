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

import com.luisz.simpleclicker.R;
import com.luisz.simpleclicker.ViewModel.ViewModel;

public class SettingsFragment extends Fragment {

    Button btnReiniciar;
    ViewModel miViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);

        btnReiniciar = view.findViewById(R.id.btnReiniciarPartida);
        btnReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miViewModel = new ViewModel(getActivity().getApplication());
            }
        });

        return view;
    }
}
