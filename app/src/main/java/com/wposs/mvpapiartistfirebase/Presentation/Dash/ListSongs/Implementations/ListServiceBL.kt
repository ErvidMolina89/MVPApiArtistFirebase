package com.wposs.mvpapiartistfirebase.Presentation.Dash.ListSongs.Implementations

import android.content.Context
import com.wposs.mvpapiartistfirebase.DataAccess.Connection.Interfaces.IRetrofitParcelable
import com.wposs.mvpapiartistfirebase.DataAccess.Connection.Services
import com.wposs.mvpapiartistfirebase.DataAccess.Repositories.IRepository
import com.wposs.mvpapiartistfirebase.DataAccess.Repositories.RepoService
import com.wposs.mvpapiartistfirebase.Models.BaseModel
import com.wposs.mvpapiartistfirebase.Models.ListTopArtists
import com.wposs.mvpapiartistfirebase.Models.ListTrack
import com.wposs.mvpapiartistfirebase.Models.MessageResponse
import com.wposs.mvpapiartistfirebase.Presentation.Dash.ListSongs.Interfaces.ISelectServiceBL
import com.wposs.mvpapiartistfirebase.Presentation.Dash.ListSongs.Interfaces.ISelectServiceListener

class ListServiceBL (private val context: Context, serviceListener : ISelectServiceListener) :
    ISelectServiceBL {

    private val listener : ISelectServiceListener = serviceListener

    override fun callService(
        objectResponse: BaseModel,
        objectSend: IRetrofitParcelable,
        service: Services,
        page: Int
    ) {
        RepoService(context).callService(objectResponse, objectSend, service, ListenerRepositories(), page)
    }

    private inner class ListenerRepositories : IRepository{

        override fun onSuccessResponse(objectResponse: Any?, services: Services) {
            when(services){
                Services.TRACKS  -> {
                    listener.responseListTrack(objectResponse as ListTrack)
                }
                Services.ARTISTS  -> {
                    listener.responseListTopArtists(objectResponse as ListTopArtists)
                }
                else -> {  }
            }
        }

        override fun onFailedResponse(response: MessageResponse, services: Services) {
            when(services){
                Services.TRACKS   -> { listener.messageError(response) }
                Services.ARTISTS   -> { listener.messageError(response)  }
                else -> {  }
            }
        }

    }



}