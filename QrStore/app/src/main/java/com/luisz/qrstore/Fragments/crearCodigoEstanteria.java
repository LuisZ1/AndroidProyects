package com.luisz.qrstore.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.luisz.qrstore.MainActivity;
import com.luisz.qrstore.R;
import com.luisz.qrstore.Util.Utilidades;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class crearCodigoEstanteria extends Fragment {

    private View view;
    private Button btnCrearCodigoEstanteria;
    private TextView txtNombre, txtLugar, txtDescripcion;
    private DatabaseReference referenceDB;
    private FirebaseFirestore db ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_crear_codigo_estanteria, container, false);

        referenceDB = FirebaseDatabase.getInstance().getReference("estanterias");

        db = FirebaseFirestore.getInstance();

        txtLugar = view.findViewById(R.id.txtCrearUbicacionEstanteria);
        txtDescripcion = view.findViewById(R.id.txtCrearDescripcionEstanteria);
        txtNombre = view.findViewById(R.id.txtCrearNombreEstanteria);

        btnCrearCodigoEstanteria = view.findViewById(R.id.btnCrearEstanteria);
        btnCrearCodigoEstanteria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utilidades.checkPermisos((MainActivity) getActivity())) {
                    addEstanteria();
                } else {
                    DynamicToast.makeError(view.getContext().getApplicationContext(), "No tiene permisos para guardar el código").show();
                }
            }
        });

        return view;
    }

    private void addEstanteria(){

        String uuidAutogen = UUID.randomUUID().toString();
        final String idEstanteria = "E" + uuidAutogen;

        // Create a new user with a first and last name
        Map<String, Object> estanteria = new HashMap<>();
        estanteria.put("nombre", txtNombre.getText().toString());
        estanteria.put("ubicacion", txtLugar.getText().toString());
        estanteria.put("descripcion", txtDescripcion.getText().toString());

        // Add a new document with a generated ID
        db.collection("estanterias")
                .document(idEstanteria)
                .set(estanteria)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Utilidades.crearCodigoQR(view, idEstanteria);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        DynamicToast.makeError(view.getContext().getApplicationContext(), "Error al guardar la estantería").show();
                    }
                });
    }
}
