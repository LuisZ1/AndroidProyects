package com.example.lzumarraga.examenluisz_trim1;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ListActivity {

    private ArrayList<Jugador> jugadores;
    ImageView icon;
    int type = -1; // La declaro aqui para poder usarla en dos métodos

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyApplication mApplication = (MyApplication)getApplicationContext();

        jugadores = mApplication.getListaJugadores();

        setListAdapter(new IconicAdapter<Jugador>(this, R.layout.fila_futbol, R.id.label, jugadores));

    }


    class IconicAdapter<T> extends ArrayAdapter<T> {
        IconicAdapter(Context c, int resourceId, int textId, ArrayList<Jugador> objects) {
            super(c, resourceId, textId, (List<T>) objects);
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            View row = convertView;
            ViewHolder holder;
            TextView lab;
            ImageView imgV;


            if (row == null) {
                LayoutInflater inflater = getLayoutInflater();

                if (getItemViewType(position) == 0) {
                    row = inflater.inflate(R.layout.fila_futbol, parent, false);
                } else {
                    if (getItemViewType(position) == 1) {
                        row = inflater.inflate(R.layout.fila_baloncesto, parent, false);
                    }
                }

                lab = (TextView) row.findViewById(R.id.label);
                imgV = (ImageView) row.findViewById(R.id.icon);
                holder = new ViewHolder(lab, imgV);
                row.setTag(holder);

            } else {
                holder = (ViewHolder) row.getTag();
            }

            holder.getLab().setText(jugadores.get(position).getNombre());
            holder.getImgV().setImageResource(jugadores.get(position).getFoto());

            return (row);
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            if (jugadores.get(position).getTipoJugador() == TipoJugador.FUTBOL) {
                type = 0;
            } else {
                if (jugadores.get(position).getTipoJugador() == TipoJugador.BALONCESTO) {
//                if (jugadores.get(position).getClass().isInstance(JugadorBaloncesto.class)) {
                    type = 1;
                }
            }

            return type;
        }
    }

    class ViewHolder {
        TextView lab;
        ImageView imgV;

        ViewHolder(TextView lab, ImageView imgV) {
            this.lab = lab;
            this.imgV = imgV;
        }

        public TextView getLab() {
            return this.lab;
        }

        public ImageView getImgV() {
            return this.imgV;
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        Intent intent = new Intent();

        if (jugadores.get(position).getTipoJugador() == TipoJugador.FUTBOL) {
            intent = new Intent(this, activityMostrarJugadorFutbol.class);
        }else{
            if (jugadores.get(position).getTipoJugador() == TipoJugador.BALONCESTO) {
                intent = new Intent(this, activityMostrarJugadorBaloncesto.class);
            }
        }

        intent.putExtra("objJugador", jugadores.get(position));

        startActivity(intent);
    }




}
