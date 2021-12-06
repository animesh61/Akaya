package com.example.akaya.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.akaya.ui.api.ApiHelper
import com.example.akaya.ui.model.HomeResult
import com.example.akaya.utils.Resource
import kotlinx.coroutines.launch

class Homecategoryviewmodel (private val apiHelper: ApiHelper) : ViewModel(){
    private var homeresultLivedata= MutableLiveData<Resource<HomeResult>>()
    fun requestcategory(mContext: Context){
        viewModelScope.launch {
            homeresultLivedata.postValue(Resource.loading(null))
            try {
                val homecategoryresponse = apiHelper.categorylist()
                homeresultLivedata.postValue(Resource.success(homecategoryresponse))


            }catch (ex:Exception){
                homeresultLivedata.postValue(Resource.error(ex.toString(),null))
            }
        }
    }



    fun category(): LiveData<Resource<HomeResult>> {
        return homeresultLivedata
    }

}