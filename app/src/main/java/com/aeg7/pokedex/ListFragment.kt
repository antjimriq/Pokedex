package com.aeg7.pokedex

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ListFragment : Fragment() {
    interface PokemonSelectListener{
        fun onPokemonSelected(pokemon: Pokemon)
    }
    private lateinit var pokemonSelectListener: PokemonSelectListener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        pokemonSelectListener= try {
            context as PokemonSelectListener
        } catch (e: ClassCastException){
            throw ClassCastException("$context must implement PokemonSelectListener")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_list, container, false)
        val recycler= view.findViewById<RecyclerView>(R.id.pokemon_recycler)
        recycler.layoutManager=LinearLayoutManager(requireActivity())
        val adapter=PokemonAdapter()
        recycler.adapter=adapter
        adapter.onItemClickListener={
            //Toast.makeText(requireActivity(),it.name,Toast.LENGTH_SHORT).show()
            pokemonSelectListener.onPokemonSelected(it)
        }

        val pokemonList= mutableListOf<Pokemon>(
            Pokemon(1,"Bulbasaur",45,49,49,45,Pokemon.PokemonType.GRASS,"https://cdn.bulbagarden.net/upload/thumb/1/19/Ash_Bulbasaur.png/1200px-Ash_Bulbasaur.png",R.raw.blastoise),
            Pokemon(2,"Ivysaur",60,62,63,60,Pokemon.PokemonType.GRASS,"https://heraldjournalism.com/wp-content/uploads/2020/07/Screenshot-203-e1594582492710.png",R.raw.ivysour),
            Pokemon(3,"Venusaur",80,82 ,83,80,Pokemon.PokemonType.GRASS,"https://images-na.ssl-images-amazon.com/images/I/71r37X3FBCL._AC_SL1500_.jpg",R.raw.venusaur),
            Pokemon(4,"Charmander",39,52,43,65,Pokemon.PokemonType.FIRE,"https://images-na.ssl-images-amazon.com/images/I/51bCvwyOAAL._AC_SX466_.jpg",R.raw.charmander),
            Pokemon(5,"Charmeleon",58,64,58,80,Pokemon.PokemonType.FIRE,"https://i.pinimg.com/originals/80/42/50/8042507726c9558b011c25c0ca4ecac8.png",R.raw.charmeleon),
            Pokemon(6,"Charizard",78,84,78,100,Pokemon.PokemonType.FIRE,"https://static.wikia.nocookie.net/ssbb/images/2/21/Charizard_SSBU.png/revision/latest?cb=20180614230743&path-prefix=es",R.raw.charizard),
            Pokemon(7,"Squirtle",44,48,65,43,Pokemon.PokemonType.WATER,"https://static.wikia.nocookie.net/espokemon/images/e/e3/Squirtle.png/revision/latest?cb=20160309230820",R.raw.squirtle),
            Pokemon(8,"Wartortle",59,63,80,58,Pokemon.PokemonType.WATER,"https://preview.free3d.com/img/2017/03/2188235964118205496/8woesxla-900.jpg",R.raw.wartortle),
            Pokemon(9,"Blastoise",79,83,100,78,Pokemon.PokemonType.WATER,"https://static.wikia.nocookie.net/espokemon/images/6/63/EP893_Blastoise_de_Beni.png/revision/latest/top-crop/width/360/height/450?cb=20151002164403",R.raw.blastoise),
            Pokemon(25,"Pikachu",35,55,40,90,Pokemon.PokemonType.ELECTRIC,"https://static.wikia.nocookie.net/ssbb/images/b/b8/025Pikachu_LG.png/revision/latest?cb=20190520161120&path-prefix=es",R.raw.pikachu),
            Pokemon(26,"Raichu",60,90,55,110,Pokemon.PokemonType.ELECTRIC,"https://static.pokemonpets.com/images/monsters-images-300-300/26-Raichu.png",R.raw.raichu),
            Pokemon(56,"Mankey",40,80,35,70,Pokemon.PokemonType.FIGHTER,"https://cdn.bulbagarden.net/upload/4/41/056Mankey.png",R.raw.mankey),
            Pokemon(57,"Primeape",65,105,60,95,Pokemon.PokemonType.FIGHTER,"https://static.pokemonpets.com/images/monsters-images-300-300/57-Primeape.png",R.raw.primeape)
        )
        adapter.submitList(pokemonList)
        return view
    }
}