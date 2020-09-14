package com.example.pokemonmvvm.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.pokemonmvvm.R;
import com.example.pokemonmvvm.constant.Constants;
import com.example.pokemonmvvm.databinding.ActivityMainBinding;
import com.example.pokemonmvvm.ui.fragment.Favorites;
import com.example.pokemonmvvm.ui.fragment.Home;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private boolean isFavoriteVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new Home())
                .commit();

        binding.changeFragment.setOnClickListener(view -> {
            if(isFavoriteVisible) {
                isFavoriteVisible = false;
                binding.changeFragment.setText(Constants.FAVORITES_TAG);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,
                        new Home()).commit();
            } else {
                isFavoriteVisible = true;
                binding.changeFragment.setText(Constants.HOME_TAG);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,
                        new Favorites()).commit();
            }
        });
    }
}