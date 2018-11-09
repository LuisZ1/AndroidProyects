package com.example.lzumarraga.pokedexconapirest.models;

import java.util.ArrayList;

public class PokeRespuesta {

    public ArrayList<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public void setPokemonList(ArrayList<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    private ArrayList<Pokemon> pokemonList;

}
