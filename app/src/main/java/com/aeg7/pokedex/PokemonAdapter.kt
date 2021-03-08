package com.aeg7.pokedex

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

private val TAG = PokemonAdapter::class.java.simpleName

class PokemonAdapter : ListAdapter<Pokemon, PokemonAdapter.ViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }
    }

    lateinit var onItemClickListener: (Pokemon) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.pokemon_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonAdapter.ViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.bind(pokemon)
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val idText = view.findViewById<TextView>(R.id.pokemon_id)
        private val nameText = view.findViewById<TextView>(R.id.name_id)
        private val typeimage = view.findViewById<ImageView>(R.id.image_id)
        fun bind(pokemon: Pokemon) {
            idText.text = pokemon.id.toString()
            nameText.text = pokemon.name
            val imageId = when (pokemon.type) {
                Pokemon.PokemonType.GRASS -> R.drawable.grass_icon
                Pokemon.PokemonType.ELECTRIC -> R.drawable.electric_icon
                Pokemon.PokemonType.FIGHTER -> R.drawable.fighter_icon
                Pokemon.PokemonType.FIRE -> R.drawable.fire_icon
                Pokemon.PokemonType.WATER -> R.drawable.water_icon
            }
            typeimage.setImageResource(imageId)
            view.setOnClickListener {
                if (::onItemClickListener.isInitialized) {
                    onItemClickListener(pokemon)
                } else {
                    Log.e(TAG, "onItemClickListener not initialized")
                }
            }
        }
    }

}