package com.luisz.qrstore.Fragments;

import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
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
import com.luisz.qrstore.Adapter.ListadoEstanteriasAdapter;
import com.luisz.qrstore.Models.Caja;
import com.luisz.qrstore.Models.Estanteria;
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

public class ConsultarTodasEstanterias extends Fragment {

    private View view;
    private ViewModel miViewModel;
    private RecyclerView miRecycler;
    private FirebaseFirestore db;
    private ArrayList<Estanteria> listadoEstanterias;
    private ListadoEstanteriasAdapter adaptador;
    private static final String STRINGESTANTERIA = "estanteria";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mostrar_todos, container, false);
        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);
        db = FirebaseFirestore.getInstance();

        listadoEstanterias = new ArrayList<>();

        consultarObjetos();

        miRecycler = view.findViewById(R.id.recyclerTodos);
        miRecycler.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 1, RecyclerView.VERTICAL, false));

        return view;
    }

    private void consultarObjetos(){
        listadoEstanterias.clear();
        db.collection(STRINGESTANTERIA)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Estanteria estanteria = new Estanteria(document.getId(), document.get("nombre").toString(), document.get("ubicacion").toString(), document.get("descripcion").toString());
                                listadoEstanterias.add(estanteria);
                            }
                            miViewModel.setListadoEstanterias((ArrayList<Estanteria>) listadoEstanterias);
                            adaptador = new ListadoEstanteriasAdapter(listadoEstanterias);
                            miRecycler.setAdapter(adaptador);

                            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
                            itemTouchHelper.attachToRecyclerView(miRecycler);

                        } else {
                            DynamicToast.makeError(view.getContext().getApplicationContext(), "No hay estanterias disponibles").show();
                        }
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

            //not used, as the first parameter above is 0
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = viewHolder.getAdapterPosition();

                if (swipeDir == ItemTouchHelper.RIGHT) {
                    Estanteria objetoEliminado = adaptador.getListaEstanterias().get(position);
                    listadoEstanterias.remove(position);
                    eliminarEstanteria(objetoEliminado, position);
                } else if (swipeDir == ItemTouchHelper.LEFT) {
                    //DynamicToast.makeSuccess(view.getContext().getApplicationContext(), "Consulta:" +position).show();
                    miViewModel.setEstanteriaEscaneada(adaptador.getListaEstanterias().get(position));
                    if (getFragmentManager() != null) {
                        getFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, new MostrarEstanteria()) .addToBackStack(null).commit();
                    }
                }
            }
        };
    }

    private void undoDelete(Estanteria objetoEliminado) {
        restaurarEstanteria(objetoEliminado);
        listadoEstanterias.clear();
        consultarObjetos();
    }

    private void restaurarEstanteria(Estanteria objeto){
        Map<String, Object> map = new HashMap<>();
        map.put("ubicacion", objeto.getUbicacion());
        map.put("nombre", objeto.getNombre());
        map.put("idestanteria", objeto.getIdestanteria());
        map.put("descripcion", objeto.getDescripcion());

        // Add a new document with a generated ID
        db.collection(STRINGESTANTERIA)
                .document(objeto.getIdestanteria())
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
                        DynamicToast.makeError(view.getContext().getApplicationContext(), "Error al restaurar la estantería").show();
                    }
                });
    }

    private void eliminarEstanteria(Estanteria objetoEliminado, int position){

        Query queryCajas = db.collection("cajas").whereEqualTo("idestanteria", objetoEliminado.getIdestanteria());

        queryCajas.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    // Handle error
                    return;
                }

                List<Caja> cajas = snapshot.toObjects(Caja.class);

                if(!cajas.isEmpty()){
                    DynamicToast.makeWarning(view.getContext().getApplicationContext(), "No puedes borrar esa estantería, hay cajas dentro").show();
                    listadoEstanterias.clear();
                    consultarObjetos();
                }else{
                    db.collection(STRINGESTANTERIA).document(objetoEliminado.getIdestanteria())
                            .delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
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
                                    Log.e("consultarEstanteria", "Error deleting document", e);
                                }
                            });
                }

            }
        });

    }

}