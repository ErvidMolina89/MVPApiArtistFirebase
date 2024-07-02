package com.wposs.mvpapiartistfirebase.Presentation.Dash.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation.findNavController
import com.wposs.mvpapiartistfirebase.Base.BaseFragment
import com.wposs.mvpapiartistfirebase.R
import com.wposs.mvpapiartistfirebase.Utils.Constants


class HomeFragment : BaseFragment() {

    private var btnTracks: Button? = null
    private var btnArtists: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_home, container, false)
        btnTracks = view?.findViewById(R.id.btn_select_service_tracks)
        btnArtists = view?.findViewById(R.id.btn_select_service_topArtist)
        return view
    }

    override fun onResume() {

        val bundle = Bundle()
        super.onResume()
        btnTracks?.setOnClickListener {
            bundle.putString(Constants.Keys.SERVICE, Constants.Service.TRACKS)
            findNavController(requireView()).navigate(
                R.id.action_homeFragment_to_listServiceFragment,
                bundle
            )
        }
        btnArtists?.setOnClickListener {
            bundle.putString(Constants.Keys.SERVICE, Constants.Service.ARTISTS)
            findNavController(requireView()).navigate(
                R.id.action_homeFragment_to_listServiceFragment,
                bundle
            )
        }
    }

}