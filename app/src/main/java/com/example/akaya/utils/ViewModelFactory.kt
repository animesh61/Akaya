package com.example.akaya.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.akaya.ui.api.ApiHelper
import com.example.akaya.ui.forgotpassword.ChangePasswordviewModel
import com.example.akaya.ui.forgotpassword.ForgotpasswordviewModel
import com.example.akaya.ui.home.Homeviewmodel
import com.example.akaya.ui.login.LoginviewModel
import com.example.akaya.ui.register.SignUpViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginviewModel::class.java)) {
            return LoginviewModel(apiHelper) as T
        }
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)){
            return SignUpViewModel(apiHelper)as T
        }
        if(modelClass.isAssignableFrom(ForgotpasswordviewModel::class.java)){
            return ForgotpasswordviewModel(apiHelper)as T
        }
        if(modelClass.isAssignableFrom(ChangePasswordviewModel::class.java)){
            return ChangePasswordviewModel(apiHelper)as T
        }
        if(modelClass.isAssignableFrom(Homeviewmodel::class.java)){
            return Homeviewmodel(apiHelper)as T
        }

        /*

        if (modelClass.isAssignableFrom(ParallelNetworkCallsViewModel::class.java)) {
            return ParallelNetworkCallsViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(RoomDBViewModel::class.java)) {
            return RoomDBViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(TimeoutViewModel::class.java)) {
            return TimeoutViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(TryCatchViewModel::class.java)) {
            return TryCatchViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(ExceptionHandlerViewModel::class.java)) {
            return ExceptionHandlerViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(LongRunningTaskViewModel::class.java)) {
            return LongRunningTaskViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(TwoLongRunningTasksViewModel::class.java)) {
            return TwoLongRunningTasksViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(IgnoreErrorAndContinueViewModel::class.java)) {
            return IgnoreErrorAndContinueViewModel(apiHelper, dbHelper) as T
        }*/
        throw IllegalArgumentException("Unknown class name")
    }

}