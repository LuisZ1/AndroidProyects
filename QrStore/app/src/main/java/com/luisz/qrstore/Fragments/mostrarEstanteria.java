package com.luisz.qrstore.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.luisz.qrstore.Adapter.ListadoCajasAdapter;
import com.luisz.qrstore.Models.Caja;
import com.luisz.qrstore.Models.Estanteria;
import com.luisz.qrstore.Models.Objeto;
import com.luisz.qrstore.R;
import com.luisz.qrstore.Viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class mostrarEstanteria extends Fragment {

    private View view;
    private ViewModel miViewModel;
    private FragmentManager fragmentManager = getFragmentManager();
    private Estanteria estanteria;
    private TextView txtNombreEstanteria, txtIdEstanteria, txtnumerocajas, txtLugarEstanteria, txtDescripcionEstanteria;
    private RecyclerView miRecyclerView;
    private ListadoCajasAdapter adaptador;
    private ScanCode escaner;
    private FirebaseFirestore db;
    private ConstraintLayout tarjetaPrincipal;

    public mostrarEstanteria() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mostrar_estanteria, container, false);
        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);
        escaner = new ScanCode();

        tarjetaPrincipal = view.findViewById(R.id.constraintLayoutMostrarEstanteria);
        tarjetaPrincipal.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new EditarEstanteria()).addToBackStack(null).commit();
                return true;
            }
        });

        estanteria = miViewModel.getEstanteriaEscaneada();

        txtNombreEstanteria = view.findViewById(R.id.txtNombreEstanteriaEscaneada);
        txtNombreEstanteria.setText(estanteria.getNombre());
        txtIdEstanteria = view.findViewById(R.id.txtIdEstanteriaEscaneada);
        txtIdEstanteria.setText(estanteria.getIdestanteria());
        txtnumerocajas = view.findViewById(R.id.txtCajasEstanteriaEscaneada);
        txtnumerocajas.setText(String.valueOf(miViewModel.getListadoCajas().size()));

        txtDescripcionEstanteria = view.findViewById(R.id.txtDescripcionEstanteria);
        txtDescripcionEstanteria.setText(estanteria.getDescripcion());
        txtLugarEstanteria = view.findViewById(R.id.txtLugasEstaneria);
        txtLugarEstanteria.setText(estanteria.getUbicacion());

        miRecyclerView = view.findViewById(R.id.recyclerCajas);
        miRecyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 1, RecyclerView.VERTICAL, false));
        adaptador = new ListadoCajasAdapter(miViewModel.getListadoCajas());
        miRecyclerView.setAdapter(adaptador);

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cajaSeleccionada = miRecyclerView.getChildAdapterPosition(view);
                if (cajaSeleccionada != -1) {
                    final String codigo = miViewModel.getListadoCajas().get(cajaSeleccionada).getidcaja();
                    //escaner.consultarCodigo(idCaja, miViewModel);

                    if (db == null) {
                        db = FirebaseFirestore.getInstance();
                    }
                    DocumentReference docRefCaja = db.collection("cajas").document(codigo);
                    docRefCaja.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Caja caja = documentSnapshot.toObject(Caja.class);
                            caja.setidcaja(documentSnapshot.getId());

                            miViewModel.setCajaEscaneada(caja);
                            //miViewModel.setEstanteriaEscaneada(null);
                            //miViewModel.setObjetoEscaneado(null);


                            //consultar Cajas de la estantería -----------------------------

                            Query queryCajas = db.collection("objetos").whereEqualTo("idcaja", codigo);

                            queryCajas.addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot snapshot, @Nullable FirebaseFirestoreException e) {
                                    if (e != null) {
                                        // Handle error
                                        return;
                                    }

                                    List<Objeto> objetos = snapshot.toObjects(Objeto.class);

                                    miViewModel.setListadoObjetos((ArrayList<Objeto>) objetos);

                                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, new mostrarCaja()).addToBackStack(null).commit();
                                }
                            });
                        }
                    });

                }
            }
        });

        return view;
    }
}
