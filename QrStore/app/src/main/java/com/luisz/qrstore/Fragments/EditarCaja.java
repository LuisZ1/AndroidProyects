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
import com.luisz.qrstore.Adapter.EstanteriaSpinnerAdapter;
import com.luisz.qrstore.Models.Caja;
import com.luisz.qrstore.Models.Estanteria;
import com.luisz.qrstore.R;
import com.luisz.qrstore.Viewmodel.ViewModel;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class EditarCaja extends Fragment {

    private View view;
    private ViewModel miViewModel;
    private Caja caja;
    private TextView txtNombreCaja, txtDescripcionCaja;
    private FirebaseFirestore db;
    private ArrayList<Estanteria> listadoEstanterias;
    private EstanteriaSpinnerAdapter adaptador;
    private Spinner spinnerEstanterias;
    private Button btnGuardar;

    public EditarCaja() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_editar_caja, container, false);
        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);
        db = FirebaseFirestore.getInstance();
        listadoEstanterias = new ArrayList<Estanteria>();

        caja = miViewModel.getCajaEscaneada();
        spinnerEstanterias = view.findViewById(R.id.spinner_estanterias_editar);
        consultarTodasEstanterias();

        txtNombreCaja = view.findViewById(R.id.editTextNombreCaja);
        txtNombreCaja.setText(caja.getNombre());
        txtDescripcionCaja = view.findViewById(R.id.editTextDescripcionCaja);
        txtDescripcionCaja.setText(caja.getDescripcion());

        btnGuardar = view.findViewById(R.id.btnEditarCaja);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFields();
            }
        });
        return view;
    }

    private void consultarTodasEstanterias() {
        listadoEstanterias.clear();
        db.collection("estanterias")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Estanteria est = new Estanteria(document.getId(), document.get("nombre").toString(), document.get("ubicacion").toString(), document.get("descripcion").toString());
                                listadoEstanterias.add(est);
                            }

                            adaptador = new EstanteriaSpinnerAdapter(view.getContext(), listadoEstanterias);
                            spinnerEstanterias.setAdapter(adaptador);
                        } else {
                            DynamicToast.makeError(view.getContext().getApplicationContext(), "No hay estanterías disponibles").show();
                        }
                    }
                });
    }

    public void updateFields() {
        String nombreCaja = txtNombreCaja.getText().toString();
        String descripcionCaja = txtDescripcionCaja.getText().toString();

        Estanteria estanteriaSeleccionada = (Estanteria) spinnerEstanterias.getSelectedItem();
        String idEstanteriaSeleccionada = estanteriaSeleccionada.getIdestanteria();

        DocumentReference docRef = db.collection("cajas").document(caja.getidcaja());

        Map<String,Object> updates = new HashMap<>();
        updates.put("nombre", nombreCaja);
        updates.put("idestanteria", idEstanteriaSeleccionada);
        updates.put("descripcion", descripcionCaja);

        docRef.update(updates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                DynamicToast.makeSuccess(view.getContext().getApplicationContext(), "Caja guardada").show();
            }
        });
    }
}