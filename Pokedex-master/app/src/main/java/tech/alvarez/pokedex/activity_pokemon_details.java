package tech.alvarez.pokedex;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import tech.alvarez.pokedex.models.Pokemon;
import tech.alvarez.pokedex.models.type;

public class activity_pokemon_details extends AppCompatActivity {

    private String Nombre="", Numero="", Tipo="";
    private TextView txtNombre, txtNumero, txtTipo;
    private ImageView imgV;
    Pokemon pokemon;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_details);


        Intent intent = getIntent();
        pokemon = intent.getParcelableExtra("objPokemon");

        //Imagen
        imgV = (ImageView) findViewById(R.id.imgVFotoPokemon);

        Glide.with(activity_pokemon_details.this)
                .load("http://pokeapi.co/media/sprites/pokemon/" + pokemon.getId() + ".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.refresh_button_red)
                .sizeMultiplier(1)
                .placeholder(R.drawable.refresh_button_red)
                .into(imgV);

        //Nombre
        Nombre = pokemon.getName().toUpperCase();
        txtNombre = (TextView) findViewById(R.id.txtNombrePokemon);
        txtNombre.setText(Nombre);

        //Numero
        Numero = String.valueOf(pokemon.getId());
        txtNumero = (TextView) findViewById(R.id.txtNumPokemon);
        txtNumero.setText(Numero);

        //Tipo
//        type[] types = pokemon.getTypes();
//        Tipo = types[0].toString();
        txtTipo = (TextView) findViewById(R.id.txtTipos);
        txtTipo.setText(Tipo);



    }
}
