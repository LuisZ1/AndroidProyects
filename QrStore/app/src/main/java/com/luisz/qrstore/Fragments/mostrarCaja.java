package com.luisz.qrstore.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.luisz.qrstore.Adapter.ListadoObjetosAdapter;
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
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class mostrarCaja extends Fragment {

    private View view;
    private ViewModel miViewModel;
    private FragmentManager fragmentManager = getFragmentManager();
    private Estanteria estanteria;
    private Caja caja;
    private TextView txtNombreCaja, txtIdCaja, TxtNumeroObjetos, txtDescripciónCaja, txtIdEstanteriaDeCaja;
    private RecyclerView miRecyclerView;
    private ListadoObjetosAdapter adaptador;
    private FirebaseFirestore db;
    private ImageView imgBtnNavegarEstanteria;
    private ConstraintLayout constraintLayoutMostrarCaja;
    private ArrayList<Objeto> listadoObjetos;


    public mostrarCaja() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mostrar_caja, container, false);
        miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);
        db = FirebaseFirestore.getInstance();

        constraintLayoutMostrarCaja = view.findViewById(R.id.constraintLayoutMostrarCaja);
        constraintLayoutMostrarCaja.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new EditarCaja()).addToBackStack(null).commit();
                return true;
            }
        });

        listadoObjetos = miViewModel.getListadoObjetos();
        consultarObjetos();

        //listadoObjetos = miViewModel.getListadoObjetos();
        caja = miViewModel.getCajaEscaneada();

        txtNombreCaja = view.findViewById(R.id.txtNombreCajaEscaneada);
        txtNombreCaja.setText(caja.getNombre());
        txtIdCaja = view.findViewById(R.id.txtIdCajaEscaneada);
        txtIdCaja.setText(caja.getIdestanteria());
        TxtNumeroObjetos = view.findViewById(R.id.txtNumeroObjetosCajaEscaneada);
        TxtNumeroObjetos.setText(String.valueOf(miViewModel.getListadoObjetos().size()));

        txtIdEstanteriaDeCaja = view.findViewById(R.id.txtIdEstanteriaDeCaja);
        txtIdEstanteriaDeCaja.setText(caja.getIdestanteria());
        txtDescripciónCaja = view.findViewById(R.id.txtDescripciónCaja);
        txtDescripciónCaja.setText(caja.getDescripcion());

        imgBtnNavegarEstanteria = view.findViewById(R.id.btnNavegarEstanteria);
        imgBtnNavegarEstanteria.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                navegarEstanteria();
            }
        });

        miRecyclerView = view.findViewById(R.id.recyclerObjetos);
        miRecyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 1, RecyclerView.VERTICAL, false));
        adaptador = new ListadoObjetosAdapter(listadoObjetos);
        miRecyclerView.setAdapter(adaptador);

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int objetoseleccionado = miRecyclerView.getChildAdapterPosition(view);
                if (objetoseleccionado != -1) {
                    final String codigo = listadoObjetos.get(objetoseleccionado).getidobjeto();
                    if (db == null) {
                        db = FirebaseFirestore.getInstance();
                    }

                    DocumentReference docRefObjeto = db.collection("objetos").document(codigo);
                    docRefObjeto.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Objeto objeto = documentSnapshot.toObject(Objeto.class);
                            objeto.setidobjeto(documentSnapshot.getId());

                            miViewModel.setObjetoEscaneado(objeto);

                            getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    new mostrarObjeto()).addToBackStack(null).commit();
                        }
                    });
                }
            }
        });

        return view;
    }

    private void navegarEstanteria(){

        if (db == null) {
            db = FirebaseFirestore.getInstance();
        }
        if (miViewModel == null) {
            miViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);
        }

        DocumentReference docRef = db.collection("estanterias").document(caja.getIdestanteria());

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Estanteria estanteria = documentSnapshot.toObject(Estanteria.class);
                estanteria.setIdestanteria(documentSnapshot.getId());

                miViewModel.setEstanteriaEscaneada(estanteria);

                //consultar Cajas de la estantería -----------------------------

                Query queryCajas = db.collection("cajas").whereEqualTo("idestanteria", caja.getIdestanteria());

                queryCajas.addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot snapshot,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            return;
                        }

                        List<Caja> cajas = snapshot.toObjects(Caja.class);

                        miViewModel.setListadoCajas((ArrayList<Caja>) cajas);

                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new mostrarEstanteria()).addToBackStack(null).commit();
                    }
                });
            }
        });
    }

    private ItemTouchHelper.Callback createHelperCallback() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            //not used, as the first parameter above is 0
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = viewHolder.getAdapterPosition();

                Objeto objetoEliminado = adaptador.getListaObjetos().get(position);

                listadoObjetos.remove(position);
                eliminarObjeto(objetoEliminado, position);
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
                            miRecyclerView.setAdapter(adaptador);

                            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
                            itemTouchHelper.attachToRecyclerView(miRecyclerView);

                        } else {
                            DynamicToast.makeError(view.getContext().getApplicationContext(), "No hay objetos disponibles").show();
                        }
                    }
                });
    }
}
