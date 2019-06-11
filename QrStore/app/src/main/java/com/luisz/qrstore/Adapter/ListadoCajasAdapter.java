package com.luisz.qrstore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luisz.qrstore.Models.Caja;
import com.luisz.qrstore.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class ListadoCajasAdapter extends RecyclerView.Adapter<ListadoCajasAdapter.ViewHolderCajas> implements View.OnClickListener {

    private ArrayList<Caja> listaCajas;
    private View.OnClickListener listener;
    private  View view;

    public ListadoCajasAdapter(ArrayList<Caja> listaMejoras) {
        this.listaCajas = listaMejoras;
    }

    @Override
    public ViewHolderCajas onCreateViewHolder(ViewGroup parent, int i) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listado_cajas, null, false);
        view.setOnClickListener(this);

        return new ViewHolderCajas(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderCajas viewHolderCaja, int i) {

        viewHolderCaja.txtNombreCaja.setText(listaCajas.get(i).getNombre());
//        viewHolderCaja.txtPrecio.setText(formateoDeNumeros.formatterV2(listaCajas.get(i).getPrecio()));
//        viewHolderCaja.txtLevel.setText(formateoDeNumeros.formatterV2(listaCajas.get(i).getNivel()));
//        viewHolderCaja.miCardView.setCardBackgroundColor(Color.parseColor(listaCajas.get(i).getColorFondo()));
//
//        viewHolderCaja.lblPrecio.setText(Html.fromHtml("&#xf3d1;"));
//        viewHolderCaja.lblLevel.setText(Html.fromHtml("&#xf201;"));
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
        return listaCajas.size();
    }

    public class ViewHolderCajas extends RecyclerView.ViewHolder {

        TextView txtNombreCaja;

        public ViewHolderCajas(View itemView) {
            super(itemView);

            txtNombreCaja = itemView.findViewById(R.id.txtNombreCaja);
        }
    }

    public void setListaMejoras(ArrayList<Caja> listaCajas) {
        this.listaCajas = listaCajas;
        notifyDataSetChanged();
    }

    public ArrayList<Caja> getListaCajas() {
        return listaCajas;
    }

    public void notifyChange() {
        notifyDataSetChanged();
    }

    public Context getContext(){
        return view.getContext();
    }

}
