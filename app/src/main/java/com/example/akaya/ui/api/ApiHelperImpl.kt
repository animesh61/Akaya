package com.example.akaya.ui.api

import com.example.akaya.ui.model.*

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun signin(msigninRequest: SigninRequest): BaseWrapperResponse=
        apiService.signin(msigninRequest)

    override suspend fun signup(msignupRequest: SignUpRequest): BaseWrapperResponse =
        apiService.signup(msignupRequest)

    override suspend fun forgotpassword(mForgotPasswordRequest: ForgotPasswordRequest): BaseWrapperResponse =
      apiService.forgotpassword(mForgotPasswordRequest)

    override suspend fun changepassword(mchangePasswordRequest: ChangePasswordRequest): BaseWrapperResponse =
    apiService.changepassword(mchangePasswordRequest)




}