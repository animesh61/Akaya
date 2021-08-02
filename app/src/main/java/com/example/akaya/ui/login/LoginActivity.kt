package com.example.akaya.ui.login

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
import com.example.akaya.ui.forgotpassword.ForgotPasswordActivity
import com.example.akaya.ui.home.Home01Activity
import com.example.akaya.ui.home.HomeActivity
import com.example.akaya.ui.model.SigninRequest
import com.example.akaya.ui.register.CreateAccountActivity
import com.example.akaya.utils.AndroidUtility
import com.example.akaya.utils.Status
import com.example.akaya.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var signInButton: CardView
    private lateinit var forgotPasswordButton: TextView
    private lateinit var signUpButton: LinearLayout
    private lateinit var viewModel: LoginviewModel
    lateinit var mCustomLoaderDialog: CustomLoaderDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signInButton = findViewById(R.id.login_SignInCard)
        forgotPasswordButton = findViewById(R.id.login_ForgotPassword)
        signUpButton = findViewById(R.id.login_SignUp)
        var login_Username=findViewById(R.id.login_Username)as EditText
        var login_Password=findViewById(R.id.login_Password)as EditText
        mCustomLoaderDialog = CustomLoaderDialog(this)

        setUpViewModel()
        setupObserver()


        signInButton.setOnClickListener {
//            val i = Intent(this, Home01Activity::class.java)
//            startActivity(i)
            signinApi()
        }

        forgotPasswordButton.setOnClickListener {
            val i = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(i)
        }

        signUpButton.setOnClickListener {
            val i = Intent(this, CreateAccountActivity::class.java)
            startActivity(i)
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
        val intent=Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }



    private fun setUpViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService)
            )
        ).get(LoginviewModel::class.java)
    }
    private fun setupObserver() {
        viewModel.signinData().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    hideLoader()
                    val baseResponse = it.data
                    val errorCode = baseResponse?.status?.error_code
                    when {
                        (errorCode == 0) -> {
                            AndroidUtility.showToast(this,"Login Successfully !!" )
                            movetoHome()

                        }
                        (errorCode == 1)->{
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



    private fun signinApi(){
        val email=login_Username.text.toString().trim()
        val password=login_Password.text.toString().trim()


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

            password == "" -> {
                AndroidUtility.showToast(this, "Password can't be blank")
                return
            }
        }

        val signupRequest = SigninRequest().apply {
            this.Email = email
            this.Password = password
        }

        viewModel.requestsignin(this,signupRequest)




    }

}