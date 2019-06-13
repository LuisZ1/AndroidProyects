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

public class EditarObjeto extends Fragment {

    private View view;
    private ViewModel miViewModel;
    private Objeto objeto;
    private TextView txtNombreObjeto, txtDescripcionObjeto;
    private FirebaseFirestore db;
    private ArrayList<Caja> listadoCajas;
    private CajaSpinnerAdapter adaptador;
    private Spinner spinnerCajas;
    private Button btnGuardar;

    public EditarObjeto() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_editar_objeto, container, false);
        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);
        db = FirebaseFirestore.getInstance();
        listadoCajas = new ArrayList<>();

        objeto = miViewModel.getObjetoEscaneado();
        spinnerCajas = view.findViewById(R.id.spinner_cajas_editar);
        consultarTodasCajas();

        txtNombreObjeto = view.findViewById(R.id.editTextNombreObjeto);
        txtNombreObjeto.setText(objeto.getNombre());
        txtDescripcionObjeto = view.findViewById(R.id.editTextDescripcionObjeto);
        txtDescripcionObjeto.setText(objeto.getDescripcion());

        btnGuardar = view.findViewById(R.id.btnEditarObjeto);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFields();
            }
        });
        return view;
    }

    private void consultarTodasCajas() {
        listadoCajas.clear();
        db.collection("cajas")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Caja caja = new Caja(document.getId(), document.get("idestanteria").toString(), document.get("nombre").toString(), document.get("descripcion").toString());
                                listadoCajas.add(caja);
                            }

                            adaptador = new CajaSpinnerAdapter(view.getContext(), listadoCajas);
                            spinnerCajas.setAdapter(adaptador);
                        } else {
                            DynamicToast.makeError(view.getContext().getApplicationContext(), "No hay estanterías disponibles").show();
                        }
                    }
                });
    }

    public void updateFields() {
        String nombreObjeto = txtNombreObjeto.getText().toString();
        String descripcionObjeto = txtDescripcionObjeto.getText().toString();

        Caja cajaSeleccionada = (Caja) spinnerCajas.getSelectedItem();
        String idCajaSeleccionada = cajaSeleccionada.getidcaja();

        DocumentReference docRef = db.collection("objetos").document(objeto.getidobjeto());

        Map<String,Object> updates = new HashMap<>();
        updates.put("nombre", nombreObjeto);
        updates.put("idcaja", idCajaSeleccionada);
        updates.put("descripcion", descripcionObjeto);

        docRef.update(updates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                DynamicToast.makeSuccess(view.getContext().getApplicationContext(), "Objeto guardado").show();
            }
        });
    }
}