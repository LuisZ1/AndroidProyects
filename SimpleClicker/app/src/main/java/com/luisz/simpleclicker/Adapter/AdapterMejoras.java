package com.luisz.simpleclicker.Adapter;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luisz.simpleclicker.Models.Mejora;
import com.luisz.simpleclicker.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterMejoras extends RecyclerView.Adapter<AdapterMejoras.ViewHolderMejoras> implements View.OnClickListener{
    
    private ArrayList<Mejora> listaMejoras;
    private View.OnClickListener listener;
    DecimalFormat formatter = new DecimalFormat("###,###,###,###,###,###,###,###,###");

    public AdapterMejoras(ArrayList<Mejora> listaMejoras) {
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

        viewHolderMejoras.lblPrecio.setText(Html.fromHtml("&#xf3d1;"));
        viewHolderMejoras.lblLevel.setText(Html.fromHtml("&#xf201;"));
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
        TextView lblPrecio, lblLevel;
        CardView miCardView;

        public ViewHolderMejoras(View itemView) {
            super(itemView);

            Typeface typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "awesome.ttf");

            txtNombreMejora = itemView.findViewById(R.id.txtNombreMejora);
            txtPrecio = itemView.findViewById(R.id.txtPrecio);
            txtLevel = itemView.findViewById(R.id.txtLevel);
            miCardView = itemView.findViewById(R.id.miCardView);
            lblPrecio = itemView.findViewById(R.id.lblPrecio);
            lblLevel = itemView.findViewById(R.id.lblNivel);

            this.lblPrecio.setTypeface(typeface);
            this.lblLevel.setTypeface(typeface);
        }
    }

    public void setListaMejoras(ArrayList<Mejora> listaMejoras) {
        this.listaMejoras = listaMejoras;
        notifyDataSetChanged();
    }

}
