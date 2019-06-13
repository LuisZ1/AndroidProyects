package com.luisz.qrstore.Fragments;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.luisz.qrstore.Adapter.ListadoCajasAdapter;
import com.luisz.qrstore.Models.Caja;
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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class ConsultarTodasCajas extends Fragment {

    private View view;
    private ViewModel miViewModel;
    private RecyclerView miRecycler;
    private FirebaseFirestore db;
    private ArrayList<Caja> listadoCajas;
    private ListadoCajasAdapter adaptador;
    private boolean borrado;

    public ConsultarTodasCajas() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mostrar_todos, container, false);
        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);
        db = FirebaseFirestore.getInstance();

        listadoCajas = new ArrayList<Caja>();

        consultarObjetos();

        miRecycler = view.findViewById(R.id.recyclerTodos);
        miRecycler.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 1, RecyclerView.VERTICAL, false));

        return view;
    }

    private void consultarObjetos() {
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
                            miViewModel.setListadoCajas((ArrayList<Caja>) listadoCajas);
                            adaptador = new ListadoCajasAdapter(listadoCajas);
                            miRecycler.setAdapter(adaptador);

                            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
                            itemTouchHelper.attachToRecyclerView(miRecycler);
                        } else {
                            DynamicToast.makeError(view.getContext().getApplicationContext(), "No hay cajas disponibles").show();
                        }
                    }
                });
    }

    private ItemTouchHelper.Callback createHelperCallback() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,
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

            //not used, as the first parameter above is 0
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = viewHolder.getAdapterPosition();

                Caja objetoEliminado = adaptador.getListaCajas().get(position);

                eliminarCaja(objetoEliminado, position);
            }
        };
        return simpleItemTouchCallback;
    }


    private void restaurarCaja(Caja objeto) {

        Map<String, Object> map = new HashMap<>();
        map.put("idcaja", objeto.getidcaja());
        map.put("nombre", objeto.getNombre());
        map.put("idestanteria", objeto.getIdestanteria());
        map.put("descripcion", objeto.getDescripcion());

        // Add a new document with a generated ID
        db.collection("cajas")
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

    private boolean eliminarCaja(Caja objetoEliminado, int position) {

        Query queryCajas = db.collection("objetos").whereEqualTo("idcaja", objetoEliminado.getidcaja());

        queryCajas.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshot, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }

                List<Objeto> cajas = snapshot.toObjects(Objeto.class);

                if (cajas.size() > 0) {
                    //TODO Toast no es puede borrar caja
                    borrado = false;
                    DynamicToast.makeWarning(view.getContext().getApplicationContext(), "No puedes borrar esa caja, hay objetos dentro").show();
                    consultarObjetos();
                } else {
                    borrado = true;
                    db.collection("cajas").document(objetoEliminado.getidcaja())
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
        return borrado;
    }

    private void undoDelete(Caja objetoEliminado) {
        restaurarCaja(objetoEliminado);
        consultarObjetos();
    }
}