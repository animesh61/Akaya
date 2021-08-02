package com.example.akaya.ui.register

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.akaya.ui.api.ApiHelper
import com.example.akaya.ui.model.BaseWrapperResponse
import com.example.akaya.ui.model.SignUpRequest
import com.example.akaya.utils.Resource
import kotlinx.coroutines.launch

class SignUpViewModel(private val apiHelper: ApiHelper): ViewModel() {
    private var signupLivadata= MutableLiveData<Resource<BaseWrapperResponse>>()


    fun requestsignup(mContext: Context, signUpRequest:SignUpRequest){
        viewModelScope.launch {
            signupLivadata.postValue(Resource.loading(null))
            try {
                val signupResponse = apiHelper.signup(signUpRequest)
                signupLivadata.postValue(Resource.success(signupResponse))


            }catch (ex:Exception){
                signupLivadata.postValue(Resource.error(ex.toString(),null))
            }
        }
    }

    fun signup(): LiveData<Resource<BaseWrapperResponse>> {
        return signupLivadata
    }
}