package com.luisz.qrstore.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.luisz.qrstore.Adapter.ListadoCajasAdapter;
import com.luisz.qrstore.Adapter.ListadoObjetosAdapter;
import com.luisz.qrstore.Models.Caja;
import com.luisz.qrstore.Models.Objeto;
import com.luisz.qrstore.R;
import com.luisz.qrstore.Viewmodel.ViewModel;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
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
                        } else {
                            DynamicToast.makeError(view.getContext().getApplicationContext(), "No hay cajas disponibles").show();
                        }
                    }
                });
    }
}