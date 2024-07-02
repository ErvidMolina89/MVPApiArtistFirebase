package com.wposs.mvpapiartistfirebase.DataAccess.Repositories

import android.content.Context
import com.wposs.mvpapiartistfirebase.DataAccess.Connection.Interfaces.IRetrofitParcelable
import com.wposs.mvpapiartistfirebase.DataAccess.Connection.Handler.RetrofitProxy
import com.wposs.mvpapiartistfirebase.DataAccess.Connection.Services
import com.wposs.mvpapiartistfirebase.Models.*

class RepoService (private val context: Context) {

    fun < T: IRepository>callService(objectResponse: BaseModel,
                                     objectSend: IRetrofitParcelable,
                                     service : Services,
                                     responder : T,
                                     page: Int,
                                     limit: Int = 50){
        RetrofitProxy()
            .withContext(context)
            .withObjectToSend(objectSend)
            .withExpectedObjeto(objectResponse::class.java)
            .withListenerListObjetcs {
                responder.onSuccessResponse(it, service)
            }
            .withListenerObjetc {
                responder.onSuccessResponse(it, service)
            }
            .listenerFailure()
            .withAnswerOfFailure { titulo, mensaje ->
                val message = MessageResponse()
                message.Code = titulo
                message.Message = mensaje.toString()
                responder.onFailedResponse(message, service)
            }
            .withService(service)
            .withPagination(page, limit)
            .startQuery()
    }
}