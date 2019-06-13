package com.luisz.qrstore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.luisz.qrstore.Models.Estanteria;
import com.luisz.qrstore.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class EstanteriaSpinnerAdapter extends ArrayAdapter {

    public EstanteriaSpinnerAdapter(Context context, ArrayList<Estanteria> listaEstanterias) {
        super(context, 0, listaEstanterias);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.spinner_item, parent, false
            );
        }

        TextView textViewName = convertView.findViewById(R.id.textoSpinner);

        Estanteria currentItem = (Estanteria) getItem(position);

        if (currentItem != null) {
            textViewName.setText(currentItem.getNombre());
        }

        return convertView;
    }
}
