package com.example.pokemonmvvm.network;

import com.example.pokemonmvvm.model.PokemonResponse;

import io.reactivex.rxjava3.core.Observable;

import retrofit2.http.GET;

import static com.example.pokemonmvvm.constant.Constants.POKEMON_API;

public interface PokeApiService {

    @GET(POKEMON_API)
    Observable<PokemonResponse> getPokemons();
}
