package com.example.akaya.ui.forgotpassword

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.akaya.ui.api.ApiHelper
import com.example.akaya.ui.model.BaseWrapperResponse
import com.example.akaya.ui.model.ChangePasswordRequest
import com.example.akaya.ui.model.ForgotPasswordRequest
import com.example.akaya.utils.Resource
import kotlinx.coroutines.launch

class ChangePasswordviewModel(private val apiHelper: ApiHelper) : ViewModel(){
    private var changepasswordLivedata= MutableLiveData<Resource<BaseWrapperResponse>>()


    fun requestchangepassword(mContext: Context, changePasswordRequest: ChangePasswordRequest){
        viewModelScope.launch {
            changepasswordLivedata.postValue(Resource.loading(null))
            try {
                val changepasswordresponse = apiHelper.changepassword(changePasswordRequest)
                changepasswordLivedata.postValue(Resource.success(changepasswordresponse))


            }catch (ex:Exception){
                changepasswordLivedata.postValue(Resource.error(ex.toString(),null))
            }
        }
    }

    fun changepassword(): LiveData<Resource<BaseWrapperResponse>> {
        return changepasswordLivedata
    }
}