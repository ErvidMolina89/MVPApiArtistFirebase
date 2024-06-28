package com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Register.Interfaces

import com.wposs.mvpapiartistfirebase.Models.User

interface IRegisterUserBL {
    fun registerUser(user: User)

}