package com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Register.Interfaces

import com.wposs.mvpapiartistfirebase.Models.MessageResponse
import com.wposs.mvpapiartistfirebase.Models.User
import com.wposs.mvpapiartistfirebase.Utils.DialogueGenerico

interface IRegisterUserView {
    fun responseRegisterUser(user: User)
    fun messageError(message: MessageResponse)
    fun showDialogFragment(title: Int, detail: Int, type: DialogueGenerico.TypeDialogue)
}