package com.wposs.mvpapiartistfirebase.DataAccess.Connection

import com.wposs.mvpapiartistfirebase.DataAccess.Connection.Interfaces.IServiceParameters

enum class Services (
    private val url: String,
    private val method: IServiceParameters.Methods
) : IServiceParameters {

    TRACKS("?method=geo.gettoptracks",IServiceParameters.Methods.GET),
    ARTISTS("?method=geo.gettopartists",IServiceParameters.Methods.GET),
    LogIn("",IServiceParameters.Methods.GET),
    REGISTER_USER("",IServiceParameters.Methods.GET),
    ;

    override fun getURL(): String {
        return url
    }

    override fun getMethods(): IServiceParameters.Methods {
        return method
    }
}