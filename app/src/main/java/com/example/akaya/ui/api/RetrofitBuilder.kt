package com.example.akaya.ui.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    private const val BASE_URL = "http://98.142.107.237:8080/api/"

    private fun getRetrofit(): Retrofit {
        val retrofit =  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = (HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        retrofit.client(client)
         return    retrofit.build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)

}