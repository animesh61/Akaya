package com.example.akaya.ui.model

data class SigninRequest(
        var Email:String?=null,
        var Password:String?=null,
)
data class SignUpRequest(var firstName:String?=null,
                         var lastName:String?=null,
                         var email:String?=null,
                         var password:String?=null,
                         var phone:String?=null,
                         var country:String?=null)

data class ForgotPasswordRequest(var EmailId:String?=null)

data class ChangePasswordRequest(var NewPassword:String?=null,
                                 var ConfirmPassword:String?=null,
                                 var CustomerId:Int=0 )