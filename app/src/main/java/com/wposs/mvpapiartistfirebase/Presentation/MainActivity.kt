package com.wposs.mvpapiartistfirebase.Presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.wposs.mvpapiartistfirebase.Base.App
import com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Login.Implementations.LoginFragment
import com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Splash.SplashFragment
import com.wposs.mvpapiartistfirebase.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        App.mContext = this
    }

    override fun onBackPressed() {
        // Obtén el fragmento actual visible
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        val currentFragment: Fragment? = navHostFragment!!.childFragmentManager.primaryNavigationFragment

        if (currentFragment is SplashFragment || currentFragment is LoginFragment) {
            return
        }
        super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(this, R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}