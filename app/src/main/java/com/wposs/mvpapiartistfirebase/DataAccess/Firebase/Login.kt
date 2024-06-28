package com.wposs.mvpapiartistfirebase.DataAccess.Firebase

import android.util.Log
import com.wposs.mvpapiartistfirebase.Models.User
import com.google.firebase.auth.FirebaseAuth
import com.wposs.mvpapiartistfirebase.Utils.Constants

class Login (private val messageSuccess: () -> Unit,
             private val failure: () -> Unit
) {

    fun loginWithUser(user : User){
        val instanceFirebase = FirebaseAuth.getInstance()
        instanceFirebase
            .signInWithEmailAndPassword(user.email!!,user.password!!)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    messageSuccess.invoke()
                    return@addOnCompleteListener
                }
                Log.e(Constants.Tag.LOGIN, "Firebase Login", it.exception)
                failure.invoke()
            }
    }

    fun registerUser(user: User){
        val instanceFirebase = FirebaseAuth.getInstance()

        instanceFirebase
            .createUserWithEmailAndPassword(user.email!!,user.password!!)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    messageSuccess.invoke()
                    return@addOnCompleteListener
                }
                Log.e(Constants.Tag.REGISTER, "Firebase Regsiter", it.exception)
                failure.invoke()
            }
    }

}