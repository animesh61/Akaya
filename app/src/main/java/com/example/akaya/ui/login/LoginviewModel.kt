package com.example.akaya.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.akaya.ui.api.ApiHelper
import com.example.akaya.ui.model.BaseWrapperResponse
import com.example.akaya.ui.model.SigninRequest
import com.example.akaya.utils.Resource
import kotlinx.coroutines.launch

class LoginviewModel(private val apiHelper: ApiHelper):ViewModel() {
    private var signinLivedata=MutableLiveData<Resource<BaseWrapperResponse>>()


    fun requestsignin(mContext: Context,signinRequest: SigninRequest){
        viewModelScope.launch {
            signinLivedata.postValue(Resource.loading(null))
            try {
                val signinresponse = apiHelper.signin(signinRequest)
                signinLivedata.postValue(Resource.success(signinresponse))


            }catch (ex:Exception){
                signinLivedata.postValue(Resource.error(ex.toString(),null))
            }
        }
    }

    fun signinData(): LiveData<Resource<BaseWrapperResponse>> {
        return signinLivedata
    }
}