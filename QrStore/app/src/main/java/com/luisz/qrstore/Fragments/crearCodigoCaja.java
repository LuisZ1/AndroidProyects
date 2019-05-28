package com.luisz.qrstore.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.luisz.qrstore.MainActivity;
import com.luisz.qrstore.Models.Estanteria;
import com.luisz.qrstore.R;
import com.luisz.qrstore.Util.Utilidades;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class crearCodigoCaja extends Fragment {

    View view;
    Button btnCrearCodigoCaja;
    ArrayList<Estanteria> listadoEstanterias;
    FirebaseFirestore db;
    Spinner spinnerEstanterias;
    EditText txtNombre, txtDescripcion;
    Boolean botonPulsado = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_crear_codigo_caja, container, false);
        db = FirebaseFirestore.getInstance();

        txtNombre = view.findViewById(R.id.txtCrearNombreCaja);
        txtDescripcion = view.findViewById(R.id.txtCrearDescripcionCaja);

        listadoEstanterias = new ArrayList<Estanteria>();
        spinnerEstanterias = view.findViewById(R.id.spinner_estanterias);

        consultarTodasEstanterias();

        btnCrearCodigoCaja = view.findViewById(R.id.btnCrearCaja);
        btnCrearCodigoCaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utilidades.checkPermisos((MainActivity) getActivity())) {
                    if (!botonPulsado) {
                        addCaja();
                    }else{
                        DynamicToast.makeError(view.getContext().getApplicationContext(), "Ya has guardado esta caja, modifica algún campo").show();
                    }
                } else {
                    DynamicToast.makeError(view.getContext().getApplicationContext(), "No tiene permisos para guardar el código").show();
                }
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

                            ArrayAdapter<Estanteria> adaptador = new ArrayAdapter<Estanteria>(view.getContext(), android.R.layout.simple_spinner_item, listadoEstanterias);
                            adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
/*
                            spinnerEstanterias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    DynamicToast.makeWarning(view.getContext().getApplicationContext(), "Elemento pulsado").show();
                                }
                            });
*/
                            spinnerEstanterias.setAdapter(adaptador);
                        } else {
                            DynamicToast.makeError(view.getContext().getApplicationContext(), "No hay estanterías disponibles").show();
                        }
                    }
                });
    }

    private void addCaja() {
        String nombreCaja = txtNombre.getText().toString();
        String descripcionCaja = txtDescripcion.getText().toString();

        Estanteria estanteriaSeleccionada = (Estanteria) spinnerEstanterias.getSelectedItem();
        String idEstanteriaSeleccionada = estanteriaSeleccionada.getIdestanteria();

        String uuidAutogen = UUID.randomUUID().toString();
        final String idCaja = "C" + uuidAutogen;

        Map<String, Object> caja = new HashMap<>();
        caja.put("nombre", nombreCaja);
        caja.put("idestanteria", idEstanteriaSeleccionada);
        caja.put("descripcion", descripcionCaja);

        // Add a new document with a generated ID
        db.collection("cajas")
                .document(idCaja)
                .set(caja)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Utilidades.crearCodigoQR(view, idCaja);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        DynamicToast.makeError(view.getContext().getApplicationContext(), "Error al guardar la Caja").show();
                        botonPulsado = false;
                    }
                });
    }

}
