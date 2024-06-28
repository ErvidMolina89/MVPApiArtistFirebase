package com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Login.Implementations

import android.content.Context
import com.wposs.mvpapiartistfirebase.DataAccess.Connection.Services
import com.wposs.mvpapiartistfirebase.DataAccess.Persistence.PreferencesManager
import com.wposs.mvpapiartistfirebase.DataAccess.Repositories.IRepository
import com.wposs.mvpapiartistfirebase.DataAccess.Repositories.RepoLogin
//import com.wposs.mvpapiartistfirebase.DataAccess.Repositories.RepoLogin
import com.wposs.mvpapiartistfirebase.Models.MessageResponse
import com.wposs.mvpapiartistfirebase.Models.User
import com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Login.Interfaces.ILoginBL
import com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Login.Interfaces.ILoginListener

class LoginBL (private val context: Context, loginListener : ILoginListener) : ILoginBL {

    private val listener : ILoginListener = loginListener

    override fun startSection(user: User) {
        RepoLogin().LogIn(user, ListenerRepositories(), Services.LogIn)
    }

    private inner class ListenerRepositories : IRepository{

        override fun onSuccessResponse(objectResponse: Any?, services: Services) {
            when(services){
                Services.LogIn  -> {
                    listener.responseLogin(objectResponse as User)
                    PreferencesManager.setUser(objectResponse, context)
                }
                else -> {  }
            }
        }

        override fun onFailedResponse(response: MessageResponse, services: Services) {
            when(services){
                Services.LogIn  -> {
                    listener.credentialsIncorrect()
                }
                else -> {  }
            }
        }

    }



}