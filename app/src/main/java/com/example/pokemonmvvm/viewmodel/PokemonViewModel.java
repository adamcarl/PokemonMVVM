package com.example.pokemonmvvm.viewmodel;

import android.accessibilityservice.GestureDescription;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pokemonmvvm.model.Pokemon;
import com.example.pokemonmvvm.model.PokemonResponse;
import com.example.pokemonmvvm.repository.PokeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static com.example.pokemonmvvm.constant.Constants.MSG0003;
import static com.example.pokemonmvvm.constant.Constants.POKEMON_IMG_URL;
import static com.example.pokemonmvvm.constant.Constants.POKEMON_TAG;

public class PokemonViewModel extends ViewModel {

    private PokeRepository repository;
    private MutableLiveData<ArrayList<Pokemon>> pokemonList = new MutableLiveData<>();
    private LiveData<List<Pokemon>> favoritePokemonList;

    @ViewModelInject
    public PokemonViewModel(PokeRepository repository) {
        this.repository = repository;
        favoritePokemonList = repository.getFavoritePokemon();
    }

    public MutableLiveData<ArrayList<Pokemon>> getPokemonList() {
        return pokemonList;
    }

    public void  getPokemons() {
        repository.getPokemons()
                .subscribeOn(Schedulers.io())
                .map(pokemonResponse -> {

                    ArrayList<Pokemon> list = pokemonResponse.getResults();
                    for(Pokemon pokemon : list) {
                        String url = pokemon.getUrl();
                        String[] pokemonIndex = url.split("/");
                        pokemon.setUrl(String.format(Locale.US,POKEMON_IMG_URL,
                                pokemonIndex[pokemonIndex.length-1]) );
                    }

                    Log.e(POKEMON_TAG, "apply: " + list.get(2).getUrl());
                    return list;

                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> pokemonList.setValue(result),
                        error-> Log.e(POKEMON_TAG, MSG0003 + error.getMessage() ));
    }

    public void insertPokemon(Pokemon pokemon) {
        repository.insertPokemon(pokemon);
    }

    public void deletePokemon(String pokemonName) {
        repository.deletePokemon(pokemonName);
    }

    public LiveData<List<Pokemon>> getFavoritePokemonList() {
        return favoritePokemonList;
    }

    public void getFavoritePokemon() {
        favoritePokemonList = repository.getFavoritePokemon();
    }
}
