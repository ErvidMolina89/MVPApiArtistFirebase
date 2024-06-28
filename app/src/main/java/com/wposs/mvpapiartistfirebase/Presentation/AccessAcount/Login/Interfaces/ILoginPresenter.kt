package com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Login.Interfaces

import com.wposs.mvpapiartistfirebase.Models.User

interface ILoginPresenter {
    fun startSection(user: User)
}