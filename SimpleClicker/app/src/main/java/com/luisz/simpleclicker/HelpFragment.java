package com.luisz.simpleclicker;

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

public class HelpFragment extends Fragment {

    private Typeface font;
    TextView txtComoJugar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_help, container, false);

        font = Typeface.createFromAsset(getActivity().getAssets(), "awesome.ttf");
        txtComoJugar = view.findViewById(R.id.txtcomoJugar);
        txtComoJugar.setTypeface(font);

        return view;
    }
}
