package com.wposs.mvpapiartistfirebase.Presentation.Dash.Home.Implementations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wposs.mvpapiartistfirebase.Base.BaseFragment
import com.wposs.mvpapiartistfirebase.R

class HomeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

}