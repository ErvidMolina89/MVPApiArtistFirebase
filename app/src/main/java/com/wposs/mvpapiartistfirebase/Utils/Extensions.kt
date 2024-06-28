package com.wposs.mvpapiartistfirebase.Utils

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception


fun Context.showDialogueGenerico(){
    if(this !is AppCompatActivity){ return }
    DialogueGenerico.getInstance().showDialogue(supportFragmentManager,"DialogoGenerico")
}

fun isNetworkAvailable(context: Context): Boolean {
    try {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    } catch (e: Exception) {
        return false
    }

}