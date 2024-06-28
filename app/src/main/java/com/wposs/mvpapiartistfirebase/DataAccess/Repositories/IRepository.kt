package com.wposs.mvpapiartistfirebase.DataAccess.Repositories

import com.wposs.mvpapiartistfirebase.DataAccess.Connection.Services
import com.wposs.mvpapiartistfirebase.Models.MessageResponse

interface IRepository {
    fun onSuccessResponse(objectResponse: Any?, services: Services)
    fun onFailedResponse(response: MessageResponse, services: Services)
}