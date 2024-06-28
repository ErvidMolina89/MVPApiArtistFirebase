package com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Register.Implementations

import android.content.Context
import com.wposs.mvpapiartistfirebase.DataAccess.Connection.Services
import com.wposs.mvpapiartistfirebase.DataAccess.Repositories.IRepository
import com.wposs.mvpapiartistfirebase.DataAccess.Repositories.RepoLogin
import com.wposs.mvpapiartistfirebase.Models.MessageResponse
import com.wposs.mvpapiartistfirebase.Models.User
import com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Register.Interfaces.IRegisterUserBL
import com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Register.Interfaces.IRegisterUserListener

class RegisterUserBL (registerUserListener : IRegisterUserListener) :
    IRegisterUserBL {

    private val listener : IRegisterUserListener = registerUserListener

    override fun registerUser(user: User) {
        RepoLogin().RegisterUser(user, ListenerRepositories(), Services.registerUser)
    }

    private inner class ListenerRepositories : IRepository{

        override fun onSuccessResponse(objectResponse: Any?, services: Services) {
            when(services){
                Services.registerUser  -> {
                    listener.responseRegisterUser(objectResponse as User)
                }
                else -> {  }
            }
        }

        override fun onFailedResponse(response: MessageResponse, services: Services) {
            when(services){
                Services.registerUser  -> {  }
                else -> {  }
            }
        }

    }



}