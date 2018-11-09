package com.example.lzumarraga.pokedexconapirest.apiPokemon;

import com.example.lzumarraga.pokedexconapirest.models.PokeRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeapiService {

    @GET("pokemon")
    Call<PokeRespuesta> getPokemonList();

}
