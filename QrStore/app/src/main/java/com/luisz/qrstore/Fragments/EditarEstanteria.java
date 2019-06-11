package com.luisz.qrstore.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.luisz.qrstore.Adapter.CajaSpinnerAdapter;
import com.luisz.qrstore.Models.Caja;
import com.luisz.qrstore.Models.Estanteria;
import com.luisz.qrstore.Models.Objeto;
import com.luisz.qrstore.R;
import com.luisz.qrstore.Viewmodel.ViewModel;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.util.ArrayList;
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

//    private void consultarTodasCajas() {
//        listadoCajas.clear();
//        db.collection("cajas")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Caja caja = new Caja(document.getId(), document.get("idestanteria").toString(), document.get("nombre").toString(), document.get("descripcion").toString());
//                                listadoCajas.add(caja);
//                            }
//
//                            adaptador = new CajaSpinnerAdapter(view.getContext(), listadoCajas);
//                            spinnerCajas.setAdapter(adaptador);
//                        } else {
//                            DynamicToast.makeError(view.getContext().getApplicationContext(), "No hay estanterías disponibles").show();
//                        }
//                    }
//                });
//    }

    public void updateFields() {
        String NombreEstanteria = txtNombreEstanteria.getText().toString();
        String DescripcionEstanteria = txtDescripcionEstanteria.getText().toString();
        String LugarEstanteria = txtLugarEstanteria.getText().toString();

        DocumentReference docRef = db.collection("estanterias").document(estanteria.getIdestanteria());

        Map<String,Object> updates = new HashMap<>();
        updates.put("nombre", NombreEstanteria);
        updates.put("ubicacion", LugarEstanteria);
        updates.put("descripcion", DescripcionEstanteria);

        docRef.update(updates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                DynamicToast.makeSuccess(view.getContext().getApplicationContext(), "Estantería guardada").show();
            }
        });
    }
}