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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.luisz.qrstore.Adapter.ListadoObjetosAdapter;
import com.luisz.qrstore.Models.Objeto;
import com.luisz.qrstore.R;
import com.luisz.qrstore.Viewmodel.ViewModel;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class ConsultarTodosObjetos extends Fragment {

    private View view;
    private ViewModel miViewModel;
    private FragmentManager fragmentManager = getFragmentManager();
    private RecyclerView miRecycler;
    private FirebaseFirestore db;
    private ArrayList<Objeto> listadoObjetos;
    private ListadoObjetosAdapter adaptador;

    public ConsultarTodosObjetos() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mostrar_todos, container, false);
        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);
        db = FirebaseFirestore.getInstance();

        listadoObjetos = new ArrayList<Objeto>();
        consultarObjetos();

        miRecycler = view.findViewById(R.id.recyclerTodos);
        miRecycler.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 1, RecyclerView.VERTICAL, false));

        return view;
    }

    private void consultarObjetos() {
        listadoObjetos.clear();
        db.collection("objetos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Objeto objeto = new Objeto(document.getId(), document.get("idcaja").toString(), document.get("nombre").toString(), document.get("descripcion").toString());
                                listadoObjetos.add(objeto);
                            }
                            miViewModel.setListadoObjetos((ArrayList<Objeto>) listadoObjetos);
                            adaptador = new ListadoObjetosAdapter(listadoObjetos);
                            miRecycler.setAdapter(adaptador);

                            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
                            itemTouchHelper.attachToRecyclerView(miRecycler);

                        } else {
                            DynamicToast.makeError(view.getContext().getApplicationContext(), "No hay objetos disponibles").show();
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
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = viewHolder.getAdapterPosition();

                if(swipeDir == ItemTouchHelper.RIGHT ) {
                    Objeto objetoEliminado = adaptador.getListaObjetos().get(position);

                    listadoObjetos.remove(position);
                    eliminarObjeto(objetoEliminado, position);
                }
            }
        };
        return simpleItemTouchCallback;
    }

    private void undoDelete(Objeto objetoEliminado) {
        restaurarObjeto(objetoEliminado);
        consultarObjetos();
    }

    private void restaurarObjeto(Objeto objeto) {
        String nombreObjeto = objeto.getNombre();
        String descripcionObjeto = objeto.getDescripcion();
        String idCajaSeleccionada = objeto.getidcaja();


        Map<String, Object> map = new HashMap<>();
        map.put("idobjeto", objeto.getidobjeto());
        map.put("nombre", nombreObjeto);
        map.put("idcaja", idCajaSeleccionada);
        map.put("descripcion", descripcionObjeto);

        // Add a new document with a generated ID
        db.collection("objetos")
                .document(objeto.getidobjeto())
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

    private void eliminarObjeto(Objeto objetoEliminado, int position) {
        db.collection("objetos").document(objetoEliminado.getidobjeto())
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
                        //Log.w(TAG, "Error deleting document", e);
                    }
                });
    }

}