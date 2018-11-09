package com.example.lzumarraga.pokedexconapirest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.lzumarraga.pokedexconapirest.apiPokemon.PokeapiService;
import com.example.lzumarraga.pokedexconapirest.models.PokeRespuesta;
import com.example.lzumarraga.pokedexconapirest.models.Pokemon;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creamos la instancia de retrofit con la URL base de la API
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void getDatos() {

        PokeapiService service = retrofit.create(PokeapiService.class);
        Call<PokeRespuesta> pokeRespuestaCall = service.getPokemonList();

        pokeRespuestaCall.enqueue(new Callback<PokeRespuesta>() {
            @Override
            public void onResponse(Call<PokeRespuesta> call, Response<PokeRespuesta> response) {
                if (response.isSuccessful()) {
                    PokeRespuesta pokemonRespuesta = response.body();
                    ArrayList<Pokemon> listaPokemon = PokeRespuesta.getPokemonList();

                    for(int i = 0 ; i<;i++){
                        Pokemon poke = listaPokemon.get[i];
                    }

                } else {
                    Log.e("msg","onResponse" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokeRespuesta> call, Throwable t) {
                Log.e("msg", "onFailure" + t.getMessage());
            }
        });

    }
}
