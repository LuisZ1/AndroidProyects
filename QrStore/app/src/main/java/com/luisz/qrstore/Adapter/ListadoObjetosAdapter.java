package com.luisz.qrstore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luisz.qrstore.Models.Objeto;
import com.luisz.qrstore.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ListadoObjetosAdapter extends RecyclerView.Adapter<ListadoObjetosAdapter.ViewHolderObjetos> implements View.OnClickListener {

    private ArrayList<Objeto> listaObjetos;
    private View.OnClickListener listener;
    private View view;

    public ListadoObjetosAdapter(ArrayList<Objeto> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    @Override
    public ListadoObjetosAdapter.ViewHolderObjetos onCreateViewHolder(ViewGroup parent, int i) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listado_objetos, null, false);
        view.setOnClickListener(this);

        return new ListadoObjetosAdapter.ViewHolderObjetos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListadoObjetosAdapter.ViewHolderObjetos viewHolderCaja, int i) {

        viewHolderCaja.txtNombreObjeto.setText(listaObjetos.get(i).getNombre());
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    @Override
    public int getItemCount() {
        return listaObjetos.size();
    }

    public class ViewHolderObjetos extends RecyclerView.ViewHolder {

        TextView txtNombreObjeto;
        CardView miCardView;

        public ViewHolderObjetos(View itemView) {
            super(itemView);

            txtNombreObjeto = itemView.findViewById(R.id.txtNombreObjeto);
        }
    }

    public void setListaObjetos(ArrayList<Objeto> listaObjetos) {
        this.listaObjetos = listaObjetos;
        notifyDataSetChanged();
    }

    public ArrayList<Objeto> getListaObjetos() {
        return listaObjetos;
    }

    public void notifyChange() {
        notifyDataSetChanged();
    }

    public Context getContext(){
        return view.getContext();
    }

    public void deleteItem(int position){

    }

}