package com.example.akaya.ui.forgotpassword

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.akaya.R
import com.example.akaya.ui.api.ApiHelperImpl
import com.example.akaya.ui.api.RetrofitBuilder
import com.example.akaya.ui.dialog.CustomLoaderDialog
import com.example.akaya.ui.login.LoginviewModel
import com.example.akaya.ui.model.ForgotPasswordRequest
import com.example.akaya.ui.model.SigninRequest
import com.example.akaya.utils.AndroidUtility
import com.example.akaya.utils.Prefs
import com.example.akaya.utils.Status
import com.example.akaya.utils.ViewModelFactory

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var sendButton: CardView
    private lateinit var et_forgot_email:EditText
    private lateinit var viewModel: ForgotpasswordviewModel
    lateinit var mCustomLoaderDialog: CustomLoaderDialog
    lateinit var forgotPassword_BackButton:ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        et_forgot_email=findViewById(R.id.et_forgot_email)
        sendButton = findViewById(R.id.forgotPassword_sendCard)
        forgotPassword_BackButton=findViewById(R.id.forgotPassword_BackButton)
        mCustomLoaderDialog = CustomLoaderDialog(this)
        setUpViewModel()
        setupObserver()


        sendButton.setOnClickListener {
//            val i = Intent(this, CreatePasswordActivity::class.java)
//            startActivity(i)
            forgotpasswordApi()
        }
        forgotPassword_BackButton.setOnClickListener{
            onBackPressed()
        }
    }

    fun showLoader() {
        mCustomLoaderDialog.show()
    }
    private fun verifyotp(){
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
        ).get(ForgotpasswordviewModel::class.java)
    }

    private fun setupObserver() {
        viewModel.forgotpassword().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    hideLoader()
                    val baseResponse = it.data
                    val errorCode = baseResponse?.status?.error_code
                    val otp=baseResponse?.otp
                    val customerid=baseResponse?.customerId
                    when {
                        (errorCode == 0) -> {
                            AndroidUtility.showToast(this,"Otp Sent Successfully!!" )
                            val i=Intent(this,VerifyOtpActivity::class.java)
                            i.putExtra("otp",otp)
                            i.putExtra("customerid",customerid)
                            Prefs.with(this).write("customerid", customerid.toString())

                            startActivity(i)



                        }
                        (errorCode == 1)->{
                            AndroidUtility.showToast(this,baseResponse.status?.message?:"" )
                        }
                    }
                    Log.e("otp",otp.toString())
                    Log.e("customerid",customerid.toString())



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



    private fun forgotpasswordApi(){
        val email=et_forgot_email.text.toString().trim()
        when {
            (!AndroidUtility.isNetworkAvailable(this)) -> {
                AndroidUtility.showToast(this, getString(R.string.please_check_internet))
                return
            }
            email == "" -> {
                AndroidUtility.showToast(this, "Email can't be blank")
                return

            }
            (!AndroidUtility.isValidEmail(email)) -> {
                AndroidUtility.showToast(this, "Please enter a valid email.")
                return
            }
        }

        val forgotPasswordRequest = ForgotPasswordRequest().apply {
            this.EmailId = email
        }

        viewModel.requestforgotpassword(this,forgotPasswordRequest)


    }
}