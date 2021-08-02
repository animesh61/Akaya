package com.example.akaya.ui.forgotpassword

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.akaya.R
import com.example.akaya.utils.AndroidUtility

class VerifyOtpActivity:AppCompatActivity() {
    private lateinit var et_otp:EditText
    private lateinit var et_otp1:EditText
    private lateinit var et_otp2:EditText
    private lateinit var et_otp3:EditText
    private lateinit var btn_verify:Button
    private lateinit var tv_otp_back:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)
        et_otp=findViewById(R.id.et_otp)
        et_otp1=findViewById(R.id.et_otp1)
        et_otp2=findViewById(R.id.et_otp2)
        et_otp3=findViewById(R.id.et_otp3)
        btn_verify=findViewById(R.id.btn_verify)
        tv_otp_back=findViewById(R.id.tv_otp_back)
        val otp=intent.getStringExtra("otp")
        val customerid=intent.getStringExtra("customerid")


        btn_verify.setOnClickListener{
            val otp_value=et_otp.text.toString().trim()
            val otp_value1=et_otp1.text.toString().trim()
            val otp_value2=et_otp2.text.toString().trim()
            val otp_value3=et_otp3.text.toString().trim()
            val otp_user=otp_value+otp_value1+otp_value2+otp_value3
            Log.e("otp1",otp.toString())
            Log.e("otp_user",otp_user.toString())

            if(otp.equals(otp_user)) {
                val i = Intent(this, ChangePasswordActivity::class.java)
                i.putExtra("customerid",customerid)
                startActivity(i)
            }
            else{
                AndroidUtility.showToast(this,"Otp didn't match")
            }
        }

        tv_otp_back.setOnClickListener{
            onBackPressed()
        }


    }
}