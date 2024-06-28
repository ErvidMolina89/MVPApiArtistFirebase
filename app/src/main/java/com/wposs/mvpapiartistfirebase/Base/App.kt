package com.wposs.mvpapiartistfirebase.Base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class App: Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        var mContext: Context?= null
    }
}