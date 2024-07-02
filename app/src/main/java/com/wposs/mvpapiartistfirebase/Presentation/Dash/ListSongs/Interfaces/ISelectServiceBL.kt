package com.wposs.mvpapiartistfirebase.Presentation.Dash.ListSongs.Interfaces

import com.wposs.mvpapiartistfirebase.DataAccess.Connection.Interfaces.IRetrofitParcelable
import com.wposs.mvpapiartistfirebase.DataAccess.Connection.Services
import com.wposs.mvpapiartistfirebase.Models.BaseModel

interface ISelectServiceBL {
    fun callService(objectResponse: BaseModel,
                    objectSend: IRetrofitParcelable,
                    service: Services,
                    page: Int)
}