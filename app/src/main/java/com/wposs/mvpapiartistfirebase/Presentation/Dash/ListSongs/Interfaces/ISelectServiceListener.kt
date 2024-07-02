package com.wposs.mvpapiartistfirebase.Presentation.Dash.ListSongs.Interfaces

import com.wposs.mvpapiartistfirebase.Models.ListTopArtists
import com.wposs.mvpapiartistfirebase.Models.ListTrack
import com.wposs.mvpapiartistfirebase.Models.MessageResponse

interface ISelectServiceListener {
    fun responseListTrack(listTracks: ListTrack)
    fun responseListTopArtists(listTopArtists: ListTopArtists)
    fun messageError(message: MessageResponse)
}