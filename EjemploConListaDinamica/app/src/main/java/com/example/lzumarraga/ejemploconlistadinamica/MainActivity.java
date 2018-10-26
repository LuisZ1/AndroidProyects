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
import java.io.ByteArrayOutputStream;

public class MainActivity extends ListActivity  {

    Equipo[] equipos = new Equipo[7];
    TextView selection;
    ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rellenarEquipos();

        setListAdapter(new IconicAdapter<Equipo>(this, R.layout.fila, R.id.label, equipos));
        selection=findViewById(R.id.selection);

    }

    /*
    * El metodo rellena un array con varios objetos equipo de futbol para mostrarlos en una lista
    * */
    public void rellenarEquipos() {
        Equipo sevilla = new Equipo("sevilla","Sevilla FC", R.drawable.sevilla);
        Equipo betis = new Equipo("betis","Real Betis", R.drawable.betis, "El Real Betis Balompié, también conocido " +
                "como Real Betis o simplemente Betis, es una entidad polideportiva con sede en Sevilla, España. Fue establecida " +
                "en septiembre de 1908 para la práctica del fútbol —aunque sus orígenes datan de 1907 y así consta como fecha " +
                "fundacional por el propio club—, y oficialmente registrada el 1 de febrero de 1909.");
        Equipo madrid = new Equipo("madrid","Real Madrid",R.drawable.madrid);
        Equipo barsa = new Equipo("barcelona","Barcelona FC", R.drawable.barsa);
        Equipo valencia = new Equipo("valencia","Valencia", R.drawable.valencia);
        Equipo talavera = new Equipo("real sociedad","Real Sociedad", R.drawable.realsociedad);
        Equipo recre = new Equipo("alaves","Alavés", R.drawable.alaves);

        equipos[0] = sevilla;
        equipos[1] = betis;
        equipos[2] = madrid;
        equipos[3] = barsa;
        equipos[4] = valencia;
        equipos[5] = talavera;
        equipos[6] = recre;
    }

    class IconicAdapter<T> extends ArrayAdapter<T> {
        IconicAdapter(Context c, int resourceId, int textId, T[] objects) {
            super(c, resourceId, textId, objects);
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            View row = convertView;

            if (row==null){
                LayoutInflater inflater=getLayoutInflater();
                row=inflater.inflate(R.layout.fila, parent, false);
            }

            TextView label = (TextView) row.findViewById(R.id.label);
            label.setText(equipos[position].toString());

            icon = (ImageView)row.findViewById(R.id.icon);
            icon.setImageResource(equipos[position].getEscudo());

            return(row);
        }
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, activityMostrarEquipo.class);

        intent.putExtra("denominacion", equipos[position].getDenominacion());
        intent.putExtra("picture", convertirImagenABitmap (icon, position));
        intent.putExtra("des", equipos[position].getDescripcion());

        startActivity(intent);
    }

    /*
     * El metodo convierte una imageview a un array de bytes para poder mandarla en un intent
     * */
    private byte[] convertirImagenABitmap (ImageView icono, int position){

        Bitmap bmp = BitmapFactory.decodeResource(getResources(), equipos[position].getEscudo());
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        return byteArray;
    }


}
