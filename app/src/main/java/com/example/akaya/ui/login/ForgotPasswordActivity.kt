package com.example.akaya.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.akaya.R

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var sendButton: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        sendButton = findViewById(R.id.forgotPassword_sendCard)

        sendButton.setOnClickListener {
            val i = Intent(this, CreatePasswordActivity::class.java)
            startActivity(i)
        }
    }
}