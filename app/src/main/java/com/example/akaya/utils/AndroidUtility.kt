package com.example.akaya.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Toast

class AndroidUtility {

   companion object{
     fun showToast(context: Context,text:String){
         Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
     }

       fun isNetworkAvailable(context: Context): Boolean {
           val connectivity =
                   context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
           if (connectivity != null) {
               val info = connectivity.allNetworkInfo
               if (info != null) {
                   for (i in info!!.indices) {
                       Log.w("INTERNET:", i.toString())
                       if (info!![i].getState() === NetworkInfo.State.CONNECTED) {
                           Log.w("INTERNET:", "connected!")
                           return true
                       }
                   }
               }
           }
           return false
       }

       fun isValidEmail(target: String): Boolean {
           return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
       }


   }
}