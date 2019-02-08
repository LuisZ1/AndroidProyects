package com.luisz.simpleclicker.Adapter;

import android.graphics.Color;
import android.graphics.Typeface;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.luisz.simpleclicker.Models.Mejora_Per_Maq_Her;
import com.luisz.simpleclicker.R;
import com.luisz.simpleclicker.Util.formateoDeNumeros;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterMejoras_Per_Maqr_Her extends RecyclerView.Adapter<AdapterMejoras_Per_Maqr_Her.ViewHolderMejoras> implements View.OnClickListener{

    private ArrayList<Mejora_Per_Maq_Her> listaMejorasPersonal;
    private View.OnClickListener listener;
    DecimalFormat formatter = new DecimalFormat("###,###,###,###,###,###,###,###,###");

    public AdapterMejoras_Per_Maqr_Her(ArrayList<Mejora_Per_Maq_Her> listaMejorasPersonal) {
        this.listaMejorasPersonal = listaMejorasPersonal;
    }

    @Override
    public ViewHolderMejoras onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_layout_mejoraautoclick,null,false);
        view.setOnClickListener(this);

        return new ViewHolderMejoras(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderMejoras viewHolderMejoras, int i) {

        viewHolderMejoras.txtNombreMejora.setText(listaMejorasPersonal.get(i).getNombre());
        viewHolderMejoras.txtPrecio.setText(formateoDeNumeros.formatterV2(listaMejorasPersonal.get(i).getPrecio()));
        viewHolderMejoras.txtTiempo.setText(listaMejorasPersonal.get(i).getMejorasCompradas()+" / "+listaMejorasPersonal.get(i).getLimiteDeCompra());
        viewHolderMejoras.miCardView.setCardBackgroundColor(Color.parseColor(listaMejorasPersonal.get(i).getColorFondo()));
        viewHolderMejoras.lblPrecio.setText(Html.fromHtml("&#xf3d1;"));
        viewHolderMejoras.lblTiempo.setText(Html.fromHtml(/*"&#xf017;"*/""));
        viewHolderMejoras.img_mejora.setImageResource(listaMejorasPersonal.get(i).getImgFondo());
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
        return listaMejorasPersonal.size();
    }

    public class ViewHolderMejoras extends RecyclerView.ViewHolder {

        TextView txtNombreMejora, txtPrecio, txtTiempo;
        TextView lblPrecio, lblTiempo;
        CardView miCardView;
        private ImageView img_mejora;

        public ViewHolderMejoras(View itemView) {
            super(itemView);

            Typeface typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "awesome.ttf");

            txtNombreMejora = itemView.findViewById(R.id.txtNombreMejora);
            txtPrecio = itemView.findViewById(R.id.txtPrecio);
            txtTiempo = itemView.findViewById(R.id.txtTiempo);
            miCardView = itemView.findViewById(R.id.miCardView);
            lblPrecio = itemView.findViewById(R.id.lblPrecio);
            lblTiempo = itemView.findViewById(R.id.lblTiempo);
            img_mejora = itemView.findViewById(R.id.img_mejora);

            this.lblPrecio.setTypeface(typeface);
            this.lblTiempo.setTypeface(typeface);
        }
    }

    public void setListaMejorasPersonal(ArrayList<Mejora_Per_Maq_Her> listaMejoras) {
        this.listaMejorasPersonal = listaMejoras;
        notifyDataSetChanged();
    }

    public ArrayList<Mejora_Per_Maq_Her> getListaMejorasPersonal() {
        return listaMejorasPersonal;
    }

    public void notifyChange(){
        notifyDataSetChanged();
    }

    public boolean eliminarMejoraPersonalComprada(int posicionMejoraPersonalComprada){
        boolean resultado = false;

        try {
            listaMejorasPersonal.remove(posicionMejoraPersonalComprada);
            notifyItemRemoved(posicionMejoraPersonalComprada);
            for(int i=0; i<posicionMejoraPersonalComprada;i++){
                listaMejorasPersonal.remove(0);
                notifyItemRemoved(0);
            }
            resultado = true;
        }catch(Exception e){}

        return resultado;
    }

}
