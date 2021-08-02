package com.example.akaya.ui.model

import com.google.gson.JsonObject

class BaseWrapperResponse {
    var status: Status? = null
    var response: JsonObject? = null
    var otp:String?=null
    var customerId:Int=0
}

class Status {
    var error_code: Int = 0
    var message: String? = null
}
