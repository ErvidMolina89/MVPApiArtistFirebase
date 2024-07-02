package com.wposs.mvpapiartistfirebase.Presentation.Dash.ListSongs.Interfaces

import com.wposs.mvpapiartistfirebase.Models.ListTopArtists
import com.wposs.mvpapiartistfirebase.Models.ListTrack
import com.wposs.mvpapiartistfirebase.Models.MessageResponse
import com.wposs.mvpapiartistfirebase.Utils.DialogueGenerico

interface ISelectServiceView {
    fun responseListTrack(listTracks: ListTrack)
    fun responselistTopArtists(listTopArtists: ListTopArtists)
    fun messageError(message: MessageResponse)
    fun showDialogFragment(title: Int, detail: Int, type: DialogueGenerico.TypeDialogue)
}