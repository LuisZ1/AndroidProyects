package com.luisz.qrstore.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.luisz.qrstore.Adapter.CajaSpinnerAdapter;
import com.luisz.qrstore.MainActivity;
import com.luisz.qrstore.Models.Caja;
import com.luisz.qrstore.R;
import com.luisz.qrstore.Util.Utilidades;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class CrearCodigoObjeto extends Fragment {

    private View view;
    private Button btnCrearCodigoObjeto;
    private ArrayList<Caja> listadoCajas = new ArrayList<>();
    private FirebaseFirestore db;
    private Spinner spinnerCajas;
    private TextView txtNombre, txtDescripcion;
    private CajaSpinnerAdapter adaptador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_crear_codigo_objeto, container, false);
        db = FirebaseFirestore.getInstance();

        spinnerCajas = view.findViewById(R.id.spinner_cajas);
        consultarTodasCajas();

        txtNombre = view.findViewById(R.id.txtCrearNombreObjeto);
        txtDescripcion = view.findViewById(R.id.txtCrearDescripcionObjeto);

        btnCrearCodigoObjeto = view.findViewById(R.id.btnEditarObjeto);
        btnCrearCodigoObjeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Utilidades.checkPermisos((MainActivity) getActivity())) {
                    addObjeto();
                } else {
                    DynamicToast.makeError(view.getContext().getApplicationContext(), "No tiene permisos para guardar el código").show();
                }
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

    private void addObjeto() {
        String nombreObjeto = txtNombre.getText().toString();
        String descripcionObjeto = txtDescripcion.getText().toString();

        if (nombreObjeto.matches("") || descripcionObjeto.matches("")) {
            DynamicToast.makeWarning(view.getContext().getApplicationContext(), "Rellena todos los campos").show();
        } else {

            Caja cajaSeleccionada = (Caja) spinnerCajas.getSelectedItem();
            String idCajaSeleccionada = cajaSeleccionada.getidcaja();

            String uuidAutogen = UUID.randomUUID().toString();
            final String idObjeto = "T" + uuidAutogen;

            Map<String, Object> objeto = new HashMap<>();
            objeto.put("idobjeto", idObjeto);
            objeto.put("nombre", nombreObjeto);
            objeto.put("idcaja", idCajaSeleccionada);
            objeto.put("descripcion", descripcionObjeto);

            // Add a new document with a generated ID
            db.collection("objetos")
                    .document(idObjeto)
                    .set(objeto)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Utilidades.crearCodigoQR(view, idObjeto);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            DynamicToast.makeError(view.getContext().getApplicationContext(), "Error al guardar el objeto").show();
                        }
                    });
        }
    }

}
