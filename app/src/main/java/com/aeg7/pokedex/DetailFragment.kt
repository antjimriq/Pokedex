package com.aeg7.pokedex

import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class DetailFragment : Fragment() {
    private lateinit var imageView: ImageView
    private lateinit var hpTextView: TextView
    private lateinit var attackText: TextView
    private lateinit var defenseText: TextView
    private lateinit var speedText: TextView
    private lateinit var loading: ProgressBar
    private lateinit var mp: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_detail, container, false)

        imageView = rootView.findViewById(R.id.fragment_detail_image)
        hpTextView = rootView.findViewById(R.id.fragment_hp_text)
        attackText = rootView.findViewById(R.id.fragment_attack_text)
        defenseText = rootView.findViewById(R.id.fragment_defense_text)
        speedText = rootView.findViewById(R.id.fragment_speed_text)
        loading = rootView.findViewById(R.id.fragment_detail_loading)

        return rootView
    }
    fun setPokemonData(pokemon: Pokemon) {
        loading.visibility = View.VISIBLE
        //Glide.with(this).load(pokemon.imageUrl).into(imageView)
        //Picasso.get().load(pokemon.imageUrl).into(imageView)
        Glide.with(this).load(pokemon.imageUrl).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                loading.visibility = View.GONE
                imageView.setImageResource(R.drawable.ic_broken_image)
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                loading.visibility = View.GONE
                return false
            }
        }).error(R.drawable.ic_broken_image).into(imageView)
        hpTextView.text = getString(R.string.hp_format, pokemon.hp)
        attackText.text = getString(R.string.attack_format, pokemon.attack)
        defenseText.text = getString(R.string.defense_format, pokemon.defense)
        speedText.text = getString(R.string.speed_format, pokemon.speed)
        if (this::mp.isInitialized && mp.isPlaying) {
            mp.stop()
            mp.release()
        }
        val mp = MediaPlayer.create(requireActivity(), pokemon.soundId)
        mp.start()
    }
    }