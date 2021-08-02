package com.example.akaya.ui.api

import com.example.akaya.ui.model.*


interface ApiHelper {
    suspend fun signin(msigninRequest: SigninRequest): BaseWrapperResponse
    suspend fun signup(msignupRequest:SignUpRequest): BaseWrapperResponse
    suspend fun forgotpassword(mForgotPasswordRequest: ForgotPasswordRequest):BaseWrapperResponse
    suspend fun changepassword(mchangePasswordRequest: ChangePasswordRequest):BaseWrapperResponse


}