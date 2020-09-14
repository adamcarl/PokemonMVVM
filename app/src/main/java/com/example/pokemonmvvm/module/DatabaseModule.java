package com.example.pokemonmvvm.module;

import android.app.Application;

import androidx.room.Room;

import com.example.pokemonmvvm.constant.Constants;
import com.example.pokemonmvvm.dao.PokeDao;
import com.example.pokemonmvvm.database.PokemonDB;
import com.example.pokemonmvvm.model.Pokemon;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DatabaseModule {

    @Provides
    @Singleton
    public static PokemonDB providePokemonDB(Application application) {
        return Room.databaseBuilder(application, PokemonDB.class, Constants.DB_POKEMON_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static PokeDao providePokeDao(PokemonDB pokemonDB) {
        return pokemonDB.pokeDao();
    }
}
