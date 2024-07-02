package com.wposs.mvpapiartistfirebase.DataAccess.Connection.Interfaces

import retrofit2.Call
import retrofit2.http.*

/**
 * url dinamica
 * https://stackoverflow.com/questions/32559333/retrofit-2-dynamic-url
 */
interface IGenericServices {

    @GET
    fun getGeneric(@Url url: String, @Query("page") page: Int, @Query("limit") limit: Int) : Call<Any>;

    @POST
    fun postGeneric(@Url url: String) : Call<Any>;

    @PUT
    fun putGeneric(@Url url: String) : Call<Any>;

    @DELETE
    fun deleteGeneric(@Url url: String) : Call<Any>;
}