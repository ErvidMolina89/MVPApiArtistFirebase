package com.wposs.mvpapiartistfirebase.Presentation.Dash.ListSongs.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.wposs.mvpapiartistfirebase.Base.App
import com.wposs.mvpapiartistfirebase.Models.DetailsTopArtist
import com.wposs.mvpapiartistfirebase.R
import com.squareup.picasso.Picasso
import com.wposs.mvpapiartistfirebase.Models.DetailsTrack
import de.hdodenhof.circleimageview.CircleImageView

class RecyclerAdapterArtists (
    private var listArtists : MutableList<DetailsTopArtist>,
    private val listener  : ArtistsListener? = null
): RecyclerView.Adapter<RecyclerAdapterArtists.SongsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsHolder {
        val view   = LayoutInflater.from(App.mContext).inflate(R.layout.card_song, null, false)
        val params = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT)
        view.layoutParams = params

        return SongsHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return listArtists.size
    }

    override fun onBindViewHolder(holder: SongsHolder, position: Int) {
        holder.setDetalleSongs(listArtists[position])
    }

    fun updateData(list: MutableList<DetailsTopArtist>) {
        this.listArtists = list
        notifyDataSetChanged()
    }

    class SongsHolder(
        val view: View,
        val mListener: ArtistsListener?
        ): RecyclerView.ViewHolder(view){

        val cardViewSongs   = view.findViewById<CardView>(R.id.card_song)
        val image           = view.findViewById<CircleImageView>(R.id.civ_card_image)
        val name            = view.findViewById<TextView>(R.id.tv_name_song)
        val duration        = view.findViewById<LinearLayout>(R.id.ll_duration)
        val listeners    = view.findViewById<TextView>(R.id.tv_listeners)
        val nameArtist      = view.findViewById<LinearLayout>(R.id.ll_tracks)
        val rank            = view.findViewById<LinearLayout>(R.id.ll_rank)

        fun setDetalleSongs(artist: DetailsTopArtist){
            if (artist.image?.get(2)?.text != null) convertImageService(artist.image!![2].text)
            if (artist.name != null) name.text = artist.name
            if (artist.listeners != null) listeners?.text = artist.listeners
            duration.visibility = View.GONE
            nameArtist.visibility = View.GONE
            rank.visibility = View.GONE

            cardViewSongs.setOnClickListener {
                mListener?.onPressCardArtists(artist)
            }
        }

        private fun convertImageService(url: String?){
            try {
                Picasso.get()
                    .load(url)
                    .centerCrop()
                    .resize(100, 100)
                    .into(image)
            }catch (e: Exception){

            }
        }
    }

    interface ArtistsListener {
        fun onPressCardArtists(artist: DetailsTopArtist)

    }
}