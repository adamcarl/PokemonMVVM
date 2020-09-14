package com.example.pokemonmvvm.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pokemonmvvm.constant.Constants;
import com.example.pokemonmvvm.model.Pokemon;

import java.util.List;

@Dao
public interface PokeDao {

    @Insert
    void insertPokemon(Pokemon pokemon);

    @Query("DELETE FROM " + Constants.TBL_POKEMON_FAVORITE + " WHERE "
            + Constants.COL_POKEMON_NAME + " = :pokemonName")
    void deletePokemon(String pokemonName);

    @Query("DELETE FROM " + Constants.TBL_POKEMON_FAVORITE)
    void deleteAll();

    @Query("SELECT * FROM " + Constants.TBL_POKEMON_FAVORITE)
    LiveData<List<Pokemon>> getFavoritePokemon();
}
