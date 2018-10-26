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

        //Pintar Denominacion equipo
        denominacion = intent.getStringExtra("denominacion");
        txtDenominacion = findViewById(R.id.denominacionEquipo);
        txtDenominacion.setText(denominacion);

        //pintar escudo equipo
        imgFoto = findViewById(R.id.escudoEquipo);
        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray("picture");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        imgFoto.setImageBitmap(bmp);

        //pintar descripcion equipo

        if(!intent.getStringExtra("des").equals("nulo")){
            descripcion = intent.getStringExtra("des");
            txtDescripcion = findViewById(R.id.txtDescripcion);
            txtDescripcion.setText(descripcion);
        }

    }



}
