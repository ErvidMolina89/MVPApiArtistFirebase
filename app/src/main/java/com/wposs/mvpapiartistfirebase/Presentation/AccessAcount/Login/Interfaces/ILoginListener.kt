package com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Login.Interfaces

import com.wposs.mvpapiartistfirebase.Models.User

interface ILoginListener {
    fun responseLogin(user: User)
    fun credentialsIncorrect()
}