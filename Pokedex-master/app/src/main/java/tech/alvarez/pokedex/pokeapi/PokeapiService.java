package tech.alvarez.pokedex.pokeapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import tech.alvarez.pokedex.models.PokemonCompleto;
import tech.alvarez.pokedex.models.PokemonRespuesta;

public interface PokeapiService {

    @GET("pokemon")
    Call<PokemonRespuesta> obtenerListaPokemon(@Query("limit") int limit, @Query("offset") int offset);



    @GET("pokemon/{id}")
    Call<PokemonCompleto> obtenerPokemon(@Path(value = "id", encoded = true) int id);

}
