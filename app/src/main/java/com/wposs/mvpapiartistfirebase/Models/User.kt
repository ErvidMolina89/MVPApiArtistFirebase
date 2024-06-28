package com.wposs.mvpapiartistfirebase.Models

class User : BaseModel() {

    var email : String ?= null
    var password : String ?= null
    var conf_password : String ?= null

    fun validateFieldsUser() : Boolean{
        return !email.isNullOrEmpty() && !password.isNullOrEmpty()
    }
    fun validatePassEqualConfirPass() : Boolean{
        return password != conf_password
    }
}