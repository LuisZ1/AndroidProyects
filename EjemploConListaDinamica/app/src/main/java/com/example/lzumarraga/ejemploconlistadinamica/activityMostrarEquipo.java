package com.example.lzumarraga.ejemploconlistadinamica;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class activityMostrarEquipo extends AppCompatActivity {

    private String denominacion = "", descripcion = "";
    private TextView txtDenominacion, txtDescripcion;
    private ImageView imgFoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrarequipo);

        Intent intent = getIntent();

        Equipo equipo = intent.getParcelableExtra("objEquipo");

        //Pintar Denominacion equipo
        denominacion = equipo.getDenominacion();
        txtDenominacion = findViewById(R.id.denominacionEquipo);
        txtDenominacion.setText(denominacion);

//        pintar escudo equipo
        imgFoto = findViewById(R.id.escudoEquipo);
        imgFoto.setImageResource(equipo.getEscudo());

        //pintar descripcion equipo

        if(!equipo.getDescripcion().isEmpty()){
            descripcion = equipo.getDescripcion();
            txtDescripcion = findViewById(R.id.txtDescripcion);
            txtDescripcion.setText(descripcion);
        }

    }



}
