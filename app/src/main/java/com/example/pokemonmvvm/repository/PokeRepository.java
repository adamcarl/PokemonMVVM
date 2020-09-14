package com.example.pokemonmvvm.repository;

import androidx.lifecycle.LiveData;

import com.example.pokemonmvvm.dao.PokeDao;
import com.example.pokemonmvvm.model.Pokemon;
import com.example.pokemonmvvm.model.PokemonResponse;
import com.example.pokemonmvvm.network.PokeApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class PokeRepository {

    private PokeDao pokeDao;
    private PokeApiService apiService;

    @Inject
    public PokeRepository(PokeDao pokeDao, PokeApiService apiService) {
        this.pokeDao = pokeDao;
        this.apiService = apiService;
    }

    public Observable<PokemonResponse> getPokemons() {
        return apiService.getPokemons();
    }

    public void insertPokemon(Pokemon pokemon) {
        pokeDao.insertPokemon(pokemon);
    }

    public void deletePokemon(String pokemonName) {
        pokeDao.deletePokemon(pokemonName);
    }

    public void deleteAll() {
        pokeDao.deleteAll();
    }

    public LiveData<List<Pokemon>> getFavoritePokemon() {
        return pokeDao.getFavoritePokemon();
    }
}
