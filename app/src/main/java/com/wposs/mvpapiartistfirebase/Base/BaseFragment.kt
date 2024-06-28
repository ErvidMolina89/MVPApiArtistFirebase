package com.wposs.mvpapiartistfirebase.Base

import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.wposs.mvpapiartistfirebase.R
import com.wposs.mvpapiartistfirebase.Utils.DialogueGenerico
import com.wposs.mvpapiartistfirebase.Utils.showDialogueGenerico

open class BaseFragment: Fragment() {

    @get:JvmName("getCustomView")
    var view: View? = null

    fun dialogueFragment(title: Int,
                                 detail: String,
                                 type: DialogueGenerico.TypeDialogue){
        DialogueGenerico
            .getInstance()
            .withTitle(title)
            .withText(detail)
            .withTypeDialogue(type)
            .withTextBtnAccept(R.string.btn_aceptar)
            .withActionBtnAccept {
                Log.e("","")
            }
        context?.showDialogueGenerico()
    }

}