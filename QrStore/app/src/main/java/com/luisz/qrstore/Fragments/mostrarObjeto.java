package com.luisz.qrstore.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.luisz.qrstore.Models.Caja;
import com.luisz.qrstore.Models.Objeto;
import com.luisz.qrstore.R;
import com.luisz.qrstore.Viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class MostrarObjeto extends Fragment {

    private View view;
    private ViewModel miViewModel;
    private Objeto objeto;
    private TextView txtNombreObjeto, txtIdObjeto, txtDescripcionObjeto, txtIdCajaObjeto;
    private ConstraintLayout tarjetaPrincipal;
    private ImageView btnConsultarCaja;
    private FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mostrar_objeto, container, false);
        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);
        db = FirebaseFirestore.getInstance();

        objeto = miViewModel.getObjetoEscaneado();

        txtNombreObjeto = view.findViewById(R.id.txtNombreObjetoEscaneado);
        txtNombreObjeto.setText(objeto.getNombre());
        txtIdObjeto = view.findViewById(R.id.txtIdObjetoEscaneado);
        txtIdObjeto.setText(objeto.getidobjeto());
        txtDescripcionObjeto = view.findViewById(R.id.txtDescripcionObjeto);
        txtDescripcionObjeto.setText(objeto.getDescripcion());
        txtIdCajaObjeto = view.findViewById(R.id.txtIdCajaObjeto);
        txtIdCajaObjeto.setText(objeto.getidcaja());

        btnConsultarCaja = view.findViewById(R.id.imgConsultarCajaObjeto);
        btnConsultarCaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultarCaja();
            }
        });

        tarjetaPrincipal = view.findViewById(R.id.tarjetaMostrarObjeto);
        tarjetaPrincipal.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(getFragmentManager() != null) {
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new EditarObjeto()).addToBackStack(null).commit();
                }
                return true;
            }
        });

        return view;
    }

    private void consultarCaja(){
        if (db == null) {
            db = FirebaseFirestore.getInstance();
        }
        if (miViewModel == null) {
            miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);
        }

        final String codigo = objeto.getidcaja();

        DocumentReference docRefCaja = db.collection("cajas").document(codigo);
        docRefCaja.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Caja caja = documentSnapshot.toObject(Caja.class);
                caja.setidcaja(documentSnapshot.getId());

                miViewModel.setCajaEscaneada(caja);

                //consultar Cajas de la estantería -----------------------------

                Query queryCajas = db.collection("objetos").whereEqualTo("idcaja", codigo);

                queryCajas.addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot snapshot, @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            return;
                        }

                        List<Objeto> objetos = snapshot.toObjects(Objeto.class);

                        miViewModel.setListadoObjetos((ArrayList<Objeto>) objetos);

                        if(getFragmentManager() != null) {
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container, new MostrarCaja()).addToBackStack(null).commit();
                        }
                    }
                });
            }
        });
    }

}
