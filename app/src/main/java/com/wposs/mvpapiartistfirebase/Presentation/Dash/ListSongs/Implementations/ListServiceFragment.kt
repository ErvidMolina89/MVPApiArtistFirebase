package com.wposs.mvpapiartistfirebase.Presentation.Dash.ListSongs.Implementations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wposs.mvpapiartistfirebase.Base.App
import com.wposs.mvpapiartistfirebase.Base.BaseFragment
import com.wposs.mvpapiartistfirebase.DataAccess.Connection.Interfaces.IRetrofitParcelable
import com.wposs.mvpapiartistfirebase.DataAccess.Connection.Services
import com.wposs.mvpapiartistfirebase.Models.BaseModel
import com.wposs.mvpapiartistfirebase.Models.DetailsTopArtist
import com.wposs.mvpapiartistfirebase.Models.DetailsTrack
import com.wposs.mvpapiartistfirebase.Models.ListTopArtists
import com.wposs.mvpapiartistfirebase.Models.ListTrack
import com.wposs.mvpapiartistfirebase.Models.MessageResponse
import com.wposs.mvpapiartistfirebase.Presentation.Dash.ListSongs.Adapters.RecyclerAdapterArtists
import com.wposs.mvpapiartistfirebase.Presentation.Dash.ListSongs.Adapters.RecyclerAdapterSongs
import com.wposs.mvpapiartistfirebase.Presentation.Dash.ListSongs.Interfaces.ISelectServicePresenter
import com.wposs.mvpapiartistfirebase.Presentation.Dash.ListSongs.Interfaces.ISelectServiceView
import com.wposs.mvpapiartistfirebase.R
import com.wposs.mvpapiartistfirebase.Utils.Constants
import com.wposs.mvpapiartistfirebase.Utils.DialogueGenerico

class ListServiceFragment : BaseFragment() {
    private var service: String? = null
    private var presenter: ISelectServicePresenter? = null
    private var actionPresenter = actionViewPresenter()
    private var rv: RecyclerView? = null
    private var title: TextView? = null
    private var adapterSons: RecyclerAdapterSongs? = null
    private var adapterArtist: RecyclerAdapterArtists? = null
    private var currentPage: Int = 1
    private var isLoading = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_list_service, container, false)
        title = view?.findViewById(R.id.tv_title_song)
        rv = view?.findViewById(R.id.rv_list_songs)
        presenter = ListServicePresenter(App.mContext!!, actionPresenter)
        if (arguments != null) {
            val arg = arguments?.getString(Constants.Keys.SERVICE)
            if (arg != null) {
                service = arg
            }
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        callServiceType()

        rv?.addOnScrollListener( object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && firstVisibleItemPosition + visibleItemCount >= totalItemCount) {
                    callServiceType()
                    isLoading = true
                }
            }
        })
    }

    private fun callServiceType() {
        when(service){
            Constants.Service.TRACKS -> {
                callService(ListTrack(), object: IRetrofitParcelable{}, Services.TRACKS)
                title?.text = getText(R.string.title_tracks)
                adapterSons = RecyclerAdapterSongs(
                    ArrayList<DetailsTrack>(),
                    onActionRecyclerSongs()
                )
                rv?.adapter = null
                rv?.setHasFixedSize(true)
                rv?.layoutManager = LinearLayoutManager(App.mContext)
                rv?.adapter = adapterSons
            }
            Constants.Service.ARTISTS -> {
                callService(ListTopArtists(), object: IRetrofitParcelable{}, Services.ARTISTS)
                title?.text = getText(R.string.title_artists)
                adapterArtist = RecyclerAdapterArtists(
                    ArrayList<DetailsTopArtist>(),
                    onActionRecyclerArtists()
                )
                rv?.adapter = null
                rv?.setHasFixedSize(true)
                rv?.layoutManager = LinearLayoutManager(App.mContext)
                rv?.adapter = adapterArtist
            }
        }
        currentPage++
    }

    private fun callService(objectResponse: BaseModel,
                            objectSend: IRetrofitParcelable,
                            service: Services
    ){
        presenter?.callService(objectResponse, objectSend, service, currentPage)
    }


    private inner class actionViewPresenter: ISelectServiceView {
        override fun responseListTrack(listTracks: ListTrack) {
            adapterSons?.updateData(listTracks.tracks?.track!!)
            isLoading = false
        }

        override fun responselistTopArtists(listTopArtists: ListTopArtists) {
            adapterArtist?.updateData(listTopArtists.topartists?.artist!!)
            isLoading = false
        }

        override fun messageError(message: MessageResponse) {
            message.Message?.let {
                dialogueFragment(R.string.error_servicio,
                    it, DialogueGenerico.TypeDialogue.ERROR)
            }
            isLoading = false
        }

        override fun showDialogFragment(
            title: Int,
            detail: Int,
            type: DialogueGenerico.TypeDialogue
        ) {
            dialogueFragment(title, getString(detail), type)
            isLoading = false
        }

    }
    private inner class onActionRecyclerSongs: RecyclerAdapterSongs.SonsListener {
        override fun onPressCardSong(song: DetailsTrack) {

        }
    }

    private inner class onActionRecyclerArtists: RecyclerAdapterArtists.ArtistsListener {
        override fun onPressCardArtists(song: DetailsTopArtist) {

        }
    }
}