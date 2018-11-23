package com.example.lzumarraga.examenluisz_trim1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class activityMostrarJugadorBaloncesto extends AppCompatActivity {

    private TextView txtnombreJugador;
    private ImageView imgFoto;
    private EditText txtPuntos, txtRebotes, txtAsistencias;
    private Button btnGuardar, btnClonar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrarjugadorbaloncesto);

        Intent intent = getIntent();

        JugadorBaloncesto jugador = (JugadorBaloncesto) intent.getParcelableExtra("objJugador");

        txtnombreJugador = findViewById(R.id.nombreJugador);
        imgFoto = findViewById(R.id.fotoJugador);
        txtPuntos= findViewById(R.id.txtPuntos);
        txtRebotes= findViewById(R.id.txtRebotes);
        txtAsistencias= findViewById(R.id.txtAsistencias);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnClonar = findViewById(R.id.btnClonar);


        imgFoto.setImageResource(jugador.getFoto());
        txtnombreJugador.setText(jugador.getNombre());
        txtPuntos.setText(String.valueOf(jugador.getPuntos()));
        txtAsistencias.setText(String.valueOf(jugador.getAsistencias()));
        txtRebotes.setText(String.valueOf(jugador.getRebotes()));

    }

    public void enConstruccion(){
        Toast.makeText(this,"En construcción",Toast.LENGTH_SHORT).show();
    }

    public void onClickEditarJugadorBal(View view){
        enConstruccion();
        btnGuardar.setVisibility(View.VISIBLE);
        btnClonar.setVisibility(View.INVISIBLE);
    }

    public void onClickGuardarJugadorBaloncesto(View v){
        enConstruccion();
        btnGuardar.setVisibility(View.INVISIBLE);
        btnClonar.setVisibility(View.VISIBLE);
    }

    public void onClickBorrarJugador(View v){
        enConstruccion();
    }

    public void onClickClonarJugador(View v){
        enConstruccion();
    }


}
