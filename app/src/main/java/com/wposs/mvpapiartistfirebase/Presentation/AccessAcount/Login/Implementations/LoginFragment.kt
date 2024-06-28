package com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Login.Implementations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.Navigation
import com.wposs.mvpapiartistfirebase.Base.App
import com.wposs.mvpapiartistfirebase.Base.BaseFragment
import com.wposs.mvpapiartistfirebase.Models.User
import com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Login.Interfaces.ILoginPresenter
import com.wposs.mvpapiartistfirebase.Presentation.AccessAcount.Login.Interfaces.ILoginView
import com.wposs.mvpapiartistfirebase.R
import com.wposs.mvpapiartistfirebase.Utils.DialogueGenerico

class LoginFragment : BaseFragment() {

    private var btnSeccion: Button? = null
    private var btnTextRegister: TextView? = null
    private var editEmail: EditText? = null
    private var editPass: EditText? = null
    private var presenter: ILoginPresenter? = null
    private var actionPresenter = actionListenerViewPresenter()
    private var user : User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_login, container, false)
        btnSeccion = view?.findViewById(R.id.btn_start_section)
        btnTextRegister = view?.findViewById(R.id.tv_register_user)
        editEmail = view?.findViewById(R.id.et_email_login)
        editPass = view?.findViewById(R.id.et_Pass_Login)

        presenter = LoginPresenter(App.mContext!!, actionPresenter)
        return view
    }

    override fun onResume() {
        super.onResume()
        btnSeccion?.setOnClickListener {
            user = User()
            user?.email         = editEmail?.text.toString()
            user?.password      = editPass?.text.toString()
            presenter?.startSection(user!!)
        }
        btnTextRegister?.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private inner class actionListenerViewPresenter: ILoginView {

        override fun responseLogin(user: User) {
            Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_homeFragment)
        }

        override fun credentialsIncorrect() {
            dialogueFragment(R.string.credentials_incorrect,
                R.string.details_credentials_incorrect,
                DialogueGenerico.TypeDialogue.ADVERTENCIA)
        }

        override fun showDialogFragment(title: Int, detail: Int, type: DialogueGenerico.TypeDialogue) {
            dialogueFragment(title, detail, type)
        }

    }
}