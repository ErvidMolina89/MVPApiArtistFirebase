package com.wposs.mvpapiartistfirebase.Models


class MessageResponse() : BaseModel() {
    var Code: Int? = null
    var Message: String? = null

    constructor(code: Int, message: String?) : this() {
        this.Code = code
        this.Message = message
    }

}