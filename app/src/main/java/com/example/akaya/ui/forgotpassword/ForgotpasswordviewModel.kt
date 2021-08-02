package com.example.akaya.ui.forgotpassword

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.akaya.ui.api.ApiHelper
import com.example.akaya.ui.model.BaseWrapperResponse
import com.example.akaya.ui.model.ForgotPasswordRequest
import com.example.akaya.ui.model.SignUpRequest
import com.example.akaya.utils.Resource
import kotlinx.coroutines.launch

class ForgotpasswordviewModel(private val apiHelper: ApiHelper) :ViewModel(){
    private var forgotpasswordLivedata= MutableLiveData<Resource<BaseWrapperResponse>>()


    fun requestforgotpassword(mContext: Context, forgotPasswordRequest: ForgotPasswordRequest){
        viewModelScope.launch {
            forgotpasswordLivedata.postValue(Resource.loading(null))
            try {
                val forgotpasswordResponse = apiHelper.forgotpassword(forgotPasswordRequest)
                forgotpasswordLivedata.postValue(Resource.success(forgotpasswordResponse))


            }catch (ex:Exception){
                forgotpasswordLivedata.postValue(Resource.error(ex.toString(),null))
            }
        }
    }

    fun forgotpassword(): LiveData<Resource<BaseWrapperResponse>> {
        return forgotpasswordLivedata
    }

}