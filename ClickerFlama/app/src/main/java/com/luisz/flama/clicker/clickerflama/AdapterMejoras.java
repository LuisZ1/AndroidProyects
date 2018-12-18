package com.luisz.flama.clicker.clickerflama;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterMejoras extends RecyclerView.Adapter<AdapterMejoras.ViewHolderMejoras> {

    private ArrayList<mejora> listaMejoras;
    DecimalFormat formatter = new DecimalFormat("###,###,###,###,###,###,###,###,###");

    public AdapterMejoras(ArrayList<mejora> listaMejoras) {
        this.listaMejoras = listaMejoras;
    }


    @Override
    public ViewHolderMejoras onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_layout,null,false);
        return new ViewHolderMejoras(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderMejoras viewHolderMejoras, int i) {
        viewHolderMejoras.txtNombreMejora.setText(listaMejoras.get(i).getNombre());
        viewHolderMejoras.txtPrecio.setText(formatter.format(listaMejoras.get(i).getPrecio()));
        viewHolderMejoras.txtLevel.setText(formatter.format(listaMejoras.get(i).getNivel()));
        viewHolderMejoras.miCardView.setCardBackgroundColor(Color.GREEN);
    }

    @Override
    public int getItemCount() {
        return listaMejoras.size();
    }

    public class ViewHolderMejoras extends RecyclerView.ViewHolder {

        TextView txtNombreMejora, txtPrecio, txtLevel;
        CardView miCardView;

        public ViewHolderMejoras(View itemView) {
            super(itemView);
            txtNombreMejora = itemView.findViewById(R.id.txtNombreMejora);
            txtPrecio = itemView.findViewById(R.id.txtPrecio);
            txtLevel = itemView.findViewById(R.id.txtLevel);
            miCardView = itemView.findViewById(R.id.miCardView);
        }
    }
}
