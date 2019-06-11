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
import com.luisz.qrstore.Adapter.ListadoEstanteriasAdapter;
import com.luisz.qrstore.Adapter.ListadoObjetosAdapter;
import com.luisz.qrstore.Models.Estanteria;
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

public class ConsultarTodasEstanterias extends Fragment {

    private View view;
    private ViewModel miViewModel;
    private FragmentManager fragmentManager = getFragmentManager();
    private RecyclerView miRecycler;
    private FirebaseFirestore db;
    private ArrayList<Estanteria> listadoEstanterias;
    private ListadoEstanteriasAdapter adaptador;

    public ConsultarTodasEstanterias() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mostrar_todos, container, false);
        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);
        db = FirebaseFirestore.getInstance();

        listadoEstanterias = new ArrayList<Estanteria>();

        consultarObjetos();

        miRecycler = view.findViewById(R.id.recyclerTodos);
        miRecycler.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 1, RecyclerView.VERTICAL, false));

        return view;
    }

    private void consultarObjetos(){
        db.collection("estanterias")
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
                        } else {
                            DynamicToast.makeError(view.getContext().getApplicationContext(), "No hay estanterias disponibles").show();
                        }
                    }
                });
    }
}