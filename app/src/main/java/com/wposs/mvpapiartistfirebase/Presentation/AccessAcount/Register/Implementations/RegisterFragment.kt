package com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Register.Implementations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import com.wposs.mvpapiartistfirebase.Base.App
import com.wposs.mvpapiartistfirebase.Base.BaseFragment
import com.wposs.mvpapiartistfirebase.Models.MessageResponse
import com.wposs.mvpapiartistfirebase.Models.User
import com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Register.Interfaces.IRegisterUserPresenter
import com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Register.Interfaces.IRegisterUserView
import com.wposs.mvpapiartistfirebase.R
import com.wposs.mvpapiartistfirebase.Utils.DialogueGenerico

class RegisterFragment : BaseFragment() {
    private var presenter: IRegisterUserPresenter? = null
    private var actionPresenter = actionViewPresenter()
    private var editEmail: EditText? = null
    private var editPass: EditText? = null
    private var editConfirPass: EditText? = null
    private var btnCreate: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_register, container, false)
        editEmail = view?.findViewById(R.id.et_email_register)
        editPass = view?.findViewById(R.id.et_pass_register)
        editConfirPass = view?.findViewById(R.id.et_conf_pass_register)
        btnCreate = view?.findViewById(R.id.btnCrear_register)
        presenter = RegisterUserPresenter(App.mContext!!, actionPresenter)
        return view
    }

    override fun onResume() {
        super.onResume()
        btnCreate?.setOnClickListener {
            val user = User()
            user.email = editEmail?.text.toString()
            user.password = editPass?.text.toString()
            user.conf_password = editConfirPass?.text.toString()
            presenter?.registerUser(user)
        }
    }

    private inner class actionViewPresenter: IRegisterUserView {
        override fun responseRegisterUser(user: User) {
            Toast.makeText(App.mContext, getString(R.string.user_create), Toast.LENGTH_LONG).show()
            Navigation.findNavController(requireView()).navigateUp()
        }

        override fun messageError(message: MessageResponse) {
            message.Message?.let {
                dialogueFragment(R.string.Error,
                    it,
                    DialogueGenerico.TypeDialogue.ERROR)
            }
        }

        override fun showDialogFragment(title: Int, detail: Int, type: DialogueGenerico.TypeDialogue) {
            dialogueFragment(title, getString(detail), type)
        }
    }
}