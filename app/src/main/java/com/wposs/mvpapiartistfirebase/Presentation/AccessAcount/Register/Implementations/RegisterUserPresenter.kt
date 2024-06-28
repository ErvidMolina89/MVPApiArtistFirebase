package com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Register.Implementations

import android.content.Context
import com.wposs.mvpapiartistfirebase.Models.User
import com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Register.Interfaces.IRegisterUserBL
import com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Register.Interfaces.IRegisterUserListener
import com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Register.Interfaces.IRegisterUserPresenter
import com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Register.Interfaces.IRegisterUserView
import com.wposs.mvpapiartistfirebase.R
import com.wposs.mvpapiartistfirebase.Utils.DialogueGenerico
import com.wposs.mvpapiartistfirebase.Utils.isNetworkAvailable

class RegisterUserPresenter (private val context: Context, view : IRegisterUserView): IRegisterUserPresenter {

    private val registerView : IRegisterUserView = view
    private val bL : IRegisterUserBL
    init {
        bL = RegisterUserBL(Listener())
    }

    override fun registerUser(user: User) {
        if (user.validatePassEqualConfirPass()) return registerView.showDialogFragment(R.string.internet, R.string.not_connection_internet, DialogueGenerico.TypeDialogue.ADVERTENCIA)
        if (!isNetworkAvailable(context)) return registerView.showDialogFragment(R.string.internet, R.string.not_connection_internet, DialogueGenerico.TypeDialogue.ADVERTENCIA)
        if (!user.validateFieldsUser()) return registerView.showDialogFragment(R.string.fields_empty, R.string.details_fields_empty, DialogueGenerico.TypeDialogue.ADVERTENCIA)
        bL.registerUser(user)
    }



    private inner class Listener : IRegisterUserListener {
        override fun responseRegisterUser(user: User) {
            registerView.responseRegisterUser(user)
        }

    }
}