package com.example.pokemonmvvm.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.pokemonmvvm.dao.PokeDao;
import com.example.pokemonmvvm.model.Pokemon;

@Database(entities = {Pokemon.class}, version = 2, exportSchema = false)
public abstract class PokemonDB extends RoomDatabase {
    public abstract PokeDao pokeDao();
}
