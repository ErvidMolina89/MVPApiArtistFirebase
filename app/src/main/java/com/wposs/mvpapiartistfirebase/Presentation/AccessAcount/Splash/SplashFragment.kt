package com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import com.wposs.mvpapiartistfirebase.Base.BaseFragment
import com.wposs.mvpapiartistfirebase.R


class SplashFragment: BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_splash, container, false)
        Handler().postDelayed(
            Runnable {
                findNavController(requireView()).navigate(R.id.action_splashFragment_to_loginFragment) },
            4000
        )
        return view
    }

}