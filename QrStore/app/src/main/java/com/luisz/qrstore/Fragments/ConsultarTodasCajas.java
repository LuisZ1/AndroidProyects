package com.luisz.qrstore.Fragments;

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
import com.luisz.qrstore.Adapter.ListadoCajasAdapter;
import com.luisz.qrstore.Models.Caja;
import com.luisz.qrstore.R;
import com.luisz.qrstore.Viewmodel.ViewModel;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class ConsultarTodasCajas extends Fragment {

    private View view;
    private ViewModel miViewModel;
    private RecyclerView miRecycler;
    private FirebaseFirestore db;
    private ArrayList<Caja> listadoCajas;
    private ListadoCajasAdapter adaptador;

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

    private void consultarObjetos(){
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

            //not used, as the first parameter above is 0
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = viewHolder.getAdapterPosition();

                Caja objetoEliminado = adaptador.getListaCajas().get(position);

                listadoCajas.remove(position);
                eliminarCaja(objetoEliminado, position);
            }
        };
        return simpleItemTouchCallback;
    }

    private void undoDelete(Caja objetoEliminado) {
        restaurarCaja(objetoEliminado);
        consultarObjetos();
    }

    private void restaurarCaja(Caja objeto){

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

    private void eliminarCaja(Caja objetoEliminado, int position){
        db.collection("cajas").document(objetoEliminado.getidcaja())
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