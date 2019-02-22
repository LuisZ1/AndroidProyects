package com.example.misrecordatorios.Adapters;

import android.content.ClipData;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.misrecordatorios.Models.Recordatorio;
import com.example.misrecordatorios.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecordatorioAdapter extends RecyclerView.Adapter<RecordatorioAdapter.ViewHolderRecordatorio> implements View.OnClickListener {

    private List<Recordatorio> listaRecordatorios;
    private View.OnClickListener listener;

    public RecordatorioAdapter (List<Recordatorio> listaRecordatorios) {
        this.listaRecordatorios = listaRecordatorios;
    }

    public RecordatorioAdapter () {
    }

    @Override
    public ViewHolderRecordatorio onCreateViewHolder(ViewGroup parent, int i) {

        //inflar interfaz elemento
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_recordatorio, null, false);
        view.setOnClickListener(this);

        return new ViewHolderRecordatorio(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderRecordatorio viewHolderRecordatorio, int i) {

        //Date fecha = new Date() listaRecordatorios.get(i).getFecha();
        SimpleDateFormat ft = new SimpleDateFormat ("hh:mm 'h, ' dd/MM/yyyy");
        Date fecha = null;
        try {
            fecha = ft.parse(listaRecordatorios.get(i).getFecha());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //cargar datos del item
        viewHolderRecordatorio.txtNota.setText(listaRecordatorios.get(i).getContenido());
        viewHolderRecordatorio.txtFecha.setText(ft.format(fecha));
        viewHolderRecordatorio.rectangulo.setBackgroundColor(Color.parseColor(listaRecordatorios.get(i).getColor()));
//        viewHolderRecordatorio.rectangulito.setTint(Color.parseColor(listaRecordatorios.get(i).getColor()));
//        viewHolderRecordatorio.rectangulo.setBackgroundResource(viewHolderRecordatorio.rectangulito);
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
        return listaRecordatorios.size();
    }

    public class ViewHolderRecordatorio extends RecyclerView.ViewHolder {

        TextView txtNota, txtFecha;
        View rectangulo;
        Drawable rectangulito;

        public ViewHolderRecordatorio(View itemView) {
            super(itemView);
            txtNota = itemView.findViewById(R.id.txtNota);
            txtFecha = itemView.findViewById(R.id.txtFecha);
            rectangulo = itemView.findViewById(R.id.myRectangleView);
            rectangulito = itemView.getResources().getDrawable(R.drawable.rectangulo);
        }
    }

    public void setListaRecordatorios(List<Recordatorio> listaRecordatorios) {
        this.listaRecordatorios = listaRecordatorios;
        notifyDataSetChanged();
    }

    public List<Recordatorio> getListaRecordatorios() {
        return listaRecordatorios;
    }

    public void notifyChange() {
        notifyDataSetChanged();
    }


}
