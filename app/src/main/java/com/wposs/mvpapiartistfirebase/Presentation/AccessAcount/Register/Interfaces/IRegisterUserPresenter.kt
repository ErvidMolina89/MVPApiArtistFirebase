package com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Register.Interfaces

import com.wposs.mvpapiartistfirebase.Models.User

interface IRegisterUserPresenter {
    fun registerUser(user: User)
}