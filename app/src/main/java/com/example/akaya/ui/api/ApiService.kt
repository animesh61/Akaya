package com.example.akaya.ui.api
import com.example.akaya.ui.model.*
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("Authentication/CustomerLogin")
    suspend fun signin(@Body msigninRequest:SigninRequest):BaseWrapperResponse
    @POST("Authentication/CustomerRegister")
    suspend fun signup(@Body mSignUpRequest:SignUpRequest):BaseWrapperResponse
    @POST("Customer/ForgotPassword")
    suspend fun forgotpassword(@Body mForgotPasswordRequest:ForgotPasswordRequest):BaseWrapperResponse
    @POST("Customer/ChangePassword")
    suspend fun changepassword(@Body mchangePasswordRequest:ChangePasswordRequest):BaseWrapperResponse


}