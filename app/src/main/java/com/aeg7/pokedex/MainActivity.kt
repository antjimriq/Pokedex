package com.aeg7.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity(),ListFragment.PokemonSelectListener {
    private lateinit var detailFragment:DetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) }

    override fun onPokemonSelected(pokemon: Pokemon) {
        findNavController(R.id.main_navigation_container).navigate(ListFragmentDirections.actionListFragmentToPokemonDetailFragment(pokemon))
    }
}