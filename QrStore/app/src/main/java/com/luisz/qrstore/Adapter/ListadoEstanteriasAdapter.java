package com.luisz.qrstore.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luisz.qrstore.Models.Caja;
import com.luisz.qrstore.Models.Estanteria;
import com.luisz.qrstore.R;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ListadoEstanteriasAdapter extends RecyclerView.Adapter<ListadoEstanteriasAdapter.ViewHolderEstanterias> implements View.OnClickListener {

    private ArrayList<Estanteria> listaEstanterias;
    private View.OnClickListener listener;

    public ListadoEstanteriasAdapter(ArrayList<Estanteria> listaEstanterias) {
        this.listaEstanterias = listaEstanterias;
    }

    @Override
    public ListadoEstanteriasAdapter.ViewHolderEstanterias onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listado_estanterias, null, false);
        view.setOnClickListener(this);

        return new ListadoEstanteriasAdapter.ViewHolderEstanterias(view);
    }

    @Override
    public void onBindViewHolder(ListadoEstanteriasAdapter.ViewHolderEstanterias viewHolderEstanterias, int i) {
        viewHolderEstanterias.txtNombreEstanteria.setText(listaEstanterias.get(i).getNombre());
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
        return listaEstanterias.size();
    }

    public class ViewHolderEstanterias extends RecyclerView.ViewHolder {

        TextView txtNombreEstanteria;

        public ViewHolderEstanterias(View itemView) {
            super(itemView);
            txtNombreEstanteria = itemView.findViewById(R.id.txtNombreEstanteria);
        }
    }

    public void setListaEstanterias(ArrayList<Estanteria> listaEstanterias) {
        this.listaEstanterias = listaEstanterias;
        notifyDataSetChanged();
    }

    public ArrayList<Estanteria> getListaEstanterias() {
        return listaEstanterias;
    }

    public void notifyChange() {
        notifyDataSetChanged();
    }

}