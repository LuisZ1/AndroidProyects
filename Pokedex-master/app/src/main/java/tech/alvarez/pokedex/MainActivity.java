package tech.alvarez.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tech.alvarez.pokedex.models.Pokemon;
import tech.alvarez.pokedex.models.PokemonCompleto;
import tech.alvarez.pokedex.models.PokemonRespuesta;
import tech.alvarez.pokedex.pokeapi.PokeapiService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "POKEDEX";

    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private ListaPokemonAdapter listaPokemonAdapter;
    ArrayList<Pokemon> listaPokemon;
    private PokemonCompleto pokemonCompletoRespuesta;

    private int offset;
    private boolean aptoParaCargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listaPokemonAdapter = new ListaPokemonAdapter(this);
        recyclerView.setAdapter(listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        listaPokemonAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), activity_pokemon_details.class);

                Pokemon pokemon = (Pokemon) listaPokemon.get(recyclerView.getChildAdapterPosition(view));

                obtenerPokemon(pokemon.getNumber());

                intent.putExtra("objPokemon", pokemon);

                startActivity(intent);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (aptoParaCargar) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i(TAG, " Llegamos al final.");

                            aptoParaCargar = false;
                            offset += 20;
                            obtenerDatos(offset);
                        }
                    }
                }
            }
        });


        retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        aptoParaCargar = true;
        offset = 0;
        obtenerDatos(offset);
    }

    private void obtenerDatos(int offset) {
        PokeapiService service = retrofit.create(PokeapiService.class);
        Call<PokemonRespuesta> pokemonRespuestaCall = service.obtenerListaPokemon(20, offset);

        pokemonRespuestaCall.enqueue(new Callback<PokemonRespuesta>() {
            @Override
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
                aptoParaCargar = true;
                if (response.isSuccessful()) {

                    PokemonRespuesta pokemonRespuesta = response.body();
                    listaPokemon = pokemonRespuesta.getResults();

                    listaPokemonAdapter.adicionarListaPokemon(listaPokemon);

                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
                aptoParaCargar = true;
                Log.e(TAG, " onFailure: " + t.getMessage());
            }
        });
    }

    private void obtenerPokemon(int idPokemon) {
        PokeapiService service = retrofit.create(PokeapiService.class);
        Call<PokemonCompleto> pokemonCall = service.obtenerPokemon(idPokemon);
//        PokemonCompleto pokemonCompletoRespuesta = new PokemonCompleto();

        pokemonCall.enqueue(new Callback<PokemonCompleto>() {
            @Override
            public void onResponse(Call<PokemonCompleto> call, Response<PokemonCompleto> response) {

                try {
                    aptoParaCargar = true;
                    if (response.isSuccessful()) {

                        pokemonCompletoRespuesta = response.body();
                        //pokemonCompletoRespuesta

                    } else {
                        Log.e(TAG, " onResponse: " + response.errorBody());
                    }

                }catch (Exception e){}
            }

            @Override
            public void onFailure(Call<PokemonCompleto> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
            }

        });
    }
}

