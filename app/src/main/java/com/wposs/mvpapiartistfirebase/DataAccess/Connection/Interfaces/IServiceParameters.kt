package com.wposs.mvpapiartistfirebase.DataAccess.Connection.Interfaces

interface IServiceParameters {

    enum class Methods{
        GET,
        POST,
        PUT,
        DELETE;
    }

    fun getURL() : String
    fun getMethods() : Methods
}