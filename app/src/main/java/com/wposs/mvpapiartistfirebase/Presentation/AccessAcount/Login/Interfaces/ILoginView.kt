package com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Login.Interfaces

import com.wposs.mvpapiartistfirebase.Models.User
import com.wposs.mvpapiartistfirebase.Utils.DialogueGenerico

interface ILoginView {
    fun responseLogin(user: User)
    fun credentialsIncorrect()
    fun showDialogFragment(title: Int, detail: Int, type: DialogueGenerico.TypeDialogue)
}