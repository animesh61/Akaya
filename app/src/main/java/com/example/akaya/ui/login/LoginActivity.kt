package com.example.akaya.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.akaya.R
import com.example.akaya.ui.home.Home01Activity

class LoginActivity : AppCompatActivity() {

    private lateinit var signInButton: CardView
    private lateinit var forgotPasswordButton: TextView
    private lateinit var signUpButton: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signInButton = findViewById(R.id.login_SignInCard)
        forgotPasswordButton = findViewById(R.id.login_ForgotPassword)
        signUpButton = findViewById(R.id.login_SignUp)

        signInButton.setOnClickListener {
            val i = Intent(this, Home01Activity::class.java)
            startActivity(i)
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
}