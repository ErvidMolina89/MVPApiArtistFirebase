package com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Register.Interfaces

import com.wposs.mvpapiartistfirebase.Models.User

interface IRegisterUserListener {
    fun responseRegisterUser(user: User)
}