package com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Login.Implementations

import android.content.Context
import com.wposs.mvpapiartistfirebase.Models.User
import com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Login.Interfaces.ILoginBL
import com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Login.Interfaces.ILoginListener
import com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Login.Interfaces.ILoginPresenter
import com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Login.Interfaces.ILoginView
import com.wposs.mvpapiartistfirebase.R
import com.wposs.mvpapiartistfirebase.Utils.DialogueGenerico
import com.wposs.mvpapiartistfirebase.Utils.isNetworkAvailable

class LoginPresenter (private val context: Context, view : ILoginView): ILoginPresenter {

    private val loginView : ILoginView = view
    private val loginBL : ILoginBL
    init {
        loginBL = LoginBL(context,Listener())
    }

    override fun startSection(user: User) {
        if (!isNetworkAvailable(context)) return loginView.showDialogFragment(R.string.internet, R.string.not_connection_internet, DialogueGenerico.TypeDialogue.ADVERTENCIA)
        if (!user.validateFieldsUser()) return loginView.showDialogFragment(R.string.fields_empty, R.string.details_fields_empty, DialogueGenerico.TypeDialogue.ADVERTENCIA)
        loginBL.startSection(user)
    }



    private inner class Listener : ILoginListener{
        override fun credentialsIncorrect() {
            loginView.credentialsIncorrect()
        }

        override fun responseLogin(user: User) {
            loginView.responseLogin(user)
        }

    }
}