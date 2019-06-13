package com.luisz.qrstore.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.luisz.qrstore.Models.Estanteria;
import com.luisz.qrstore.R;
import com.luisz.qrstore.Viewmodel.ViewModel;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class EditarEstanteria extends Fragment {

    private View view;
    private ViewModel miViewModel;
    private Estanteria estanteria;
    private TextView txtNombreEstanteria, txtDescripcionEstanteria, txtLugarEstanteria;
    private FirebaseFirestore db;
    private Button btnGuardar;

    public EditarEstanteria() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_editar_estateria, container, false);
        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);
        db = FirebaseFirestore.getInstance();

        estanteria = miViewModel.getEstanteriaEscaneada();

        txtNombreEstanteria = view.findViewById(R.id.editTextNombreEstanteria);
        txtNombreEstanteria.setText(estanteria.getNombre());
        txtDescripcionEstanteria = view.findViewById(R.id.editTextDescripcionEstanteria);
        txtDescripcionEstanteria.setText(estanteria.getDescripcion());
        txtLugarEstanteria = view.findViewById(R.id.editTextLugarEstanteria);
        txtLugarEstanteria.setText(estanteria.getUbicacion());

        btnGuardar = view.findViewById(R.id.btnEditarEstanteria);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFields();
            }
        });
        return view;
    }

    public void updateFields() {
        String nombreEstanteria = txtNombreEstanteria.getText().toString();
        String descripcionEstanteria = txtDescripcionEstanteria.getText().toString();
        String lugarEstanteria = txtLugarEstanteria.getText().toString();

        DocumentReference docRef = db.collection("estanterias").document(estanteria.getIdestanteria());

        Map<String,Object> updates = new HashMap<>();
        updates.put("nombre", nombreEstanteria);
        updates.put("ubicacion", descripcionEstanteria);
        updates.put("descripcion", lugarEstanteria);

        docRef.update(updates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                DynamicToast.makeSuccess(view.getContext().getApplicationContext(), "Estantería guardada").show();
            }
        });
    }
}