package com.wposs.mvpapiartistfirebase.Models

import com.google.gson.annotations.SerializedName

class Image : BaseModel() {
    @SerializedName("#text")
    var text:  String ?= null
    var size:  String ?= null
}