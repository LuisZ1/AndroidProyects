package com.example.lzumarraga.ejemploconlistadinamica;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends ListActivity {

    Equipo[] equipos;
    TextView selection;
    ImageView icon;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //rellenarEquipos();
        //cargarEquiposJSON();
        recuperarEquiposJSON();

        setListAdapter(new IconicAdapter<Equipo>(this, R.layout.fila_equipo_primera, R.id.label, equipos));
        selection = findViewById(R.id.selection);

    }

    /*
     * El metodo rellena un array con varios objetos equipo de futbol para mostrarlos en una lista
     * */
    @Deprecated
    public void rellenarEquipos() {
        Equipo[] equipos = new Equipo[25];

        Equipo sevilla = new Equipo("sevilla", "Sevilla FC", R.drawable.sevilla, liga.PRIMERA, "El Sevilla Fútbol Club es un " +
                "club de fútbol español organizado como sociedad anónima deportiva. Tiene su sede en Sevilla, capital de la comunidad " +
                "autónoma de Andalucía, y actualmente juega en Primera División. Fue fundado el 25 de enero de 18905\u200B6\u200B y " +
                "su primer presidente fue el vice-cónsul británico Edward Farquharson Johnston.7\u200B Posteriormente fue inscrito " +
                "en el registro de asociaciones el 14 de octubre de 1905, siendo su presidente el jerezano José Luis Gallegos Arnosa.");
        Equipo betis = new Equipo("betis", "Real Betis", R.drawable.betis, liga.PRIMERA, "El Real Betis Balompié, también conocido " +
                "como Real Betis o simplemente Betis, es una entidad polideportiva con sede en Sevilla, España. Fue establecida " +
                "en septiembre de 1908 para la práctica del fútbol —aunque sus orígenes datan de 1907 y así consta como fecha " +
                "fundacional por el propio club—, y oficialmente registrada el 1 de febrero de 1909.");
        Equipo madrid = new Equipo("madrid", "Real Madrid", R.drawable.madrid, liga.PRIMERA, "El Real Madrid Club de Fútbol, " +
                "más conocido simplemente como Real Madrid, es una entidad polideportiva con sede en Madrid, España. Fue declarada " +
                "oficialmente registrada como club de fútbol por sus socios el 6 de marzo de 1902 con el objeto de la práctica y " +
                "desarrollo de este deporte —si bien sus orígenes datan al año 1900,8\u200B y su denominación de (Sociedad) Madrid " +
                "Foot-ball Club a noviembre de 1901—. Tuvo a Julián Palacios y los hermanos Juan Padrós y Carlos Padrós como " +
                "principales valedores de su creación.");
        Equipo barsa = new Equipo("barcelona", "Barcelona FC", R.drawable.barsa, liga.PRIMERA);
        Equipo valencia = new Equipo("valencia", "Valencia", R.drawable.valencia, liga.PRIMERA);
        Equipo talavera = new Equipo("real sociedad", "Real Sociedad", R.drawable.realsociedad, liga.PRIMERA);
        Equipo recre = new Equipo("alaves", "Alavés", R.drawable.alaves, liga.PRIMERA);

        equipos[0] = sevilla;
        equipos[1] = betis;
        equipos[2] = madrid;
        equipos[3] = barsa;
        equipos[4] = valencia;
        equipos[5] = talavera;
        equipos[6] = recre;

        Equipo albacete = new Equipo("albacete", "Albacete CF", R.drawable.betis, liga.SEGUNDA, "descripcion");
        Equipo alcorcon = new Equipo("alcorcon", "Alcorcon UD", R.drawable.betis, liga.SEGUNDA, "descripcion");
        Equipo cadiz = new Equipo("cadiz", "Cádiz CD", R.drawable.betis, liga.SEGUNDA, "descripcion");
        Equipo cordoba = new Equipo("cordoba", "Córdoba CF", R.drawable.betis, liga.SEGUNDA, "descripcion");
        Equipo almeria = new Equipo("almeria", "UD Almería", R.drawable.betis, liga.SEGUNDA, "descripcion");

        equipos[7] = albacete;
        equipos[8] = alcorcon;
        equipos[9] = cadiz;
        equipos[10] = cordoba;
        equipos[11] = almeria;

    }

    class IconicAdapter<T> extends ArrayAdapter<T> {
        IconicAdapter(Context c, int resourceId, int textId, T[] objects) {
            super(c, resourceId, textId, objects);
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            View row = convertView;
            ViewHolder holder;
            TextView lab;
            ImageView imgV;

            if (row == null) {
                LayoutInflater inflater = getLayoutInflater();

                if (getItemViewType(position) == 0) {
                    row = inflater.inflate(R.layout.fila_equipo_primera, parent, false);
                } else {
                    if (getItemViewType(position) == 1) {
                        row = inflater.inflate(R.layout.fila_equipo_segunda, parent, false);
                    }
                }

                lab = (TextView) row.findViewById(R.id.label);
                imgV = (ImageView) row.findViewById(R.id.icon);
                holder = new ViewHolder(lab, imgV);
                row.setTag(holder);

            } else {
                holder = (ViewHolder) row.getTag();
            }

//            holder.getLab().setText(equipitos.getEquipos()[position].toString());
//            holder.getImgV().setImageResource(equipitos.getEquipos()[position].getEscudo());

            holder.getLab().setText(equipos[position].toString());
//            holder.getImgV().setImageResource(equipos[position].getEscudo());
            holder.getImgV().setImageResource(R.drawable.pink);

            return (row);
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            int type = -1;

            if (equipos[position].getLiga() == liga.PRIMERA) {
                type = 0;
            } else {
                if (equipos[position].getLiga() == liga.SEGUNDA) {
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
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, activityMostrarEquipo.class);
        equipos[position].setLiga(liga.SEGUNDA);

        intent.putExtra("objEquipo", equipos[position]);


        startActivity(intent);
        refreshMain();
    }

    public void refreshMain(){

        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        this.finish();
    }

    /*
     * El metodo convierte una imageview a un array de bytes para poder mandarla en un intent
     * */
    private byte[] convertirImagenABitmap(ImageView icono, int position) {

        Bitmap bmp = BitmapFactory.decodeResource(getResources(), equipos[position].getEscudo());
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        return byteArray;
    }

    @Deprecated
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("cotilleos.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Deprecated
    public void cargarEquiposJSON() {
        rellenarEquipos();
        String json = gson.toJson(equipos);
    }

    public String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("cotilleos.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void recuperarEquiposJSON() {

        String json = readJSONFromAsset();

        equipos = gson.fromJson(json, Equipo[].class);
    }

}
