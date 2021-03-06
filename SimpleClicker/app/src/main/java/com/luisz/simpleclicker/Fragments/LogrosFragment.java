package com.luisz.simpleclicker.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luisz.simpleclicker.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LogrosFragment extends Fragment {

    private Typeface font;
    TextView txtComoJugar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help, container, false);

        getActivity().setTitle(getActivity().getApplicationContext().getString(R.string.logros));

        font = Typeface.createFromAsset(getActivity().getAssets(), "awesome.ttf");
        txtComoJugar = view.findViewById(R.id.txtcomoJugar);
        txtComoJugar.setTypeface(font);

        return view;
    }
}
