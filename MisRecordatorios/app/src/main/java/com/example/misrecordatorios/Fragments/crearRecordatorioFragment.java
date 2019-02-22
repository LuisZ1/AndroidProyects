package com.example.misrecordatorios.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.misrecordatorios.MainActivity;
import com.example.misrecordatorios.Models.Recordatorio;
import com.example.misrecordatorios.R;
import com.example.misrecordatorios.ViewModel.ViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class crearRecordatorioFragment extends Fragment {

    private static ViewModel miViewModel;
    private static View view = null;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_crear, container, false);

        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);

//        FloatingActionButton fab = view.findViewById(R.id.fab);
//        fab.setImageResource(R.drawable.ic_check_black_24dp);
//            fab.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    EditText miEdit = view.findViewById(R.id.EditorContenido);
//                    Recordatorio rec = new Recordatorio(new Date(), miEdit.getText().toString(), "#2196F3" );
//
//                    miViewModel.listadoRecordatorios.add(rec);
//
//                    Snackbar.make(view, "Cambios guardados", Snackbar.LENGTH_SHORT)
//                            .setAction("Action", null).show();
//                }
//            });

        return view;
    }

    public static boolean guardarRecordatorio(){
        boolean veredicto = false;

        EditText miEdit = view.findViewById(R.id.EditorContenido);
        if( !miEdit.getText().toString().equals("")) {
            Recordatorio rec = new Recordatorio(new Date().toString(), miEdit.getText().toString(), "#2196F3");

            //miViewModel.listadoRecordatorios.add(rec);

            Snackbar.make(view, "Cambios guardados", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();

            veredicto = true;
        }
        return veredicto;
    }

}
