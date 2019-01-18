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

import com.luisz.simpleclicker.Models.MejoraAutoClick;
import com.luisz.simpleclicker.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterMejorasAutoClick extends RecyclerView.Adapter<AdapterMejorasAutoClick.ViewHolderMejoras> implements View.OnClickListener{

    private ArrayList<MejoraAutoClick> listaMejorasAutoClick;
    private View.OnClickListener listener;
    DecimalFormat formatter = new DecimalFormat("###,###,###,###,###,###,###,###,###");

    public AdapterMejorasAutoClick(ArrayList<MejoraAutoClick> listaMejoras) {
        this.listaMejorasAutoClick = listaMejoras;
    }

    @Override
    public ViewHolderMejoras onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_layout_mejoraautoclick,null,false);
        view.setOnClickListener(this);

        return new ViewHolderMejoras(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderMejoras viewHolderMejoras, int i) {

        viewHolderMejoras.txtNombreMejora.setText(listaMejorasAutoClick.get(i).getNombre());
        viewHolderMejoras.txtPrecio.setText(formatter.format(listaMejorasAutoClick.get(i).getPrecio()));
        viewHolderMejoras.txtTiempo.setText(formatter.format(1000/listaMejorasAutoClick.get(i).getDelay()));
        viewHolderMejoras.miCardView.setCardBackgroundColor(Color.parseColor(listaMejorasAutoClick.get(i).getColorFondo()));

        viewHolderMejoras.lblPrecio.setText(Html.fromHtml("&#xf3d1;"));
        viewHolderMejoras.lblTiempo.setText(Html.fromHtml("&#xf017;"));
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
        return listaMejorasAutoClick.size();
    }

    public class ViewHolderMejoras extends RecyclerView.ViewHolder {

        TextView txtNombreMejora, txtPrecio, txtTiempo;
        TextView lblPrecio, lblTiempo;
        CardView miCardView;

        public ViewHolderMejoras(View itemView) {
            super(itemView);

            Typeface typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "awesome.ttf");

            txtNombreMejora = itemView.findViewById(R.id.txtNombreMejora);
            txtPrecio = itemView.findViewById(R.id.txtPrecio);
            txtTiempo = itemView.findViewById(R.id.txtTiempo);
            miCardView = itemView.findViewById(R.id.miCardView);
            lblPrecio = itemView.findViewById(R.id.lblPrecio);
            lblTiempo = itemView.findViewById(R.id.lblTiempo);

            this.lblPrecio.setTypeface(typeface);
            this.lblTiempo.setTypeface(typeface);
        }
    }

    public void setListaMejorasAutoClick(ArrayList<MejoraAutoClick> listaMejoras) {
        this.listaMejorasAutoClick = listaMejoras;
        notifyDataSetChanged();
    }

    public ArrayList<MejoraAutoClick> getListaMejorasAutoClick() {
        return listaMejorasAutoClick;
    }

    public void notifyChange(){
        notifyDataSetChanged();
    }

    public boolean eliminarMejoraComprada(int posicionMejoraAutoClickComprada){
        boolean resultado = false;

        try {
            listaMejorasAutoClick.remove(posicionMejoraAutoClickComprada);
            notifyItemRemoved(posicionMejoraAutoClickComprada);
            for(int i=0; i<posicionMejoraAutoClickComprada;i++){
                listaMejorasAutoClick.remove(0);
                notifyItemRemoved(0);
            }
            resultado = true;
        }catch(Exception e){}

        return resultado;
    }

}
