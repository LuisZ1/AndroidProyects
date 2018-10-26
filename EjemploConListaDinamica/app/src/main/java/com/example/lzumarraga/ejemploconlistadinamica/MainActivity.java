package com.example.lzumarraga.ejemploconlistadinamica;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity {

    Equipo[] equipos = new Equipo[7];
    TextView selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rellenarEquipos();

        setListAdapter(new IconicAdapter<Equipo>(this, R.layout.fila, R.id.label, equipos));
        selection=findViewById(R.id.selection);
    }

    public void rellenarEquipos() {
        Equipo sevilla = new Equipo("Sevilla");
        Equipo betis = new Equipo("betis");
        Equipo madrid = new Equipo("madrid");
        Equipo barsa = new Equipo("barcelona");
        Equipo valencia = new Equipo("valencia");
        Equipo talavera = new Equipo("talavera");
        Equipo recre = new Equipo("recreativo");

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

            ImageView icon=(ImageView)row.findViewById(R.id.icon);


            if (equipos[position].getNombre().equals("Sevilla")) {
                icon.setImageResource(R.drawable.sevilla);
            }
           /* else if (equipos[position].equals("GPS")){
                icon.setImageResource(R.drawable.compass48x48);
            }
            else if (equipos[position].equals("alarma")){
                icon.setImageResource(R.drawable.alarm48x48);
            }
            else{
                icon.setImageResource(R.drawable.delete);
            }
*/
            return(row);
        }
    }

}
