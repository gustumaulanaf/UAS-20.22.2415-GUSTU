package com.gustu.github.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BASE {
    private val URL = "https://api.github.com/"
    private lateinit var retrofit: Retrofit
    val api: API
        get() {
            retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .client(OkHttpClient())
                .build()
            return retrofit.create(API::class.java)
        }
}