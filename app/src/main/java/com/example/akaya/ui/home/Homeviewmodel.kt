package com.example.akaya.ui.home

import android.content.Context
import androidx.lifecycle.*
import com.example.akaya.ui.api.ApiHelper
import com.example.akaya.ui.model.BaseWrapperResponse
import com.example.akaya.ui.model.HomeResult
import com.example.akaya.ui.model.SignUpRequest
import com.example.akaya.ui.model.offersList
import com.example.akaya.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Homeviewmodel(private val apiHelper: ApiHelper) : ViewModel(){
    private var homeresultLivedata= MutableLiveData<Resource<HomeResult>>()
    fun requesthomebanner(mContext: Context){
        viewModelScope.launch {
            homeresultLivedata.postValue(Resource.loading(null))
            try {
                val homeresponse = apiHelper.bannerlist()
                homeresultLivedata.postValue(Resource.success(homeresponse))


            }catch (ex:Exception){
                homeresultLivedata.postValue(Resource.error(ex.toString(),null))
            }
        }
    }

    fun requestcategories(mContext: Context){
        viewModelScope.launch {
            homeresultLivedata.postValue(Resource.loading(null))
            try {
                val homeresponse = apiHelper.categorylist()
                homeresultLivedata.postValue(Resource.success(homeresponse))


            }catch (ex:Exception){
                homeresultLivedata.postValue(Resource.error(ex.toString(),null))
            }
        }
    }


    fun home_banner(): LiveData<Resource<HomeResult>> {
        return homeresultLivedata
    }

    fun category(): LiveData<Resource<HomeResult>> {
        return homeresultLivedata
    }



}