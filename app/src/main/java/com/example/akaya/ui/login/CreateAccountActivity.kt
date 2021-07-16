package com.example.akaya.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.akaya.R

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var signInButton: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        signInButton = findViewById(R.id.createAccount_SignIn)

        signInButton.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }
    }
}