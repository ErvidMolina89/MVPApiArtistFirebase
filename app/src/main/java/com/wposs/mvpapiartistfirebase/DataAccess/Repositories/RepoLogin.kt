package com.wposs.mvpapiartistfirebase.DataAccess.Repositories

import com.wposs.mvpapiartistfirebase.DataAccess.Firebase.Login
import com.wposs.mvpapiartistfirebase.Models.MessageResponse
import com.wposs.mvpapiartistfirebase.Models.User
import com.wposs.mvpapiartistfirebase.DataAccess.Connection.Services

class RepoLogin () {

    fun < T: IRepository>LogIn(user : User, responder : T, servicio : Services){
        Login({
            responder.onSuccessResponse(user, servicio)
        },{
            responder.onFailedResponse(MessageResponse(), servicio)
        }).loginWithUser(user)
    }

    fun < T: IRepository>RegisterUser(user : User, responder : T, servicio : Services){
        Login({
            responder.onSuccessResponse(user, servicio)
        },{
            responder.onFailedResponse(MessageResponse(), servicio)
        }).registerUser(user)
    }
}