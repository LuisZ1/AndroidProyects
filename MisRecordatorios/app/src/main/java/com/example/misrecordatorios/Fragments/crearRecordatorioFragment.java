package com.example.misrecordatorios.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.misrecordatorios.R;
import com.example.misrecordatorios.ViewModel.ViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class crearRecordatorioFragment extends Fragment {

    private ViewModel miViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_crear, container, false);

        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);

        return view;
    }

}
