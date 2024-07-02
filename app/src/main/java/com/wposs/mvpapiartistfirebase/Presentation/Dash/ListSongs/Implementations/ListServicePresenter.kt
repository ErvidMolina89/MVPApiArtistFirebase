package com.wposs.mvpapiartistfirebase.Presentation.Dash.ListSongs.Implementations

import android.content.Context
import com.wposs.mvpapiartistfirebase.DataAccess.Connection.Interfaces.IRetrofitParcelable
import com.wposs.mvpapiartistfirebase.DataAccess.Connection.Services
import com.wposs.mvpapiartistfirebase.Models.BaseModel
import com.wposs.mvpapiartistfirebase.Models.ListTopArtists
import com.wposs.mvpapiartistfirebase.Models.ListTrack
import com.wposs.mvpapiartistfirebase.Models.MessageResponse
import com.wposs.mvpapiartistfirebase.Presentation.Dash.ListSongs.Interfaces.ISelectServiceBL
import com.wposs.mvpapiartistfirebase.Presentation.Dash.ListSongs.Interfaces.ISelectServiceListener
import com.wposs.mvpapiartistfirebase.Presentation.Dash.ListSongs.Interfaces.ISelectServicePresenter
import com.wposs.mvpapiartistfirebase.Presentation.Dash.ListSongs.Interfaces.ISelectServiceView
import com.wposs.mvpapiartistfirebase.R
import com.wposs.mvpapiartistfirebase.Utils.DialogueGenerico
import com.wposs.mvpapiartistfirebase.Utils.isNetworkAvailable

class ListServicePresenter (private val context: Context, view : ISelectServiceView):
    ISelectServicePresenter {

    private val selectServiceView : ISelectServiceView = view
    private val bl : ISelectServiceBL
    init {
        bl = ListServiceBL(context,Listener())
    }

    override fun callService(
        objectResponse: BaseModel,
        objectSend: IRetrofitParcelable,
        service: Services,
        page: Int
    ) {
        if (!isNetworkAvailable(context)) return selectServiceView.showDialogFragment(R.string.internet, R.string.not_connection_internet, DialogueGenerico.TypeDialogue.ADVERTENCIA)
        bl.callService(objectResponse, objectSend, service, page)
    }



    private inner class Listener : ISelectServiceListener {
        override fun responseListTrack(listTracks: ListTrack) {
            selectServiceView.responseListTrack(listTracks)
        }

        override fun responseListTopArtists(listTopArtists: ListTopArtists) {
            selectServiceView.responselistTopArtists(listTopArtists)
        }

        override fun messageError(message: MessageResponse) {
            selectServiceView.messageError(message)
        }

    }
}