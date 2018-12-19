package com.luisz.flama.clicker.clickerflama.adaptadores;

import android.arch.lifecycle.MutableLiveData;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luisz.flama.clicker.clickerflama.R;
import com.luisz.flama.clicker.clickerflama.modelos.mejora;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterMejoras extends RecyclerView.Adapter<AdapterMejoras.ViewHolderMejoras> implements View.OnClickListener{

    private ArrayList<mejora> listaMejoras;
    private View.OnClickListener listener;
    DecimalFormat formatter = new DecimalFormat("###,###,###,###,###,###,###,###,###");

    public AdapterMejoras(ArrayList<mejora> listaMejoras) {
        this.listaMejoras = listaMejoras;
    }

    @Override
    public ViewHolderMejoras onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_layout,null,false);
        view.setOnClickListener(this);
        return new ViewHolderMejoras(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderMejoras viewHolderMejoras, int i) {
        viewHolderMejoras.txtNombreMejora.setText(listaMejoras.get(i).getNombre());
        viewHolderMejoras.txtPrecio.setText(formatter.format(listaMejoras.get(i).getPrecio()));
        viewHolderMejoras.txtLevel.setText(formatter.format(listaMejoras.get(i).getNivel()));
        viewHolderMejoras.miCardView.setCardBackgroundColor(Color.parseColor(listaMejoras.get(i).getColorFondo()));
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
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

    public void setListaMejoras(ArrayList<mejora> listaMejoras) {
        this.listaMejoras = listaMejoras;
    }
}
