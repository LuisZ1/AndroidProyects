package com.example.lzumarraga.examenluisz_trim1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class activityMostrarJugadorFutbol extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String[] posicionesJugador;
    private EditText txtnombreJugador;
    private ImageView imgFoto;
    private Switch swtEditar;
    private Button btnGuardar;
    private Button btnClonar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrarjugadorfutbol);

        Intent intent = getIntent();

        JugadorFutbol jugador = (JugadorFutbol) intent.getParcelableExtra("objJugador");

        posicionesJugador = jugador.getPosiciones();

        txtnombreJugador = findViewById(R.id.nombreJugador);
        imgFoto = findViewById(R.id.fotoJugador);
        swtEditar = findViewById(R.id.switch1);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnClonar = findViewById(R.id.btnClonar);

        imgFoto.setImageResource(jugador.getFoto());
        txtnombreJugador.setText(jugador.getNombre());

        Spinner spin=(Spinner)findViewById(R.id.mySpinner);

        spin.setOnItemSelectedListener(this);

        ArrayAdapter<String> lista=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, posicionesJugador);

        lista.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(lista);
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void enConstruccion(){
        Toast.makeText(this,"En construcción",Toast.LENGTH_SHORT).show();
    }

    public void onClickEditarJugadorFut(View view){
        enConstruccion();
        btnGuardar.setVisibility(View.VISIBLE);
        btnClonar.setVisibility(View.INVISIBLE);
    }

    public void onClickGuardarJugadorFutbol(View v){
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
