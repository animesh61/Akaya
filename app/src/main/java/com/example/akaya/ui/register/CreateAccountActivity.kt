package com.example.akaya.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.akaya.R
import com.example.akaya.ui.api.ApiHelperImpl
import com.example.akaya.ui.api.RetrofitBuilder
import com.example.akaya.ui.dialog.CustomLoaderDialog
import com.example.akaya.ui.home.Home01Activity
import com.example.akaya.ui.login.LoginActivity
import com.example.akaya.ui.login.LoginviewModel
import com.example.akaya.ui.model.SignUpRequest
import com.example.akaya.utils.AndroidUtility
import com.example.akaya.utils.Status
import com.example.akaya.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var signInButton: LinearLayout
    private lateinit var et_fname:EditText
    private lateinit var et_lastname:EditText
    private lateinit var et_email:EditText
    private lateinit var et_mobile:EditText
    private lateinit var et_country:EditText
    private lateinit var et_password:EditText
    private lateinit var et_confirmpassword:EditText
    private lateinit var createAccount_SignUp:CardView
    private lateinit var viewModel: SignUpViewModel
    lateinit var mCustomLoaderDialog: CustomLoaderDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        et_fname=findViewById(R.id.et_fname)as EditText
        et_lastname=findViewById(R.id.et_lastname)as EditText
        et_email=findViewById(R.id.et_email)as EditText
        et_mobile=findViewById(R.id.et_mobile)as EditText
        et_country=findViewById(R.id.et_country)as EditText
        et_password=findViewById(R.id.et_password)as EditText
        et_confirmpassword=findViewById(R.id.et_confirmpassword)as EditText
        createAccount_SignUp=findViewById(R.id.createAccount_SignUp)as CardView
        signInButton = findViewById(R.id.login_SignUp)
        mCustomLoaderDialog = CustomLoaderDialog(this)
        setUpViewModel()
        setupObserver()


        signInButton.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }
        createAccount_SignUp.setOnClickListener{
            signupApi()
        }


    }

    fun showLoader() {
        mCustomLoaderDialog.show()
    }

    fun hideLoader() {
        if (mCustomLoaderDialog.isShowing)
            mCustomLoaderDialog.cancel()
    }
    private fun movetoHome(){
        val intent=Intent(this, Home01Activity::class.java)
        startActivity(intent)
    }



    private fun setUpViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService)
            )
        ).get(SignUpViewModel::class.java)
    }
    private fun setupObserver() {
        viewModel.signup().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    hideLoader()
                    val baseResponse = it.data
                    val errorCode = baseResponse?.status?.error_code
                    when {
                        (errorCode == 0) -> {
                            AndroidUtility.showToast(this,"Register Successfully !!" )
                            movetoHome()

                        }
                        (errorCode == 2)->{
                            AndroidUtility.showToast(this,baseResponse.status?.message?:"" )
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


    private fun signupApi(){
        val fname=et_fname.text.toString().trim()
        val lname=et_lastname.text.toString().trim()
        val email=et_email.text.toString().trim()
        val mobile=et_mobile.text.toString().trim()
        val country=et_country.text.toString().trim()
         val password=et_password.text.toString().trim()
        val confirmpasswd=et_confirmpassword.text.toString().trim()


        when {
            (!AndroidUtility.isNetworkAvailable(this)) -> {
                AndroidUtility.showToast(this, getString(R.string.please_check_internet))
                return
            }
            fname=="" -> {
                AndroidUtility.showToast(this, "First Name can't be blank")
                return
            }
            lname=="" -> {
                AndroidUtility.showToast(this, "Last Name can't be blank")
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
            mobile == "" -> {
                AndroidUtility.showToast(this, "Mobile no can't be blank")
                return
            }
            country == "" -> {
                AndroidUtility.showToast(this, "Country  can't be blank")
                return
            }
            password ==""->{
                AndroidUtility.showToast(this, "Password can't be blank")
             return
            }

            confirmpasswd == "" -> {
                AndroidUtility.showToast(this, "ConfirmPassword can't be blank")
                return
            }
            password!=confirmpasswd->{
                AndroidUtility.showToast(this, "Password didn't match")
                return

            }
        }

        val signupRequest = SignUpRequest().apply {
            this.firstName = fname
            this.lastName = lastName
            this.email=email
            this.phone=mobile
            this.country=country
            this.password=password
        }

        viewModel.requestsignup(this,signupRequest)




    }

}