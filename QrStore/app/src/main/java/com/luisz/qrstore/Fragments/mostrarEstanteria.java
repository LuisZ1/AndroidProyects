package com.luisz.qrstore.Fragments;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
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
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class MostrarEstanteria extends Fragment {

    private View view;
    private ViewModel miViewModel;
    private Estanteria estanteria;
    private TextView txtNombreEstanteria, txtIdEstanteria, txtnumerocajas, txtLugarEstanteria, txtDescripcionEstanteria;
    private RecyclerView miRecyclerView;
    private ListadoCajasAdapter adaptador;
    private FirebaseFirestore db;
    private ConstraintLayout tarjetaPrincipal;
    private ArrayList<Caja> listadoCajas;
    private static final String STRINGCAJA = "cajas";

    public MostrarEstanteria() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mostrar_estanteria, container, false);
        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);
        db = FirebaseFirestore.getInstance();

        listadoCajas = new ArrayList<>();

        tarjetaPrincipal = view.findViewById(R.id.constraintLayoutMostrarEstanteria);
        tarjetaPrincipal.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(getFragmentManager() != null) {
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new EditarEstanteria()).addToBackStack(null).commit();
                }
                return true;
            }
        });

        estanteria = miViewModel.getEstanteriaEscaneada();

        txtNombreEstanteria = view.findViewById(R.id.txtNombreEstanteriaEscaneada);
        txtNombreEstanteria.setText(estanteria.getNombre());
        txtIdEstanteria = view.findViewById(R.id.txtIdEstanteriaEscaneada);
        txtIdEstanteria.setText(estanteria.getIdestanteria());
        txtnumerocajas = view.findViewById(R.id.txtCajasEstanteriaEscaneada);

        txtDescripcionEstanteria = view.findViewById(R.id.txtDescripcionEstanteria);
        txtDescripcionEstanteria.setText(estanteria.getDescripcion());
        txtLugarEstanteria = view.findViewById(R.id.txtLugasEstaneria);
        txtLugarEstanteria.setText(estanteria.getUbicacion());

        miRecyclerView = view.findViewById(R.id.recyclerCajas);
        miRecyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 1, RecyclerView.VERTICAL, false));

        consultarObjetos();

        return view;
    }

    private void navegarCaja(int position) {
        int cajaSeleccionada = position;
        if (cajaSeleccionada != -1) {
            final String codigo = listadoCajas.get(cajaSeleccionada).getidcaja();

            if (db == null) {
                db = FirebaseFirestore.getInstance();
            }
            DocumentReference docRefCaja = db.collection(STRINGCAJA).document(codigo);
            docRefCaja.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    Caja caja = documentSnapshot.toObject(Caja.class);
                    caja.setidcaja(documentSnapshot.getId());

                    miViewModel.setCajaEscaneada(caja);

                    if (getFragmentManager() != null) {
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new MostrarCaja()).addToBackStack(null).commit();
                    }

                }
            });

        }

    }

    private void consultarObjetos() {
        listadoCajas.clear();
        Query queryCajas = db.collection(STRINGCAJA).whereEqualTo("idestanteria", estanteria.getIdestanteria());

        queryCajas.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    // Handle error
                    return;
                }

                List<Caja> cajas = snapshot.toObjects(Caja.class);

                miViewModel.setListadoCajas((ArrayList<Caja>) cajas);

                listadoCajas = (ArrayList<Caja>) cajas;

                txtnumerocajas.setText(String.valueOf(listadoCajas.size()));

                adaptador = new ListadoCajasAdapter(listadoCajas);
                ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
                itemTouchHelper.attachToRecyclerView(miRecyclerView);
                miRecyclerView.setAdapter(adaptador);
            }
        });
    }

    private ItemTouchHelper.Callback createHelperCallback() {
        return new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                new RecyclerViewSwipeDecorator.Builder(view.getContext(), c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeRightBackgroundColor(getResources().getColor(R.color.rojo))
                        .addSwipeRightActionIcon(R.drawable.ic_delete_sweep_black_24dp)
                        .addSwipeRightLabel("Eliminar")
                        .setSwipeRightLabelColor(getResources().getColor(R.color.blanco))

                        .addSwipeLeftBackgroundColor(getResources().getColor(R.color.azulClaro))
                        .addSwipeLeftActionIcon(R.drawable.ic_arrow_forward_black_24dp)
                        .addSwipeLeftLabel("Consultar")
                        .setSwipeLeftLabelColor(getResources().getColor(R.color.blanco))
                        .create()
                        .decorate();

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {

                int position = viewHolder.getAdapterPosition();
                if (swipeDir == ItemTouchHelper.RIGHT) {

                    Caja objetoEliminado = adaptador.getListaCajas().get(position);

                    eliminarCaja(objetoEliminado, position);

                } else if (swipeDir == ItemTouchHelper.LEFT) {
                    navegarCaja(position);
                }

            }

        };
    }

    private void restaurarCaja(Caja objeto) {

        Map<String, Object> map = new HashMap<>();
        map.put("idcaja", objeto.getidcaja());
        map.put("nombre", objeto.getNombre());
        map.put("idestanteria", objeto.getIdestanteria());
        map.put("descripcion", objeto.getDescripcion());

        // Add a new document with a generated ID
        db.collection(STRINGCAJA)
                .document(objeto.getidcaja())
                .set(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        DynamicToast.makeSuccess(view.getContext().getApplicationContext(), "Restaurado").show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        DynamicToast.makeError(view.getContext().getApplicationContext(), "Error al guardar el objeto").show();
                    }
                });
    }

    private void eliminarCaja(Caja objetoEliminado, int position) {

        Query queryCajas = db.collection("objetos").whereEqualTo("idcaja", objetoEliminado.getidcaja());

        queryCajas.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshot, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }

                List<Objeto> cajas = snapshot.toObjects(Objeto.class);

                if (!cajas.isEmpty()) {
                    DynamicToast.makeWarning(view.getContext().getApplicationContext(), "No puedes borrar esa caja, hay objetos dentro").show();
                    consultarObjetos();
                } else {
                    db.collection(STRINGCAJA).document(objetoEliminado.getidcaja())
                            .delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    listadoCajas.remove(position);
                                    adaptador.notifyItemRemoved(position);
                                    Snackbar snackbar = Snackbar.make(view, "Elemento eliminado: " + position, Snackbar.LENGTH_LONG);
                                    snackbar.setAction("Deshacer", v -> undoDelete(objetoEliminado));
                                    snackbar.setActionTextColor(getResources().getColor(R.color.azulClaro));
                                    snackbar.setBackgroundTint(getResources().getColor(R.color.blanco));
                                    snackbar.setTextColor(getResources().getColor(R.color.azulOscuro));
                                    snackbar.show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                }
                            });
                }
            }
        });
    }

    private void undoDelete(Caja objetoEliminado) {
        restaurarCaja(objetoEliminado);
        consultarObjetos();
    }

}
