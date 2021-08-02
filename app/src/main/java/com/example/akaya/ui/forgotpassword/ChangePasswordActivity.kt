package com.example.akaya.ui.forgotpassword

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.akaya.R
import com.example.akaya.ui.api.ApiHelperImpl
import com.example.akaya.ui.api.RetrofitBuilder
import com.example.akaya.ui.dialog.CustomLoaderDialog
import com.example.akaya.ui.login.LoginActivity
import com.example.akaya.ui.model.ChangePasswordRequest
import com.example.akaya.ui.model.ForgotPasswordRequest
import com.example.akaya.utils.AndroidUtility
import com.example.akaya.utils.Prefs
import com.example.akaya.utils.Status
import com.example.akaya.utils.ViewModelFactory

class ChangePasswordActivity : AppCompatActivity() {
    private lateinit var et_password: EditText
    private lateinit var et_confirmpassword: EditText
    private lateinit var viewModel: ChangePasswordviewModel
    lateinit var mCustomLoaderDialog: CustomLoaderDialog
    lateinit var cv_reset:CardView
     var customerid_value:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_password)
        et_password = findViewById(R.id.et_password)
        et_confirmpassword = findViewById(R.id.et_confirmpassword)
        cv_reset=findViewById(R.id.cv_reset)
       // customerid_value=intent.getStringExtra("customerid")
        customerid_value = Prefs.with(this).read("customerid")

        Log.e("customer_idvalue",customerid_value.toString())

        mCustomLoaderDialog = CustomLoaderDialog(this)

        setUpViewModel()
        setupObserver()
        cv_reset.setOnClickListener{
            changePasswordApi()
        }

    }

    fun showLoader() {
        mCustomLoaderDialog.show()
    }

    fun hideLoader() {
        if (mCustomLoaderDialog.isShowing)
            mCustomLoaderDialog.cancel()
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService)
            )
        ).get(ChangePasswordviewModel::class.java)
    }

    private fun setupObserver() {
        viewModel.changepassword().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    hideLoader()
                    val baseResponse = it.data
                    val errorCode = baseResponse?.status?.error_code
                    when {
                        (errorCode == 0) -> {
                            AndroidUtility.showToast(this, baseResponse.status?.message?:"")
                            val intent=Intent(this,LoginActivity::class.java)
                            startActivity(intent)


                        }
                        (errorCode == 1) -> {
                            AndroidUtility.showToast(this, baseResponse.status?.message ?: "")
                        }
                    }


                }


                Status.LOADING -> {
                    showLoader()
                }
                Status.ERROR -> {
                    hideLoader()
                    AndroidUtility.showToast(this, getString(R.string.something_went_wrong))
                }
            }

        })
    }



    private fun changePasswordApi(){
        val password=et_password.text.toString().trim()
        val conpassword=et_confirmpassword.text.toString().trim()
        when {
            (!AndroidUtility.isNetworkAvailable(this)) -> {
                AndroidUtility.showToast(this, getString(R.string.please_check_internet))
                return
            }
            password == "" -> {
                AndroidUtility.showToast(this, "Password can't be blank")
                return

            }
            conpassword ==""->{
                AndroidUtility.showToast(this, "Confirm Password can't be blank")
                 return
            }
            password!=conpassword->{
                AndroidUtility.showToast(this, "Password didn't match")
                return

            }

        }

        val changePasswordRequest = ChangePasswordRequest().apply {
            this.NewPassword = password
            this.ConfirmPassword=conpassword
            this.CustomerId=customerid_value!!.toInt()
            Log.e("customer_id_value1",this.CustomerId.toString())
        }

        viewModel.requestchangepassword(this,changePasswordRequest)


    }

}